package net.qiujuer.powerback.ankey.ui.activity;

import android.os.Bundle;

import net.qiujuer.genius.ui.widget.EditText;
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
        setStatusBarColorRes(R.color.colorAccent);
        setTitleBackgroundColorRes(R.color.colorAccent);

        mPresenter = new CreatePresenter(this);
    }

    @Override
    public String getDescription() {
        return getDate(R.id.edit_description);
    }

    @Override
    public String getUsername() {
        return getDate(R.id.edit_username);
    }

    @Override
    public String getPassword() {
        return getDate(R.id.edit_password);
    }

    @Override
    public String getSite() {
        return getDate(R.id.edit_site);
    }

    @Override
    public String getEmail() {
        return getDate(R.id.edit_email);
    }

    @Override
    public String getQQ() {
        return getDate(R.id.edit_qq);
    }

    @Override
    public String getCall() {
        return getDate(R.id.edit_call);
    }

    private String getDate(int id) {
        String date = ((EditText) findViewById(id)).getText().toString();
        if (date.equals("")) {
            return "no";
        } else return date;
    }
}
