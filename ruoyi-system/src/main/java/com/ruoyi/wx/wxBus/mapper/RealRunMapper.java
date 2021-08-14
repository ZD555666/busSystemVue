package com.ruoyi.wx.wxBus.mapper;

import com.ruoyi.wx.wxBus.domain.MyRealRun;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @version 1.0
 * @author: ZD
 * @date: 2021/8/12 21:31
 * @desc:
 */
@Mapper
public interface RealRunMapper {

    //查询实时数据
    List<MyRealRun> selectRealRun(@Param("direction")int direction,
                                  @Param("busNo") String busNo);
}
