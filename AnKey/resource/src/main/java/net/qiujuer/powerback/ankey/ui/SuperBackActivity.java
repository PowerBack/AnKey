package net.qiujuer.powerback.ankey.ui;

import android.view.View;

import net.qiujuer.powerback.ankey.resource.R;

public class SuperBackActivity extends SuperActivity {
    @Override
    protected void initToolBar() {
        super.initToolBar();
        if (mToolbar != null) {
            mToolbar.setNavigationIcon(R.drawable.abc_ic_ab_back_mtrl_am_alpha);
            mToolbar.setNavigationOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    finish();
                }
            });
        }
    }
}
