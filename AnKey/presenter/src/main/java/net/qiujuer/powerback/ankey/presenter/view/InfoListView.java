package net.qiujuer.powerback.ankey.presenter.view;

import net.qiujuer.powerback.ankey.model.view.InfoViewModel;

/**
 * Created by qiujuer
 * on 15/10/29.
 */
public interface InfoListView extends BaseAdapterView<InfoViewModel> {
    void showLoading();

    void hideLoading();
}
