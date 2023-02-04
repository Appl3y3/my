package com.appleye.handler;

import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;

import java.util.Map;

/**
 * @Description: TODO
 * @Author: Appleye
 * @CreateTime: 2023-01-09 23:11
 **/
public interface DocumentParser {
    default String set(Element element) {throw new UnsupportedOperationException("不支持该方法"); };

    default Map<String, String> set(Map<String, String> map, Document document){ throw new UnsupportedOperationException("不支持该方法"); }
}
