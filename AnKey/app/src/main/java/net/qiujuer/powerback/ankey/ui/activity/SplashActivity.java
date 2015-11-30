package net.qiujuer.powerback.ankey.ui.activity;

import android.os.Bundle;
import android.support.v4.app.FragmentTransaction;
import android.view.View;
import android.widget.TextView;

import net.qiujuer.powerback.ankey.R;
import net.qiujuer.powerback.ankey.presenter.SplashPresenter;
import net.qiujuer.powerback.ankey.presenter.view.SplashView;
import net.qiujuer.powerback.ankey.ui.SuperActivity;
import net.qiujuer.powerback.ankey.ui.fragment.IntroduceFragment;
import net.qiujuer.powerback.ankey.ui.fragment.MaterialSlide;
import net.qiujuer.powerback.ankey.util.FormatUtil;

/**
 * splash view and introduce view
 * Created by GuDong on 10/25/15 14:23.
 */
public class SplashActivity extends SuperActivity implements SplashView, IntroduceFragment.OnStatusChangeListener {
    private static final int KEY_DELAY_TIME = 2000;
    private SplashPresenter mSplashPresenter;

    private IntroduceFragment mIntroduceFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        mSplashPresenter = new SplashPresenter(this);

        initView();
    }

    private void initView() {
        // instance view and use font for text
        TextView tvAppName = (TextView) findViewById(R.id.tv_brand_name);
        tvAppName.setText(FormatUtil.getTextWithFont(this, getString(R.string.app_name), "fonts/Lobster.otf"));
        // after delay time and entry main view
        tvAppName.postDelayed(new Runnable() {
            @Override
            public void run() {
                mSplashPresenter.timeOut();
            }
        }, KEY_DELAY_TIME);
    }

    @Override
    public void showIntroduceView() {
        mIntroduceFragment = new IntroduceFragment();
        mIntroduceFragment.setOnStatusChangeListener(this);

        // add list fragment list to introduce view
        addSlideListToIntroduceView(mIntroduceFragment);

        // add IntroduceFragment to main layout with anim
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.setCustomAnimations(android.R.anim.fade_in, android.R.anim.fade_out);
        transaction.replace(R.id.fl_container, mIntroduceFragment, "introduce");
        transaction.commit();

        // set up IntroduceFragment
        findViewById(R.id.tv_brand_name).post(new Runnable() {
            @Override
            public void run() {
                setUpIntroduceView(mIntroduceFragment);
            }
        });
    }

    private void addSlideListToIntroduceView(IntroduceFragment introduceFragment) {
        // will be added fragment which used to show our app
        MaterialSlide slideOne = MaterialSlide.newInstance(R.drawable.introduc_friend, getString(R.string.introduce_friendly_title), getString(R.string.introduce_friendly_content), R.color.snow_light, R.color.snow_primary);
        MaterialSlide slideTwo = MaterialSlide.newInstance(R.drawable.introduc_simple, getString(R.string.introduce_simple_title), getString(R.string.introduce_simple_content), R.color.snow_light, R.color.snow_primary);
        MaterialSlide slideThree = MaterialSlide.newInstance(R.drawable.introduc_safe, getString(R.string.introduce_safe_title), getString(R.string.introduce_safe_content), R.color.snow_light, R.color.snow_primary);

        //fragment background color
        int colorOne = getResources().getColor(R.color.deep_purple_500);
        int colorTwo = getResources().getColor(R.color.indigo_500);
        int colorThree = getResources().getColor(R.color.dark_primary);

        //Add slides
        introduceFragment.addSlideWithColor(slideOne, colorOne);
        introduceFragment.addSlideWithColor(slideTwo, colorTwo);
        introduceFragment.addSlideWithColor(slideThree, colorThree);
    }

    private void setUpIntroduceView(IntroduceFragment introduceFragment) {

        introduceFragment.setSkipText(getString(R.string.introduce_skip));
        introduceFragment.setDoneText(getString(R.string.introduce_done));

        introduceFragment.setIntroduceAction(new IntroduceFragment.IIntroduceAction() {
            @Override
            public void onSkipPressed() {
                gotoKeyVerifyView();
            }

            @Override
            public void onDonePressed() {
                gotoKeyVerifyView();
            }
        });
    }

    @Override
    protected int getToolBarLayoutRes() {
        return View.NO_ID;
    }

    @Override
    public void gotoKeyVerifyView() {
        KeyVerifyActivity.show(this, MainActivity.class);
        finish();
    }

    @Override
    public void onColorChange(int color) {
        setStatusBarColor(color);
    }
}
