package net.qiujuer.powerback.ankey;

import android.app.Activity;

import net.qiujuer.powerback.ankey.resource.R;

import java.util.ArrayList;
import java.util.List;

import uk.co.chrisjenx.calligraphy.CalligraphyConfig;

/**
 * Created by qiujuer
 * on 15/10/21.
 */
public class SuperApplication extends android.app.Application {
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

    public static void addActivity(Activity activity) {
        SuperApplication application = (SuperApplication) activity.getApplication();
        application.add(activity);
    }

    public static void removeActivity(Activity activity) {
        SuperApplication application = (SuperApplication) activity.getApplication();
        application.remove(activity);
    }
}
