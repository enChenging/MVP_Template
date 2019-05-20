package com.release.mvp.dagger.base;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

import com.release.mvp.ui.base.ViewPagerAdapter;

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
    static FragmentManager childFragmentManager(@Fragmentq Fragment fragment) {
        return fragment.getChildFragmentManager();
    }

    @Provides
    @FragmentScope
    static ViewPagerAdapter viewPagerAdapter(@ChildFragmentManager FragmentManager fragmentManager) {
        return new ViewPagerAdapter(fragmentManager);
    }
}
