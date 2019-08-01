package com.release.mvp.dagger.main_module.recommend_page;

import androidx.fragment.app.Fragment;

import com.release.mvp.R;
import com.release.mvp.dagger.base.BaseFragmentModule;
import com.release.mvp.dagger.base.Fragmentq;
import com.release.mvp.dagger.base.FragmentScope;
import com.release.mvp.presenter.page.recommendPage.RecommendPageView;
import com.release.mvp.ui.adapter.RecommendAdapter;
import com.release.mvp.MainActivity;
import com.release.mvp.ui.page.recommend_page.RecommendPage;

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
    @Fragmentq
    @FragmentScope
    abstract Fragment fragment(RecommendPage recommendPage);

    @Binds
    @FragmentScope
    abstract RecommendPageView recommendPageView(RecommendPage recommendPag);

    @Provides
    @FragmentScope
    static RecommendAdapter recommendAdapter(MainActivity activity) {
        return new RecommendAdapter(R.layout.item_recommend, null);
    }
}
