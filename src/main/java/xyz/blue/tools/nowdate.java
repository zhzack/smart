package xyz.blue.tools;

import java.text.SimpleDateFormat;
import java.util.Date;

public class nowdate {
    Date now = new Date();
    SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");//可以方便地修改日期格式

    public String nowDate() {

        return dateFormat.format(now);//获取当前时间
    }

}
