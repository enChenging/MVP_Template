package com.release.mvp.ui.page.livePage;

import android.view.View;
import android.widget.ImageView;

import com.release.mvp.R;
import com.release.mvp.ui.base.BaseFragment;

import butterknife.BindView;

/**
 * @author Mr.release
 * @create 2019/3/22
 * @Describe
 */
public class LivePage extends BaseFragment {

    @BindView(R.id.iv_image)
    ImageView mImageView;


    public static LivePage newInstance() {
        return new LivePage();
    }

    @Override
    public int getLayoutId() {
        return R.layout.page_live;
    }

    @Override
    public void initView(View view) {

    }

    @Override
    public void initListener() {

    }

    @Override
    public void initData() {

    }
}
