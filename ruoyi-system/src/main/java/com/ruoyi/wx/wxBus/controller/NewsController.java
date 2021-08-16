package com.ruoyi.wx.wxBus.controller;

import com.ruoyi.wx.wxBus.domain.News;
import com.ruoyi.wx.wxBus.service.impl.IsShowServiceImpl;
import com.ruoyi.wx.wxBus.utils.CrawlerUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
    @Autowired
    private IsShowServiceImpl isShowService;

    @RequestMapping(value = "/getNews")
    public List<News> getNews(int param){
        Integer check = isShowService.selectNew();
        if(check == 0){
           return null;
        }else{
            List<News> newsData = null;
            try {
                newsData = crawlerUtil.getNewsData(param);
            } catch (Exception e) {
                e.printStackTrace();
            }
            return newsData;
        }

    }

}
