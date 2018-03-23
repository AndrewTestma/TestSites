package com.pojo;

public class User {
    private Integer tsuserid;

    private String tsname;

    private String tspassword;

    private String tsoperatingenvid;

    private Integer tsproductid;

    public Integer getTsuserid() {
        return tsuserid;
    }

    public void setTsuserid(Integer tsuserid) {
        this.tsuserid = tsuserid;
    }

    public String getTsname() {
        return tsname;
    }

    public void setTsname(String tsname) {
        this.tsname = tsname;
    }

    public String getTspassword() {
        return tspassword;
    }

    public void setTspassword(String tspassword) {
        this.tspassword = tspassword;
    }

    public String getTsoperatingenvid() {
        return tsoperatingenvid;
    }

    public void setTsoperatingenvid(String tsoperatingenvid) {
        this.tsoperatingenvid = tsoperatingenvid;
    }

    public Integer getTsproductid() {
        return tsproductid;
    }

    public void setTsproductid(Integer tsproductid) {
        this.tsproductid = tsproductid;
    }
}