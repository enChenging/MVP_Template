package com.release.mvp.dagger.main_module.kinds_page;

import androidx.fragment.app.Fragment;

import com.release.mvp.dagger.base.BaseFragmentModule;
import com.release.mvp.dagger.base.Fragmentq;
import com.release.mvp.dagger.base.FragmentScope;
import com.release.mvp.presenter.page.kindsPage.KindsPageView;
import com.release.mvp.ui.page.kinds_page.KindsPage;

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
    @Fragmentq
    @FragmentScope
    abstract Fragment fragment(KindsPage kindsPage);

    @Binds
    @FragmentScope
    abstract KindsPageView kindsPageView(KindsPage kindsPage);
}
