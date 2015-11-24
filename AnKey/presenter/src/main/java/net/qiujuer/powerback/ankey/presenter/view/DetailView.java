package net.qiujuer.powerback.ankey.presenter.view;

import android.content.Context;

import java.util.UUID;

/**
 * Created by qiujuer
 * on 15/11/24.
 */
public interface DetailView extends BaseLoadingView, BaseVerifyView {
    int ERROR_NOT_FOUND_MODEL = 4;

    void setDescription(String value);

    void setPassword(String value);

    void setUsername(String value);

    void setSite(String value);

    void setEmail(String value);

    void setQQ(String value);

    void setCall(String value);

    void setRemarks(String value);

    void setColor(int color);

    UUID getInfoId();

    Context getContext();
}
