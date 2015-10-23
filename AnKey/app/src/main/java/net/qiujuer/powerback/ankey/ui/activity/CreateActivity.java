package net.qiujuer.powerback.ankey.ui.activity;

import android.os.Bundle;

import net.qiujuer.powerback.ankey.R;
import net.qiujuer.powerback.ankey.presenter.CreatePresenter;
import net.qiujuer.powerback.ankey.presenter.view.CreateView;
import net.qiujuer.powerback.ankey.ui.SuperBackActivity;

public class CreateActivity extends SuperBackActivity implements CreateView {

    private CreatePresenter mPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create);
        setStatusBarColorRes(R.color.cyan_700);
        setTitleBackgroundColorRes(R.color.cyan_500);

        mPresenter = new CreatePresenter(this);
    }

    @Override
    public String getHead() {
        return null;
    }

    @Override
    public String getPassword() {
        return null;
    }
}
