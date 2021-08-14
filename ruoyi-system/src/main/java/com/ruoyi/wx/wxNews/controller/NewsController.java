package com.ruoyi.wx.wxNews.controller;

import com.ruoyi.wx.wxNews.domain.News;
import com.ruoyi.wx.wxNews.utils.CrawlerUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

/**
 * @version 1.0
 * @author: ZD
 * @date: 2021/7/26 18:42
 * @desc:
 */
@RestController
@RequestMapping(value = "/wx")
public class NewsController {

    @Autowired
    private CrawlerUtil crawlerUtil;

    @RequestMapping(value = "/getNews")
    public List<News> getNews(int param){
        List<News> newsData = null;
        try {
            newsData = crawlerUtil.getNewsData(param);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return newsData;
    }

}