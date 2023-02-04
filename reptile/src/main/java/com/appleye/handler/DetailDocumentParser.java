package com.appleye.handler;

import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.util.HashMap;
import java.util.Map;

/**
 * @Description: 获取分辨率, 影片时长, 发布时间, 影片格式, 影片大小
 * @Author: Appleye
 * @CreateTime: 2023-01-11 21:45
 **/
public class DetailDocumentParser implements DocumentParser{
    @Override
    public Map<String, String> set(Map<String, String> map, Document document) {
        Elements content = document.getElementById("content").select("p");
        String p;
        for (Element element : content) {
            getDetail(map, element);
        }
        return map;
    }

    public static void getDetail(Map<String, String> map, Element element) {
        String p;
        p = element.text();
        if (p.contains("】") && !p.endsWith("：")) {
            String[] split = p.split("：");
            map.put(split[0].replace("【分辨率】", "resolution")
                    .replace("【影片时长】", "minutes")
                    .replace("【发布时间】", "publishDate")
                    .replace("【影片格式】", "format")
                    .replace("【影片大小】", "size"), split[1].replace("分钟", "").replace(" MB", ""));
        }
    }
}
