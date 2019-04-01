package com.release.mvp.ui.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import com.release.mvp.ui.page.newsPage.BusinessFragment;
import com.release.mvp.ui.page.newsPage.EntertainmentFragment;
import com.release.mvp.ui.page.newsPage.ImportantNewsFragment;
import com.release.mvp.ui.page.newsPage.TechnologyFragment;
import com.release.mvp.ui.page.newsPage.VideoFragment;

/**
 * @author Mr.release
 * @create 2019/3/22
 * @Describe
 */
public class NewsAdapter extends FragmentStatePagerAdapter {
    private Fragment[] fragments;

    public NewsAdapter(FragmentManager fm) {
        super(fm);
        fragments = new Fragment[5];
    }

    @Override
    public Fragment getItem(int position) {
        if (fragments[position] == null) {
            switch (position) {
                case 0:
                    fragments[position] = ImportantNewsFragment.newInstance();
                    break;
                case 1:
                    fragments[position] = VideoFragment.newInstance();
                    break;
                case 2:
                    fragments[position] = BusinessFragment.newInstance();
                    break;
                case 3:
                    fragments[position] = EntertainmentFragment.newInstance();
                    break;
                case 4:
                    fragments[position] = TechnologyFragment.newInstance();
                    break;
                default:
                    break;
            }
        }
        return fragments[position];
    }

    @Override
    public int getCount() {
        return fragments.length;
    }
}
