package com.volunteer.volunteer.util;

import java.util.Random;
/**
 * @Author: MaoLin
 * @Date: 2019/3/28 17:40
 * @Version 1.0
 */
public class RandomUtil {

    /**
     * 生成唯一主键
     * 产生冲突的可能性是 1/1000000
     * synchronized关键字，防止多线程冲突
     */
    public static synchronized int getUniqueKey() {
        //生成一个六位的随机数
        Random random = new Random();
        return random.nextInt(900000) + 100000;
    }
}
