package com.release.mvp.injector.base;

import com.release.mvp.ui.base.ViewPagerAdapter;
import com.trello.rxlifecycle3.components.support.RxFragment;

import androidx.fragment.app.FragmentManager;
import dagger.Module;
import dagger.Provides;

/**
 * @author Mr.release
 * @create 2019/4/19
 * @Describe
 */
@Module
public abstract class BaseFragmentModule {

    @Provides
    @FragmentScope
    @ChildFragmentManager
    static FragmentManager childFragmentManager(@Fragment RxFragment fragment) {
        return fragment.getChildFragmentManager();
    }

    @Provides
    @FragmentScope
    static ViewPagerAdapter viewPagerAdapter(@ChildFragmentManager FragmentManager fragmentManager) {
        return new ViewPagerAdapter(fragmentManager);
    }
}
