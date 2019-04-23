package com.release.mvp.presenter.page.newsPage.special;

import com.release.mvp.bean.SpecialInfoBean;
import com.release.mvp.presenter.base.BaseView;
import com.release.mvp.ui.adapter.item.SpecialItem;

import java.util.List;

/**
 * @author Mr.release
 * @create 2019/4/15
 * @Describe
 */
public interface NewsSpecialView extends BaseView {

    void loadDataView(List<SpecialItem> specialItems);

    void loadHeadView(SpecialInfoBean specialBean);
}
