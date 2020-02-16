package com.pds.access.service;

import com.pds.access.domain.request.AccessReq;
import com.pds.access.domain.request.SearchReq;
import com.pds.access.domain.request.UserReq;
import com.pds.access.domain.response.AccessRes;
import com.pds.access.domain.response.UserRes;

import java.util.List;

/**
 * @Author: lijiao
 * @Date: Create in 13:58 2020/2/9
 * @version: 1.0
 */
public interface AccessManagementService {

    /**
     * 添加用户，会返回一个userCode，存在二维码中
     *
     * @param req
     * @return
     */
    String insertUser(UserReq req);

    /**
     * 添加进出记录
     *
     * @param req
     * @return
     */
    Boolean inOrOut(AccessReq req);

    /**
     * 得到所有进出记录数据
     *
     * @return
     */
    List<UserRes> getAllRecords();

    /**
     * 根据用户号获取当前用户的进出记录
     *
     * @param userCode
     * @return
     */
    List<AccessRes> getRecordByCode(String userCode);

    /**
     * 删除此住户信息和此住户信息下的所有进出记录
     * @param userCode
     * @return
     */
    Boolean deleteAccessRecords(String userCode);

    /**
     * 按照前端的进出条件搜索住户
     * @param req
     * @return
     */
    List<UserRes> selectBySearch(SearchReq req);

    /**
     * 更改住户状态
     * @param userCode
     * @return
     */
    Boolean changeUserType(String userCode);

}
