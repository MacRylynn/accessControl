package com.pds.access.entity;

import java.util.Date;

public class PdsUserManagementEntity {
    /**
     * 主键
     */
    private Integer id;

    /**
     * 用户号（门牌号+当前毫秒数）
     */
    private String userCode;

    /**
     * 注册时间
     */
    private Date registeredTime;

    /**
     * 进小区次数
     */
    private Integer timesIn;

    /**
     * 出小区次数
     */
    private Integer timesOut;

    /**
     * 用户类型  (0 表示正常 1表示禁止)
     */
    private Integer userType;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getUserCode() {
        return userCode;
    }

    public void setUserCode(String userCode) {
        this.userCode = userCode == null ? null : userCode.trim();
    }

    public Date getRegisteredTime() {
        return registeredTime;
    }

    public void setRegisteredTime(Date registeredTime) {
        this.registeredTime = registeredTime;
    }

    public Integer getTimesIn() {
        return timesIn;
    }

    public void setTimesIn(Integer timesIn) {
        this.timesIn = timesIn;
    }

    public Integer getTimesOut() {
        return timesOut;
    }

    public void setTimesOut(Integer timesOut) {
        this.timesOut = timesOut;
    }

    public Integer getUserType() {
        return userType;
    }

    public void setUserType(Integer userType) {
        this.userType = userType;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", id=").append(id);
        sb.append(", userCode=").append(userCode);
        sb.append(", registeredTime=").append(registeredTime);
        sb.append(", timesIn=").append(timesIn);
        sb.append(", timesOut=").append(timesOut);
        sb.append(", userType=").append(userType);
        sb.append("]");
        return sb.toString();
    }
}