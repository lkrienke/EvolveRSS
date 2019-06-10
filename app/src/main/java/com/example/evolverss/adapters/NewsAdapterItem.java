package com.example.evolverss.adapters;

import androidx.annotation.IntDef;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

public class NewsAdapterItem<T> {
    public static final int NEWS_ITEM = 0;
    public static final int SECTION_HEADER = 1;
    private T object;
    @ViewType
    private int viewType;

    public NewsAdapterItem(T object, int viewType) {
        this.object = object;
        this.viewType = viewType;
    }

    public T getObject() {
        return object;
    }

    public void setObject(T object) {
        this.object = object;
    }

    public int getViewType() {
        return viewType;
    }

    public void setViewType(int viewType) {
        this.viewType = viewType;
    }

    @Retention(RetentionPolicy.SOURCE)
    @IntDef({NEWS_ITEM, SECTION_HEADER})
    @interface ViewType {
    }
}
