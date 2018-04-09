package com.pojo;

import com.fasterxml.jackson.annotation.JsonFormat;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

public class Autosteps {
    private Integer tsautostepsid;

    private String tsautostepsname;

    private Integer tsproductid;

    private String tsmodulename;

    private String tsselecttype;

    private String tsselectcontent;

    private String tsactiontype;

    private String tsactioncontent;

    private String tsframepath;

    private Integer tswait;

    private String tsverificationtype;

    private String tsverficationframe;

    private String tsverificationcontent;

    private Integer tscommon;

    private String tsremarks;

    @DateTimeFormat(pattern= "yyyy-MM-dd HH:mm:ss")
    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss",timezone = "GMT+8")
    private Date tscreatetime;

    private String tscreator;

    private Integer tssuccess;

    public Integer getTsautostepsid() {
        return tsautostepsid;
    }

    public void setTsautostepsid(Integer tsautostepsid) {
        this.tsautostepsid = tsautostepsid;
    }

    public String getTsautostepsname() {
        return tsautostepsname;
    }

    public void setTsautostepsname(String tsautostepsname) {
        this.tsautostepsname = tsautostepsname;
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

    public String getTsselecttype() {
        return tsselecttype;
    }

    public void setTsselecttype(String tsselecttype) {
        this.tsselecttype = tsselecttype;
    }

    public String getTsselectcontent() {
        return tsselectcontent;
    }

    public void setTsselectcontent(String tsselectcontent) {
        this.tsselectcontent = tsselectcontent;
    }

    public String getTsactiontype() {
        return tsactiontype;
    }

    public void setTsactiontype(String tsactiontype) {
        this.tsactiontype = tsactiontype;
    }

    public String getTsactioncontent() {
        return tsactioncontent;
    }

    public void setTsactioncontent(String tsactioncontent) {
        this.tsactioncontent = tsactioncontent;
    }

    public String getTsframepath() {
        return tsframepath;
    }

    public void setTsframepath(String tsframepath) {
        this.tsframepath = tsframepath;
    }

    public Integer getTswait() {
        return tswait;
    }

    public void setTswait(Integer tswait) {
        this.tswait = tswait;
    }

    public String getTsverificationtype() {
        return tsverificationtype;
    }

    public void setTsverificationtype(String tsverificationtype) {
        this.tsverificationtype = tsverificationtype;
    }

    public String getTsverficationframe() {
        return tsverficationframe;
    }

    public void setTsverficationframe(String tsverficationframe) {
        this.tsverficationframe = tsverficationframe;
    }

    public String getTsverificationcontent() {
        return tsverificationcontent;
    }

    public void setTsverificationcontent(String tsverificationcontent) {
        this.tsverificationcontent = tsverificationcontent;
    }

    public Integer getTscommon() {
        return tscommon;
    }

    public void setTscommon(Integer tscommon) {
        this.tscommon = tscommon;
    }

    public String getTsremarks() {
        return tsremarks;
    }

    public void setTsremarks(String tsremarks) {
        this.tsremarks = tsremarks;
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

    public Integer getTssuccess() {
        return tssuccess;
    }

    public void setTssuccess(Integer tssuccess) {
        this.tssuccess = tssuccess;
    }
}