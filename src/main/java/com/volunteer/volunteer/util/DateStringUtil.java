package com.volunteer.volunteer.util;

/**
 * 用来提供处理日期字符串的工具类
 */
public class DateStringUtil {

    public static String dateDealer(int input){
        String res = "";
        if(input < 10){
            res = "0" + input;
        }else {
            res = input + "";
        }
        return res;
    }
}
