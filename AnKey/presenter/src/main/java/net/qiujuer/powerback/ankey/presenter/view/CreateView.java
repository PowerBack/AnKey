package net.qiujuer.powerback.ankey.presenter.view;

/**
 * Created by qiujuer
 * on 15/10/22.
 */
public interface CreateView {
    int ERROR_NULL_DES = 1;
    int ERROR_NULL_PASSWORD = 2;
    int ERROR_UNKNOWN = 3;

    String getDescription();

    String getPassword();

    String getUsername();

    String getSite();

    String getEmail();

    String getQQ();

    String getCall();

    String getRemarks();

    int getColor();

    void needKey();

    void setError(int error);

    void setOk();
}
