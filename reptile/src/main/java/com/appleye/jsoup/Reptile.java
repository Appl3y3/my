package com.appleye.jsoup;

import com.appleye.handler.*;
import com.appleye.utils.ConfigReader;
import com.appleye.utils.DBUtils;
import com.appleye.utils.NumUtils;
import com.appleye.utils.ReptileUtils;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.*;

public class Reptile {

    static Logger logger = LoggerFactory.getLogger(Reptile.class);

    static Properties properties = ConfigReader.getProperties("dataSources.properties");
    static String DRIVER = properties.getProperty("DRIVER");
    static String URL = properties.getProperty("URL");
    static String USERNAME = properties.getProperty("USERNAME");
    static String PASSWORD = properties.getProperty("PASSWORD");
    static HashMap<String, String> template = new HashMap<>();

    static List<String> existMovieList;
    static Integer totalPage = 200;
    static Integer list = 50;
    static Integer total = totalPage * list;
    static int update;
    static int duplicate;

    static {
//        template.put("mainUrl", "https://138290.xyz");
        template.put("mainUrl", "https://140392.xyz/");
        template.put("catalogUrlTemplate", "%s/list.php?class=guochan&page=%d");
        template.put("movieUrlTemplate", "%s/movie.php?class=guochan&id=%s");
        template.put("magnetUrlTemplate", "%s/download.php?class=guochan&id=%s");

        Connection conn = DBUtils.getConn(DRIVER, URL, USERNAME, PASSWORD);
        existMovieList = getExistMovieList(conn);
    }


    public static void main(String[] args) {

        for (int i = 1; i <= totalPage; i++) {
            Document catalogDocument = ReptileUtils.getDocument(getUrl(template.get("catalogUrlTemplate"), template.get("mainUrl"), i));
            parseElementsToMapAdvance(catalogDocument, i);
        }

    }

    @Deprecated
    public static void parseElementsToMap(Document catalogDocument, Integer page) {

        Elements li = catalogDocument.select("ul.list").select("a[href^=/movie.php]");
        String movieId;
        String title;
        for (int i = 0; i < li.size(); i++) {
            movieId = new IdDocumentParser().set(li.get(i));

            //如果movieId存在则跳过
            if (existMovieList.contains(movieId)) {
                logger.info(String.format("第%d页第%d个存在，跳过~", page, i + 1));
                continue;
            }


            String attr = li.get(i).select("a").attr("href");
            String script = String.valueOf(li.get(i).select("a > script"));
//            if (attr.contains("id=")) {

                //获取【movieId】, 【标题】
                movieId = attr.substring(attr.indexOf("id=") + 3);



                String tmpTitle = script.substring(script.indexOf("d(") + 3, script.indexOf("))") - 1);
                title = TextHandler.parseTitle(tmpTitle);
                Map<String, String> map = new HashMap<>();
                map.put("movieId", movieId);
                map.put("title", title);

                //获取【磁力地址】
                Document downloadDocument = ReptileUtils.getDocument(getUrl(template.get("magnetUrlTemplate"), template.get("mainUrl"), movieId));
                String magnet = downloadDocument.select("a").first().attr("href");
                map.put("magnet", magnet);

                Document movieDocument = ReptileUtils.getDocument(getUrl(template.get("movieUrlTemplate"), template.get("mainUrl"), movieId));
                //获取【分辨率】, 【影片时长】, 【发布时间】, 【影片格式】, 【影片大小】
                Elements content = movieDocument.getElementById("content").select("p");
            for (org.jsoup.nodes.Element element : content) {
                    DetailDocumentParser.getDetail(map, element);
                }
                //获取【图片】
                Elements images = movieDocument.getElementsByClass("capture").select("img");
                StringBuilder imagesPath = new StringBuilder();
                for (int j = 0; j < images.size(); j++) {
                    if (j == 0) {
                        imagesPath.append(images.get(j).attr("src"));
                    } else {
                        imagesPath.append("|").append(images.get(j).attr("src"));
                    }
                }
                map.put("imagesPath", imagesPath.toString());
                Connection conn = DBUtils.getConn(DRIVER, URL, USERNAME, PASSWORD);
                logger.info(String.format("第%d页第%d个", page, i));
                save(map, conn);
            }
        }

    public static void parseElementsToMapAdvance(Document catalogDocument, Integer curPage) {

        Elements list = catalogDocument.select("ul.list");
        Elements li = list.select("a[href^=/html/movie]");

        String movieId;
        String title;
        for (int i = 0; i < li.size(); i++) {
            Map<String, String> map = new HashMap<>();

            movieId = new IdDocumentParser().set(li.get(i));
            if (existMovieList.contains(movieId)) {
                duplicate++;
                logger.info(String.format("进度%s，更新%d个，重复%d个", NumUtils.getPercent((((curPage - 1) * 50) + i + 1) * 1.0 / total, 2), update, duplicate));
                continue;
            }
            title = new TitleDocumentHandler().set(li.get(i));
            map.put("movieId", movieId);
            map.put("title", title);

            //获取【磁力地址】
            Document downloadDocument = ReptileUtils.getDocument(getUrl(template.get("magnetUrlTemplate"), template.get("mainUrl"), movieId));
            String magnet = new MagnetDocumentHandler().set(downloadDocument);
            map.put("magnet", magnet);

            Document movieDocument = ReptileUtils.getDocument(getUrl(template.get("movieUrlTemplate"), template.get("mainUrl"), movieId));
            //获取【分辨率】, 【影片时长】, 【发布时间】, 【影片格式】, 【影片大小】
            new DetailDocumentParser().set(map, movieDocument);
            //获取【图片】
            new ImageDocumentParser().set(map, movieDocument);

            Connection conn = DBUtils.getConn(DRIVER, URL, USERNAME, PASSWORD);
            update++;
            logger.info(String.format("进度%s，更新%d个，重复%d个", NumUtils.getPercent((((curPage - 1) * 50) + i + 1) * 1.0 / total, 2), update, duplicate));
            save(map, conn);


        }
    }



    public static void save(Map<String, String> map, Connection conn) {
        String sql = "insert into t_movie(movie_id,title,publish_date,minutes,size,format,resolution,magnet,images_path)" +
                " values ('%s','%s','%s','%d','%d','%s','%s','%s','%s')";
        sql = String.format(sql, map.get("movieId"), map.get("title"), map.get("publishDate")
                , Integer.parseInt(map.get("minutes")), Integer.parseInt(map.get("size")), map.get("format")
                , map.get("resolution"), map.get("magnet"), map.get("imagesPath"));


        // 数据来源movieId重复时，更新existMovieList
        try {
            DBUtils.insert(conn, sql);
        } catch (SQLException e) {
            logger.warn(String.format("重复插入%s",sql));
            existMovieList = getExistMovieList(conn);
        }

    }

    public static String getUrl(String urlTemplate, String mainUrl, Integer page) {
        return String.format(urlTemplate, mainUrl, page);
    }

    public static String getUrl(String urlTemplate, String mainUrl, String movieId) {
        return String.format(urlTemplate, mainUrl, movieId);
    }

    public static List<String> getExistMovieList(Connection conn) {
        String sql = "select movie_id from t_movie";
        ResultSet resultSet = DBUtils.select(conn, sql);
        List<String> list = new ArrayList<>();
        try {
            while (resultSet.next()) {
                list.add(resultSet.getString("movie_id"));
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return list;
    }



}


