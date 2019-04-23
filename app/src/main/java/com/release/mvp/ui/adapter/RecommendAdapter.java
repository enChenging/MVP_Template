package com.release.mvp.ui.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.release.mvp.R;
import com.release.mvp.ui.base.IBaseAdapter;
import com.release.mvp.bean.RecommendPageBean;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * @author Mr.release
 * @create 2019/3/22
 * @Describe
 */
public class RecommendAdapter extends IBaseAdapter<RecommendPageBean.DataBean> {

    public RecommendAdapter(Context context) {
        super(context);
    }

    @Override
    public View getView(int position, View view, ViewGroup parent) {
        ViewHolder holder;
        if (view == null) {
            view = mInflater.inflate(R.layout.item_recommend, null);
            holder = new ViewHolder(view);
            view.setTag(holder);
        } else {
            holder = (ViewHolder) view.getTag();
        }

        RecommendPageBean.DataBean dataBean = mDatas.get(position);
        Glide.with(mContext).load(dataBean.getImage()).optionalCenterCrop().into(holder.iv_bg);
        holder.tv_title.setText(dataBean.getTitle());

        return view;
    }

    static class ViewHolder {
        @BindView(R.id.iv_bg)
        ImageView iv_bg;
        @BindView(R.id.tv_title)
        TextView tv_title;

        public ViewHolder(View view) {
            ButterKnife.bind(this, view);
        }
    }
}
