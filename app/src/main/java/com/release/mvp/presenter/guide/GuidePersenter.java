package com.release.mvp.presenter.guide;

import android.content.Context;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.release.mvp.R;
import com.release.mvp.presenter.base.BasePresenter;
import com.release.mvp.utils.DensityUtil;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

/**
 * @author Mr.release
 * @create 2019/3/28
 * @Describe
 */
public class GuidePersenter extends BasePresenter<GuideView> {


    public List<ImageView> imageList = new ArrayList<>();
    int[] images = {R.mipmap.guide_1, R.mipmap.guide_2, R.mipmap.guide_3};

    @Inject
    protected GuidePersenter(GuideView view) {
        super(view);
    }

    public void imageViews(Context context, LinearLayout mDotGroup) {
        for (int i = 0; i < images.length; i++) {
            ImageView iv = new ImageView(context);
            iv.setBackgroundResource(images[i]);
            imageList.add(iv);

            View view = new View(context);
            LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(
                    DensityUtil.dip2px(context, 6), DensityUtil.dip2px(context, 6));
            if (i != 0) {
                params.leftMargin = DensityUtil.dip2px(context, 10);
            }
            view.setBackgroundResource(R.drawable.ic_dots_blue);
            view.setLayoutParams(params);
            mDotGroup.addView(view);
        }
    }
}
