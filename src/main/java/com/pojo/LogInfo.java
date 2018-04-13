package com.pojo;

import com.fasterxml.jackson.annotation.JsonFormat;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

/**
 * @author Mr.Andrew
 * @Description: 日志实体类
 * @date 2018/4/12 16:51
 */
public class LogInfo {

    @DateTimeFormat(pattern= "yyyy-MM-dd HH:mm:ss")
    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss",timezone = "GMT+8")
    private Date logtime;

    private String content="控制台输出：";

    public Date getLogtime() {
        return logtime;
    }

    public void setLogtime(Date logtime) {
        this.logtime = logtime;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = this.content+"\r\n"+content;
    }

    @Override
    public String toString() {
        return "LogInfo{" +
                "logtime=" + logtime +
                ", content='" + content + '\'' +
                '}';
    }
}
