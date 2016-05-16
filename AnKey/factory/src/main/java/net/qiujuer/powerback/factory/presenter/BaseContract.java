package net.qiujuer.powerback.factory.presenter;

import android.support.annotation.StringRes;

/**
 * Created by qiujuer
 * on 16/5/16.
 * Presenter base contract,
 * Defines the basic contract operation of Presenter and View,
 * and the sub class should inherit the contract and make the corresponding operation.
 */
public interface BaseContract {
    interface Presenter {
        void start();

        void destroy();
    }

    interface View<T extends Presenter> {
        void setPresenter(T presenter);

        void showError(@StringRes int str);

        void showLoading();
    }
}
