package net.qiujuer.powerback.ankey.fragments;

import android.animation.ArgbEvaluator;
import android.graphics.Rect;
import android.os.Bundle;
import android.support.annotation.ColorInt;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.text.Html;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import net.qiujuer.powerback.ankey.R;
import net.qiujuer.powerback.common.widget.drawable.DirectArrowDrawable;
import net.qiujuer.powerback.common.widget.drawable.Drawables;

import java.util.ArrayList;
import java.util.List;
import java.util.Vector;

/**
 * Introduce view
 * Created by GuDong on 10/25/15 22:15.
 */
public class IntroduceFragment extends net.qiujuer.powerback.common.app.Fragment {
    private final ArgbEvaluator argbEvaluator = new ArgbEvaluator();
    private final List<Fragment> fragments = new Vector<>();
    private final ArrayList<Integer> colors = new ArrayList<>();
    private ViewPager introViewPager;
    private RelativeLayout controlsRelativeLayout;
    private Button skipIntroButton;
    private Button doneSlideButton;
    private Button nextSlideImageButton;
    private View separatorView;
    private LinearLayout dotsLayout;
    private TextView[] dots;
    private PagerAdapter pagerAdapter;

    private boolean isSkipForceHidden;
    private boolean isNextForceHidden;
    private boolean isDoneForceHidden;

    private IIntroduceAction mIntroduceAction;

    private int currentPosition;
    private int numberOfSlides;

    private int activeDotColor;
    private int inactiveDocsColor;

    private OnStatusChangeListener mListener;

    public interface OnStatusChangeListener {
        void onColorChange(int color);
    }

