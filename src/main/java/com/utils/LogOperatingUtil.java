package com.utils;

import com.pojo.LogInfo;
import java.util.Date;

/**
 * @author Mr.Andrew
 * @Description: ��־������
 * @date 2018/4/13 11:02
 */
public class LogOperatingUtil {
    /**
     * @Description:д����־
     * @param:д������
     * @date:2018/4/13 10:58
     */
    public void writeTxtFile(String str,LogInfo logInfo){
        String filein=str+"\r\n";
        logInfo.setLogtime(new Date());
        logInfo.setContent(filein);
    }
}
