package org.fisco.bcos.controllers;

import com.alibaba.fastjson.JSON;
import org.fisco.bcos.constants.GasConstants;
import org.fisco.bcos.models.Receipt;
import org.fisco.bcos.models.Response;
import org.fisco.bcos.models.User;
import org.fisco.bcos.temp.FISCO;
import org.fisco.bcos.web3j.crypto.Credentials;
import org.fisco.bcos.web3j.crypto.gm.GenCredential;
import org.fisco.bcos.web3j.protocol.Web3j;
import org.fisco.bcos.web3j.protocol.core.methods.response.TransactionReceipt;
import org.fisco.bcos.web3j.tuples.generated.Tuple3;
import org.fisco.bcos.web3j.tx.gas.StaticGasProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping(value = "/api/v1")
public class Router {
    @Autowired
    Web3j web3j;
    @Autowired
    Credentials credentials;
    Credentials other = GenCredential.create();

    @Value("${contract.addr:#{null}}")
    private String contractAddr;

    private FISCO getFISCO(Credentials credentials){
        return FISCO.load(contractAddr, web3j, credentials, new StaticGasProvider(GasConstants.GAS_PRICE, GasConstants.GAS_LIMIT));
    }

    // ========== Private Methods ==========
    private Map<String, String> __addUser(String username, String usergroup) {
        credentials = GenCredential.create();
        Map<String, String> map = new HashMap<>();
        map.put("username", username);
        map.put("usergroup", usergroup);
        map.put("useraddr", credentials.getAddress());
        map.put("auth", credentials.getEcKeyPair().getPrivateKey().toString(16));
        return map;
    }

    private List<Object> __getReceipts(List<BigInteger> list) throws Exception{
        FISCO fisco = getFISCO(other);
        List<Object> receipts = new ArrayList<>();
        for (BigInteger id : list) {
            Receipt receipt = new Receipt(fisco.getReceipt(id).send());
            receipt.setId(id);
            receipt.setFromName(fisco.getUser(receipt.getFromAddr()).send().getValue1());
            receipt.setToName(fisco.getUser(receipt.getToAddr()).send().getValue1());
            receipts.add(receipt);
        }
        return receipts;
    }

    // ========== Admin Methods ==========
    @RequestMapping(value = "/admin", method = RequestMethod.POST)
    public String admin() throws Exception {
        String username = "admin";
        String usergroup = "admin";
        Response response;
        if (contractAddr == null) {
            Map<String, String> map = __addUser(username, usergroup);
            Credentials admin = GenCredential.create(map.get("auth"));
            FISCO fisco = FISCO.deploy(web3j, admin, new StaticGasProvider(GasConstants.GAS_PRICE, GasConstants.GAS_LIMIT)).send();
            contractAddr = fisco.getContractAddress();
            fisco.addUser(map.get("useraddr"), map.get("username"), map.get("usergroup")).send();
            map.put("contractAddr", contractAddr);
            response = new Response(200, null, map);
        } else {
            response = new Response(400, "admin existed", contractAddr);
        }
        return JSON.toJSONString(response, true);
    }

    @RequestMapping(value = "/users", method = RequestMethod.POST)
    public String addUser(@RequestBody Map<String, String> body) throws Exception {
        String auth = body.get("auth");
        String username = body.get("username");
        String usergroup = body.get("usergroup");
        Response response;
        if (contractAddr == null) {
            response = new Response(400, "contract not exist. please contact the admin.", null);
        } else {
            Credentials admin = GenCredential.create(auth);
            FISCO fisco = getFISCO(admin);
            if (!admin.getAddress().equals(fisco.owner().send())) {
                response = new Response(400, "not support. please contact the admin.", null);
            } else {
                Map<String, String> map = __addUser(username, usergroup);
                TransactionReceipt receipt = fisco.addUser(map.get("useraddr"), map.get("username"), map.get("usergroup")).send();
                fisco.getAddUserOutput(receipt);
                response = new Response(200, null, map);
            }
        }
        return JSON.toJSONString(response, true);
    }

    @RequestMapping(value = "/users", method = RequestMethod.DELETE)
    public String delUser(@RequestBody Map<String, String> body) throws Exception {
        String auth = body.get("auth");
        String useraddr = body.get("useraddr");
        Response response;
        if (contractAddr == null) {
            response = new Response(400, "contract not exist. please contact the admin.", null);
        } else {
            Credentials admin = GenCredential.create(auth);
            FISCO fisco = getFISCO(admin);
            if (!admin.getAddress().equals(fisco.owner().send())) {
                response = new Response(400, "not support. please contact the admin.", null);
            } else {
                fisco.delUser(useraddr).send();
                response = new Response(200, null, useraddr);
            }
        }
        return JSON.toJSONString(response, true);
    }

