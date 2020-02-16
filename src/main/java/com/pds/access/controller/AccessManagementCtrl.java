package com.pds.access.controller;

import com.pds.access.domain.base.CommonRequest;
import com.pds.access.domain.base.CommonResponse;
import com.pds.access.domain.request.AccessReq;
import com.pds.access.domain.request.SearchReq;
import com.pds.access.domain.request.UserReq;
import com.pds.access.domain.response.AccessRes;
import com.pds.access.domain.response.UserRes;
import com.pds.access.service.AccessManagementService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

/**
 * @Author: lijiao
 * @Date: Create in 13:57 2020/2/9
 * @version: 1.0
 */
@RestController
@RequestMapping("/web/wx")
public class AccessManagementCtrl {
    private static final Logger logger = LoggerFactory.getLogger(AccessManagementCtrl.class);

    @Resource
    private AccessManagementService accessManagementService;

    @GetMapping("/index")
    public String testSystem() {
        return "系统正常";
    }

    @PostMapping("/adduser")
    public CommonResponse<String> insertUser(@RequestBody CommonRequest<UserReq> commonRequest) {
        logger.info("AccessManagementCtrl|insertUser，增加用户信息，入参为：{}", commonRequest.toString());
        CommonResponse<String> res = new CommonResponse<>();
        UserReq req = commonRequest.getRequestData();
        if (req == null) {
            logger.info("AccessManagementCtrl|insertUser，请求参数为空");
            return null;
        }
        String result = accessManagementService.insertUser(req);
        res.setResultData(result);
        return res;
    }

    @PostMapping("/inorout")
    public CommonResponse<Boolean> inOrOut(@RequestBody CommonRequest<AccessReq> commonRequest) {
        logger.info("AccessManagementCtrl|inOrOut，增加用户进出信息，入参为：{}", commonRequest.toString());
        CommonResponse<Boolean> res = new CommonResponse<>();
        AccessReq req = commonRequest.getRequestData();
        if (req == null) {
            logger.info("AccessManagementCtrl|inOrOut，请求参数为空");
            return null;
        }
        Boolean result = accessManagementService.inOrOut(req);
        if (!result){
            res.setResultMsg("该用户已被禁止出入,或者进出类型输入错误");
        }
        res.setResultData(result);
        return res;
    }

    @PostMapping("/getallrecords")
    public CommonResponse<List<UserRes>> getAllRecords() {
        logger.info("AccessManagementCtrl|getAllRecords，查询所有用户进出信息");
        CommonResponse<List<UserRes>> res = new CommonResponse<>();
        List<UserRes> result = accessManagementService.getAllRecords();
        res.setResultData(result);
        return res;
    }

    @PostMapping("/getonerecords")
    public CommonResponse<List<AccessRes>> getRecordByCode(@RequestBody CommonRequest<UserReq> commonRequest) {
        logger.info("AccessManagementCtrl|getRecordByCode，查询单家用户的进出记录，入参为：{}", commonRequest.toString());
        CommonResponse<List<AccessRes>> res = new CommonResponse<>();
        UserReq req = commonRequest.getRequestData();
        if (req == null) {
            logger.info("AccessManagementCtrl|getRecordByCode，请求参数为空");
            return null;
        }
        List<AccessRes> result = accessManagementService.getRecordByCode(req.getUserCode());
        res.setResultData(result);
        return res;
    }


    @PostMapping("/delete")
    public CommonResponse<Boolean> deleteAccessRecords(@RequestBody CommonRequest<UserReq> commonRequest) {
        logger.info("AccessManagementCtrl|deleteAccessRecords，删除住户，入参为：{}", commonRequest.toString());
        CommonResponse<Boolean> res = new CommonResponse<>();
        UserReq req = commonRequest.getRequestData();
        if (req == null) {
            logger.info("AccessManagementCtrl|deleteAccessRecords，请求参数为空");
            return null;
        }
        Boolean result = accessManagementService.deleteAccessRecords(req.getUserCode());
        res.setResultData(result);
        return res;
    }

    @PostMapping("/search")
    public CommonResponse<List<UserRes>> selectBySearch(@RequestBody CommonRequest<SearchReq> commonRequest) {
        logger.info("AccessManagementCtrl|selectBySearch，按照条件查询住户，入参为：{}", commonRequest.toString());
        CommonResponse<List<UserRes>> res = new CommonResponse<>();
        SearchReq req = commonRequest.getRequestData();
        if (req == null) {
            logger.info("AccessManagementCtrl|selectBySearch，请求参数为空");
            return null;
        }
        List<UserRes> result = accessManagementService.selectBySearch(req);
        res.setResultData(result);
        return res;
    }

    @PostMapping("/change")
    public CommonResponse<Boolean> changeUserType(@RequestBody CommonRequest<UserReq> commonRequest) {
        logger.info("AccessManagementCtrl|changeUserType，修改住户状态，入参为：{}", commonRequest.toString());
        CommonResponse<Boolean> res = new CommonResponse<>();
        UserReq req = commonRequest.getRequestData();
        if (req == null) {
            logger.info("AccessManagementCtrl|changeUserType，请求参数为空");
            return null;
        }
        Boolean result = accessManagementService.changeUserType(req.getUserCode());
        res.setResultData(result);
        return res;
    }
}
