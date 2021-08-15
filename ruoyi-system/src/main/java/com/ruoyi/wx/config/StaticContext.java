package com.ruoyi.wx.config;

import org.springframework.context.ConfigurableApplicationContext;

/**
 * @author gjw
 * @version 1.0
 * @date 2021/8/14 20:17
 */
public class StaticContext {

    private static ConfigurableApplicationContext context;

    public static ConfigurableApplicationContext getContext() {
        return context;
    }

    public static void setContext(ConfigurableApplicationContext context) {
        StaticContext.context = context;
    }


}
