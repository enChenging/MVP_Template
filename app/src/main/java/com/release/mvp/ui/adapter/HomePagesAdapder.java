package com.release.mvp.ui.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.release.mvp.ui.page.livePage.LivePage;
import com.release.mvp.ui.page.newsPage.NewsPage;
import com.release.mvp.ui.page.recommendPage.RecommendPage;

/**
 * @author Mr.release
 * @create 2019/3/26
 * @Describe
 */
public class HomePagesAdapder extends FragmentPagerAdapter {

    private Fragment[] fragments;

    public HomePagesAdapder(FragmentManager fm) {
        super(fm);
        fragments = new Fragment[3];
    }

    @Override
    public Fragment getItem(int position) {
        if (fragments[position] == null) {
            switch (position) {
                case 0:
                    fragments[position] = NewsPage.newInstance();
                    break;
                case 1:
                    fragments[position] = RecommendPage.newInstance();
                    break;
                case 2:
                    fragments[position] = LivePage.newInstance();
                    break;
//                case 3:
//                    fragments[position] = SettingPage.newInstance();
//                    break;
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
