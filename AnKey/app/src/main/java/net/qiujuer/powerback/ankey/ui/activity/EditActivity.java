package net.qiujuer.powerback.ankey.ui.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.Selection;
import android.text.TextUtils;
import android.view.View;

import net.qiujuer.genius.ui.widget.EditText;
import net.qiujuer.powerback.ankey.R;
import net.qiujuer.powerback.ankey.model.AppModel;
import net.qiujuer.powerback.ankey.presenter.EditPresenter;
import net.qiujuer.powerback.ankey.presenter.view.EditView;

import java.util.UUID;

/**
 * Created by qiujuer
 */
public class EditActivity extends CreateActivity implements EditView {
    private static final String KEY_INFO_ID = "info_id";
    private EditPresenter mPresenter;
    private UUID mId;

    public static void show(Context context, UUID id) {
        Intent intent = new Intent(context, EditActivity.class);
        intent.putExtra(KEY_INFO_ID, id.toString());
        context.startActivity(intent);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit);

        if (initId())
            mPresenter.load();
        else
            finish();
    }

    @Override
    protected void initPresenter() {
        mPresenter = new EditPresenter(this);
    }

    private boolean initId() {
        Intent intent = getIntent();
        String id = intent.getStringExtra(KEY_INFO_ID);
        if (TextUtils.isEmpty(id))
            return false;
        try {
            mId = UUID.fromString(id);
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
        return (mId != AppModel.EMPTY_ID);
    }

    private void setEnterText(int id, String value) {
        if (TextUtils.isEmpty(value))
            return;
        EditText text = ((EditText) findViewById(id));
        if (text != null) {
            text.setText(value);
            text.requestFocus();
            Editable editable = text.getText();
            Selection.setSelection(editable, editable.length());
        }
    }


    @Override
    public void onClick(View v) {
        EditPresenter presenter = mPresenter;
        if (presenter == null)
            return;
        if (v.getId() == R.id.action_submit) {
            mPresenter.submit();
        }
    }

    @Override
    public void setDescription(String value) {
        setEnterText(R.id.edit_description, value);
    }

    @Override
    public void setPassword(String value) {
        setEnterText(R.id.edit_password, value);
    }

    @Override
    public void setUsername(String value) {
        setEnterText(R.id.edit_username, value);
    }

    @Override
    public void setSite(String value) {
        setEnterText(R.id.edit_site, value);
    }

    @Override
    public void setEmail(String value) {
        setEnterText(R.id.edit_email, value);
    }

    @Override
    public void setQQ(String value) {
        setEnterText(R.id.edit_qq, value);
    }

    @Override
    public void setCall(String value) {
        setEnterText(R.id.edit_call, value);
    }

    @Override
    public void setRemarks(String value) {
        setEnterText(R.id.edit_remark, value);
    }

    @Override
    public void setColor(int color) {

    }

    @Override
    public UUID getInfoId() {
        return mId;
    }
}
