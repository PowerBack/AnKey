package net.qiujuer.powerback.factory.presenter;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.text.TextUtils;
import android.widget.Toast;

import net.qiujuer.genius.kit.handler.Run;
import net.qiujuer.genius.kit.handler.runable.Action;
import net.qiujuer.powerback.common.Common;
import net.qiujuer.powerback.common.app.Application;
import net.qiujuer.powerback.common.utils.Log;
import net.qiujuer.powerback.sign.SignTool;

/**
 * Created by qiujuer
 * on 15/10/19.
 */
public class AppPresenter {
    private static final String TAG = AppPresenter.class.getName();
    private static SignTool SIGNER;
    private static boolean SIGNER_ALLOW_DESTROY = true;


    public static void init(Application application) {
        Common.init(application);
    }

    public static void dispose() {
        Common.init(null);
    }

    public static Application getApp() {
        return Common.getApp();
    }

    public static boolean isLogin() {
        return false;
    }

    public static boolean isFirstUse() {
        return false;
    }

    public static boolean isHaveNetwork() {
        final Context context = getApp();
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
        return false;
    }

    public static boolean setKey(String key) {
        return false;
    }

    public static String encrypt(String src) throws NullPointerException {
        // Check the string is empty
        if (TextUtils.isEmpty(src))
            return null;

        // If tool is null , will throw NullPointerException
        SignTool tool = SIGNER;
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
        SignTool tool = SIGNER;
        if (tool == null)
            throw new NullPointerException("The app key tool is null, you should call setKey()");
        else {
            return tool.decrypt(encrypted);
        }
    }

    public static void destroyKey() {
        SignTool tool = SIGNER;
        if (tool != null) {
            SIGNER = null;
            tool.destroy();
        }
    }

    public static boolean validKey() {
        return SIGNER != null;
    }

    public static boolean allowDestroySign() {
        return SIGNER_ALLOW_DESTROY;
    }

    public static void setAllowDestroySign(boolean allowDestroySign) {
        SIGNER_ALLOW_DESTROY = allowDestroySign;
    }


    private static Thread mClearKeyThread;

    public static void clearKey() {
        if (AppPresenter.allowDestroySign() && AppPresenter.validKey() && mClearKeyThread == null) {
            mClearKeyThread = new Thread("ClearKeyThread") {
                @Override
                public void run() {
                    super.run();
                    try {
                        log("clear key thread running.");
                        Thread.sleep(500);
                        // notify user will clear key
                        showClearToast();

                        // in this, check the thread status
                        if (mClearKeyThread == null)
                            return;

                        // wait same time to clear
                        Thread.sleep(5 * 1000);
                        AppPresenter.destroyKey();
                        log("cleared key.");
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                        log("skip clear key.");
                    } finally {
                        mClearKeyThread = null;
                    }
                }
            };
            mClearKeyThread.start();
        }
    }

    public static void cancelClearKey() {
        Thread thread = mClearKeyThread;
        if (thread != null) {
            mClearKeyThread = null;
            thread.interrupt();
            log("cancel clear key.");
        }
    }

    private static void showClearToast() {
        final Application application = getApp();
        if (application == null)
            return;
        Run.onUiAsync(new Action() {
            @Override
            public void call() {
                Toast.makeText(application, "Will destroy the secret in 5's", Toast.LENGTH_LONG).show();
            }
        });
    }

    private static void log(String msg) {
        Log.d(TAG, msg);
    }
}
