package com.ruoyi.wx.wxuser.mapper;

import java.util.List;
import com.ruoyi.wx.wxuser.domain.TWxUser;

/**
 * 小程序用户Mapper接口
 *
 * @author gjw
 * @date 2021-07-24
 */
public interface TWxUserMapper
{
    /**
     * 查询小程序用户
     *
     * @param id 小程序用户ID
     * @return 小程序用户
     */
    public TWxUser selectTWxUserById(Long id);

    /**
     * 查询小程序用户列表
     *
     * @param tWxUser 小程序用户
     * @return 小程序用户集合
     */
    public List<TWxUser> selectTWxUserList(TWxUser tWxUser);

    /**
     * 新增小程序用户
     *
     * @param tWxUser 小程序用户
     * @return 结果
     */
    public int insertTWxUser(TWxUser tWxUser);

    /**
     * 修改小程序用户
     *
     * @param tWxUser 小程序用户
     * @return 结果
     */
    public int updateTWxUser(TWxUser tWxUser);

    /**
     * 删除小程序用户
     *
     * @param id 小程序用户ID
     * @return 结果
     */
    public int deleteTWxUserById(Long id);

    /**
     * 批量删除小程序用户
     *
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    public int deleteTWxUserByIds(Long[] ids);

    TWxUser queryWxUserByOpId(String openId);

    int updWxUserByOpId(TWxUser user);

    int queryMoneyByOpId(String opId);


}
