package com.appleye.jsoup;

import sun.misc.BASE64Decoder;

import java.io.IOException;

/**
 * @author Appleye
 * @created 2020-12-18 00:16
 */
public class TextHandler {

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
