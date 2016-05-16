/*
 * Copyright (C) 2015-2016 Qiujuer-PowerBack <qiujuer@live.cn>
 * WebSite https://github.com/PowerBack/AnKey
 * Author Qiujuer
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *         http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package net.qiujuer.powerback.common.app;

import android.app.Activity;
import android.util.Log;

import net.qiujuer.powerback.common.R;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

import uk.co.chrisjenx.calligraphy.CalligraphyConfig;

/**
 * Created by qiujuer
 * on 16/5/16.
 */
public abstract class Application extends android.app.Application {
    private AtomicInteger mForegroundCount = new AtomicInteger();
    protected List<Activity> mActivities = new ArrayList<Activity>();

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

    public abstract void clearKey();

    public abstract void cancelClearKey();

    public static void addActivity(Activity activity) {
        Application application = (Application) activity.getApplication();
        application.add(activity);
        Application.log(activity.getClass().getName() + "-onCreate");
    }

    public static void removeActivity(Activity activity) {
        Application application = (Application) activity.getApplication();
        application.remove(activity);
        Application.log(activity.getClass().getName() + "-onDestroy");
        if (application.mActivities.size() == 0)
            application.cancelClearKey();
    }

    public static void onStart(Activity activity) {
        Application application = (Application) activity.getApplication();
        application.cancelClearKey();
        int count = application.mForegroundCount.incrementAndGet();
        log(activity.getClass().getName() + "-onStart:" + count);
    }

    public static void onStop(Activity activity) {
        Application application = (Application) activity.getApplication();
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

    protected static void log(String str) {
        Log.d("ACTIVITY-STATUS", str);
    }
}
