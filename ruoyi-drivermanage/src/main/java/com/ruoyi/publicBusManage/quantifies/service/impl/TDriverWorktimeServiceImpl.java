package com.ruoyi.publicBusManage.quantifies.service.impl;

import java.util.List;

import com.ruoyi.publicBusManage.quantifies.domain.TDriverTime;
import com.ruoyi.publicBusManage.quantifies.domain.TDriverYy;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.publicBusManage.quantifies.mapper.TDriverWorktimeMapper;
import com.ruoyi.publicBusManage.quantifies.domain.TDriverWorktime;
import com.ruoyi.publicBusManage.quantifies.service.ITDriverWorktimeService;

/**
 * 司机工作时间Service业务层处理
 *
 * @author ym
 * @date 2021-08-09
 */
@Service
public class TDriverWorktimeServiceImpl implements ITDriverWorktimeService
{
    @Autowired
    private TDriverWorktimeMapper tDriverWorktimeMapper;

    /**
     * 查询司机工作时间
     *
     * @param id 司机工作时间ID
     * @return 司机工作时间
     */
    @Override
    public TDriverWorktime selectTDriverWorktimeById(Long id)
    {
        return tDriverWorktimeMapper.selectTDriverWorktimeById(id);
    }

    /**
     * 查询司机工作时间列表
     *
     * @param tDriverWorktime 司机工作时间
     * @return 司机工作时间
     */
    @Override
    public List<TDriverWorktime> selectTDriverWorktimeList(TDriverWorktime tDriverWorktime)
    {
        return tDriverWorktimeMapper.selectTDriverWorktimeList(tDriverWorktime);
    }

    /**
     * 新增司机工作时间
     *
     * @param tDriverWorktime 司机工作时间
     * @return 结果
     */
    @Override
    public int insertTDriverWorktime(TDriverWorktime tDriverWorktime)
    {
        return tDriverWorktimeMapper.insertTDriverWorktime(tDriverWorktime);
    }

    /**
     * 修改司机工作时间
     *
     * @param tDriverWorktime 司机工作时间
     * @return 结果
     */
    @Override
    public int updateTDriverWorktime(TDriverWorktime tDriverWorktime)
    {
        return tDriverWorktimeMapper.updateTDriverWorktime(tDriverWorktime);
    }

    /**
     * 批量删除司机工作时间
     *
     * @param ids 需要删除的司机工作时间ID
     * @return 结果
     */
    @Override
    public int deleteTDriverWorktimeByIds(Long[] ids)
    {
        return tDriverWorktimeMapper.deleteTDriverWorktimeByIds(ids);
    }

    /**
     * 删除司机工作时间信息
     *
     * @param id 司机工作时间ID
     * @return 结果
     */
    @Override
    public int deleteTDriverWorktimeById(Long id)
    {
        return tDriverWorktimeMapper.deleteTDriverWorktimeById(id);
    }

//    查询司机id
    @Override
    public TDriverYy selectDriverId(TDriverYy tDriverYy) {

        return tDriverWorktimeMapper.selectDriverId(tDriverYy);
    }

    @Override
    public int insertPunchInRecord(TDriverWorktime tDriverWorktime) {

        return tDriverWorktimeMapper.insertPunchInRecord(tDriverWorktime);
    }

    @Override
    public int insertOffDutyRecord(TDriverWorktime tDriverWorktime) {
        return tDriverWorktimeMapper.insertOffDutyRecord(tDriverWorktime);
    }

//    查询司机上班打卡时间
    @Override
    public TDriverTime selectDriverOnTime(TDriverTime tDriverTime) {
        return tDriverWorktimeMapper.selectDriverOnTime(tDriverTime);
    }
}
