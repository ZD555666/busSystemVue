package com.ruoyi.wx.complaint.service.impl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.wx.complaint.mapper.TWxComplaintMapper;
import com.ruoyi.wx.complaint.domain.TWxComplaint;
import com.ruoyi.wx.complaint.service.ITWxComplaintService;

/**
 * 投诉建议管理Service业务层处理
 * 
 * @author ruoyi
 * @date 2021-08-16
 */
@Service
public class TWxComplaintServiceImpl implements ITWxComplaintService 
{
    @Autowired
    private TWxComplaintMapper tWxComplaintMapper;

    /**
     * 查询投诉建议管理
     * 
     * @param complaintid 投诉建议管理ID
     * @return 投诉建议管理
     */
    @Override
    public TWxComplaint selectTWxComplaintById(Long complaintid)
    {
        return tWxComplaintMapper.selectTWxComplaintById(complaintid);
    }

    /**
     * 查询投诉建议管理列表
     * 
     * @param tWxComplaint 投诉建议管理
     * @return 投诉建议管理
     */
    @Override
    public List<TWxComplaint> selectTWxComplaintList(TWxComplaint tWxComplaint)
    {
        return tWxComplaintMapper.selectTWxComplaintList(tWxComplaint);
    }

    /**
     * 新增投诉建议管理
     * 
     * @param tWxComplaint 投诉建议管理
     * @return 结果
     */
    @Override
    public int insertTWxComplaint(TWxComplaint tWxComplaint)
    {
        return tWxComplaintMapper.insertTWxComplaint(tWxComplaint);
    }

    /**
     * 修改投诉建议管理
     * 
     * @param tWxComplaint 投诉建议管理
     * @return 结果
     */
    @Override
    public int updateTWxComplaint(TWxComplaint tWxComplaint)
    {
        return tWxComplaintMapper.updateTWxComplaint(tWxComplaint);
    }

    /**
     * 批量删除投诉建议管理
     * 
     * @param complaintids 需要删除的投诉建议管理ID
     * @return 结果
     */
    @Override
    public int deleteTWxComplaintByIds(Long[] complaintids)
    {
        return tWxComplaintMapper.deleteTWxComplaintByIds(complaintids);
    }

    /**
     * 删除投诉建议管理信息
     * 
     * @param complaintid 投诉建议管理ID
     * @return 结果
     */
    @Override
    public int deleteTWxComplaintById(Long complaintid)
    {
        return tWxComplaintMapper.deleteTWxComplaintById(complaintid);
    }
}
