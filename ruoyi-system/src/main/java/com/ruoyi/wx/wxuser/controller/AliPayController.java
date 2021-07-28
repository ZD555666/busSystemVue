package com.ruoyi.wx.wxuser.controller;

import com.alibaba.fastjson.JSONObject;
import com.alipay.api.AlipayApiException;
import com.alipay.api.AlipayClient;
import com.alipay.api.DefaultAlipayClient;
import com.alipay.api.request.AlipayTradePrecreateRequest;
import com.alipay.api.response.AlipayTradePrecreateResponse;
import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.common.utils.OrderUtil;
import com.ruoyi.wx.config.AlipayConfig;
import com.ruoyi.wx.config.PayResponse;
import com.ruoyi.wx.config.ScheduledConfig;
import com.ruoyi.wx.wxuser.domain.WxAliPay;
import com.ruoyi.wx.wxuser.domain.WxRecord;
import com.ruoyi.wx.wxuser.service.WxAliPayService;
import com.ruoyi.wx.wxuser.service.WxMoneyService;
import com.ruoyi.wx.wxuser.service.WxRecordService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.Date;
import java.util.HashMap;

/**
 * @author gjw
 * @version 1.0
 * @date 2021/7/27 22:16
 */
@RestController
@RequestMapping("/wx")
public class AliPayController {

    @Autowired
    private WxMoneyService moneyService;

    @Autowired
    private WxRecordService recordService;

    @Autowired
    private WxAliPayService aliPayService;

    @Autowired
    private RedisTemplate<String, String> redisTemplate;

    @RequestMapping("/aliPay")
    @ResponseBody
    public AjaxResult aliPay(@RequestBody HashMap<String, Object> map) {
        AlipayClient alipayClient = new DefaultAlipayClient(AlipayConfig.gatewayUrl, AlipayConfig.APPID, AlipayConfig.RSA_PRIVATE_KEY,
                AlipayConfig.format, AlipayConfig.charset, AlipayConfig.ALIPAY_PUBLIC_KEY, AlipayConfig.sign_type);
        AlipayTradePrecreateRequest request = new AlipayTradePrecreateRequest();
        request.setReturnUrl(AlipayConfig.return_url);
        request.setNotifyUrl(AlipayConfig.notify_url);//在公共参数中设置回跳和通知地址
        // 支付请求
        String opId = (String) map.get("opId");
        Integer amount = (Integer) map.get("amount");
        String orderNo = OrderUtil.getOrderNo(4);
        String subject = "公交充值" + amount + "元";
        request.setBizContent("{" +
                "    \"out_trade_no\":\"" + orderNo + "\"," +//商户订单号
                "    \"total_amount\":\"" + amount + "\"," +
                "    \"subject\":\"" + subject + "\"," +
                "    \"store_id\":\"" + PayResponse.store_id + "\"," +
                "    \"timeout_express\":\"1m\"}");
        AlipayTradePrecreateResponse response = null;
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
        System.err.println(qrCode);
        System.err.println(response.getBody());
        aliPayService.insertPayInfo(new WxAliPay().setOpenId(opId).
                setOut_trade_no(orderNo).setSubject(subject).setTotal_amount(amount*100).
                setStore_id(PayResponse.store_id).setPayState(PayResponse.WAIT_BUYER_PAY));
        JSONObject json = new JSONObject();
        json.put("qrCode",qrCode);
        json.put("orderNo",orderNo);
        return AjaxResult.success(json);
    }

    @RequestMapping("/queryPay")
    @ResponseBody
    public String queryPay() {
//        String opId = (String) map.get("opId");
//        Integer amount = (Integer) map.get("amount");
//        int money = Integer.valueOf(redisTemplate.opsForValue().get("money")) + amount;
//        int i = moneyService.updMoneyByOpId(opId, money * 100);
//        if (i > 0)
//            recordService.insertRecord(new WxRecord().setOpenId(opId).setIsAdd(0).setRecord(amount).setRecordTime(new Date()));
        return "123";
    }


}
