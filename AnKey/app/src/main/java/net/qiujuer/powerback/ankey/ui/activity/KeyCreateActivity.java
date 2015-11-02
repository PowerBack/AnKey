package net.qiujuer.powerback.ankey.ui.activity;

import android.os.Bundle;
import android.view.View;

import net.qiujuer.genius.ui.widget.EditText;
import net.qiujuer.powerback.ankey.R;
import net.qiujuer.powerback.ankey.presenter.KeyCreatePresenter;
import net.qiujuer.powerback.ankey.presenter.view.KeyCreateView;
import net.qiujuer.powerback.ankey.ui.SuperBackActivity;
import net.qiujuer.powerback.ankey.util.AnKeyTransformationMethod;

public class KeyCreateActivity extends SuperBackActivity implements KeyCreateView, View.OnClickListener {

    private KeyCreatePresenter mPresenter;
    private EditText mKey;
    private EditText mKeyConfirm;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_key_create);

        mPresenter = new KeyCreatePresenter(this);

        findViewById(R.id.btn_submit).setOnClickListener(this);
        mKey = (EditText) findViewById(R.id.edit_key);
        mKeyConfirm = (EditText) findViewById(R.id.edit_key_confirm);

        mKey.setTransformationMethod(AnKeyTransformationMethod.getInstance());
        mKeyConfirm.setTransformationMethod(AnKeyTransformationMethod.getInstance());
    }


    @Override
    public String getKey() {
        return mKey.getText().toString();
    }

    @Override
    public String getKeyConfirm() {
        return mKeyConfirm.getText().toString();
    }

    @Override
    public void setError(int status) {
        showToast("Error");
    }

    @Override
    public void setOk() {
        finish();
    }

    @Override
    public void onClick(View v) {
        mPresenter.submit();
    }
}
