package com.pojo;

import com.fasterxml.jackson.annotation.JsonFormat;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

public class Result {
    private Integer tsresultid;

    private Integer tstotaltime;

    private Integer tstotalsteps;

    private Integer tsrunsteps;

    private Integer tsresult;

    private Integer tscount;

    private String tsexecutive;

    @DateTimeFormat(pattern= "yyyy-MM-dd HH:mm:ss")
    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss",timezone = "GMT+8")
    private Date tsexecutiontime;

    private Integer tsbusinessid;

    private Integer tsproductid;

    public Integer getTsresultid() {
        return tsresultid;
    }

    public void setTsresultid(Integer tsresultid) {
        this.tsresultid = tsresultid;
    }

    public Integer getTstotaltime() {
        return tstotaltime;
    }

    public void setTstotaltime(Integer tstotaltime) {
        this.tstotaltime = tstotaltime;
    }

    public Integer getTstotalsteps() {
        return tstotalsteps;
    }

    public void setTstotalsteps(Integer tstotalsteps) {
        this.tstotalsteps = tstotalsteps;
    }

    public Integer getTsrunsteps() {
        return tsrunsteps;
    }

    public void setTsrunsteps(Integer tsrunsteps) {
        this.tsrunsteps = tsrunsteps;
    }

    public Integer getTsresult() {
        return tsresult;
    }

    public void setTsresult(Integer tsresult) {
        this.tsresult = tsresult;
    }

    public Integer getTscount() {
        return tscount;
    }

    public void setTscount(Integer tscount) {
        this.tscount = tscount;
    }

    public String getTsexecutive() {
        return tsexecutive;
    }

    public void setTsexecutive(String tsexecutive) {
        this.tsexecutive = tsexecutive;
    }

    public Date getTsexecutiontime() {
        return tsexecutiontime;
    }

    public void setTsexecutiontime(Date tsexecutiontime) {
        this.tsexecutiontime = tsexecutiontime;
    }

    public Integer getTsbusinessid() {
        return tsbusinessid;
    }

    public void setTsbusinessid(Integer tsbusinessid) {
        this.tsbusinessid = tsbusinessid;
    }

    public Integer getTsproductid() {
        return tsproductid;
    }

    public void setTsproductid(Integer tsproductid) {
        this.tsproductid = tsproductid;
    }
}