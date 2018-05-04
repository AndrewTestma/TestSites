package com.utils;

import org.assertj.db.type.Changes;
import org.assertj.db.type.Source;

/**
 * @author Mr.Andrew
 * @Description: AssertJ∂œ—‘¿‡
 * @date 2018/3/269:37
 */
public class AssertJDB {

    Source source;
    Changes changes;

    public void monitorInit(){
        source= new Source("jdbc:mysql://192.168.140.2:3306/testsites?useUnicode=true&characterEncoding=UTF-8&allowMultiQueries=true", "root", "123456");
        changes=new Changes(source);
    }


}
