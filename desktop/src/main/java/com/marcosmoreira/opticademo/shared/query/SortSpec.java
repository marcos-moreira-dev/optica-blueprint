package com.marcosmoreira.opticademo.shared.query;

public record SortSpec(String field, SortDirection direction) {

    public static SortSpec ascending(String field) {
        return new SortSpec(field, SortDirection.ASC);
    }

    public static SortSpec descending(String field) {
        return new SortSpec(field, SortDirection.DESC);
    }
}
