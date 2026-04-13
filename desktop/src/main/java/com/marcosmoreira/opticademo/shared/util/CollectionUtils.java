package com.marcosmoreira.opticademo.shared.util;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Utility class for common collection operations.
 */
public final class CollectionUtils {

    private CollectionUtils() {
        // utility class
    }

    /**
     * Returns the size of the list, or 0 if the list is null.
     */
    public static int safeSize(List<?> list) {
        return list == null ? 0 : list.size();
    }

    /**
     * Returns the size of the array, or 0 if the array is null.
     */
    public static int safeSize(Object[] array) {
        return array == null ? 0 : array.length;
    }

    /**
     * Returns the first element of the list, or null if the list is null or empty.
     */
    public static <T> T firstOrNull(List<T> list) {
        if (list == null || list.isEmpty()) {
            return null;
        }
        return list.get(0);
    }

    /**
     * Returns the first element of the array, or null if the array is null or empty.
     */
    public static <T> T firstOrNull(T[] array) {
        if (array == null || array.length == 0) {
            return null;
        }
        return array[0];
    }

    /**
     * Returns a new list containing only the non-null elements from the original list.
     */
    public static <T> List<T> filterNonNull(List<T> list) {
        if (list == null) {
            return Collections.emptyList();
        }
        List<T> result = new ArrayList<>();
        for (T item : list) {
            if (item != null) {
                result.add(item);
            }
        }
        return result;
    }

    /**
     * Returns a new list containing only the non-null elements from the array.
     */
    public static <T> List<T> filterNonNull(T[] array) {
        if (array == null) {
            return Collections.emptyList();
        }
        List<T> result = new ArrayList<>();
        for (T item : array) {
            if (item != null) {
                result.add(item);
            }
        }
        return result;
    }

    /**
     * Creates a list from varargs elements (skips nulls).
     */
    @SafeVarargs
    public static <T> List<T> toList(T... elements) {
        if (elements == null) {
            return Collections.emptyList();
        }
        List<T> result = new ArrayList<>(elements.length);
        for (T element : elements) {
            if (element != null) {
                result.add(element);
            }
        }
        return result;
    }

    /**
     * Returns an unmodifiable empty list of the given type.
     */
    public static <T> List<T> emptyList() {
        return Collections.emptyList();
    }

    /**
     * Returns true if the list is null or empty.
     */
    public static boolean isEmpty(List<?> list) {
        return list == null || list.isEmpty();
    }

    /**
     * Returns true if the list is not null and contains at least one element.
     */
    public static boolean isNotEmpty(List<?> list) {
        return !isEmpty(list);
    }
}
