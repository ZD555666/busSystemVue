package com.ruoyi.wx.wxuser.service.impl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.wx.wxuser.mapper.TWxUserMapper;
import com.ruoyi.wx.wxuser.domain.TWxUser;
import com.ruoyi.wx.wxuser.service.ITWxUserService;

/**
 * 小程序用户Service业务层处理
 * 
 * @author gjw
 * @date 2021-07-24
 */
@Service
public class TWxUserServiceImpl implements ITWxUserService 
{
    @Autowired
    private TWxUserMapper tWxUserMapper;

    /**
     * 查询小程序用户
     * 
     * @param id 小程序用户ID
     * @return 小程序用户
     */
    @Override
    public TWxUser selectTWxUserById(Long id)
    {
        return tWxUserMapper.selectTWxUserById(id);
    }

    /**
     * 查询小程序用户列表
     * 
     * @param tWxUser 小程序用户
     * @return 小程序用户
     */
    @Override
    public List<TWxUser> selectTWxUserList(TWxUser tWxUser)
    {
        return tWxUserMapper.selectTWxUserList(tWxUser);
    }

    /**
     * 新增小程序用户
     * 
     * @param tWxUser 小程序用户
     * @return 结果
     */
    @Override
    public int insertTWxUser(TWxUser tWxUser)
    {
        return tWxUserMapper.insertTWxUser(tWxUser);
    }

    /**
     * 修改小程序用户
     * 
     * @param tWxUser 小程序用户
     * @return 结果
     */
    @Override
    public int updateTWxUser(TWxUser tWxUser)
    {
        return tWxUserMapper.updateTWxUser(tWxUser);
    }

    /**
     * 批量删除小程序用户
     * 
     * @param ids 需要删除的小程序用户ID
     * @return 结果
     */
    @Override
    public int deleteTWxUserByIds(Long[] ids)
    {
        return tWxUserMapper.deleteTWxUserByIds(ids);
    }

    /**
     * 删除小程序用户信息
     * 
     * @param id 小程序用户ID
     * @return 结果
     */
    @Override
    public int deleteTWxUserById(Long id)
    {
        return tWxUserMapper.deleteTWxUserById(id);
    }
}
