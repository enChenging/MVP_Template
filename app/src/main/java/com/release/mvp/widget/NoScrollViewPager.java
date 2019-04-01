package com.release.mvp.widget;

import android.content.Context;
import android.util.AttributeSet;
import android.view.MotionEvent;

/**
 * @author Mr.release
 * @create 2019/3/22
 * @Describe
 */
public class NoScrollViewPager extends LazyViewPager {

    public NoScrollViewPager(Context context) {
        super(context);
    }

    public NoScrollViewPager(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    public boolean onTouchEvent(MotionEvent ev) {
        return false;//把触摸事件传递给父类
    }

    //事件拦截方法，确保手指的触摸事件能够传递到子控件中，假设子控件是listview就可以响应手指的上下滑动
    @Override
    public boolean onInterceptTouchEvent(MotionEvent ev) {
        return false;
    }

}