    // ========== Login Method ==========
    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public String login(@RequestBody Map<String, String> body) throws Exception {
        String username = body.get("username");
        String password = body.get("password");
        System.out.println("username" + username);
        System.out.println("password" + password);
        Response response;
        if (contractAddr == null) {
            response = new Response(400, "contract not exist. please contact the admin.", null);
        } else {
            FISCO fisco = getFISCO(other);
            credentials = GenCredential.create(password);

            String addr = credentials.getAddress();
            System.out.println(addr);

            Tuple3<String, String, Boolean> tuple = fisco.getUser(addr).send();
            System.out.println(tuple);

            User user = new User(tuple);
            System.out.println(user);

            if (user.getUsername().equals(username)){
                Map<String, String> obj = new HashMap<>();
                obj.put("auth", password);
                obj.put("username", user.getUsername());
                obj.put("usergroup",user.getUsergroup());
                obj.put("useraddr",credentials.getAddress());
                response = new Response(200, null, obj);
            } else {
                response = new Response(400, "wrong username of password", null);
            }
        }
        return JSON.toJSONString(response, true);
    }

    // ========== Get Methods ==========
    @RequestMapping(value = "/users", method = RequestMethod.GET)
    public String getUsers() throws Exception {
        Response response;
        if (contractAddr == null) {
            response = new Response(400, "contract not exist. please contact the admin.", null);
        } else {
            FISCO fisco = getFISCO(other);
            List<String> list = fisco.getUsers().send();
            List<Object> users = new ArrayList<>();
            for (String useraddr : list) {
                User user = new User(fisco.getUser(useraddr).send());
                if (user.getUsed() && !user.getUsergroup().equals("admin")) {
                    user.setUseraddr(useraddr);
                    users.add(user);
                }
            }
            response = new Response(200, null, users);
        }
        return JSON.toJSONString(response, true);
    }

    @RequestMapping(value = "/receiptOuts", method = RequestMethod.GET)
    public String getReceiptOuts(@RequestParam String useraddr) throws Exception {
        Response response;
        if (contractAddr == null) {
            response = new Response(400, "contract not exist. please contact the admin.", null);
        } else {
            FISCO fisco = getFISCO(other);
            List<BigInteger> list = fisco.getReceiptOuts(useraddr).send();
            response = new Response(200, null, __getReceipts(list));
        }
        return JSON.toJSONString(response, true);
    }

    @RequestMapping(value = "/receiptIns", method = RequestMethod.GET)
    public String getReceiptIns(@RequestParam String useraddr) throws Exception {
        Response response;
        if (contractAddr == null) {
            response = new Response(400, "contract not exist. please contact the admin.", null);
        } else {
            FISCO fisco = getFISCO(other);
            List<BigInteger> list = fisco.getReceiptIns(useraddr).send();
            response = new Response(200, null, __getReceipts(list));
        }
        return JSON.toJSONString(response, true);
    }

    // ========== Post Method ==========
    @RequestMapping(value = "/receipts", method = RequestMethod.POST)
    public String addReceipt(@RequestBody Map<String, String> body) throws Exception{
        String fromAddr = body.get("fromAddr");
        String toAddr = body.get("toAddr");
        String amount = body.get("amount");
        String id = body.get("id");
        String action = body.get("action");
        Response response;
        if (contractAddr == null) {
            response = new Response(400, "contract not exist. please contact the admin.", null);
        } else {
            response = new Response(200, "ok", action);
            if ("gen".equals(action)){
                credentials = GenCredential.create(fromAddr);
                FISCO fisco = getFISCO(credentials);
                TransactionReceipt receipt = fisco.genReceipt(toAddr, BigInteger.valueOf(Long.parseLong(amount))).send();
            } else if ("split".equals(action)){
                credentials = GenCredential.create(fromAddr);
                FISCO fisco = getFISCO(credentials);
                TransactionReceipt receipt = fisco.splitReceipt(toAddr, BigInteger.valueOf(Long.parseLong(amount)), BigInteger.valueOf(Long.parseLong(id))).send();
            } else if ("req".equals(action)){
                credentials = GenCredential.create(fromAddr);
                FISCO fisco = getFISCO(credentials);
                TransactionReceipt receipt = fisco.reqReceipt(toAddr, BigInteger.valueOf(Long.parseLong(amount)), BigInteger.valueOf(Long.parseLong(id))).send();
            } else if ("pay".equals(action)){
                credentials = GenCredential.create(toAddr);
                FISCO fisco = getFISCO(credentials);
                TransactionReceipt receipt = fisco.payReceipt(fromAddr, BigInteger.valueOf(Long.parseLong(id))).send();
            } else {
                response = new Response(400, "wrong action", null);
            }
        }
        return JSON.toJSONString(response, true);
    }
}
