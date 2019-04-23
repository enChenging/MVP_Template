package com.release.mvp.injector.main_module.kinds_page;

import com.release.mvp.injector.base.BaseFragmentModule;
import com.release.mvp.injector.base.Fragment;
import com.release.mvp.injector.base.FragmentScope;
import com.release.mvp.presenter.page.kindsPage.KindsPageView;
import com.release.mvp.ui.page.kinds_page.KindsPage;
import com.trello.rxlifecycle3.components.support.RxFragment;

import dagger.Binds;
import dagger.Module;

/**
 * @author Mr.release
 * @create 2019/4/22
 * @Describe
 */
@Module(includes = {BaseFragmentModule.class, KindsPagePresenterModule.class})
public abstract class KindsPageModule {

    @Binds
    @Fragment
    @FragmentScope
    abstract RxFragment fragment(KindsPage kindsPage);

    @Binds
    @FragmentScope
    abstract KindsPageView kindsPageView(KindsPage kindsPage);
}
