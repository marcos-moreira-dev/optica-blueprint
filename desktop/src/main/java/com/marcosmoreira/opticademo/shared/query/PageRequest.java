package com.marcosmoreira.opticademo.shared.query;

public class PageRequest {

    public static final int DEFAULT_PAGE_SIZE = 20;

    private final int pageIndex;
    private final int pageSize;
    private final String sortField;
    private final SortDirection sortDirection;

    public PageRequest(int pageIndex, int pageSize) {
        this(pageIndex, pageSize, null, null);
    }

    public PageRequest(int pageIndex, int pageSize, String sortField, SortDirection sortDirection) {
        if (pageIndex < 0) throw new IllegalArgumentException("pageIndex must be >= 0");
        if (pageSize < 1) throw new IllegalArgumentException("pageSize must be >= 1");
        this.pageIndex = pageIndex;
        this.pageSize = pageSize;
        this.sortField = sortField;
        this.sortDirection = sortDirection;
    }

    public static PageRequest of(int pageIndex, int pageSize) {
        return new PageRequest(pageIndex, pageSize);
    }

    public static PageRequest of(int pageIndex) {
        return new PageRequest(pageIndex, DEFAULT_PAGE_SIZE);
    }

    public PageRequest withSort(String sortField, SortDirection direction) {
        return new PageRequest(pageIndex, pageSize, sortField, direction);
    }

    public PageRequest withAscSort(String sortField) {
        return withSort(sortField, SortDirection.ASC);
    }

    public PageRequest withDescSort(String sortField) {
        return withSort(sortField, SortDirection.DESC);
    }

    public int getPageIndex() {
        return pageIndex;
    }

    public int getPageSize() {
        return pageSize;
    }

    public String getSortField() {
        return sortField;
    }

    public SortDirection getSortDirection() {
        return sortDirection;
    }

    public boolean hasSort() {
        return sortField != null && sortDirection != null;
    }
}
