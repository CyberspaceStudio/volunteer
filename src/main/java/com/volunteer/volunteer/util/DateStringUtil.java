package com.volunteer.volunteer.util;

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
