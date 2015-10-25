package net.qiujuer.powerback.ankey.presenter;

import android.text.TextUtils;

import net.qiujuer.genius.kit.util.HashKit;
import net.qiujuer.powerback.ankey.model.xml.UserModel;
import net.qiujuer.powerback.ankey.presenter.view.KeyCreateView;

/**
 * Created by qiujuer
 */
public class KeyCreatePresenter {
    private KeyCreateView mView;

    public KeyCreatePresenter(KeyCreateView view) {
        mView = view;
    }

    public void submit() {
        if (check()) {
            String key = mView.getKeyConfirm();
            UserModel model = new UserModel();
            model.setKey(HashKit.getMD5String(key));
            model.save();

            mView.setOk();
        }
    }

    private boolean check() {
        if (TextUtils.isEmpty(mView.getKey())) {
            mView.setError(KeyCreateView.STATUS_KEY_NULL);
            return false;
        } else if (TextUtils.isEmpty(mView.getKeyConfirm())) {
            mView.setError(KeyCreateView.STATUS_KEY_CONFIRM_NULL);
            return false;
        } else if (!mView.getKeyConfirm().equals(mView.getKey())) {
            mView.setError(KeyCreateView.STATUS_KEY_CONFIRM_NULL);
            return false;
        } else {
            return true;
        }
    }
}
