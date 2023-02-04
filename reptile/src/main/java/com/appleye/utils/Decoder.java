package com.appleye.utils;

import sun.misc.BASE64Decoder;

import java.io.IOException;

public class Decoder {
    /**
     * 解码
     * @param str
     * @return
     */
    public static String parseTitle(String str){

        try {
            byte[] bytes = new BASE64Decoder().decodeBuffer(str);
            String decode = new String(bytes, "UTF-8");
            return decode;
        } catch (IOException e) {
            e.printStackTrace();

        }
        return "解码失败";
    }
}
