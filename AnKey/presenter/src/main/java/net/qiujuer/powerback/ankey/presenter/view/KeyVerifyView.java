package net.qiujuer.powerback.ankey.presenter.view;

/**
 * Created by qiujuer
 * on 15/10/22.
 */
public interface KeyVerifyView {
    String getKey();

    void verifyOk();

    void verifyError();

    void needCreate();

}
