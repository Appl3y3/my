package com.appleye.handler;

import com.appleye.jsoup.TextHandler;
import org.jsoup.nodes.Element;

/**
 * @Description: 实现获取标题
 * @Author: Appleye
 * @CreateTime: 2023-01-09 23:30
 **/
public class TitleDocumentHandler implements DocumentParser{
    @Override
    public String set(Element element) {
        String tmpStr = String.valueOf(element.select("a > script"));
        String title = tmpStr.substring(tmpStr.indexOf("d(") + 3, tmpStr.indexOf("))") - 1);
        return TextHandler.parseTitle(title);
    }
}
