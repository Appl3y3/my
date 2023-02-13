package com.appleye.utils;

import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

import java.io.IOException;


public class ReptileUtils {
    public static Document getDocument(String url) {
        Document document;
        try {
            Connection connect = Jsoup.connect(url);
            connect.userAgent("Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/58.0.3029.110 Safari/537.36");
            document = connect.get();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return document;
    }

}
