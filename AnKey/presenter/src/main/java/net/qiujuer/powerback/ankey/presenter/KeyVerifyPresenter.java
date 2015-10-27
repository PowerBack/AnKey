package net.qiujuer.powerback.ankey.presenter;

import android.text.TextUtils;

import net.qiujuer.powerback.ankey.model.xml.UserModel;
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
            mView.verifyOk();
        } else {
            mView.verifyError();
        }
    }

    public void checkKeyStatus() {
        UserModel model = new UserModel();
        if (TextUtils.isEmpty(model.getKey()) || TextUtils.isEmpty(model.getSalt()))
            mView.needCreate();
    }
}
