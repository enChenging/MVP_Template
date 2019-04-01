package com.release.mvp.ui.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.release.mvp.R;
import com.release.mvp.ui.base.MyBaseAdapter;
import com.release.mvp.modle.ImportantNewsBean;
import com.release.mvp.utils.GlideApp;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * @author Mr.release
 * @create 2019/3/22
 * @Describe
 */
public class ImportantNewsAdapter extends MyBaseAdapter<ImportantNewsBean.DataBean> {

    public ImportantNewsAdapter(Context context, List<ImportantNewsBean.DataBean> datas) {
        super(context, datas);

    }

    @Override
    public View getView(int position, View view, ViewGroup parent) {
        ViewHolder holder;
        if (view == null) {
            view = mInflater.inflate(R.layout.item_important_news, null);
            holder = new ViewHolder(view);
            view.setTag(holder);
        } else {
            holder = (ViewHolder) view.getTag();
        }

        ImportantNewsBean.DataBean dataBean = mDatas.get(position);
        GlideApp.with(mContext).load(dataBean.getPic()).optionalCenterCrop().into(holder.iv_bg);
        holder.tv_title.setText(dataBean.getTitle());
        holder.tv_content.setText(dataBean.getFood_str());
        holder.tv_zan.setText("点赞" + dataBean.getNum() + "人");

        return view;
    }

    static class ViewHolder {
        @BindView(R.id.iv_bg)
        ImageView iv_bg;
        @BindView(R.id.tv_title)
        TextView tv_title;
        @BindView(R.id.tv_content)
        TextView tv_content;
        @BindView(R.id.tv_zan)
        TextView tv_zan;

        public ViewHolder(View view) {
            ButterKnife.bind(this, view);
        }
    }
}
