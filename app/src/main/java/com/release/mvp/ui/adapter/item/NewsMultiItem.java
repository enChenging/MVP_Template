package com.release.mvp.ui.adapter.item;


import com.chad.library.adapter.base.entity.MultiItemEntity;
import com.release.mvp.bean.NewsInfoBean;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

import androidx.annotation.IntDef;

public class NewsMultiItem implements MultiItemEntity {

    public static final int ITEM_TYPE_NORMAL = 1;
    public static final int ITEM_TYPE_PHOTO_SET = 2;

    private NewsInfoBean mNewsBean;
    private int mItemType;

    public NewsMultiItem(@NewsItemType int itemType, NewsInfoBean newsBean) {
        mItemType = itemType;
        mNewsBean = newsBean;
    }

    public NewsInfoBean getNewsBean() {
        return mNewsBean;
    }


    @Override
    public int getItemType() {
        return mItemType;
    }


    @Retention(RetentionPolicy.SOURCE)
    @IntDef({ITEM_TYPE_NORMAL, ITEM_TYPE_PHOTO_SET})
    public @interface NewsItemType {}
}
