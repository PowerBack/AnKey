package net.qiujuer.powerback.ankey.ui;

import android.graphics.Typeface;
import android.os.Bundle;
import android.support.annotation.LayoutRes;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.SpannableStringBuilder;
import android.text.Spanned;
import android.view.ViewGroup;

import com.readystatesoftware.systembartint.SystemBarTintManager;

import net.qiujuer.powerback.ankey.resource.R;

import uk.co.chrisjenx.calligraphy.CalligraphyTypefaceSpan;
import uk.co.chrisjenx.calligraphy.TypefaceUtils;

public class SuperActivity extends AppCompatActivity {
    protected Toolbar mToolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        super.setContentView(R.layout.activity_super);

        // create our manager instance after the content view is set
        SystemBarTintManager tintManager = new SystemBarTintManager(this);
        // enable status bar tint
        tintManager.setStatusBarTintEnabled(true);
        // enable navigation bar tint
        tintManager.setNavigationBarTintEnabled(true);


        // set a custom tint color for all system bars
        tintManager.setTintColor(getResources().getColor(R.color.colorPrimaryDark));
        // set a custom navigation bar resource
        tintManager.setNavigationBarTintResource(R.color.colorPrimaryDark);
        tintManager.setStatusBarTintColor(getResources().getColor(R.color.colorPrimaryDark));

        initTitle();
    }

    private void initTitle() {
        mToolbar = (Toolbar) findViewById(R.id.toolbar);
        setTitle(getTitle());
    }

    public void setTitle(CharSequence title) {
        SpannableStringBuilder sBuilder = new SpannableStringBuilder();
        sBuilder.append(title); // Bold this
        // Create the Typeface you want to apply to certain text
        Typeface typeface = TypefaceUtils.load(getAssets(), "fonts/Lobster.otf");
        CalligraphyTypefaceSpan typefaceSpan = new CalligraphyTypefaceSpan(typeface);
        // Apply typeface to the Spannable 0 - 6 "Hello!" This can of course by dynamic.
        sBuilder.setSpan(typefaceSpan, 0, sBuilder.length(), Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
        mToolbar.setTitle(sBuilder);
    }


    @Override
    public void setContentView(@LayoutRes int layoutResID) {
        getLayoutInflater().inflate(layoutResID, (ViewGroup) findViewById(R.id.lay_super_root));
    }
}
