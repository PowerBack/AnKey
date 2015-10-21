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

    public void addActivity(Activity activity) {
        mActivities.add(activity);
    }

    public void removeActivity(Activity activity) {
        mActivities.remove(activity);
    }

    public void exit() {
        for (Activity activity : mActivities) {
            if (!activity.isFinishing())
                activity.finish();
        }
    }
}
