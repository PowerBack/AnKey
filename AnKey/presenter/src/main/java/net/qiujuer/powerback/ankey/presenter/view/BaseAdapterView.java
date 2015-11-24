package net.qiujuer.powerback.ankey.presenter.view;

import java.util.List;

/**
 * Created by qiujuer
 * on 15/10/29.
 */
public interface BaseAdapterView<T> {
    void showNull();

    void hideNull();

    List<T> getDataSet();

    void setDataSet(List<T> dataSet);

    void notifyDataSetChanged();

    void notifyItemChanged(int position);

    void notifyItemChanged(int position, Object payload);

    void notifyItemInserted(int position);

    void notifyItemRemoved(int position);

    void notifyItemMoved(int fromPosition, int toPosition);
}
