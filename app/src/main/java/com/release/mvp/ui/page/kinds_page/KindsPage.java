package com.release.mvp.ui.page.kinds_page;

import com.release.mvp.R;
import com.release.mvp.presenter.page.kindsPage.KindsPagePresenter;
import com.release.mvp.presenter.page.kindsPage.KindsPageView;
import com.release.mvp.ui.base.BaseFragment;

/**
 * @author Mr.release
 * @create 2019/3/22
 * @Describe
 */
public class KindsPage extends BaseFragment<KindsPagePresenter> implements KindsPageView {

    public static KindsPage newInstance() {
        return new KindsPage();
    }

    @Override
    public int getLayoutId() {
        return R.layout.page_kinds;
    }

}
