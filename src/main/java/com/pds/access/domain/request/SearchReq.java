package com.pds.access.domain.request;

import java.io.Serializable;

/**
 * @Author: lijiao
 * @Date: Create in 10:52 2020/2/14
 * @version: 1.0
 */
public class SearchReq implements Serializable {
    //搜索的最小值
    private int min;

    //搜索的最大值
    private int max;

    public int getMin() {
        return min;
    }

    public void setMin(int min) {
        this.min = min;
    }

    public int getMax() {
        return max;
    }

    public void setMax(int max) {
        this.max = max;
    }

    @Override
    public String toString() {
        return "SearchReq{" +
                "min=" + min +
                ", max=" + max +
                '}';
    }
}
