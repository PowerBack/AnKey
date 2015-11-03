package net.qiujuer.powerback.ankey.ui.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import net.qiujuer.powerback.ankey.R;
import net.qiujuer.powerback.ankey.model.view.InfoViewModel;
import net.qiujuer.powerback.ankey.presenter.InfoListPresenter;
import net.qiujuer.powerback.ankey.presenter.view.InfoListView;
import net.qiujuer.powerback.ankey.ui.adapter.callback.InfoListAdapterCallback;
import net.qiujuer.powerback.ankey.ui.adapter.holder.InfoHolder;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

/**
 * Created by qiujuer
 * on 15/10/29.
 */
public class InfoListAdapter extends RecyclerView.Adapter<InfoHolder> implements InfoListView {
    private InfoListPresenter mPresenter;
    private InfoListAdapterCallback mCallback;
    private List<InfoViewModel> mDataSet;

    public InfoListAdapter(InfoListAdapterCallback callback) {
        mDataSet = new ArrayList<InfoViewModel>();
        mPresenter = new InfoListPresenter(this);
        mCallback = callback;
    }

    @Override
    public InfoHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        // Create a new view.
        View v = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_list_info_recycler, parent, false);
        InfoHolder holder = new InfoHolder(v, viewType);
        v.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Object object = v.getTag();
                if (object != null && mCallback != null) {
                    mCallback.onItemSelected((UUID) object);
                }
            }
        });
        return holder;
    }

    @Override
    public void onBindViewHolder(InfoHolder holder, int position) {
        holder.setData(mDataSet.get(position));
    }

    @Override
    public int getItemCount() {
        return mDataSet.size();
    }

    @Override
    public List<InfoViewModel> getDataSet() {
        return mDataSet;
    }

    @Override
    public void setDataSet(List<InfoViewModel> dataSet) {
        if (dataSet != mDataSet) {
            mDataSet.clear();
            if (dataSet != null)
                mDataSet.addAll(dataSet);
        }
    }

    @Override
    public void showNull() {
        InfoListAdapterCallback callback = mCallback;
        if (callback != null) {
            callback.showNull();
        }
    }

    @Override
    public void hideNull() {
        InfoListAdapterCallback callback = mCallback;
        if (callback != null) {
            callback.hideNull();
        }
    }

    @Override
    public void showLoading() {
        InfoListAdapterCallback callback = mCallback;
        if (callback != null) {
            callback.showLoading();
        }
    }

    @Override
    public void hideLoading() {
        InfoListAdapterCallback callback = mCallback;
        if (callback != null) {
            callback.hideLoading();
        }
    }

    public void destroy() {
        InfoListPresenter presenter = mPresenter;
        if (presenter != null) {
            mPresenter = null;
            presenter.destroy();
            mCallback = null;
            mDataSet.clear();
        }
    }

    public void refresh() {
        InfoListPresenter presenter = mPresenter;
        if (presenter != null)
            presenter.refresh();
    }
}
