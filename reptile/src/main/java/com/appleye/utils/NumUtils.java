package com.appleye.utils;

import java.text.NumberFormat;

/**
 * @Description: TODO
 * @Author: Appleye
 * @CreateTime: 2023-01-15 12:27
 **/
public class NumUtils {

    /**
     * @description java实现小数转百分数
     * @param data  小数
     * @param digit 百分数保留小数点位数
     * @return 返回百分数
     */
    public static String getPercent(double data, int digit) {
        NumberFormat numberFormat = NumberFormat.getPercentInstance();
        numberFormat.setMinimumFractionDigits(digit);
        return numberFormat.format(data);
    }
}
