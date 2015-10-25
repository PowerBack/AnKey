package net.qiujuer.powerback.ankey.ui;

import android.graphics.Typeface;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.LayoutRes;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.SpannableStringBuilder;
import android.text.Spanned;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;

import com.readystatesoftware.systembartint.SystemBarTintManager;

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

        initRoot();
        initToolBar();
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

    public void setTitleBackgroundColorRes(int res) {
        if (mToolbar != null)
            mToolbar.setBackgroundResource(res);
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
}
