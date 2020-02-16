package com.pds.access.domain.response;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.io.Serializable;
import java.util.Date;


/**
 * @Author: lijiao
 * @Date: Create in 14:09 2020/2/9
 * @version: 1.0
 */
public class UserRes implements Serializable {
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
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss",timezone="GMT+8")
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

    public Integer getUserType() {
        return userType;
    }

    public void setUserType(Integer userType) {
        this.userType = userType;
    }

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

    @Override
    public String toString() {
        return "UserRes{" +
                "id=" + id +
                ", userCode='" + userCode + '\'' +
                ", registeredTime=" + registeredTime +
                ", timesIn=" + timesIn +
                ", timesOut=" + timesOut +
                ", userType=" + userType +
                '}';
    }
}
