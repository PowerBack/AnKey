package net.qiujuer.powerback.ankey.presenter;

import net.qiujuer.powerback.ankey.presenter.view.InfoListView;

/**
 * Created by qiujuer
 * on 15/10/29.
 */
public class InfoListPresenter {
    private InfoListView mView;

    public InfoListPresenter(InfoListView view) {
        mView = view;
    }

    public void refresh() {
        mView.hideNull();
        mView.showLoading();
    }

    public void destroy() {

    }
}
