package com.release.mvp.injector.main_module.recommend_page;

import com.release.mvp.R;
import com.release.mvp.injector.base.BaseFragmentModule;
import com.release.mvp.injector.base.Fragment;
import com.release.mvp.injector.base.FragmentScope;
import com.release.mvp.presenter.page.recommendPage.RecommendPageView;
import com.release.mvp.ui.adapter.RecommendAdapter;
import com.release.mvp.ui.home.MainActivity;
import com.release.mvp.ui.page.recommend_page.RecommendPage;
import com.trello.rxlifecycle3.components.support.RxFragment;

import dagger.Binds;
import dagger.Module;
import dagger.Provides;

/**
 * @author Mr.release
 * @create 2019/4/22
 * @Describe
 */
@Module(includes = {BaseFragmentModule.class, RecommendPagePresenterModule.class})
public abstract class RecommendPageModule {

    @Binds
    @Fragment
    @FragmentScope
    abstract RxFragment fragment(RecommendPage recommendPage);

    @Binds
    @FragmentScope
    abstract RecommendPageView recommendPageView(RecommendPage recommendPag);

    @Provides
    @FragmentScope
    static RecommendAdapter recommendAdapter(MainActivity activity) {
        return new RecommendAdapter(R.layout.item_recommend, null);
    }
}
