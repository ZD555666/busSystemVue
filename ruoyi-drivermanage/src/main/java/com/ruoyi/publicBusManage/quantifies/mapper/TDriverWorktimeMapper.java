package com.ruoyi.publicBusManage.quantifies.mapper;

import java.util.List;

import com.ruoyi.publicBusManage.quantifies.domain.TDriverTime;
import com.ruoyi.publicBusManage.quantifies.domain.TDriverWorktime;
import com.ruoyi.publicBusManage.quantifies.domain.TDriverYy;
import org.apache.ibatis.annotations.Mapper;

/**
 * 司机工作时间Mapper接口
 *
 * @author ym
 * @date 2021-08-09
 */
@Mapper
public interface TDriverWorktimeMapper
{
    /**
     * 查询司机工作时间
     *
     * @param id 司机工作时间ID
     * @return 司机工作时间
     */
    public TDriverWorktime selectTDriverWorktimeById(Long id);

    /**
     * 查询司机工作时间列表
     *
     * @param tDriverWorktime 司机工作时间
     * @return 司机工作时间集合
     */
    public List<TDriverWorktime> selectTDriverWorktimeList(TDriverWorktime tDriverWorktime);

    /**
     * 新增司机工作时间
     *
     * @param tDriverWorktime 司机工作时间
     * @return 结果
     */
    public int insertTDriverWorktime(TDriverWorktime tDriverWorktime);

    /**
     * 修改司机工作时间
     *
     * @param tDriverWorktime 司机工作时间
     * @return 结果
     */
    public int updateTDriverWorktime(TDriverWorktime tDriverWorktime);

    /**
     * 删除司机工作时间
     *
     * @param id 司机工作时间ID
     * @return 结果
     */
    public int deleteTDriverWorktimeById(Long id);

    /**
     * 批量删除司机工作时间
     *
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    public int deleteTDriverWorktimeByIds(Long[] ids);

//    查询司机id
    public TDriverYy selectDriverId(TDriverYy tDriverYy);

//    插入打卡数据
    public int insertPunchInRecord(TDriverWorktime tDriverWorktime);

//    插入下班打卡数据
    public int insertOffDutyRecord(TDriverWorktime tDriverWorktime);

//    查询数据库上班打卡的时间
    public TDriverTime selectDriverOnTime(TDriverTime tDriverTime);
}
