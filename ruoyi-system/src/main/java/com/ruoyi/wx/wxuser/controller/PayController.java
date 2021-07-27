package com.ruoyi.wx.wxuser.controller;

import com.alipay.api.AlipayApiException;
import com.alipay.api.AlipayClient;
import com.alipay.api.DefaultAlipayClient;
import com.alipay.api.request.AlipayTradePrecreateRequest;
import com.alipay.api.response.AlipayTradePrecreateResponse;
import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.wx.config.AlipayConfig;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;

/**
 * @author gjw
 * @version 1.0
 * @date 2021/7/27 22:16
 */
@RestController
@RequestMapping("/wx")
public class PayController {

    @RequestMapping("/aliPay")
    @ResponseBody
    public AjaxResult aliPay(@RequestBody HashMap<String,Object> map) {
        AlipayClient alipayClient = new DefaultAlipayClient(AlipayConfig.gatewayUrl,AlipayConfig.APPID,AlipayConfig.RSA_PRIVATE_KEY,
                AlipayConfig.format,AlipayConfig.charset,AlipayConfig.ALIPAY_PUBLIC_KEY,AlipayConfig.sign_type);
        AlipayTradePrecreateRequest request = new AlipayTradePrecreateRequest();
        request.setReturnUrl(AlipayConfig.return_url);
        request.setNotifyUrl(AlipayConfig.notify_url);//在公共参数中设置回跳和通知地址
        // 支付请求
        request.setBizContent("{" +
                "    \"out_trade_no\":\"" + "20150320010101003" + "\"," +//商户订单号
                "    \"total_amount\":\"" + "100" + "\"," +
                "    \"subject\":\"" + "外卖" + "当面付" + "\"," +
                "    \"store_id\":\"100011\"," +
                "    \"timeout_express\":\"5m\"}");
        AlipayTradePrecreateResponse response = null;
        //支付宝二维码URL
        String qrCode = "";
        try {
            response = alipayClient.execute(request);
            if (!response.isSuccess()) {
                System.out.println(response.getMsg());
            }
            qrCode = response.getQrCode();
        } catch (AlipayApiException e) {
            System.err.println("异常！！！！！");
        }
        // 封装支付信息 返回
        System.err.println(qrCode);
        System.err.println(response.getBody());
        return AjaxResult.success(qrCode);

    }


}
