package net.qiujuer.powerback.ankey.presenter;

import android.text.TextUtils;

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
            if (AppPresenter.createKey(key))
                mView.setOk();
            else
                mView.setError(KeyCreateView.STATUS_KEY_IS_HAVE);
        }
    }

    private boolean check() {
        String key = mView.getKey();
        String confirm = mView.getKeyConfirm();
        if (TextUtils.isEmpty(key)) {
            mView.setError(KeyCreateView.STATUS_KEY_NULL);
            return false;
        } else if (TextUtils.isEmpty(confirm)) {
            mView.setError(KeyCreateView.STATUS_KEY_CONFIRM_NULL);
            return false;
        } else if (!key.equals(confirm)) {
            mView.setError(KeyCreateView.STATUS_KEY_NOT_EQUAL_CONFIRM);
            return false;
        } else if (key.length() < 6) {
            mView.setError(KeyCreateView.STATUS_KEY_LEN_LESS_THAN_SEX);
            return false;
        } else {
            return true;
        }
    }
}
