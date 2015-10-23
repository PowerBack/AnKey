package net.qiujuer.powerback.ankey.ui.activity;

import android.os.Bundle;

import net.qiujuer.powerback.ankey.R;
import net.qiujuer.powerback.ankey.presenter.KeyVerifyPresenter;
import net.qiujuer.powerback.ankey.presenter.view.KeyVerifyView;
import net.qiujuer.powerback.ankey.ui.SuperActivity;

public class KeyActivity extends SuperActivity implements KeyVerifyView {
    private KeyVerifyPresenter mPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_key);

        mPresenter = new KeyVerifyPresenter(this);
    }

    @Override
    public String getKey() {
        return null;
    }

    @Override
    public String setVerifyStatus() {
        return null;
    }
}
