package com.gamerole.common.base;

import android.app.Activity;
import android.app.ActivityManager;
import android.content.Context;

import java.util.Iterator;
import java.util.Stack;

/**
 * 应用程序Activity管理类：用于Activity管理和应用程序退出
 *
 * @author wfs
 */
public class AppManager {

    private static Stack<Activity> activityStack = new Stack();
    private static AppManager instance;

    private AppManager() {
    }

    /**
     * 单一实例
     */
    public static AppManager getAppManager() {
        if (instance == null) {
            instance = new AppManager();
        }
        return instance;
    }

    /**
     * 添加Activity到堆栈
     */
    public void addActivity(Activity activity) {
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
        for (int i = 0, size = activityStack.size(); i < size; i++) {
            if (null != activityStack.get(i)) {
                activityStack.get(i).finish();
            }
        }
        activityStack.clear();
    }

    /**
     * 结束所有Activity
     */
    public void finishAllActivity(Class<?> cls) {
        Iterator iterator = activityStack.iterator();

        while (iterator.hasNext()) {
            Activity next = (Activity) iterator.next();
            if (next.getClass().equals(cls)) {

            } else {
                if (next != null) {
                    next.finish();
                    iterator.remove();
                }
            }
        }
    }

    /**
     * 结束所有Activity
     */
    public void finishAllActivity(String cls) {
        Iterator iterator = activityStack.iterator();
        while (iterator.hasNext()) {
            Activity next = (Activity) iterator.next();
            if (!next.getClass().getName().equals(cls)) {
                next.finish();
                iterator.remove();
            }
        }
    }

    /**
     * 退出应用程序
     */
    @SuppressWarnings("deprecation")
    public void AppExit(Context context) {
        try {
            finishAllActivity();
            ActivityManager activityMgr = (ActivityManager) context.getSystemService(Context.ACTIVITY_SERVICE);
            activityMgr.restartPackage(context.getPackageName());
            System.exit(0);
        } catch (Exception e) {
        }
    }

    public void finishAllLogin() {
        for (Activity activity : activityStack) {
            if (!activity.getLocalClassName().contains("LoginActivity")) {
                activity.finish();
            }
        }
    }
    /**
     * 退出应用程序
     */

    public Stack<Activity> getAllActivities() {
        return activityStack;
    }
}