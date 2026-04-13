package com.marcosmoreira.opticademo.shared.query;

import java.util.List;

public class PageResult<T> {

    private final List<T> items;
    private final int pageIndex;
    private final int pageSize;
    private final int totalItems;
    private final int totalPages;

    public PageResult(List<T> items, int pageIndex, int pageSize, int totalItems) {
        this.items = items;
        this.pageIndex = pageIndex;
        this.pageSize = pageSize;
        this.totalItems = totalItems;
        this.totalPages = pageSize > 0 ? (int) Math.ceil((double) totalItems / pageSize) : 0;
    }

    public List<T> getItems() {
        return items;
    }

    public int getPageIndex() {
        return pageIndex;
    }

    public int getPageSize() {
        return pageSize;
    }

    public int getTotalItems() {
        return totalItems;
    }

    public int getTotalPages() {
        return totalPages;
    }

    public boolean hasNext() {
        return pageIndex + 1 < totalPages;
    }

    public boolean hasPrev() {
        return pageIndex > 0;
    }

    public boolean isEmpty() {
        return items == null || items.isEmpty();
    }
}
