package com.ruoyi.wx.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;

@Component
@PropertySource("classpath:application.yml") //指定yml文件位置
@ConfigurationProperties(prefix = "server")
public class AlipayConfig {

    @Value("${server.port}")
    public static String port;

    public static String APPID = "2021000117688216";

    //商户私钥
    public static String RSA_PRIVATE_KEY = "MIIEvwIBADANBgkqhkiG9w0BAQEFAASCBKkwggSlAgEAAoIBAQCk35E/TQva9F3s98NXYAwK70MOup0uX" +
            "vO0c3cgWb+k2NXVldnTWBp54UhW9lffHdpYW4+WeVRg4SvbEopV5sk0dJm9Y6hGwRdqI7RmTyE8eQWpZxyK6HS1LAuEncDBnLQHVhTWE+0g7w3aRWo" +
            "vS3dzTCcYRonHSR7yisgz7J39LwVwnRc1alt9wbGIv7WebDcx9SgrrB490l9tvNnfiTic3MehzFZju21wlQ9PjUMjgEXgDINimlw9LGmv3MO2OaqMU7" +
            "6uNp0Vz8k7eP5f2NcODUMWRdaWK+oY0m5xLI38zN6M2jkvCwEDijSChUR4nlIRguCo/fasnjOTV4R4SlhvAgMBAAECggEAJyWybi5UF0wYlTCLIE78" +
            "7Qe8i13B/oWrJfiYm4TT3UdjFpC7JNRxjOjx7TwWxzJmar5qzJbIcyxj5mNzl0vyEFNzQaXNCefFMH2D0xsM0DgIIR4Lh+wD8hSgM3/lidM0Z92HmwEmN" +
            "8CrMF11Bf/MQAMf8UKmajwxbfOH79RJ+xhxGJPsIKvRoQkSouJ1r1qUoEsELqYtJv3pxZCA7URd3mRbLI2Epht+eZOBmZtAfzq2qcIF4bY3hHKY1PZx" +
            "5429Wb3utgb3WvCb2xzwhmlSSiJbpN9seN6QYzF71HvjVQWepK0cQcNfYvfPR36xf5ZXT+Gr5jlQc/yY5rLt0pJRAQKBgQDz5/M5jZTd/Pa76hxgpsD+Rn8" +
            "kxkM4vlexafSjQ3D9mzMplNaCOvxw36vZrCAQO6mwx7k4nrc2KmjUBTqFdDwKy453PSBVctc4Vi85Xi8ddZc7ke7r0zcGBLeusSkWWKG3cVXUErTS47rdS2" +
            "XlX51RK24pEMk/Mot8XWv4nx4v7wKBgQCtDGfqxiecUaXmXrTTqaKzazpzkTKLW6nZiYs0GLFO8xU7qAk88ba8H/TBaFG09lx/mgy4ttLCZl45GhIlz6Bl" +
            "RuCt++BQIIdNM+mmauevkbSQkdQbPSxgkfWvBP+z+FF1BDFyvAlM5jGmbHmO3i0pIpsYfrPoXYurCB9LM93fgQKBgQDUS1jOCGjBy5OqvjvS+HHVIOE7zgHm" +
            "mu5TMslHbcUkSHkAwVbwZY0iZdz7irEhz/yELWMPYD5RvBogKSjlzeUQ9FpdeOvy1BF2LLXP9iWGO7rVk9DknKQw7dw/Bl6f36dqtxAgleOkB6m3QhA/1Lhm" +
            "VSn+PmkcQ89bASmgbkblKwKBgQCnKl3UaPlow2LcyM4rzioy4jnD3BaUksbgM0N63wOhfaccSvYpnmJlFwWsNCcCBh9S0LDLWyj7zEHnLW0sg4axbyeqSq/" +
            "lF5c8mBJ0xb7sVne0VHsumQiefUF1ggG1jAK8ZIGUEVt2osSBvTYsMio0G47xuav3oy5B2QXJ8t+5gQKBgQCFEH5eP0dvFpWfMNeGMt9rIkcfeGkDvtw2ll65bI9P5" +
            "WsGzpdi4bO8pVWe5ZZYkDMGk8OXbCfs+IT63aXuE2Q6Nnug9D+VerMPEvrxFx4e3nOpTfgT7KUfBDp29uBq4DBt4chbHiOGJUdJMuty1dra3ZziRuduK3RoIjXCp6buTQ==";

    //支付宝公钥
    public static String ALIPAY_PUBLIC_KEY = "MIIBIjANBgkqhkiG9w0BAQEFAAOCAQ8AMIIBCgKCAQEAkFXwAo71TFdFmfL1Ue0V7vZXDghxVp0z5b" +
            "6qsMz3reYAHj92uo2qULX1DFXeIXaFCjCM/5P0+KltsBcRsCRE4E5FXc9dX7x2raNpyxiHg/Tv+kEdL4RA6s+6VnCsOqxT7D0itpnxLV5o0L6HJ" +
            "Eb1oyo7/OWK2vX3p3WHA5PvdQtSgcSIo6sU4NKoIwQ/sIgsmq9HLYNs/UpXKEnR/K3BJTYb6GT9AYftQF4iPcmxHDRvDX05hNkNsN7Dvw4r0Q86" +
            "3AjjSD2o/O1odcietoxvOszp9kqeoisJLFkmllbhCUN/i7WVNIh9wto1ah0q07/FdW6MwN2VY4TuUUXWSaAmwQIDAQAB";

    public static String notify_url = "https://www.mystudy365.cn:8080/wx/notify_url";

    public static String return_url = "https://www.mystudy365.cn:8080/wx/return_url";

    // 签名方式
    public static String sign_type = "RSA2";

    // 字符编码格式
    public static String charset = "utf-8";

    //返回格式
    public static String format = "json";

    // 支付宝网关
    public static String gatewayUrl = "https://openapi.alipaydev.com/gateway.do";

    // 日志记录路径
    public static String log_path = "D:\\";

}
