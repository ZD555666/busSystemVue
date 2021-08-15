package com.ruoyi.wx.wxuser.service;

import java.util.List;
import com.ruoyi.wx.wxuser.domain.TWxRecord;

/**
 * 消费记录Service接口
 * 
 * @author gjw
 * @date 2021-08-15
 */
public interface ITWxRecordService 
{
    /**
     * 查询消费记录
     * 
     * @param rid 消费记录ID
     * @return 消费记录
     */
    public TWxRecord selectTWxRecordById(Long rid);

    /**
     * 查询消费记录列表
     * 
     * @param tWxRecord 消费记录
     * @return 消费记录集合
     */
    public List<TWxRecord> selectTWxRecordList(TWxRecord tWxRecord);

    /**
     * 新增消费记录
     * 
     * @param tWxRecord 消费记录
     * @return 结果
     */
    public int insertTWxRecord(TWxRecord tWxRecord);

    /**
     * 修改消费记录
     * 
     * @param tWxRecord 消费记录
     * @return 结果
     */
    public int updateTWxRecord(TWxRecord tWxRecord);

    /**
     * 批量删除消费记录
     * 
     * @param rids 需要删除的消费记录ID
     * @return 结果
     */
    public int deleteTWxRecordByIds(Long[] rids);

    /**
     * 删除消费记录信息
     * 
     * @param rid 消费记录ID
     * @return 结果
     */
    public int deleteTWxRecordById(Long rid);
}
