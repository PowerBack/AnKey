package net.qiujuer.powerback.ankey.presenter;

import android.text.TextUtils;

import net.qiujuer.powerback.ankey.presenter.view.CreateView;

/**
 * Created by qiujuer
 * on 15/10/22.
 */
public class CreatePresenter {
    private CreateView mView;

    public CreatePresenter(CreateView view) {
        mView = view;
    }

    private boolean checkValue() {
        return mView != null && !(TextUtils.isEmpty(mView.getPassword()) || TextUtils.isEmpty(mView.getHead()));
    }

    public boolean submit() {
        return checkValue();
    }
}
