package net.qiujuer.powerback.ankey.ui.view;

import android.content.Context;
import android.graphics.Typeface;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import net.qiujuer.genius.ui.Ui;
import net.qiujuer.genius.ui.widget.Loading;
import net.qiujuer.powerback.ankey.R;
import net.qiujuer.powerback.ankey.presenter.DetailPresenter;
import net.qiujuer.powerback.ankey.ui.activity.KeyVerifyActivity;

import java.util.UUID;


public class DetailView extends LinearLayout implements net.qiujuer.powerback.ankey.presenter.view.DetailView, View.OnClickListener {
    private DetailPresenter mPresenter;
    private UUID mId;
    private OnEditClickListener mListener;
    private LinearLayout mItems;

    public DetailView(Context context) {
        super(context);
        init(null, 0);
    }

    public DetailView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(attrs, 0);
    }

    public DetailView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        init(attrs, defStyle);
    }

    @Override
    protected void onAttachedToWindow() {
        super.onAttachedToWindow();
        mPresenter.load();
    }

    private void init(AttributeSet attrs, int defStyle) {
        setOrientation(VERTICAL);
        View.inflate(getContext(), R.layout.lay_detail_view, this);

        mItems = (LinearLayout) findViewById(R.id.lay_items);
        mItems.removeAllViews();
        mItems.setVisibility(GONE);

        findViewById(R.id.img_edit).setOnClickListener(this);
        findViewById(R.id.img_copy).setOnClickListener(this);
        findViewById(R.id.text_password).setOnClickListener(this);

        mPresenter = new DetailPresenter(this);
    }

    public void setOnEditListener(OnEditClickListener listener) {
        mListener = listener;
    }

    public void setInfoId(UUID id) {
        mId = id;
    }

    private void addItem(int titleId, String value) {
        if (!TextUtils.isEmpty(value)) {
            View view = View.inflate(getContext(), R.layout.lay_detail_view_item, null);
            ((TextView) view.findViewById(R.id.text_title)).setText(titleId);
            ((TextView) view.findViewById(R.id.text_value)).setText(value);
            mItems.addView(view);
            mItems.setVisibility(VISIBLE);
        }
    }

    @Override
    public void setDescription(String value) {
        ((TextView) findViewById(R.id.text_description)).setText(value);
    }

    @Override
    public void setPassword(String value) {
        ((TextView) findViewById(R.id.text_password)).setText(value);
    }

    @Override
    public void setUsername(String value) {
        addItem(R.string.label_username, value);
    }

    @Override
    public void setSite(String value) {
        addItem(R.string.label_site, value);
    }

    @Override
    public void setEmail(String value) {
        addItem(R.string.label_email, value);
    }

    @Override
    public void setQQ(String value) {
        addItem(R.string.label_qq, value);
    }

    @Override
    public void setCall(String value) {
        addItem(R.string.label_call, value);
    }

    @Override
    public void setRemarks(String value) {
        addItem(R.string.label_remark, value);
    }

    @Override
    public void setColor(int color) {

    }

    @Override
    public UUID getInfoId() {
        return mId;
    }

    @Override
    public void showLoading() {
        setEnabled(false);
        findViewById(R.id.lay_title).setVisibility(GONE);
        findViewById(R.id.lay_scroll).setVisibility(GONE);
        findViewById(R.id.lay_loading).setVisibility(VISIBLE);
        Loading loading = (Loading) findViewById(R.id.loading);
        loading.start();
    }

    @Override
    public void hideLoading() {
//        setEnabled(true);
//        Loading loading = (Loading) findViewById(R.id.loading);
//        loading.stop();
//        findViewById(R.id.lay_loading).setVisibility(GONE);
//        findViewById(R.id.lay_title).setVisibility(VISIBLE);
//        findViewById(R.id.lay_scroll).setVisibility(VISIBLE);
    }

    @Override
    public void setError(int error) {

    }

    @Override
    public void setOk() {

    }

    @Override
    public void needKey() {
        KeyVerifyActivity.show(this.getContext());
    }

    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.img_edit) {
            if (mListener != null)
                mListener.onClick(this);
        } else if (v.getId() == R.id.img_copy) {
            mPresenter.copy();
            Toast.makeText(getContext(), R.string.label_copy_ok, Toast.LENGTH_SHORT).show();
        } else if (v.getId() == R.id.text_password) {
            TextView textView = ((TextView) v);
            if (isPasswordInputType(textView.getInputType())) {
                textView.setInputType(EditorInfo.TYPE_CLASS_TEXT | EditorInfo.TYPE_TEXT_VARIATION_VISIBLE_PASSWORD);
                Typeface typeface = Ui.getFont(getContext(), "FZLanTingHeiS-L-GB-Regular.TTF");
                textView.setTypeface(typeface);
            } else
                textView.setInputType(EditorInfo.TYPE_CLASS_TEXT | EditorInfo.TYPE_TEXT_VARIATION_PASSWORD);

        }
    }

    private static boolean isPasswordInputType(int inputType) {
        final int variation =
                inputType & (EditorInfo.TYPE_MASK_CLASS | EditorInfo.TYPE_MASK_VARIATION);
        return variation
                == (EditorInfo.TYPE_CLASS_TEXT | EditorInfo.TYPE_TEXT_VARIATION_PASSWORD)
                || variation
                == (EditorInfo.TYPE_CLASS_TEXT | EditorInfo.TYPE_TEXT_VARIATION_WEB_PASSWORD)
                || variation
                == (EditorInfo.TYPE_CLASS_NUMBER | EditorInfo.TYPE_NUMBER_VARIATION_PASSWORD);
    }

    private static boolean isVisiblePasswordInputType(int inputType) {
        final int variation =
                inputType & (EditorInfo.TYPE_MASK_CLASS | EditorInfo.TYPE_MASK_VARIATION);
        return variation
                == (EditorInfo.TYPE_CLASS_TEXT | EditorInfo.TYPE_TEXT_VARIATION_VISIBLE_PASSWORD);
    }

    public interface OnEditClickListener {
        void onClick(DetailView view);
    }
}
