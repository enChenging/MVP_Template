package com.release.mvp.widget.pageTransformer;

import androidx.viewpager.widget.ViewPager;
import android.view.View;

/**
 * @author Mr.release
 * @create 2019/3/22
 * @Describe
 */
public class RotatePageTransformer implements ViewPager.PageTransformer {

    //最大旋转角度
    private static float  MAX_ROTATE= 15f;

    public void transformPage(View view, float position) {
        int pageWidth = view.getWidth();

        if (position < -1) { // [-Infinity,-1)
            // This page is way off-screen to the left.
            view.setRotation(0);

        } else if (position <= 0) { // [-1,0]
            //A页面
            //position[-1,0]
            //旋转角度【-25,0】
            float rotate = MAX_ROTATE*position;
            //旋转中心
            view.setPivotX(view.getWidth()/2);
            view.setPivotY(view.getHeight());
            view.setRotation(rotate);

        } else if (position <= 1) { // (0,1]
            //B页面
            //position[1,0]
            //旋转角度【25,0】
            float rotate = MAX_ROTATE*position;
            view.setPivotX(view.getWidth()/2);
            view.setPivotY(view.getHeight());
            view.setRotation(rotate);

        } else { // (1,+Infinity]
            // This page is way off-screen to the right.
            view.setRotation(0);
        }
    }
}
