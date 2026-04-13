package com.marcosmoreira.opticademo.shared.query;

import java.util.Collections;
import java.util.List;

public class PaginationHelper {

    private PaginationHelper() {
    }

    public static <T> PageResult<T> page(List<T> items, PageRequest request) {
        if (items == null || items.isEmpty()) {
            return new PageResult<>(Collections.emptyList(), request.getPageIndex(), request.getPageSize(), 0);
        }

        int totalItems = items.size();
        int pageSize = request.getPageSize();
        int pageIndex = request.getPageIndex();

        int fromIndex = pageIndex * pageSize;
        int toIndex = Math.min(fromIndex + pageSize, totalItems);

        List<T> slice;
        if (fromIndex >= totalItems) {
            slice = Collections.emptyList();
        } else {
            slice = items.subList(fromIndex, toIndex);
        }

        return new PageResult<>(slice, pageIndex, pageSize, totalItems);
    }
}
