package com.appleye.handler;

import org.jsoup.nodes.Element;

/**
 * @Description: 获取磁力链接
 * @Author: Appleye
 * @CreateTime: 2023-01-11 21:35
 **/
public class MagnetDocumentHandler implements DocumentParser{
    @Override
    public String set(Element element) {
        return element.select("a").first().attr("href");
    }
}
