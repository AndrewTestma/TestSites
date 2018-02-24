package com.pojo;



import com.fasterxml.jackson.annotation.JsonFormat;
import freemarker.template.utility.DateUtil;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

public class UITestCase {
    private Integer tsuitestcaseid;

    private String tsname;

    private Integer tsproductid;

    private String tsmodulename;

    private Integer tsgrade;

    private Integer tsresultid;

    private Integer tscommon;

    @DateTimeFormat(pattern= "yyyy-MM-dd HH:mm:ss")
    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss",timezone = "GMT+8")
    private Date tscreatetime;

    private String tscreator;

    public Integer getTsuitestcaseid() {
        return tsuitestcaseid;
    }

    public void setTsuitestcaseid(Integer tsuitestcaseid) {
        this.tsuitestcaseid = tsuitestcaseid;
    }

    public String getTsname() {
        return tsname;
    }

    public void setTsname(String tsname) {
        this.tsname = tsname;
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

    public Integer getTsgrade() {
        return tsgrade;
    }

    public void setTsgrade(Integer tsgrade) {
        this.tsgrade = tsgrade;
    }

    public Integer getTsresultid() {
        return tsresultid;
    }

    public void setTsresultid(Integer tsresultid) {
        this.tsresultid = tsresultid;
    }

    public Integer getTscommon() {
        return tscommon;
    }

    public void setTscommon(Integer tscommon) {
        this.tscommon = tscommon;
    }

    public Date getTscreatetime() {
        return tscreatetime;
    }

    public void setTscreatetime(Date tscreatetime) {
        this.tscreatetime = tscreatetime;
    }

    public String getTscreator() {
        return tscreator;
    }

    public void setTscreator(String tscreator) {
        this.tscreator = tscreator;
    }
}