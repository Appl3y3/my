package com.appleye.handler;

import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;

import java.util.HashMap;
import java.util.Map;

/**
 * @Description: 获取图片
 * @Author: Appleye
 * @CreateTime: 2023-01-11 21:50
 **/
public class ImageDocumentParser implements DocumentParser{
    @Override
    public Map<String, String> set(Map<String, String> map, Document document) {
        Elements images = document.getElementsByClass("capture").select("img");
        StringBuilder imagesPath = new StringBuilder();
        for (int j = 0; j < images.size(); j++) {
            if (j == 0) {
                imagesPath.append(images.get(j).attr("src"));
            } else {
                imagesPath.append("|").append(images.get(j).attr("src"));
            }
        }
        map.put("imagesPath", imagesPath.toString());
        return map;
    }
}
