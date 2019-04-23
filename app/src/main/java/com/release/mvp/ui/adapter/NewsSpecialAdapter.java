package com.release.mvp.ui.adapter;

import android.view.View;
import android.widget.ImageView;

import com.chad.library.adapter.base.BaseSectionQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.flyco.labelview.LabelView;
import com.release.mvp.R;
import com.release.mvp.ui.adapter.item.SpecialItem;
import com.release.mvp.ui.page.news_page.NewsDetailActivity;
import com.release.mvp.ui.page.news_page.NewsSpecialActivity;
import com.release.mvp.utils.DefIconFactory;
import com.release.mvp.utils.ImageLoader;
import com.release.mvp.utils.NewsUtils;
import com.release.mvp.utils.StringUtils;

import java.util.List;

import javax.inject.Inject;

import androidx.core.content.ContextCompat;

/**
 * @author Mr.release
 * @create 2019/4/15
 * @Describe
 */
public class NewsSpecialAdapter extends BaseSectionQuickAdapter<SpecialItem, BaseViewHolder> {


    /**
     * Same as QuickAdapter#QuickAdapter(Context,int) but with
     * some initialization data.
     *
     * @param layoutResId      The layout resource id of each item.
     * @param sectionHeadResId The section head layout id for each item
     * @param data             A new list is created out of this one to avoid mutable list
     */
    public NewsSpecialAdapter(int layoutResId, int sectionHeadResId, List<SpecialItem> data) {
        super(layoutResId, sectionHeadResId, data);
    }

    @Override
    protected void convertHead(BaseViewHolder holder, SpecialItem item) {
        holder.setText(R.id.tv_head, item.header);
    }

    @Override
    protected void convert(BaseViewHolder holder, SpecialItem item) {
        ImageView newsIcon = holder.getView(R.id.iv_icon);
        ImageLoader.loadCenterCrop(mContext, item.t.getImgsrc(), newsIcon, DefIconFactory.provideIcon());
        holder.setText(R.id.tv_title, item.t.getTitle())
                .setText(R.id.tv_source, StringUtils.clipNewsSource(item.t.getSource()))
                .setText(R.id.tv_time, item.t.getPtime());

        if (NewsUtils.isNewsSpecial(item.t.getSkipType())) {
            LabelView labelView = holder.getView(R.id.label_view);
            labelView.setVisibility(View.VISIBLE);
            labelView.setBgColor(ContextCompat.getColor(mContext, R.color.item_special_bg));
            labelView.setText("专题");
        } else if (NewsUtils.isNewsPhotoSet(item.t.getSkipType())) {
            LabelView labelView = holder.getView(R.id.label_view);
            labelView.setVisibility(View.VISIBLE);
            labelView.setBgColor(ContextCompat.getColor(mContext, R.color.item_photo_set_bg));
            labelView.setText("图集");
        } else {
            holder.setVisible(R.id.label_view, false);
        }

        holder.getConvertView().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (NewsUtils.isNewsSpecial(item.t.getSkipType())) {
                    NewsSpecialActivity.start(mContext, item.t.getSpecialID());
                } else if (NewsUtils.isNewsPhotoSet(item.t.getSkipType())) {
//                    PhotoSetActivity.launch(mContext, item.t.getPhotosetID());
                } else {
                    NewsDetailActivity.start(mContext, item.t.getPostid());
                }
            }
        });
    }
}
