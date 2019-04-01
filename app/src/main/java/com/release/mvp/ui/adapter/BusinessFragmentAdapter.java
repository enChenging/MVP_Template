package com.release.mvp.ui.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;

import com.release.mvp.R;
import com.release.mvp.ui.base.MyBaseAdapter;

import java.util.List;

/**
 * @author Mr.release
 * @create 2019/3/22
 * @Describe
 */
public class BusinessFragmentAdapter extends MyBaseAdapter {
    public BusinessFragmentAdapter(Context context, List<Integer> items) {
        super(context, items);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View _view = mInflater.inflate(R.layout.item_important_news2, null);
        return _view;
    }
}
