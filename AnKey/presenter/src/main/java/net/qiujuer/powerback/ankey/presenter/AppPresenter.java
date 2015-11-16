package net.qiujuer.powerback.ankey.presenter;

import android.app.Application;
import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.text.TextUtils;

import com.android.volley.RequestQueue;

import net.qiujuer.genius.kit.util.HashKit;
import net.qiujuer.powerback.ankey.model.AppModel;
import net.qiujuer.powerback.ankey.model.xml.SettingModel;
import net.qiujuer.powerback.ankey.model.xml.UserModel;
import net.qiujuer.powerback.ankey.tool.key.KeyTool;
import net.qiujuer.powerback.ankey.tool.key.KeyToolHelper;

import java.util.UUID;

/**
 * Created by qiujuer
 * on 15/10/19.
 */
public class AppPresenter {
    private static final Object SERVICE_LOCK = new Object();
    private static KeyTool KEY_TOOL;


    public static void setApplication(Application application) {
        AppModel.setApplication(application);
    }

    public static void dispose() {
        AppModel.dispose();
    }

    public static RequestQueue getRequestQueue() {
        return AppModel.getRequestQueue();
    }

    public static boolean isLogin() {
        return false;
    }

    public static boolean isFirstUse() {
        SettingModel model = new SettingModel();
        return model.isFirstUse();
    }

    public static boolean isHaveNetwork() {
        final Context context = AppModel.getApplication();
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

    public static boolean createKey(String key) {
        UserModel model = new UserModel();
        if (TextUtils.isEmpty(model.getKey()) && TextUtils.isEmpty(model.getSalt())) {
            String salt = UUID.randomUUID().toString();
            String keyHash = HashKit.getMD5String(HashKit.getMD5String(key));
            model.setSalt(salt);
            model.setKey(keyHash);
            model.save();
            return true;
        }
        return false;
    }

    public static boolean setKey(String key) {
        UserModel model = new UserModel();
        String keyHash = model.getKey();
        if (keyHash != null) {
            key = HashKit.getMD5String(HashKit.getMD5String(key));
            if (key.equals(keyHash)) {
                KEY_TOOL = KeyToolHelper.createDefaultKeyTool(key, model.getSalt());
                return true;
            }
        }
        return false;
    }

    public static String encrypt(String src) throws NullPointerException {
        // Check the string is empty
        if (TextUtils.isEmpty(src))
            return null;

        // If tool is null , will throw NullPointerException
        KeyTool tool = KEY_TOOL;
        if (tool == null)
            throw new NullPointerException("The app key tool is null, you should call setKey()");
        else {
            return tool.encrypt(src);
        }
    }

    public static String decrypt(String encrypted) throws NullPointerException {
        // Check the string is empty
        if (TextUtils.isEmpty(encrypted))
            return null;

        // If tool is null , will throw NullPointerException
        KeyTool tool = KEY_TOOL;
        if (tool == null)
            throw new NullPointerException("The app key tool is null, you should call setKey()");
        else {
            return tool.decrypt(encrypted);
        }
    }

    public static void destroyKey() {
        KeyTool tool = KEY_TOOL;
        if (tool != null) {
            KEY_TOOL = null;
            tool.destroyKey();
        }
    }
}