    public void setOnStatusChangeListener(OnStatusChangeListener listener) {
        this.mListener = listener;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_introduce, null);
        initView(view);
        fillViewPage();
        addListener();
        return view;
    }

    private void initView(View view) {
        introViewPager = (ViewPager) view.findViewById(R.id.introViewPager);
        controlsRelativeLayout = (RelativeLayout) view.findViewById(R.id.controlsRelativeLayout);
        skipIntroButton = (Button) view.findViewById(R.id.skipIntroButton);
        nextSlideImageButton = (Button) view.findViewById(R.id.nextSlideImageButton);
        doneSlideButton = (Button) view.findViewById(R.id.doneSlideButton);
        separatorView = view.findViewById(R.id.separatorView);
        dotsLayout = (LinearLayout) view.findViewById(R.id.viewPagerCountDots);
        nextSlideImageButton.setBackgroundDrawable(Drawables.getDirectDrawable(getResources(),
                90,
                DirectArrowDrawable.DIRECT_RIGHT,
                new Rect(24, 18, 24, 18)));
    }

    private void fillViewPage() {
        if (fragments.isEmpty()) {
            throw new IllegalStateException("the fragments is empty , please add slide fragment by use addSlideWithColor() method before add IntroduceFragment to FragmentTransaction. ");
        }

        numberOfSlides = fragments.size();

        activeDotColor = R.color.white;
        inactiveDocsColor = R.color.colorPrimary;

        //Instantiate the indicator dots if there are more than one slide
        if (numberOfSlides > 1) {
            setViewPagerDots();
            if (!isSkipForceHidden) {
                skipIntroButton.setVisibility(View.VISIBLE);
            }
        } else {
            skipIntroButton.setVisibility(View.INVISIBLE);
            nextSlideImageButton.setVisibility(View.INVISIBLE);

            if (!isDoneForceHidden) {
                doneSlideButton.setVisibility(View.VISIBLE);
            }
        }

        // set adapter for viewpager
        pagerAdapter = new IntroduceFragmentAdapter(getChildFragmentManager());
        introViewPager.setAdapter(pagerAdapter);
    }

    private void onColorChange(int color) {
        introViewPager.setBackgroundColor(color);
        controlsRelativeLayout.setBackgroundColor(color);

        OnStatusChangeListener listener = mListener;
        if (listener != null)
            listener.onColorChange(color);
    }

    private void addListener() {
        skipIntroButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (mIntroduceAction != null) {
                    mIntroduceAction.onSkipPressed();
                }
            }
        });

        doneSlideButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (mIntroduceAction != null) {
                    mIntroduceAction.onDonePressed();
                }
            }
        });

        nextSlideImageButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                introViewPager.setCurrentItem(currentPosition + 1, true);
            }
        });

        introViewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
                if (position < (pagerAdapter.getCount() - 1) && position < (colors.size() - 1)) {
                    int color = (Integer)
                            argbEvaluator.evaluate(positionOffset, colors.get(position), colors.get(position + 1));
                    onColorChange(color);
                } else {
                    int color = colors.get(colors.size() - 1);
                    onColorChange(color);
                }
            }

            @Override
            public void onPageSelected(int position) {
                currentPosition = position;

                //Hide SKIP button if last slide item, visible if not
                if (position == numberOfSlides - 1) {
                    if (!isSkipForceHidden) {
                        fadeViewOut(skipIntroButton);
                    }
                } else {
                    if (skipIntroButton.getVisibility() == View.INVISIBLE && !isSkipForceHidden) {
                        fadeViewIn(skipIntroButton);
                    }
                }

                //Hide NEXT button if last slide item and set DONE button
                //visible, otherwise hide Done button and set NEXT button visible
                if (position == numberOfSlides - 1) {
                    if (!isNextForceHidden) {
                        fadeViewOut(nextSlideImageButton);
                    }

                    if (!isDoneForceHidden) {
                        fadeViewIn(doneSlideButton);
                    }
                } else {
                    if (nextSlideImageButton.getVisibility() == View.INVISIBLE && !isNextForceHidden) {
                        fadeViewIn(nextSlideImageButton);
                    }

                    if (doneSlideButton.getVisibility() == View.VISIBLE && !isDoneForceHidden) {
                        fadeViewOut(doneSlideButton);
                    }
                }

                //Set dots
                if (numberOfSlides > 1) {
                    //Set current inactive dots color
                    for (int i = 0; i < numberOfSlides; i++) {
                        dots[i].setTextColor(getResources().getColor(inactiveDocsColor));
                    }
                    //Set current active dot color
                    dots[position].setTextColor(getResources().getColor(activeDotColor));
                }
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
    }

    /**
     * Add a slide to the intro
     *
     * @param fragment Fragment of the slide to be added
     * @param color    Background color of the fragment
     */
    public void addSlideWithColor(@NonNull Fragment fragment, @ColorInt int color) {
        fragments.add(fragment);
        colors.add(color);
    }

    /**
     * set action listener on click
     *
     * @param introduceAction a implement of callback
     */
    public void setIntroduceAction(IIntroduceAction introduceAction) {
        mIntroduceAction = introduceAction;
    }

    private void setViewPagerDots() {
        dots = new TextView[numberOfSlides];

        //Set first inactive dots color
        for (int i = 0; i < numberOfSlides; i++) {
            dots[i] = new TextView(getContext());
            dots[i].setText(Html.fromHtml("&#8226;"));
            dots[i].setTextSize(30);
            dots[i].setTextColor(getResources().getColor(inactiveDocsColor));
            dotsLayout.addView(dots[i]);
        }

        //Set first active dot color
        dots[0].setTextColor(getResources().getColor(activeDotColor));
    }

    /**
     * Set the text color of the done button
     *
     * @param color Color value to set
     */
    public void setDoneButtonTextColor(@ColorInt int color) {
        doneSlideButton.setTextColor(color);
    }

    /**
     * Set the color of the separator between slide content and bottom controls
     *
     * @param color Color value to set
     */
    public void setSeparatorColor(@ColorInt int color) {
        separatorView.setBackgroundColor(color);
    }

    /**
     * Set the color of the active dot indicator
     *
     * @param color Color value to set
     */
    public void setActiveDotColor(@ColorInt int color) {
        activeDotColor = color;
    }

    /**
     * Set the color of the inactive dot indicator
     *
     * @param color Color value to set
     */
    public void setInactiveDocsColor(@ColorInt int color) {
        inactiveDocsColor = color;
    }

    /**
     * Set the string value of the skip button
     *
     * @param text String value to set
     */
    public void setSkipText(@NonNull String text) {
        skipIntroButton.setText(text);
    }

    /**
     * Set the string value of the done button
     *
     * @param text String value to set
     */
    public void setDoneText(@NonNull String text) {
        doneSlideButton.setText(text);
    }

    /**
     * Show the skip button
     */
    public void showSkip() {
        skipIntroButton.setVisibility(View.VISIBLE);
        isSkipForceHidden = false;
    }

    /**
     * Hide the skip button
     */
    public void hideSkip() {
        skipIntroButton.setVisibility(View.INVISIBLE);
        isSkipForceHidden = true;
    }

    /**
     * Show the next button
     */
    public void showNext() {
        nextSlideImageButton.setVisibility(View.VISIBLE);
        isNextForceHidden = false;
    }

    /**
     * Hide the next button
     */
    public void hideNext() {
        nextSlideImageButton.setVisibility(View.INVISIBLE);
        isNextForceHidden = true;
    }

    /**
     * Show the done button
     */
    public void showDone() {
        doneSlideButton.setVisibility(View.VISIBLE);
        isDoneForceHidden = false;
    }

    /**
     * Hide the done button
     */
    public void hideDone() {
        doneSlideButton.setVisibility(View.INVISIBLE);
        isDoneForceHidden = true;
    }

    private void fadeViewIn(final View view) {
        Animation fadeAnimation = AnimationUtils.loadAnimation(getContext(), android.R.anim.fade_in);
        fadeAnimation.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {
                view.setVisibility(View.VISIBLE);
            }

            @Override
            public void onAnimationEnd(Animation animation) {

            }

            @Override
            public void onAnimationRepeat(Animation animation) {

            }
        });
        view.startAnimation(fadeAnimation);
    }

    private void fadeViewOut(final View view) {
        Animation fadeAnimation = AnimationUtils.loadAnimation(getContext(), android.R.anim.fade_out);
        fadeAnimation.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {

            }

            @Override
            public void onAnimationEnd(Animation animation) {
                view.setVisibility(View.INVISIBLE);
            }

            @Override
            public void onAnimationRepeat(Animation animation) {

            }
        });
        view.startAnimation(fadeAnimation);
    }

    private class IntroduceFragmentAdapter extends FragmentPagerAdapter {

        public IntroduceFragmentAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {
            return fragments.get(position);
        }

        @Override
        public int getCount() {
            return fragments.size();
        }
    }

    public interface IIntroduceAction {
        /**
         * Perform action when skip button is pressed
         */
        void onSkipPressed();

        /**
         * Perform action when done button is pressed
         */
        void onDonePressed();

    }
}
