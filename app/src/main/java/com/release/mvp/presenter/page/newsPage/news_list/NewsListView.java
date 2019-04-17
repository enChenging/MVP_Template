package com.release.mvp.presenter.page.newsPage.news_list;

import com.release.mvp.bean.NewsInfoBean;
import com.release.mvp.presenter.LoadDataView;
import com.release.mvp.ui.adapter.item.NewsMultiItem;

import java.util.List;

/**
 * @author Mr.release
 * @create 2019/3/29
 * @Describe
 */
public interface NewsListView extends LoadDataView<List<NewsMultiItem>> {

    void loadAdData(NewsInfoBean newsBean);
}
