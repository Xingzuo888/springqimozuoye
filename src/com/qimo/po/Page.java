package com.qimo.po;

public class Page {
    private Integer count;//从多少开始查询
    private Integer size;//一页有多少条数据

    public Integer getCount() {
        return count;
    }

    public void setCount(Integer count) {
        this.count = count;
    }

    public Integer getSize() {
        return size;
    }

    public void setSize(Integer size) {
        this.size = size;
    }
}
