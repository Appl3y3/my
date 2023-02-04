package com.appleye.handler;

import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.util.Map;

/**
 * @Description: 实现获取电影id
 * @Author: Appleye
 * @CreateTime: 2023-01-09 23:20
 **/
public class IdDocumentParser implements DocumentParser {
    @Override
    public String set(Element element) {
        String tmpAttr = element.select("a").attr("href");
        int start = tmpAttr.indexOf("guochan_") + 8;
        int end = tmpAttr.indexOf(".html");
        return tmpAttr.substring(start, end);
    }

}
