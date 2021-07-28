package com.ruoyi.wx.config;

import com.alipay.api.AlipayApiException;
import com.alipay.api.AlipayClient;
import com.alipay.api.DefaultAlipayClient;
import com.alipay.api.request.AlipayTradeQueryRequest;
import com.alipay.api.response.AlipayTradeQueryResponse;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

/**
 * @version 1.0
 * @author: gjw
 * @date: 2021/7/28 14:21
 * @desc:
 */
@Service
public class ScheduledConfig {
    public static boolean isOpen = false;
    public static boolean isPay = true;
    @Scheduled(cron = "0/5 * * * * ?")
    public void queryIsPay(String orderNo) throws AlipayApiException {
        if (isOpen) {
            AlipayClient alipayClient = new DefaultAlipayClient(AlipayConfig.gatewayUrl, AlipayConfig.APPID, AlipayConfig.RSA_PRIVATE_KEY,
                    AlipayConfig.format, AlipayConfig.charset, AlipayConfig.ALIPAY_PUBLIC_KEY, AlipayConfig.sign_type);
            AlipayTradeQueryRequest request = new AlipayTradeQueryRequest();
            request.setBizContent("{" +
                    "\"out_trade_no\":\"" + orderNo + "\"," +
                    "      \"query_options\":[" +
                    "        \"trade_settle_info\"" +
                    "      ]" +
                    "  }");
            AlipayTradeQueryResponse response = alipayClient.execute(request);
            if (response.isSuccess()) {
                System.err.println("调用成功");
                isOpen=false;

            } else {
                System.err.println("调用失败");
            }
        }
    }


}
