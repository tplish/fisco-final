package org.fisco.bcos.models;

import org.fisco.bcos.web3j.tuples.generated.Tuple5;

import java.math.BigInteger;

public class Receipt {
    private BigInteger id;
    private String fromAddr;
    private String fromName;
    private String toAddr;
    private String toName;
    private BigInteger amount;
    private Boolean isCheck;
    private Boolean isUsed;

    public Receipt(Tuple5<String, String, BigInteger, Boolean, Boolean> tuple5) {
        fromAddr = tuple5.getValue1();
        toAddr = tuple5.getValue2();
        amount = tuple5.getValue3();
        isCheck = tuple5.getValue4();
        isUsed = tuple5.getValue5();
    }

    public void setId(BigInteger id) {
        this.id = id;
    }

    public void setFromName(String fromName) {
        this.fromName = fromName;
    }

    public void setToName(String toName) {
        this.toName = toName;
    }

    public BigInteger getId() {
        return id;
    }

    public String getFromName() {
        return fromName;
    }

    public String getToName() {
        return toName;
    }

    public String getFromAddr() {
        return fromAddr;
    }

    public String getToAddr() {
        return toAddr;
    }

    public BigInteger getAmount() {
        return amount;
    }

    public Boolean getCheck() {
        return isCheck;
    }

    public Boolean getUsed() {
        return isUsed;
    }

    @Override
    public String toString() {
        return "Receipt{" +
                "id=" + id +
                ", fromAddr='" + fromAddr + '\'' +
                ", fromName='" + fromName + '\'' +
                ", toAddr='" + toAddr + '\'' +
                ", toName='" + toName + '\'' +
                ", amount=" + amount +
                ", isCheck=" + isCheck +
                ", isUsed=" + isUsed +
                '}';
    }
}
