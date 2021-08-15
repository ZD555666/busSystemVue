package com.ruoyi.wx.wxuser.service.impl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.wx.wxuser.mapper.TWxRecordMapper;
import com.ruoyi.wx.wxuser.domain.TWxRecord;
import com.ruoyi.wx.wxuser.service.ITWxRecordService;

/**
 * 消费记录Service业务层处理
 * 
 * @author gjw
 * @date 2021-08-15
 */
@Service
public class TWxRecordServiceImpl implements ITWxRecordService 
{
    @Autowired
    private TWxRecordMapper tWxRecordMapper;

    /**
     * 查询消费记录
     * 
     * @param rid 消费记录ID
     * @return 消费记录
     */
    @Override
    public TWxRecord selectTWxRecordById(Long rid)
    {
        return tWxRecordMapper.selectTWxRecordById(rid);
    }

    /**
     * 查询消费记录列表
     * 
     * @param tWxRecord 消费记录
     * @return 消费记录
     */
    @Override
    public List<TWxRecord> selectTWxRecordList(TWxRecord tWxRecord)
    {
        return tWxRecordMapper.selectTWxRecordList(tWxRecord);
    }

    /**
     * 新增消费记录
     * 
     * @param tWxRecord 消费记录
     * @return 结果
     */
    @Override
    public int insertTWxRecord(TWxRecord tWxRecord)
    {
        return tWxRecordMapper.insertTWxRecord(tWxRecord);
    }

    /**
     * 修改消费记录
     * 
     * @param tWxRecord 消费记录
     * @return 结果
     */
    @Override
    public int updateTWxRecord(TWxRecord tWxRecord)
    {
        return tWxRecordMapper.updateTWxRecord(tWxRecord);
    }

    /**
     * 批量删除消费记录
     * 
     * @param rids 需要删除的消费记录ID
     * @return 结果
     */
    @Override
    public int deleteTWxRecordByIds(Long[] rids)
    {
        return tWxRecordMapper.deleteTWxRecordByIds(rids);
    }

    /**
     * 删除消费记录信息
     * 
     * @param rid 消费记录ID
     * @return 结果
     */
    @Override
    public int deleteTWxRecordById(Long rid)
    {
        return tWxRecordMapper.deleteTWxRecordById(rid);
    }
}
