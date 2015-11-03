package net.qiujuer.powerback.ankey.presenter;

import android.text.TextUtils;

import net.qiujuer.genius.kit.util.HashKit;
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

    private boolean verify(String key) {
        UserModel model = new UserModel();
        String keyHash = model.getKey();
        if (keyHash == null)
            return false;

        key = HashKit.getMD5String(HashKit.getMD5String(key));
        return key.equals(keyHash);
    }

    public void verify() {
        String key = mView.getKey();
        if (!TextUtils.isEmpty(key) && verify(key)) {
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
