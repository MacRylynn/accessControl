package com.pds.access.domain.response;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.io.Serializable;
import java.util.Date;


/**
 * @Author: lijiao
 * @Date: Create in 14:09 2020/2/9
 * @version: 1.0
 */
public class AccessRes implements Serializable {
    /**
     * 主键
     */
    private Integer id;

    /**
     * 用户号（门牌号+当前毫秒数）
     */
    private String userCode;

    /**
     * 进出类型
     */
    private String accessType;

    /**
     * 进出时间
     */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss",timezone="GMT+8")
    private Date accessTime;

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

    public String getAccessType() {
        return accessType;
    }

    public void setAccessType(String accessType) {
        this.accessType = accessType == null ? null : accessType.trim();
    }

    public Date getAccessTime() {
        return accessTime;
    }

    public void setAccessTime(Date accessTime) {
        this.accessTime = accessTime;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", id=").append(id);
        sb.append(", userCode=").append(userCode);
        sb.append(", accessType=").append(accessType);
        sb.append(", accessTime=").append(accessTime);
        sb.append("]");
        return sb.toString();
    }
}
