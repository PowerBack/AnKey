package net.qiujuer.powerback.ankey.ui.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;

import net.qiujuer.genius.ui.Ui;
import net.qiujuer.powerback.ankey.R;
import net.qiujuer.powerback.ankey.presenter.KeyVerifyPresenter;
import net.qiujuer.powerback.ankey.presenter.view.KeyVerifyView;
import net.qiujuer.powerback.ankey.ui.SuperActivity;

public class KeyVerifyActivity extends SuperActivity implements KeyVerifyView, View.OnClickListener {
    private final static String KEY_CLX = "clx";
    private KeyVerifyPresenter mPresenter;
    private EditText mKey;
    private Class<?> mClx;

    public static void show(Context context) {
        show(context, null);
    }

    public static void show(Context context, Class<?> clx) {
        Intent intent = new Intent(context, KeyVerifyActivity.class);
        if (clx != null)
            intent.putExtra(KEY_CLX, clx);
        context.startActivity(intent);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_key_verify);
        setCenterTitle(true);

        initBound();
        initUi();

        mPresenter = new KeyVerifyPresenter(this);
    }

    private void initBound() {
        Intent intent = getIntent();
        mClx = (Class<?>) intent.getSerializableExtra(KEY_CLX);
    }

    private void initUi() {
        mKey = (EditText) findViewById(R.id.edit_key);
        mKey.setTypeface(Ui.getFont(this, "FZLanTingHeiS-L-GB-Regular.TTF"));
        findViewById(R.id.btn_submit).setOnClickListener(this);
    }

    @Override
    protected String getTitleFont() {
        return "fonts/Lobster.otf";
    }

    @Override
    protected void onInflateMenu(Toolbar toolbar) {
        //toolbar.inflateMenu(R.menu.menu_key_verify);
    }

    @Override
    public String getKey() {
        return mKey.getText().toString();
    }

    @Override
    public void verifyOk() {
        if (mClx != null) {
            Intent intent = new Intent(this, mClx);
            startActivity(intent);
        }
        finish();
    }

    @Override
    public void verifyError() {
        showToast("Error");
    }

    @Override
    public void needCreate() {
        Intent intent = new Intent(this, KeyCreateActivity.class);
        startActivity(intent);
    }

    @Override
    public void onClick(View v) {
        mPresenter.verify();
    }

    @Override
    public boolean onMenuItemClick(MenuItem item) {
        if (item.getItemId() == R.id.action_add) {
            Intent intent = new Intent(this, KeyCreateActivity.class);
            startActivity(intent);
            return true;

        }
        return super.onMenuItemClick(item);
    }

    @Override
    protected void onResume() {
        super.onResume();
        mPresenter.checkKeyStatus();
    }
}
