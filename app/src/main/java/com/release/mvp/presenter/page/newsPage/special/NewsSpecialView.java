package com.release.mvp.presenter.page.newsPage.special;

import com.release.mvp.bean.SpecialInfoBean;
import com.release.mvp.presenter.BaseView;
import com.release.mvp.ui.adapter.item.SpecialItem;

import java.util.List;

/**
 * @author Mr.release
 * @create 2019/4/15
 * @Describe
 */
public interface NewsSpecialView extends BaseView {

    void loadData(List<SpecialItem> specialItems);

    void loadHead(SpecialInfoBean specialBean);
}
