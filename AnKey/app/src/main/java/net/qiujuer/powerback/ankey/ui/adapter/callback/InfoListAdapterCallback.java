package net.qiujuer.powerback.ankey.ui.adapter.callback;

import java.util.UUID;

/**
 * Created by qiujuer
 * on 15/10/29.
 */
public interface InfoListAdapterCallback {
    void onItemSelected(UUID id);

    void showLoading();

    void hideLoading();

    void showNull();

    void hideNull();
}
