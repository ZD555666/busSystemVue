package com.ruoyi.publicBusManage.departureConfirm.mapper;

import java.util.List;
import com.ruoyi.publicBusManage.departureConfirm.domain.TDepartureConfirm;
import com.ruoyi.publicBusManage.departureConfirm.domain.TDriverym;
import com.ruoyi.publicBusManage.departureConfirm.domain.TScheduleym;
import org.apache.ibatis.annotations.Mapper;

/**
 * 公交车信息Mapper接口
 *
 * @author YM
 * @date 2021-08-10
 */
@Mapper
public interface TDepartureConfirmMapper
{
    /**
     * 查询公交车信息
     *
     * @param busid 公交车信息ID
     * @return 公交车信息
     */
    public TDepartureConfirm selectTDepartureConfirmById(Long busid);

    /**
     * 查询公交车信息列表
     *
     * @param tDepartureConfirm 公交车信息
     * @return 公交车信息集合
     */
    public List<TDepartureConfirm> selectTDepartureConfirmList(TDepartureConfirm tDepartureConfirm);

    /**
     * 新增公交车信息
     *
     * @param tDepartureConfirm 公交车信息
     * @return 结果
     */
    public int insertTDepartureConfirm(TDepartureConfirm tDepartureConfirm);

    /**
     * 修改公交车信息
     *
     * @param tDepartureConfirm 公交车信息
     * @return 结果
     */
    public int updateTDepartureConfirm(TDepartureConfirm tDepartureConfirm);

    /**
     * 删除公交车信息
     *
     * @param busid 公交车信息ID
     * @return 结果
     */
    public int deleteTDepartureConfirmById(Long busid);

    /**
     * 批量删除公交车信息
     *
     * @param busids 需要删除的数据ID
     * @return 结果
     */
    public int deleteTDepartureConfirmByIds(Long[] busids);

//    新增方法，确认发车
    public int addGoBusConfirm(TDepartureConfirm tDepartureConfirm);

//    改变schedule 表的车辆状态
    public int updateBusScheduleState(TScheduleym tDepartureConfirm);

//    查询司机是否存在
    public List<TDriverym> selectDriverInfoList(TDriverym tDriverym);

}
