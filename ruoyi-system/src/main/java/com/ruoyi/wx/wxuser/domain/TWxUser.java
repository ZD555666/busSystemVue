package com.ruoyi.wx.wxuser.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;

/**
 * 小程序用户对象 t_wx_user
 * 
 * @author gjw
 * @date 2021-07-24
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class TWxUser extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** $column.columnComment */
    private Long id;

    /** 标识 */
    @Excel(name = "标识")
    private String openid;

    /** 昵称 */
    @Excel(name = "昵称")
    private String nickname;

    /** 性别，1男，0女 */
    @Excel(name = "性别，1男，0女")
    private Integer gender;

    /** 城市 */
    @Excel(name = "城市")
    private String city;

    /** 省份 */
    @Excel(name = "省份")
    private String province;

    /** 国家 */
    @Excel(name = "国家")
    private String country;

    /** 头像 */
    @Excel(name = "头像")
    private String avatarurl;

    /** 开放平台标识符 */
    @Excel(name = "开放平台标识符")
    private String unionid;

    /** 是否授权 */
    @Excel(name = "是否授权")
    private Integer isauth;

    @Excel(name = "手机号")
    private String phone;
}
