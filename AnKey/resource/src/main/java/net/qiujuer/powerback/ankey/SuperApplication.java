package net.qiujuer.powerback.ankey;

import android.app.Activity;
import android.util.Log;

import net.qiujuer.powerback.ankey.resource.R;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

import uk.co.chrisjenx.calligraphy.CalligraphyConfig;

/**
 * Created by qiujuer
 * on 15/10/21.
 */
public class SuperApplication extends android.app.Application {
    private AtomicInteger mForegroundCount = new AtomicInteger();
    private List<Activity> mActivities = new ArrayList<Activity>();

    @Override
    public void onCreate() {
        super.onCreate();
        CalligraphyConfig.initDefault(new CalligraphyConfig.Builder()
                .setDefaultFontPath("fonts/FZLanTingHeiS-L-GB-Regular.TTF")
                .setFontAttrId(R.attr.fontPath)
                .build()
        );
    }

    public void add(Activity activity) {
        mActivities.add(activity);
    }

    public void remove(Activity activity) {
        mActivities.remove(activity);
    }

    public void exit() {
        for (Activity activity : mActivities) {
            if (!activity.isFinishing())
                activity.finish();
        }
    }

    public void clearKey() {

    }

    public void cancelClearKey() {

    }

    public static void addActivity(Activity activity) {
        SuperApplication application = (SuperApplication) activity.getApplication();
        application.add(activity);
        SuperApplication.log(activity.getClass().getName() + "-onCreate");
    }

    public static void removeActivity(Activity activity) {
        SuperApplication application = (SuperApplication) activity.getApplication();
        application.remove(activity);
        SuperApplication.log(activity.getClass().getName() + "-onDestroy");
    }

    public static void onStart(Activity activity) {
        SuperApplication application = (SuperApplication) activity.getApplication();
        application.cancelClearKey();
        int count = application.mForegroundCount.incrementAndGet();
        log(activity.getClass().getName() + "-onStart:" + count);
    }

    public static void onStop(Activity activity) {
        SuperApplication application = (SuperApplication) activity.getApplication();
        int count = application.mForegroundCount.decrementAndGet();
        log(activity.getClass().getName() + "-onStop:" + count);
        if (count == 0) {
            application.clearKey();
        }
    }

    public static void onPause(Activity activity) {
        log(activity.getClass().getName() + "-onPause");
    }

    public static void onRestart(Activity activity) {
        log(activity.getClass().getName() + "-onRestart");
    }

    public static void onResume(Activity activity) {
        log(activity.getClass().getName() + "-onResume");
    }

    public static void log(String str) {
        Log.e("ACTIVITY-STATUS", str);
    }

}
