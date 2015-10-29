package net.qiujuer.powerback.ankey.ui.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.ViewGroup;

import net.qiujuer.powerback.ankey.model.view.InfoViewModel;
import net.qiujuer.powerback.ankey.presenter.InfoListPresenter;
import net.qiujuer.powerback.ankey.presenter.view.InfoListView;
import net.qiujuer.powerback.ankey.ui.adapter.callback.InfoListAdapterCallback;
import net.qiujuer.powerback.ankey.ui.adapter.holder.InfoHolder;

import java.util.List;

/**
 * Created by qiujuer
 * on 15/10/29.
 */
public class InfoListAdapter extends RecyclerView.Adapter<InfoHolder> implements InfoListView {
    private InfoListPresenter mPresenter;
    private InfoListAdapterCallback mCallback;

    public InfoListAdapter(InfoListAdapterCallback callback) {
        mPresenter = new InfoListPresenter(this);
        mCallback = callback;
    }

    @Override
    public InfoHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return null;
    }

    @Override
    public void onBindViewHolder(InfoHolder holder, int position) {

    }

    @Override
    public int getItemCount() {
        return 0;
    }

    @Override
    public List<InfoViewModel> getDataSet() {
        return null;
    }

    @Override
    public void setDataSet(List<InfoViewModel> dataSet) {

    }

    @Override
    public void showNull() {

    }

    @Override
    public void hideNull() {

    }

    @Override
    public void showLoading() {

    }

    @Override
    public void hideLoading() {

    }
}
