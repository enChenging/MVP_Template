package com.release.mvp.ui.page.newsPage;

import android.view.View;
import android.widget.ListView;

import com.release.mvp.R;
import com.release.mvp.ui.adapter.BusinessFragmentAdapter;
import com.release.mvp.ui.base.BaseFragment;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

/**
 * 科技
 *
 * @author Mr.release
 * @create 2019/3/22
 * @Describe
 */
public class TechnologyFragment extends BaseFragment {
    @BindView(R.id.lv_list_news)
    ListView mLvListNews;

    private List<Integer> items = new ArrayList<>();


    public static TechnologyFragment newInstance() {
        return new TechnologyFragment();
    }

    @Override
    public int getLayoutId() {
        return R.layout.fragment_important_news;
    }

    @Override
    public void initView(View view) {
        for (int i = 0; i < 13; i++) {
            items.add(i);
        }
        BusinessFragmentAdapter adapter = new BusinessFragmentAdapter(getContext(), items);
        mLvListNews.setAdapter(adapter);
    }

    @Override
    public void initListener() {

    }

    @Override
    public void initData() {

    }
}
