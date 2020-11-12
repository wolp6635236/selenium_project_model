package com.cdecube.common;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @Author: liupeng
 * @Description:
 * @Date: Created in 16:35 2018/6/21
 * @Modified By:
 */
public class tool {
    public  static String date() {
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date currentTime = new Date();
        String dateString = formatter.format(currentTime);
        System.out.println(dateString);
        return dateString;
    }
    public void st() {
        String i="2017-12-24 21:56:00";

        for(int a=0;a<100000;a++) {
            if( date().equals(i) ){
                System.out.print("成功");
                // test2.login();
            }
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println(date()+"||");
            // String dateString=dateString;
        }
    }

    public static void main(String[] args) {
        tool da = new tool();

        da.date();
    }
}
