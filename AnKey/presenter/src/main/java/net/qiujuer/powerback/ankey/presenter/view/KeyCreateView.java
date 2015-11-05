package net.qiujuer.powerback.ankey.presenter.view;

/**
 * Created by qiujuer
 */
public interface KeyCreateView {
    int STATUS_KEY_NULL = -1;
    int STATUS_KEY_CONFIRM_NULL = -2;
    int STATUS_KEY_NOT_EQUAL_CONFIRM = -3;
    int STATUS_KEY_LEN_LESS_THAN_SEX = -4;
    int STATUS_KEY_IS_HAVE = -5;

    String getKey();

    String getKeyConfirm();

    void setError(int status);

    void setOk();
}
