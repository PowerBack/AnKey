package net.qiujuer.powerback.ankey.presenter;

import android.text.TextUtils;

import net.qiujuer.powerback.ankey.presenter.view.KeyVerifyView;

/**
 * Created by qiujuer
 * on 15/10/24.
 */
public class KeyVerifyPresenter {
    private KeyVerifyView mView;

    public KeyVerifyPresenter(KeyVerifyView view) {
        mView = view;
    }

    public void verify() {
        if (!TextUtils.isEmpty(mView.getKey())) {
            mView.setVerifyStatus(0);
        }
    }
}
