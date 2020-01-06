package org.fisco.bcos.models;

import org.fisco.bcos.web3j.tuples.generated.Tuple3;

import java.math.BigInteger;
import java.util.List;

public class User {
    private String useraddr;
    private String username;
    private String usergroup;
    private Boolean isUsed;

    public User(){

    }

    public User(Tuple3<String, String, Boolean> tuple3) {
        username = tuple3.getValue1();
        usergroup = tuple3.getValue2();
        isUsed = tuple3.getValue3();
    }

    public String getUseraddr() {
        return useraddr;
    }

    public String getUsername() {
        return username;
    }

    public String getUsergroup() {
        return usergroup;
    }

    public Boolean getUsed() {
        return isUsed;
    }

    public void setUseraddr(String useraddr) {
        this.useraddr = useraddr;
    }
}
