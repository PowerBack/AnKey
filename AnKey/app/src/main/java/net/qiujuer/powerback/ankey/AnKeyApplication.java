package net.qiujuer.powerback.ankey;

import android.widget.Toast;

import net.qiujuer.genius.kit.util.UiKit;
import net.qiujuer.powerback.ankey.presenter.AppPresenter;

/**
 * Created by qiujuer
 * on 15/10/24.
 */
public class AnKeyApplication extends SuperApplication {
    private Thread mClearKeyThread;

    public void onCreate() {
        super.onCreate();
        AppPresenter.setApplication(this);
    }

    private void showClearToast() {
        UiKit.runOnMainThreadSync(new Runnable() {
            @Override
            public void run() {
                Toast.makeText(AnKeyApplication.this, "Will destroy the secret in 5's", Toast.LENGTH_LONG).show();
            }
        });
    }

    @Override
    public synchronized void clearKey() {
        if (AppPresenter.allowDestroyKey() && AppPresenter.validKey() && mClearKeyThread == null) {
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

    @Override
    public synchronized void cancelClearKey() {
        Thread thread = mClearKeyThread;
        if (thread != null) {
            mClearKeyThread = null;
            thread.interrupt();
            log("cancel clear key.");
        }
    }
}
