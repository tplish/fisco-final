//package org.fisco.bcos.controllers;
//
//import com.alibaba.fastjson.JSON;
//import org.fisco.bcos.constants.GasConstants;
//import org.fisco.bcos.models.Response;
//import org.fisco.bcos.models.User;
//import org.fisco.bcos.temp.FISCO;
//import org.fisco.bcos.web3j.crypto.Credentials;
//import org.fisco.bcos.web3j.crypto.gm.GenCredential;
//import org.fisco.bcos.web3j.protocol.Web3j;
//import org.fisco.bcos.web3j.protocol.core.methods.response.TransactionReceipt;
//import org.fisco.bcos.web3j.tuples.generated.Tuple5;
//import org.fisco.bcos.web3j.tuples.generated.Tuple7;
//import org.fisco.bcos.web3j.tx.gas.StaticGasProvider;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.beans.factory.annotation.Value;
//import org.springframework.web.bind.annotation.*;
//
//import java.math.BigInteger;
//import java.util.HashMap;
//import java.util.List;
//import java.util.Map;
//
//@RestController
//@RequestMapping(value = "/api/v1")
//public class Routerx {
//    @Autowired Web3j web3j;
//    @Autowired Credentials credentials;
//
//    @Value("${contract.addr:#{null}}")
//    private String contractAddr;
//
//    private FISCO getFISCO(Credentials credentials){
//        return FISCO.load(contractAddr, web3j, credentials, new StaticGasProvider(GasConstants.GAS_PRICE, GasConstants.GAS_LIMIT));
//    }
//
//    private Map<String, String> __addUser(String username, String userinfo, String usergroup) {
//        credentials = GenCredential.create();
//        Map<String, String> map = new HashMap<>();
//        map.put("username", username);
//        map.put("userinfo", userinfo);
//        map.put("usergroup", usergroup);
//
//        map.put("auth", credentials.getEcKeyPair().getPrivateKey().toString(16));
//        map.put("addr", credentials.getAddress());
//        return map;
//    }
//
//    private User __getUser(String auth, String addr) throws Exception{
//        credentials = GenCredential.create(auth);
//        FISCO fisco = getFISCO(credentials);
//        Tuple7<String, String, String, String, Boolean, List<BigInteger>, List<BigInteger>> tuple7 = fisco.getUser(addr).send();
//        return new User(tuple7);
//    }
//
//    private Iou __getIou(String auth, BigInteger id) throws  Exception{
//        credentials = GenCredential.create(auth);
//        FISCO fisco = getFISCO(credentials);
//        Tuple5<BigInteger, String, String, BigInteger, Boolean> tuple5 = fisco.getIou(id).send();
//        return new Iou(tuple5);
//    }
//
//    @RequestMapping(value = "/login", method = RequestMethod.POST)
//    public String login(@RequestBody Map<String, String> body) throws  Exception {
//        String auth = body.get("auth");
//        String addr = GenCredential.create(auth).getAddress();
//
//        Response response = new Response(200, null, __getUser(auth, addr));
//        return JSON.toJSONString(response, true);
//    }
//
//    // create an admin
//    @RequestMapping(value = "/admin", method = RequestMethod.POST)
//    public String admin() throws Exception {
//        String username = "admin";
//        String userinfo = "admin";
//        String usergroup = "admin";
//
//        Response response;
//        if (contractAddr == null){
//            Map<String, String> map = __addUser(username, userinfo, usergroup);
//            credentials = GenCredential.create(map.get("auth"));
//            FISCO fisco = FISCO.deploy(web3j, credentials, new StaticGasProvider(GasConstants.GAS_PRICE, GasConstants.GAS_LIMIT)).send();
//            contractAddr = fisco.getContractAddress();
//            fisco.addUser(map.get("addr"), username, userinfo, usergroup).send();
//
//            map.put("contractAddr", contractAddr);
//            response = new Response(200, null, map);
//        } else {
//            response = new Response(400, "admin existed", contractAddr);
//        }
//        return JSON.toJSONString(response, true);
//    }
//
//    // create a user
//    @RequestMapping(value = "/users", method = RequestMethod.POST)
//    public String addUser(@RequestBody Map<String, String> body) throws Exception{
//        String auth = body.get("auth");
//        String username = body.get("username");
//        String userinfo = body.get("userinfo");
//        String usergroup = body.get("usergroup");
//
//        Response response;
//        if (contractAddr == null){
//            response = new Response(400, "contract not exist. please contact admin.", null);
//        } else {
//            Credentials admin = GenCredential.create(auth);
//            FISCO fisco = getFISCO(admin);
//            if (!admin.getAddress().equals(fisco.owner().send())){
//                response = new Response(400, "not support. please contact admin.", null);
//            } else {
//                Map <String, String> map = __addUser(username, userinfo, usergroup);
//                fisco.addUser(map.get("addr"), username, userinfo, usergroup).send();
//                response = new Response(200, null, map);
//            }
//        }
//        return JSON.toJSONString(response, true);
//    }
//
//    // delete a user
//    @RequestMapping(value = "/users", method = RequestMethod.DELETE)
//    public String delUser(@RequestBody Map<String, String> body) throws Exception{
//        String auth = body.get("auth");
//        String addr = body.get("addr");
//
//        Response response;
//        if (contractAddr == null){
//            response = new Response(400, "contract not exist", null);
//        } else {
//            Credentials admin = GenCredential.create(auth);
//            FISCO fisco = getFISCO(admin);
//
//            if (!admin.getAddress().equals(fisco.owner().send())){
//                response = new Response(400, "not support. please contact the admin", null);
//            } else {
//                fisco.delUser(addr);
//                response = new Response(400, "user not exist", __getUser(auth, addr));
//            }
//        }
//        return JSON.toJSONString(response, true);
//    }
//
//    // get a user
//    @RequestMapping(value = "/users", method = RequestMethod.GET)
//    public String getUser(@RequestBody Map<String, String> body) throws Exception{
//        String auth = body.get("auth");
//        String addr = body.get("addr");
//
//        Response response;
//        if (contractAddr == null){
//            response = new Response(400, "contract not exist", null);
//        } else {
//            response = new Response(200 ,null, __getUser(auth, addr));
//        }
//        return JSON.toJSONString(response, true);
//    }
//
//    // get an iou
//    @RequestMapping(value = "/ious", method = RequestMethod.GET)
//    public String getIou(@RequestBody Map<String, String> body) throws Exception{
//        String auth = body.get("auth");
//        BigInteger id = BigInteger.valueOf(Long.parseLong(body.get("id")));
//
//        Response response;
//        if (contractAddr == null){
//            response = new Response(400, "contract not exist", null);
//        } else {
//            response = new Response(200, null, __getIou(auth, id));
//        }
//        return JSON.toJSONString(response, true);
//    }
//
//    // generate || split || request || pay an iou
//    @RequestMapping(value = "/ious", method = RequestMethod.POST)
//    public String addIou(@RequestBody Map<String, String> body) throws Exception{
//        String from = body.get("from");
//        String to = body.get("to");
//        String _amount = body.get("amount"); if (_amount == null) _amount = "0";
//        String _id = body.get("id"); if (_id == null) _id = "0";
//
//        BigInteger amount = BigInteger.valueOf(Long.parseLong(_amount));
//        BigInteger id = BigInteger.valueOf(Long.parseLong(_id));
//        String action = body.get("action");
//
//        Response response = null;
//        if (contractAddr == null){
//            response = new Response(400, "contract not exist", null);
//        } else {
//            if ("gen".equals(action)){
//                credentials = GenCredential.create(from);
//                FISCO fisco = getFISCO(credentials);
//
//                TransactionReceipt receipt = fisco.generateIou(credentials.getAddress(), to, amount).send();
//                id = fisco.getGenerateIouOutput(receipt).getValue1();
//                response = new Response(200, null, __getIou(from, id));
//            } else if ("split".equals(action)){
//                credentials = GenCredential.create(from);
//                FISCO fisco = getFISCO(credentials);
//
//                TransactionReceipt receipt = fisco.splitIou(credentials.getAddress(), to, amount, id).send();
//                BigInteger id1 = fisco.getSplitIouOutput(receipt).getValue2();
//                BigInteger id2 = fisco.getSplitIouOutput(receipt).getValue3();
//
//                Iou[] ious = {__getIou(from, id), __getIou(from, id1), __getIou(from, id2)};
//                response = new Response(200, null, ious);
//            } else if ("req".equals(action)){
//                credentials = GenCredential.create(from);
//                FISCO fisco = getFISCO(credentials);
//
//                fisco.reqIou(credentials.getAddress(), to, amount, id).send();
//                response = new Response(200, null, __getIou(from, id));
//            } else if ("pay".equals(action)){
//                credentials = GenCredential.create(to);
//                FISCO fisco = getFISCO(credentials);
//
//                fisco.payIou(from, credentials.getAddress(), id).send();
//                response = new Response(200, null, __getIou(from, id));
//            }
//        }
//        return JSON.toJSONString(response, true);
//    }
//}
