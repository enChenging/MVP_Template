package com.release.mvp.presenter.page.recommendPage;

import com.release.mvp.bean.NewsInfoBean;
import com.release.mvp.presenter.LoadDataView;

/**
 * @author Mr.release
 * @create 2019/4/1
 * @Describe
 */
public interface RecommendPageView extends LoadDataView {

    void loadAdData(NewsInfoBean newsBean);
    
}
