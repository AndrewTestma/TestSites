package com.pojo;

import java.util.Date;

public class UITestCase {
    private Integer tsuitestcaseid;

    private String tsnum;

    private Integer tsproductid;

    private String tsmodulename;

    private String tsautostepsname;

    private String tsfrontcase;

    private String tsexpected;

    private String tsverificationid;

    private String tsverificationcontent;

    private Byte tsdebug;

    private Integer tsgrade;

    private Date tscreatetime;

    private Byte tsregress;

    private Byte tssmoke;

    private String tscreator;

    public Integer getTsuitestcaseid() {
        return tsuitestcaseid;
    }

    public void setTsuitestcaseid(Integer tsuitestcaseid) {
        this.tsuitestcaseid = tsuitestcaseid;
    }

    public String getTsnum() {
        return tsnum;
    }

    public void setTsnum(String tsnum) {
        this.tsnum = tsnum;
    }

    public Integer getTsproductid() {
        return tsproductid;
    }

    public void setTsproductid(Integer tsproductid) {
        this.tsproductid = tsproductid;
    }

    public String getTsmodulename() {
        return tsmodulename;
    }

    public void setTsmodulename(String tsmodulename) {
        this.tsmodulename = tsmodulename;
    }

    public String getTsautostepsname() {
        return tsautostepsname;
    }

    public void setTsautostepsname(String tsautostepsname) {
        this.tsautostepsname = tsautostepsname;
    }

    public String getTsfrontcase() {
        return tsfrontcase;
    }

    public void setTsfrontcase(String tsfrontcase) {
        this.tsfrontcase = tsfrontcase;
    }

    public String getTsexpected() {
        return tsexpected;
    }

    public void setTsexpected(String tsexpected) {
        this.tsexpected = tsexpected;
    }

    public String getTsverificationid() {
        return tsverificationid;
    }

    public void setTsverificationid(String tsverificationid) {
        this.tsverificationid = tsverificationid;
    }

    public String getTsverificationcontent() {
        return tsverificationcontent;
    }

    public void setTsverificationcontent(String tsverificationcontent) {
        this.tsverificationcontent = tsverificationcontent;
    }

    public Byte getTsdebug() {
        return tsdebug;
    }

    public void setTsdebug(Byte tsdebug) {
        this.tsdebug = tsdebug;
    }

    public Integer getTsgrade() {
        return tsgrade;
    }

    public void setTsgrade(Integer tsgrade) {
        this.tsgrade = tsgrade;
    }

    public Date getTscreatetime() {
        return tscreatetime;
    }

    public void setTscreatetime(Date tscreatetime) {
        this.tscreatetime = tscreatetime;
    }

    public Byte getTsregress() {
        return tsregress;
    }

    public void setTsregress(Byte tsregress) {
        this.tsregress = tsregress;
    }

    public Byte getTssmoke() {
        return tssmoke;
    }

    public void setTssmoke(Byte tssmoke) {
        this.tssmoke = tssmoke;
    }

    public String getTscreator() {
        return tscreator;
    }

    public void setTscreator(String tscreator) {
        this.tscreator = tscreator;
    }

    @Override
    public String toString() {
        return "UITestCase{" +
                "tsuitestcaseid=" + tsuitestcaseid +
                ", tsnum='" + tsnum + '\'' +
                ", tsproductid=" + tsproductid +
                ", tsmodulename='" + tsmodulename + '\'' +
                ", tsautostepsname='" + tsautostepsname + '\'' +
                ", tsfrontcase='" + tsfrontcase + '\'' +
                ", tsexpected='" + tsexpected + '\'' +
                ", tsverificationid='" + tsverificationid + '\'' +
                ", tsverificationcontent='" + tsverificationcontent + '\'' +
                ", tsdebug=" + tsdebug +
                ", tsgrade=" + tsgrade +
                ", tscreatetime=" + tscreatetime +
                ", tsregress=" + tsregress +
                ", tssmoke=" + tssmoke +
                ", tscreator='" + tscreator + '\'' +
                '}';
    }
}