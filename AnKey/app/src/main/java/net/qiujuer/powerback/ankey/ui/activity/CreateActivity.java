package net.qiujuer.powerback.ankey.ui.activity;

import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.View;

import net.qiujuer.genius.ui.widget.EditText;
import net.qiujuer.genius.ui.widget.FloatActionButton;
import net.qiujuer.powerback.ankey.R;
import net.qiujuer.powerback.ankey.presenter.CreatePresenter;
import net.qiujuer.powerback.ankey.presenter.view.CreateView;
import net.qiujuer.powerback.ankey.ui.SuperBackActivity;
import net.qiujuer.powerback.ankey.widget.drawable.Drawables;

public class CreateActivity extends SuperBackActivity implements CreateView, View.OnClickListener {
    private CreatePresenter mPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create);
        setStatusBarColorRes(R.color.colorAccent);
        setTitleBackgroundColorRes(R.color.colorAccent);

        initFloatActionButton();

        initPresenter();
    }

    protected void initPresenter() {
        mPresenter = new CreatePresenter(this);
    }

    private void initFloatActionButton() {
        FloatActionButton actionButton = (FloatActionButton) findViewById(R.id.action_submit);
        Drawable drawable = Drawables.getOkDrawable(getResources());
        actionButton.setImageDrawable(drawable);
        actionButton.setOnClickListener(this);
    }

    private String getEnterText(int id) {
        EditText text = ((EditText) findViewById(id));
        if (text != null)
            return text.getText().toString();
        else
            return null;
    }

    @Override
    public String getDescription() {
        return getEnterText(R.id.edit_description);
    }

    @Override
    public String getUsername() {
        return getEnterText(R.id.edit_username);
    }

    @Override
    public String getPassword() {
        return getEnterText(R.id.edit_password);
    }

    @Override
    public String getSite() {
        return getEnterText(R.id.edit_site);
    }

    @Override
    public String getEmail() {
        return getEnterText(R.id.edit_email);
    }

    @Override
    public String getQQ() {
        return getEnterText(R.id.edit_qq);
    }

    @Override
    public String getCall() {
        return getEnterText(R.id.edit_call);
    }

    @Override
    public String getRemarks() {
        return getEnterText(R.id.edit_remark);
    }

    @Override
    public int getColor() {
        return 0;
    }

    @Override
    public void needKey() {
        KeyVerifyActivity.show(this);
    }

    @Override
    public void setError(int error) {
        showToast("Error: " + error);
    }

    @Override
    public void setOk() {
        finish();
    }

    @Override
    public void showLoading() {

    }

    @Override
    public void hideLoading() {

    }

    @Override
    public void onClick(View v) {
        CreatePresenter presenter = mPresenter;
        if (presenter == null)
            return;
        if (v.getId() == R.id.action_submit) {
            mPresenter.submit();
        }
    }
}
