package net.qiujuer.powerback.ankey.activities;

import android.os.Bundle;
import android.support.v4.app.FragmentTransaction;
import android.view.View;
import android.widget.TextView;

import net.qiujuer.powerback.ankey.R;
import net.qiujuer.powerback.ankey.fragments.IntroduceFragment;
import net.qiujuer.powerback.ankey.fragments.MaterialSlide;
import net.qiujuer.powerback.ankey.utils.FormatUtil;
import net.qiujuer.powerback.common.app.Activity;
import net.qiujuer.powerback.factory.presenter.app.SplashContract;
import net.qiujuer.powerback.factory.presenter.app.SplashPresenter;

/**
 * splash view and introduce view
 * Created by GuDong on 10/25/15 14:23.
 */
public class SplashActivity extends Activity implements IntroduceFragment.OnStatusChangeListener, SplashContract.View {
    private static final int KEY_DELAY_TIME = 2000;
    private SplashContract.Presenter mPresenter;
    private IntroduceFragment mIntroduceFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        // init the presenter
        new SplashPresenter(this);

        initView();
    }

    private void initView() {
        // instance view and use font for text
        TextView tvAppName = (TextView) findViewById(R.id.tv_brand_name);
        if (tvAppName != null) {
            tvAppName.setText(FormatUtil.getTextWithFont(this, getString(R.string.app_name), "fonts/Lobster.otf"));
        }
    }

    @Override
    protected void onStart() {
        super.onStart();
        mPresenter.start();
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
        setUpIntroduceView(mIntroduceFragment);
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
        //introduceFragment.setSkipText(getString(R.string.introduce_skip));
        //introduceFragment.setDoneText(getString(R.string.introduce_done));

        introduceFragment.setIntroduceAction(new IntroduceFragment.IIntroduceAction() {
            @Override
            public void onSkipPressed() {
                gotoObtainPassphrase();
            }

            @Override
            public void onDonePressed() {
                gotoObtainPassphrase();
            }
        });
    }

    @Override
    protected int getToolBarLayoutRes() {
        return View.NO_ID;
    }

    @Override
    public void onColorChange(int color) {
        setStatusBarColor(color);
    }

    @Override
    public void gotoObtainPassphrase() {
        //KeyVerifyActivity.show(this, MainActivity.class);
        MainActivity.show(this);
        finish();
    }

    @Override
    public void setPresenter(SplashContract.Presenter presenter) {
        mPresenter = presenter;
    }
}
