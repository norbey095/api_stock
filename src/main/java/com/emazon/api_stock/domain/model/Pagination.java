package com.emazon.api_stock.domain.model;

import java.util.List;

public class Pagination<T> {
    private List<T> contentList;
    Long totalElement;

    public Pagination(List<T> contentList, Long totalElement) {
        this.contentList = contentList;
        this.totalElement = totalElement;
    }

    public Pagination() {
    }

    public List<T> getContentList() {
        return contentList;
    }

    public void setContentList(List<T> contentList) {
        this.contentList = contentList;
    }

    public Long getTotalElement() {
        return totalElement;
    }

    public void setTotalElement(Long totalElement) {
        this.totalElement = totalElement;
    }
}
