package net.qiujuer.powerback.ankey.ui;

import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Typeface;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.LayoutRes;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.SpannableStringBuilder;
import android.text.Spanned;
import android.view.Gravity;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.readystatesoftware.systembartint.SystemBarTintManager;

import net.qiujuer.genius.ui.widget.Loading;
import net.qiujuer.powerback.ankey.SuperApplication;
import net.qiujuer.powerback.ankey.reflect.StatusBarProxy;
import net.qiujuer.powerback.ankey.resource.R;

import uk.co.chrisjenx.calligraphy.CalligraphyTypefaceSpan;
import uk.co.chrisjenx.calligraphy.TypefaceUtils;

public class SuperActivity extends AppCompatActivity implements Toolbar.OnMenuItemClickListener {
    protected Toolbar mToolbar;
    protected RelativeLayout mRoot;
    protected SystemBarTintManager mBarTintManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        SuperApplication.addActivity(this);
        super.onCreate(savedInstanceState);
        super.setContentView(getRootContentView());

        hideSmartBar();

        initRoot();
        initToolBar();
    }

    @Override
    public void finish() {
        super.finish();
        //overridePendingTransition(R.anim.anim_activity_enter, R.anim.anim_activity_exit);
    }

    @Override
    public void startActivityForResult(Intent intent, int requestCode, Bundle options) {
        super.startActivityForResult(intent, requestCode, options);
        //overridePendingTransition(R.anim.anim_activity_enter, R.anim.anim_activity_exit);
    }

    private void initTintManager() {
        // create our manager instance after the content view is set
        // SystemBarTintManager tintManager = new SystemBarTintManager(this);
        // enable status bar tint
        // tintManager.setStatusBarTintEnabled(true);
        // enable navigation bar tint
        // tintManager.setNavigationBarTintEnabled(false);

        // set a custom tint color for all system bars
        // tintManager.setTintColor(getResources().getColor(R.color.dark_darker));
        // set a custom navigation bar resource
        // tintManager.setNavigationBarTintResource(R.color.dark_darker);
        // set a custom status bar drawable
        // tintManager.setStatusBarTintColor(getResources().getColor(R.color.dark_darker));

        // Create SystemBarTintManager
        SystemBarTintManager tintManager = new SystemBarTintManager(this);
        tintManager.setStatusBarTintEnabled(true);
        tintManager.setStatusBarTintResource(R.color.dark_dark);
        mBarTintManager = tintManager;
    }

    private void hideSmartBar() {
        try {
            if (getWindow().getDecorView() != null) {
                getWindow().getDecorView().setSystemUiVisibility(2050);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void initRoot() {
        mRoot = (RelativeLayout) findViewById(R.id.lay_super_root);

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            // Trans status bar
            StatusBarProxy.setImmersedWindow(getWindow(), true);
            // Init tint manager
            initTintManager();
            SystemBarTintManager.SystemBarConfig config = mBarTintManager.getConfig();
            // Set root padding
            mRoot.setPadding(0, config.getPixelInsetTop(false), config.getPixelInsetRight(), config.getPixelInsetBottom());
        }
    }

    protected void initToolBar() {
        int layoutResID = getToolBarLayoutRes();
        if (layoutResID != View.NO_ID) {
            getLayoutInflater().inflate(layoutResID, mRoot);
            int count = mRoot.getChildCount();
            for (int i = 0; i < count; i++) {
                View view = mRoot.getChildAt(i);
                if (view instanceof Toolbar) {
                    mToolbar = (Toolbar) view;
                    break;
                }
            }
            if (mToolbar == null)
                throw new IllegalArgumentException("Cannot add a title that is not 'Toolbar' type.");

            if (mToolbar.getId() == View.NO_ID)
                mToolbar.setId(R.id.toolbar);

            //setSupportActionBar(mToolbar);
            mToolbar.setOnMenuItemClickListener(this);
            onInflateMenu(mToolbar);
            setTitle(getTitle());
        }
    }

    protected int getRootContentView() {
        return R.layout.activity_super;
    }

    protected int getToolBarLayoutRes() {
        return R.layout.toolbar_super;
    }

    protected String getTitleFont() {
        return "fonts/Hero.otf";
    }

    public void setStatusBarColorRes(int res) {
        if (mBarTintManager != null)
            mBarTintManager.setStatusBarTintResource(res);
    }

    public void setStatusBarColor(int color) {
        if (mBarTintManager != null)
            mBarTintManager.setStatusBarTintColor(color);
    }

    public void setTitleBackgroundColorRes(int res) {
        if (mToolbar != null)
            mToolbar.setBackgroundResource(res);
    }

    public void setTitleBackgroundColor(int color) {
        if (mToolbar != null)
            mToolbar.setBackgroundColor(color);
    }

    @Override
    public void setTitle(CharSequence title) {
        // Get title
        CharSequence sequence = null;
        if (mToolbar != null)
            sequence = mToolbar.getTitle();
        // Format title
        SpannableStringBuilder sBuilder;
        if (sequence != null && sequence instanceof SpannableStringBuilder) {
            sBuilder = (SpannableStringBuilder) sequence;
            sBuilder.clearSpans();
            sBuilder.clear();
        } else {
            sBuilder = new SpannableStringBuilder();
        }

        // Add title
        sBuilder.append(title);
        // Create the Typeface you want to apply to certain text
        Typeface typeface = TypefaceUtils.load(getAssets(), getTitleFont());
        CalligraphyTypefaceSpan typefaceSpan = new CalligraphyTypefaceSpan(typeface);
        sBuilder.setSpan(typefaceSpan, 0, sBuilder.length(), Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
        // Set title
        super.setTitle(sBuilder);
        if (mToolbar != null)
            mToolbar.setTitle(sBuilder);
    }

    public void setCenterTitle(boolean isCenter) {
        int count = mToolbar.getChildCount();
        for (int i = 0; i < count; i++) {
            View view = mToolbar.getChildAt(i);
            if (view instanceof TextView) {
                TextView tv = (TextView) view;

                int gravity = Gravity.START | Gravity.CENTER_VERTICAL;
                int width = ViewGroup.LayoutParams.WRAP_CONTENT;
                if (isCenter) {
                    gravity = Gravity.CENTER;
                    width = ViewGroup.LayoutParams.MATCH_PARENT;
                }

                ViewGroup.LayoutParams lp = tv.getLayoutParams();
                if (lp == null)
                    lp = new ViewGroup.LayoutParams(width, ViewGroup.LayoutParams.MATCH_PARENT);
                else {
                    lp.width = width;
                    lp.height = ViewGroup.LayoutParams.MATCH_PARENT;
                }

                tv.setGravity(gravity);
                tv.setLayoutParams(lp);
            }
        }
    }

    @Override
    public void setContentView(@LayoutRes int layoutResID) {
        getLayoutInflater().inflate(layoutResID, mRoot);
        if (mToolbar != null) {
            int count = mRoot.getChildCount();
            if (count > 1) {
                for (int i = 0; i < count; i++) {
                    View view = mRoot.getChildAt(i);
                    if (view != mToolbar) {
                        RelativeLayout.LayoutParams lp = null;
                        ViewGroup.LayoutParams params = view.getLayoutParams();
                        if (params instanceof RelativeLayout.LayoutParams) {
                            lp = (RelativeLayout.LayoutParams) params;
                        } else {
                            lp = new RelativeLayout.LayoutParams(params);
                        }

                        lp.addRule(RelativeLayout.BELOW, mToolbar.getId());
                        view.setLayoutParams(lp);
                        return;
                    }
                }
            }
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        SuperApplication.removeActivity(this);
    }

    @Override
    public boolean onMenuItemClick(MenuItem item) {
        return false;
    }

    protected void onInflateMenu(Toolbar toolbar) {

    }

    public void showToast(String msg) {
        Toast.makeText(this, msg, Toast.LENGTH_SHORT).show();
    }

    public void showToast(int strRes) {
        Toast.makeText(this, strRes, Toast.LENGTH_SHORT).show();
    }

    public AlertDialog createDialog(String msg) {
        return createDialog(null, msg);
    }

    public AlertDialog createDialog(String title, String msg) {
        AlertDialog.Builder builder = new AlertDialog.Builder(this, R.style.Theme_Dialog);
        if (title != null)
            builder.setTitle(title);
        if (msg != null)
            builder.setMessage(msg);
        builder.setNegativeButton(R.string.label_dialog_negative, null);
        builder.setPositiveButton(R.string.label_dialog_positive, null);
        return builderAlertDialog(builder);
    }

    private AlertDialog builderAlertDialog(AlertDialog.Builder builder) {
        AlertDialog alertDialog = builder.create();

        Window window = alertDialog.getWindow();
        window.setGravity(Gravity.TOP);

        //Drawable drawable = Drawables.getShadowDrawable(getResources(), getResources().getColor(R.color.white_alpha_240));
        //window.setBackgroundDrawable(drawable);


        /*
        int b = (int) (getResources().getDisplayMetrics().density * 4);
        View view = window.getDecorView();
        view.setPadding(view.getPaddingLeft(),
                view.getPaddingTop(),
                view.getPaddingRight(),
                view.getPaddingBottom() > b ? view.getPaddingBottom() : b);
        */

        return alertDialog;
    }


    public AlertDialog createDialog(View content) {
        return createDialog(0, content);
    }

    public AlertDialog createDialog(int title, View content) {
        return createDialog(title, content, null, null);
    }

    public AlertDialog createDialog(int title, View content,
                                    DialogInterface.OnClickListener negativeButtonListener,
                                    DialogInterface.OnClickListener positiveButtonListener) {
        AlertDialog.Builder builder = new AlertDialog.Builder(this, R.style.Theme_Dialog);
        if (title != 0)
            builder.setTitle(title);
        if (content != null)
            builder.setView(content);
        if (null != negativeButtonListener)
            builder.setNegativeButton(R.string.label_dialog_negative, negativeButtonListener);
        if (null != positiveButtonListener)
            builder.setPositiveButton(R.string.label_dialog_positive, positiveButtonListener);

        return builderAlertDialog(builder);
    }

    public AlertDialog createDialog(String title, View content,
                                    String negativeButtonStr,
                                    String positiveButtonStr,
                                    DialogInterface.OnClickListener negativeButtonListener,
                                    DialogInterface.OnClickListener positiveButtonListener) {
        AlertDialog.Builder builder = new AlertDialog.Builder(this, R.style.Theme_Dialog);
        if (title != null)
            builder.setTitle(title);
        if (content != null)
            builder.setView(content);
        builder.setNegativeButton(negativeButtonStr, negativeButtonListener);
        builder.setPositiveButton(positiveButtonStr, positiveButtonListener);
        return builderAlertDialog(builder);
    }

    public AlertDialog createDialog(int title, View content, DialogInterface.OnClickListener positiveButtonListener) {
        AlertDialog.Builder builder = new AlertDialog.Builder(this, R.style.Theme_Dialog);
        if (title != 0)
            builder.setTitle(title);
        if (content != null)
            builder.setView(content);
        builder.setPositiveButton(R.string.label_dialog_positive, positiveButtonListener);
        return builderAlertDialog(builder);
    }

    private AlertDialog mLoadingDialog;

    public void showLoading() {
        if (mLoadingDialog == null) {
            View view = View.inflate(this, R.layout.lay_loading, null);
            mLoadingDialog = createDialog(view);
        }
        mLoadingDialog.show();
        Loading loading = (Loading) (mLoadingDialog.getWindow().getDecorView().findViewById(R.id.loading));
        loading.start();
    }

    public void hideLoading() {
        AlertDialog dialog = mLoadingDialog;
        if (dialog != null) {
            Loading loading = (Loading) (dialog.getWindow().getDecorView().findViewById(R.id.loading));
            loading.stop();
            mLoadingDialog.dismiss();
        }
    }
}
