package com.release.mvp.utils;

import android.app.Activity;
import android.app.ActivityManager;
import android.content.Context;

import java.util.Stack;

/**
 * @项目名称: <br>
 * @类描述: 堆栈管理<br>
 * @创建人 <br>
 * @创建时间 <br>
 * @修改人 <br>
 * @修改时间 2016年1月6日 上午9:42:14 <br>
 */
public class AppManager {

    private static Stack<Activity> activityStack = new Stack<>();

    private AppManager() {
    }

    /**
     * 获取Activity栈数量
     */
    public static int getActivitySize() {
        return activityStack.size();
    }

    /**
     * 添加Activity到堆栈
     */
    public static void addActivity(Activity activity) {
        activityStack.push(activity);
    }

    /**
     * 获取当前Activity（堆栈中最后一个压入的）
     */
    public static Activity currentActivity() {
        return activityStack.lastElement();
    }

    /**
     * 结束当前Activity（堆栈中最后一个压入的）
     */
    public static void finishCurrentActivity() {
        Activity activity = activityStack.pop();
        activity.finish();
    }

    /**
     * 结束指定的Activity
     */
    public static void finishActivity(Activity activity) {
        if (activity != null) {
            activityStack.remove(activity);
            if (!activity.isFinishing()) {
                activity.finish();
            }
        }
    }

    /**
     * 移除指定的Activity
     */
    public static void removeActivity(Activity activity) {
        if (activity != null) {
            activityStack.remove(activity);
        }
    }

    /**
     * 结束指定类名的Activity
     */
    public static void finishActivity(Class<?> cls) {
        finishActivity(getActivity(cls));
    }

    /**
     * 获取指定类名的Activity
     */
    public static Activity getActivity(Class<?> cls) {
        for (Activity activity : activityStack) {
            if (activity.getClass().equals(cls)) {
                return activity;
            }
        }
        return null;
    }

    /**
     * 结束所有Activity
     */
    public static void finishAllActivity() {
        for (Activity activity : activityStack) {
            if (activity != null) {
                activity.finish();
            }
        }
        activityStack.clear();
    }

    /**
     * 结束除指定Activity之外的所有Activity
     */
    public static void finishAllActivity(Class<?> cls) {
        for (Activity activity : activityStack) {
            if (activity != null) {
                if (!activity.getClass().equals(cls)) {
                    activity.finish();
                }
            }
        }
    }

    /**
     * 退出应用程序
     */
    public static void appExit(Context context) {
        try {
            finishAllActivity();
            ActivityManager activityMgr = (ActivityManager) context.getSystemService(Context.ACTIVITY_SERVICE);
            if (android.os.Build.VERSION.SDK_INT < 8) {
                activityMgr.restartPackage(context.getPackageName());
            } else {
                activityMgr.killBackgroundProcesses(context.getPackageName());
            }
            System.exit(0);
//			android.os.Process.killProcess(android.os.Process.myPid());

        } catch (Exception e) {
        }
    }
}
