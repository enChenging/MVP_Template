package com.release.mvp.utils;

import android.app.Activity;
import android.app.ActivityManager;
import android.content.Context;

import java.util.Stack;

/**
* @项目名称: <br>
* @类描述: 堆栈管理<br>
* @创建人  <br>
* @创建时间  <br>
* @修改人  <br>
* @修改时间 2016年1月6日 上午9:42:14 <br>
 */
public class AppManager {

	private static Stack<Activity> activityStack;
	private static AppManager instance;

	private AppManager() {
	}

	/**
	 * 单一实例
	 */
	public static AppManager getAppManager() {
		if (instance == null) {
			synchronized (AppManager.class) {
				if (instance == null) {
					instance = new AppManager();
				}
			}
		}
		return instance;
	}

	/**
	 * 压栈
	 *
	 * @param activity
	 */
	public void push(Activity activity) {
		activityStack.push(activity);
	}

	/**
	 * 添加Activity到堆栈
	 */
	public void addActivity(Activity activity) {
		if (activityStack == null) {
			activityStack = new Stack<Activity>();
		}
		activityStack.add(activity);
	}

	/**
	 * 获取当前Activity（堆栈中最后一个压入的）
	 */
	public Activity currentActivity() {
		Activity activity = activityStack.lastElement();
		return activity;
	}

	/**
	 * 结束当前Activity（堆栈中最后一个压入的）
	 */
	public void finishActivity() {
		Activity activity = activityStack.lastElement();
		finishActivity(activity);
	}

	/**
	 * 结束指定的Activity
	 */
	public void finishActivity(Activity activity) {
		if (activity != null) {
			activityStack.remove(activity);
			activity.finish();
			activity = null;
		}
	}

	/**
	 * 结束指定类名的Activity
	 */
	public void finishActivity(Class<?> cls) {
		for (Activity activity : activityStack) {
			if (activity.getClass().equals(cls)) {
				finishActivity(activity);
			}
		}
	}

	/**
	 * 结束所有Activity
	 */
	public void finishAllActivity() {
		if (activityStack == null) {
			return;
		}
		for (int i = 0, size = activityStack.size(); i < size; i++) {
			if (null != activityStack.get(i)) {
				activityStack.get(i).finish();
			}
		}
		activityStack.clear();
	}

	/**
	 * 结束除了主页其他所有Activity
	 */
	public void finishOtherAllActivity() {
		for (int i = 1, size = activityStack.size(); i < size; i++) {
			if (null != activityStack.get(i)) {
				activityStack.get(i).finish();
			}
		}
	}

	public void finishOtherActivity() {
				activityStack.get(activityStack.size()-1).finish();
				activityStack.get(activityStack.size()-2).finish();
	}

	/**
	 * 移除
	 *
	 * @param activity
	 */
	public void remove(Activity activity) {
		if (activityStack.size() > 0 && activity == activityStack.peek())
			activityStack.pop();
		else
			activityStack.remove(activity);
	}

	/**
	 * 退出应用程序
	 */
	public void appExit(Context context) {
		try {
			finishAllActivity();
			ActivityManager activityMgr = (ActivityManager) context.getSystemService(Context.ACTIVITY_SERVICE);
			if( android.os.Build.VERSION.SDK_INT < 8){
				activityMgr.restartPackage(context.getPackageName());
			}else{
				activityMgr.killBackgroundProcesses(context.getPackageName());
			}
			System.exit(0);
//			android.os.Process.killProcess(android.os.Process.myPid());

		} catch (Exception e) {
		}
	}
}
