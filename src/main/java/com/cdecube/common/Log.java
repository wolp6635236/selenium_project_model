package com.cdecube.common;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @Author: liupeng
 * @Description:
 * @Date: Created in 16:02 2018/8/7
 * @Modified By:
 */
public class Log {
    public static Logger log = LoggerFactory.getLogger(Log.class);

    public static void info(String str){
        if(null != str){
            log.info(str);
        }
    }

    public static void info(Object obj){
        info(String.valueOf(obj));
    }

    public static String getMethodName() {
        StackTraceElement[] stacktrace = Thread.currentThread().getStackTrace();
        StackTraceElement e = stacktrace[2];
        String methodName = e.getMethodName();

        return "===== Method : " + methodName + "   ";
    }

    public static String getClassName(Object obj) {
        String methodName = obj.getClass().getName();

        return "===== Method : " + methodName + "   ";
    }
}