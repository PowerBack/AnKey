package net.qiujuer.powerback.ankey.ui.adapter.holder;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import net.qiujuer.powerback.ankey.R;
import net.qiujuer.powerback.ankey.model.view.InfoViewModel;

/**
 * Created by qiujuer
 * on 15/10/29.
 */
public class InfoHolder extends RecyclerView.ViewHolder {
    private TextView mDes;
    private View mColor;

    public InfoHolder(View itemView, int type) {
        super(itemView);
        mDes = (TextView) itemView.findViewById(R.id.txt_des);
        mColor = itemView.findViewById(R.id.view_color);
    }

    public void setData(InfoViewModel model) {
        mDes.setText(model.getDescription());
        mColor.setBackgroundColor(model.getColor());
    }
}
