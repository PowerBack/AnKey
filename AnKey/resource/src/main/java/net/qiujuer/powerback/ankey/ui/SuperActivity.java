package net.qiujuer.powerback.ankey.ui;

import android.graphics.Typeface;
import android.os.Bundle;
import android.support.annotation.LayoutRes;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.SpannableStringBuilder;
import android.text.Spanned;
import android.view.MenuItem;
import android.view.ViewGroup;

import com.readystatesoftware.systembartint.SystemBarTintManager;

import net.qiujuer.powerback.ankey.resource.R;

import uk.co.chrisjenx.calligraphy.CalligraphyTypefaceSpan;
import uk.co.chrisjenx.calligraphy.TypefaceUtils;

public class SuperActivity extends AppCompatActivity implements Toolbar.OnMenuItemClickListener {
    protected Toolbar mToolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        super.setContentView(R.layout.activity_super);
        initToolBar();

        // create our manager instance after the content view is set
        SystemBarTintManager tintManager = new SystemBarTintManager(this);
        // enable status bar tint
        tintManager.setStatusBarTintEnabled(true);
        // enable navigation bar tint
        tintManager.setNavigationBarTintEnabled(true);

        // set a custom tint color for all system bars
        tintManager.setTintColor(getResources().getColor(R.color.dark_darker));
        // set a custom navigation bar resource
        tintManager.setNavigationBarTintResource(R.color.dark_darker);
        // set a custom status bar drawable
        tintManager.setStatusBarTintColor(getResources().getColor(R.color.dark_darker));

        SystemBarTintManager.SystemBarConfig config = tintManager.getConfig();
        findViewById(R.id.lay_super_root).setPadding(0, config.getPixelInsetTop(false), config.getPixelInsetRight(), config.getPixelInsetBottom());
    }

    protected void initToolBar() {
        mToolbar = (Toolbar) findViewById(R.id.toolbar);

        mToolbar = (Toolbar) findViewById(R.id.toolbar);
        mToolbar.setNavigationIcon(R.mipmap.ic_nav_main);
        mToolbar.setOnMenuItemClickListener(this);

        onInflateMenu(mToolbar);
        setTitle(getTitle());
    }

    @Override
    public void setTitle(CharSequence title) {
        SpannableStringBuilder sBuilder;
        CharSequence sequence = mToolbar.getTitle();
        if (sequence != null && sequence instanceof SpannableStringBuilder) {
            sBuilder = (SpannableStringBuilder) sequence;
            sBuilder.clearSpans();
            sBuilder.clear();
        } else {
            sBuilder = new SpannableStringBuilder();
        }

        sBuilder.append(title);
        // Create the Typeface you want to apply to certain text
        Typeface typeface = TypefaceUtils.load(getAssets(), getTitleFont());
        CalligraphyTypefaceSpan typefaceSpan = new CalligraphyTypefaceSpan(typeface);
        sBuilder.setSpan(typefaceSpan, 0, sBuilder.length(), Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
        mToolbar.setTitle(sBuilder);
    }

    protected String getTitleFont() {
        return "fonts/Hero.otf";
    }


    @Override
    public void setContentView(@LayoutRes int layoutResID) {
        getLayoutInflater().inflate(layoutResID, (ViewGroup) findViewById(R.id.lay_super_content));
    }

    @Override
    public boolean onMenuItemClick(MenuItem item) {
        return false;
    }

    protected void onInflateMenu(Toolbar toolbar) {

    }
}
