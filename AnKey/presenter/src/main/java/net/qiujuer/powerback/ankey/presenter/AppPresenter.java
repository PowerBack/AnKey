package net.qiujuer.powerback.ankey.presenter;

import android.app.Application;
import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

import com.android.volley.RequestQueue;

import net.qiujuer.powerback.ankey.model.Model;
import net.qiujuer.powerback.ankey.model.xml.SettingModel;

/**
 * Created by qiujuer
 * on 15/10/19.
 */
public class AppPresenter {
    private static final Object SERVICE_LOCK = new Object();


    public static void setApplication(Application application) {
        Model.setApplication(application);
    }

    public static void dispose() {
        Model.dispose();
    }

    public static RequestQueue getRequestQueue() {
        return Model.getRequestQueue();
    }

    public static boolean isLogin() {
        return false;
    }

    public static boolean isFirstUse() {
        SettingModel model = new SettingModel();
        return model.isFirstUse();
    }

    public static boolean isHaveNetwork() {
        final Context context = Model.getApplication();
        if (context == null)
            return false;
        ConnectivityManager connectivityManager = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        if (connectivityManager == null) {
            return false;
        } else {
            NetworkInfo[] networkInfo = connectivityManager.getAllNetworkInfo();
            if (networkInfo != null && networkInfo.length > 0) {
                for (NetworkInfo aNetworkInfo : networkInfo) {
                    if (aNetworkInfo.getState() == NetworkInfo.State.CONNECTED) {
                        return true;
                    }
                }
            }
        }
        return false;
    }
}
