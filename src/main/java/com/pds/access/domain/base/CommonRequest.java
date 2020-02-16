package com.pds.access.domain.base;

/**
 * @ClassName CommonRequest
 * @Description 公共请求参数
 * @Author: lijiao73
 * @Date: 2019/11/14 9:10
 */
public class CommonRequest<T> {
    private T requestData;

    public T getRequestData() {
        return requestData;
    }

    public void setRequestData(T requestData) {
        this.requestData = requestData;
    }

    @Override
    public String toString() {
        return "CommonRequest{" +
                "requestData=" + requestData +
                '}';
    }
}
