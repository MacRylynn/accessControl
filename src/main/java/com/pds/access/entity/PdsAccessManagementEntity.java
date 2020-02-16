package com.pds.access.entity;

import java.util.Date;

public class PdsAccessManagementEntity {
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