package com.java110.po.account;

import java.io.Serializable;
import java.util.Date;

public class AccountPo implements Serializable {

    private String amount;
private String acctType;
private String objId;
private String acctId;
private String acctName;
private String objType;
public String getAmount() {
        return amount;
    }
public void setAmount(String amount) {
        this.amount = amount;
    }
public String getAcctType() {
        return acctType;
    }
public void setAcctType(String acctType) {
        this.acctType = acctType;
    }
public String getObjId() {
        return objId;
    }
public void setObjId(String objId) {
        this.objId = objId;
    }
public String getAcctId() {
        return acctId;
    }
public void setAcctId(String acctId) {
        this.acctId = acctId;
    }
public String getAcctName() {
        return acctName;
    }
public void setAcctName(String acctName) {
        this.acctName = acctName;
    }
public String getObjType() {
        return objType;
    }
public void setObjType(String objType) {
        this.objType = objType;
    }



}
