package com.ruoyi.wx.wxuser.domain;

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

    /** 性别，0男，1女 */
    @Excel(name = "性别，0男，1女")
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

    public void setId(Long id) 
    {
        this.id = id;
    }

    public Long getId() 
    {
        return id;
    }
    public void setOpenid(String openid) 
    {
        this.openid = openid;
    }

    public String getOpenid() 
    {
        return openid;
    }
    public void setNickname(String nickname) 
    {
        this.nickname = nickname;
    }

    public String getNickname() 
    {
        return nickname;
    }
    public void setGender(Integer gender) 
    {
        this.gender = gender;
    }

    public Integer getGender() 
    {
        return gender;
    }
    public void setCity(String city) 
    {
        this.city = city;
    }

    public String getCity() 
    {
        return city;
    }
    public void setProvince(String province) 
    {
        this.province = province;
    }

    public String getProvince() 
    {
        return province;
    }
    public void setCountry(String country) 
    {
        this.country = country;
    }

    public String getCountry() 
    {
        return country;
    }
    public void setAvatarurl(String avatarurl) 
    {
        this.avatarurl = avatarurl;
    }

    public String getAvatarurl() 
    {
        return avatarurl;
    }
    public void setUnionid(String unionid) 
    {
        this.unionid = unionid;
    }

    public String getUnionid() 
    {
        return unionid;
    }
    public void setIsauth(Integer isauth) 
    {
        this.isauth = isauth;
    }

    public Integer getIsauth() 
    {
        return isauth;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("id", getId())
            .append("openid", getOpenid())
            .append("nickname", getNickname())
            .append("gender", getGender())
            .append("city", getCity())
            .append("province", getProvince())
            .append("country", getCountry())
            .append("avatarurl", getAvatarurl())
            .append("unionid", getUnionid())
            .append("isauth", getIsauth())
            .toString();
    }
}
