package net.qiujuer.powerback.ankey.presenter.view;

import java.util.List;

/**
 * Created by qiujuer
 * on 15/10/29.
 */
public interface BaseAdapterView<T> {
    List<T> getDataSet();

    void setDataSet(List<T> dataSet);

    void notifyDataSetChanged();

    void showNull();

    void hideNull();
}
