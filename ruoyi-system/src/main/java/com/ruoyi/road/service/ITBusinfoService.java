package com.ruoyi.road.service;

import java.util.List;
import com.ruoyi.road.domain.TBusinfo;

/**
 * 详细信息Service接口
 * 
 * @author gjw
 * @date 2021-07-22
 */
public interface ITBusinfoService 
{
    /**
     * 查询详细信息
     * 
     * @param busid 详细信息ID
     * @return 详细信息
     */
    public TBusinfo selectTBusinfoById(Long busid);

    /**
     * 查询详细信息列表
     * 
     * @param tBusinfo 详细信息
     * @return 详细信息集合
     */
    public List<TBusinfo> selectTBusinfoList(TBusinfo tBusinfo);

    /**
     * 新增详细信息
     * 
     * @param tBusinfo 详细信息
     * @return 结果
     */
    public int insertTBusinfo(TBusinfo tBusinfo);

    /**
     * 修改详细信息
     * 
     * @param tBusinfo 详细信息
     * @return 结果
     */
    public int updateTBusinfo(TBusinfo tBusinfo);

    /**
     * 批量删除详细信息
     * 
     * @param busids 需要删除的详细信息ID
     * @return 结果
     */
    public int deleteTBusinfoByIds(Long[] busids);

    /**
     * 删除详细信息信息
     * 
     * @param busid 详细信息ID
     * @return 结果
     */
    public int deleteTBusinfoById(Long busid);
}
