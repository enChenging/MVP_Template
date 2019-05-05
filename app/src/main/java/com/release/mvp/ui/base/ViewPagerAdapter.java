package com.release.mvp.ui.base;


import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentStatePagerAdapter;
import androidx.viewpager.widget.PagerAdapter;

/**
 * Created by long on 2016/6/2.
 * ViewPager适配器
 */
public class ViewPagerAdapter extends FragmentStatePagerAdapter {

    List<String> mTitles;
    List<Fragment> fragments;

    public ViewPagerAdapter(FragmentManager fm) {
        super(fm);
        fragments = new ArrayList<>();
        mTitles = new ArrayList<>();
    }

    @Override
    public Fragment getItem(int position) {
        return fragments.get(position);
    }

    @Override
    public int getCount() {
        return fragments.size();
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return mTitles.get(position);
    }

    @Override
    public int getItemPosition(Object object) {
        return PagerAdapter.POSITION_NONE;
    }

    public void setItems(List<Fragment> fragments, List<String> mTitles) {
        this.fragments = fragments;
        this.mTitles = mTitles;
        notifyDataSetChanged();
    }

    public void setItems(List<Fragment> fragments, String[] mTitles) {
        this.fragments = fragments;
        this.mTitles = Arrays.asList(mTitles);
        notifyDataSetChanged();
    }

    public void addItem(Fragment fragment, String title) {
        fragments.add(fragment);
        mTitles.add(title);
        notifyDataSetChanged();
    }

    public void delItem(int position) {
        mTitles.remove(position);
        fragments.remove(position);
        notifyDataSetChanged();
    }

    public int delItem(String title) {
        int index = mTitles.indexOf(title);
        if (index != -1) {
            delItem(index);
        }
        return index;
    }

    public void swapItems(int fromPos, int toPos) {
        Collections.swap(mTitles, fromPos, toPos);
        Collections.swap(fragments, fromPos, toPos);
        notifyDataSetChanged();
    }

    public void modifyTitle(int position, String title) {
        mTitles.set(position, title);
        notifyDataSetChanged();
    }
}
