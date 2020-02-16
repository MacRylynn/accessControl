package com.pds.access.domain.base;

/**
 * @ClassName CommonResponse
 * @Description 公共返回参数
 * @Author: lijiao73
 * @Date: 2019/11/14 9:10
 */
public class CommonResponse<T> {
    private static final String SUCCESS = "0000";

    private static final String FAILED = "0001";

    /**
     * 返回码
     */
    private String resultCode = "0000";

    /**
     * 返回信息
     */
    private String resultMsg = "请求成功";

    /**
     * 返回数据
     */
    private T resultData;

    public CommonResponse() {
    }

    public CommonResponse(String resultCode, String resultMsg) {
        this.resultCode = resultCode;
        this.resultMsg = resultMsg;
    }

    public CommonResponse(String code, T data) {
        this.resultCode = code;
        this.resultData = data;
    }

    public static <T> CommonResponse<T> success() {
        return success(null);
    }

    public static <T> CommonResponse<T> success(T data) {
        return new CommonResponse<>(CommonResponse.SUCCESS, data);
    }

    public static <T> CommonResponse<T> failed(String msg) {
        return new CommonResponse<>(CommonResponse.FAILED, msg);
    }


    public static <T> CommonResponse<T> fail(String code, String msg) {
        return new CommonResponse<>(code, msg);
    }

    public String getResultCode() {
        return resultCode;
    }

    public void setResultCode(String resultCode) {
        this.resultCode = resultCode;
    }

    public String getResultMsg() {
        return resultMsg;
    }

    public void setResultMsg(String resultMsg) {
        this.resultMsg = resultMsg;
    }

    public T getResultData() {
        return resultData;
    }

    public void setResultData(T resultData) {
        this.resultData = resultData;
    }
}
