package com.ruoyi.wx.wxuser.controller;

import com.alibaba.fastjson.JSONObject;
import com.ruoyi.common.core.controller.BaseController;
import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.common.utils.HttpClientUtil;
import com.ruoyi.wx.wxuser.domain.TWxUser;
import com.ruoyi.wx.wxuser.domain.WxRecord;
import com.ruoyi.wx.wxuser.service.ITWxUserService;
import com.ruoyi.wx.wxuser.service.SendMsgService;
import com.ruoyi.wx.wxuser.service.WxMoneyService;
import com.ruoyi.wx.wxuser.service.WxRecordService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.web.bind.annotation.*;

import java.util.*;
import java.util.concurrent.TimeUnit;

/**
 * @author gjw
 * @version 1.0
 * @date 2021/7/24 15:25
 */
@RestController
@RequestMapping("/wx")
public class UserInfoController extends BaseController {

    @Autowired
    private ITWxUserService tWxUserService;

    @Autowired
    private WxMoneyService moneyService;

    @Autowired
    @Qualifier("sendMsgServiceImpl")
    private SendMsgService sendMsgService;

    @Autowired
    private WxRecordService recordService;

    @Autowired
    private RedisTemplate<String, String> redisTemplate;

    @GetMapping("/getOpenId")
    public AjaxResult getOpenId(@RequestParam String code) {
        final String appId = "wxf881ad7f711270e6";
        final String appSecret = "3487fac5574cee41c546937ad0c2cb03";
        String url = "https://api.weixin.qq.com/sns/jscode2session";
        Map<String, String> param = new HashMap<>();
        param.put("appid", appId);
        param.put("secret", appSecret);
        param.put("js_code", code);
        param.put("grant_type", "authorization_code");
        String wxResult = HttpClientUtil.doGet(url, param);
        JSONObject jsonObject = JSONObject.parseObject(wxResult);
        // 获取参数返回的
        String session_key = jsonObject.get("session_key").toString();
        String open_id = jsonObject.get("openid").toString();
        System.out.println(session_key + "," + open_id);
        TWxUser user = tWxUserService.queryWxUserByOpId(open_id);
        if (user != null) {
            jsonObject.put("isRegis", true);
            System.err.println(user + "!!!!!!!!!");
            redisTemplate.opsForValue().set(user.getOpenid(), JSONObject.toJSONString(user));
        }
        return AjaxResult.success(jsonObject);
    }


    @PostMapping("/sendSms")
    public AjaxResult sendSms(@RequestBody HashMap<String, String> map) {

        String code = redisTemplate.opsForValue().get(map.get("phone"));
        if (code != null) return AjaxResult.error("验证码已存在");

        code = UUID.randomUUID().toString().substring(0, 4);
        String isSend = sendMsgService.sendPhoneMsg(map.get("phone"), "SMS_219749033", code);
        if (isSend.equals("OK")) {
            redisTemplate.opsForValue().set(map.get("phone"), code, 1, TimeUnit.MINUTES);
            return AjaxResult.success("发送成功");
        } else {
            return AjaxResult.error("发送失败");
        }
    }

    @PostMapping("/subSms")
    public AjaxResult subSms(@RequestBody HashMap<String, Object> map) {
        String code = redisTemplate.opsForValue().get(map.get("phone"));
        if (code == null) return AjaxResult.error("未发送验证码或已失效，请重新发送");
        if (code.equals(map.get("sms"))) {
            Object userInfo = map.get("userInfo");
            TWxUser wxUser = JSONObject.parseObject((String) userInfo, TWxUser.class);
            wxUser.setOpenid((String) map.get("opid"));
            wxUser.setPhone((String) map.get("phone"));
            TWxUser user = tWxUserService.queryWxUserByOpId(wxUser.getOpenid());
            if (user == null) {
                tWxUserService.insertTWxUser(wxUser);
                moneyService.insertMoneyByOpId(wxUser.getOpenid(), 0);
                redisTemplate.opsForValue().set(wxUser.getOpenid(), JSONObject.toJSONString(wxUser));
            } else {
                redisTemplate.opsForValue().set(wxUser.getOpenid(), JSONObject.toJSONString(user));
            }

            return AjaxResult.success("验证成功");
        } else {
            return AjaxResult.error("验证码错误");
        }
    }

    @PostMapping("/getLoginUserInfo")
    public AjaxResult getLoginUserInfo(@RequestBody HashMap<String, Object> map) {
        System.err.println(map.get("opId"));
        String res = redisTemplate.opsForValue().get(map.get("opId"));
        System.err.println(res + "1111111111111111");
        return AjaxResult.success("获取成功", JSONObject.parseObject(res));
    }

    @PostMapping("/saveInfo")
    public AjaxResult saveInfo(@RequestBody TWxUser saveUser) {
        System.out.println(saveUser + "=======>>>>>");
        redisTemplate.opsForValue().set(saveUser.getOpenid(), JSONObject.toJSONString(saveUser));
        return AjaxResult.success(tWxUserService.updWxUserByOpId(saveUser) == 1 ? "保存成功" : "保存失败");
    }

    @GetMapping("/queryBalance")
    public AjaxResult queryBalance(@RequestParam("opId") String opId) {
        return AjaxResult.success(moneyService.queryMoneyByOpId(opId));
    }

    @GetMapping("/queryInCome")
    public AjaxResult queryInCome(@RequestParam("opId") String opId) {
        return AjaxResult.success(recordService.queryIncomeRecordByOpId(opId, 0));
    }

    @GetMapping("/reqScanCode")
    public AjaxResult reqScanCode(@RequestParam String opId) {
        String code = UUID.randomUUID().toString().substring(0, 18);
        String redisCode = redisTemplate.opsForValue().get(opId + "scan");
        if (redisCode != null) return AjaxResult.success(redisCode);
        redisTemplate.opsForValue().set(opId + "scan", "/wx/scanCode/" + opId + "/" + code, 1, TimeUnit.MINUTES);
        return AjaxResult.success("/wx/scanCode/" + opId + "/" + code);
    }

    @GetMapping("scanCode/{opId}/{code}")
    public AjaxResult scanCode(@PathVariable("opId") String opId, @PathVariable("code") String code) {
        String redisCode = redisTemplate.opsForValue().get(opId + "scan");
        String substring = redisCode.substring(redisCode.length() - 18, redisCode.length());
        JSONObject json = JSONObject.parseObject(redisTemplate.opsForValue().get(opId));
        if (code.equals(substring) && json.get("openid").equals(opId)) {
            int money = moneyService.queryMoneyByOpId((String) json.get("openid"));
            int i = moneyService.updMoneyByOpId((String) json.get("openid"), money - (1 * 100));//扣一块钱
            if (i > 0) {
                recordService.insertRecord(new WxRecord().setOpenId(opId).setIsAdd(1).setRecord(1 * 100).setRecordTime(new Date()));
                return AjaxResult.success("paySuccess");
            } else {
                return AjaxResult.error("payError");
            }
        } else {
            return AjaxResult.error("payError");
        }
    }

    @GetMapping("/queryOutCome")
    public AjaxResult queryOutCome(@RequestParam("opId") String opId) {
        return AjaxResult.success(recordService.queryIncomeRecordByOpId(opId, 1));
    }


}
