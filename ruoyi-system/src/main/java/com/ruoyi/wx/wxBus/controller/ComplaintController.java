package com.ruoyi.wx.wxBus.controller;

import com.ruoyi.wx.wxBus.domain.Complaint;
import com.ruoyi.wx.wxBus.service.impl.ComplaintServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.*;

/**
 * @version 1.0
 * @author: ZD
 * @date: 2021/7/29 1:27
 * @desc:
 */

@RestController
@RequestMapping(value = "/wx")
public class ComplaintController {

    @Autowired
    private ComplaintServiceImpl complaintService;

    //用户提交投诉的建议
    @RequestMapping(value = "/uploadFile")
    public String getImageFile(@RequestParam("file") MultipartFile file,
                               @RequestParam("number") String phoneNumber,
                               @RequestParam("problem") String problem) {
        System.out.println(phoneNumber);
        //将文件保存到服务器上
        String path = "F:\\test\\";
        if (file.isEmpty()) {
            return "fail";
        }
        String fileName = file.getOriginalFilename();
        File dest = new File(new File(path).getAbsolutePath() + "/" + fileName);
        System.out.println(dest.getAbsolutePath());
        if (!dest.getParentFile().exists()) {
            dest.getParentFile().mkdirs();
        }
        try {
            file.transferTo(dest); // 保存文件
        } catch (Exception e) {
            e.printStackTrace();
        }
        //将数据保存到数据库
        Complaint complaint = new Complaint();
        complaint.setImagePath(dest.getAbsolutePath());
        complaint.setPhoneNumber(phoneNumber);
        complaint.setProblem(problem);
        complaintService.addComplaint(complaint);

        return "success";
    }
}
