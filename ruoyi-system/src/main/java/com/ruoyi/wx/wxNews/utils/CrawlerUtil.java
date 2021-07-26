package com.ruoyi.wx.wxNews.utils;

import com.ruoyi.wx.wxNews.domain.News;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.stereotype.Component;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

/**
 * @version 1.0
 * @author: ZD
 * @date: 2021/7/26 17:04
 * @desc:
 */
@Component
public class CrawlerUtil {

    public List<News> getNewsData(int param) throws Exception {
        List<News> newsList = new ArrayList<>();
        String url = "https://www.tiatiatoutiao.com/guona/list_1_"+param+".html";
        Document document = Jsoup.parse(new URL(url), 300000);
        Element element = document.getElementById("listNewsTimeLy");
        Elements elements = element.getElementsByTag("li");
        for(Element el : elements) {
            String title = el.getElementsByTag("b").text();
            String src = el.getElementsByClass("animation").attr("src");
            String time = el.getElementsByClass("from").text();
            News news = new News();
            news.setTitle(title);
            news.setSrc(src);
            news.setTime(time);
            newsList.add(news);
        }
        return newsList;
    }
}
