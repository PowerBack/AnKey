package net.qiujuer.powerback.ankey;

import net.qiujuer.powerback.ankey.presenter.AppPresenter;

/**
 * Created by qiujuer
 * on 15/10/24.
 */
public class AnKeyApplication extends SuperApplication {
    public void onCreate() {
        super.onCreate();
        AppPresenter.setApplication(this);
    }
}
