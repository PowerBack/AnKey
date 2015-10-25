package net.qiujuer.powerback.ankey.ui.activity;

import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.text.SpannableStringBuilder;
import android.text.Spanned;
import android.view.View;
import android.view.WindowManager;
import android.widget.TextView;

import net.qiujuer.powerback.ankey.R;
import net.qiujuer.powerback.ankey.presenter.SplashPresenter;
import net.qiujuer.powerback.ankey.presenter.view.SplashView;
import net.qiujuer.powerback.ankey.ui.SuperActivity;

import uk.co.chrisjenx.calligraphy.CalligraphyTypefaceSpan;
import uk.co.chrisjenx.calligraphy.TypefaceUtils;

/**
 * Created by GuDong on 10/25/15 14:23.
 * Contact with 1252768410@qq.com.
 */
public class SplashActivity extends SuperActivity implements SplashView {
    private static final int KEY_DELAY_TIME = 2000;
    private SplashPresenter mSplashPresenter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // make view full screen
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);

        setContentView(R.layout.activity_splash);

        mSplashPresenter = new SplashPresenter(this);

        // instance view and use font for text
        TextView tvAppName = (TextView) findViewById(R.id.tv_brand_name);
        tvAppName.setText(getTextWithFont(getString(R.string.app_name), "fonts/Lobster.otf"));

        // after delay time and entry main view
        tvAppName.postDelayed(new Runnable() {
            @Override
            public void run() {
                mSplashPresenter.timeOut();
            }
        }, KEY_DELAY_TIME);
    }

    @Override
    protected int getToolBarLayoutRes() {
        return View.NO_ID;
    }

    @Override
    public void gotoKeyVerifyView() {
        Intent intent = new Intent(SplashActivity.this, KeyVerifyActivity.class);
        startActivity(intent);
        finish();
    }

    /**
     * use font to title
     * @param title
     * @param fontPath the path of font which is in assert directory
     * @return a fontable title text
     */
    protected SpannableStringBuilder getTextWithFont(String title, String fontPath){
        // init builder
        SpannableStringBuilder sBuilder = new SpannableStringBuilder();
        // Add title
        sBuilder.append(title);

        // Create the Typeface you want to apply to certain text
        Typeface typeface = TypefaceUtils.load(getAssets(), fontPath);
        CalligraphyTypefaceSpan typefaceSpan = new CalligraphyTypefaceSpan(typeface);
        sBuilder.setSpan(typefaceSpan, 0, sBuilder.length(), Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);

        return sBuilder;
    }

}
