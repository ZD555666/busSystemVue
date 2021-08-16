package com.ruoyi.wx.complaint.mapper;

import java.util.List;
import com.ruoyi.wx.complaint.domain.TWxComplaint;

/**
 * 投诉建议管理Mapper接口
 * 
 * @author ruoyi
 * @date 2021-08-16
 */
public interface TWxComplaintMapper 
{
    /**
     * 查询投诉建议管理
     * 
     * @param complaintid 投诉建议管理ID
     * @return 投诉建议管理
     */
    public TWxComplaint selectTWxComplaintById(Long complaintid);

    /**
     * 查询投诉建议管理列表
     * 
     * @param tWxComplaint 投诉建议管理
     * @return 投诉建议管理集合
     */
    public List<TWxComplaint> selectTWxComplaintList(TWxComplaint tWxComplaint);

    /**
     * 新增投诉建议管理
     * 
     * @param tWxComplaint 投诉建议管理
     * @return 结果
     */
    public int insertTWxComplaint(TWxComplaint tWxComplaint);

    /**
     * 修改投诉建议管理
     * 
     * @param tWxComplaint 投诉建议管理
     * @return 结果
     */
    public int updateTWxComplaint(TWxComplaint tWxComplaint);

    /**
     * 删除投诉建议管理
     * 
     * @param complaintid 投诉建议管理ID
     * @return 结果
     */
    public int deleteTWxComplaintById(Long complaintid);

    /**
     * 批量删除投诉建议管理
     * 
     * @param complaintids 需要删除的数据ID
     * @return 结果
     */
    public int deleteTWxComplaintByIds(Long[] complaintids);
}
