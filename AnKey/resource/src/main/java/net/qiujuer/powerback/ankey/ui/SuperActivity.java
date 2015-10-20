package net.qiujuer.powerback.ankey.ui;

import android.os.Bundle;
import android.support.annotation.LayoutRes;
import android.support.v7.app.AppCompatActivity;
import android.view.ViewGroup;

import com.readystatesoftware.systembartint.SystemBarTintManager;

import net.qiujuer.powerback.ankey.resource.R;

public class SuperActivity extends AppCompatActivity {

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
    }

    @Override
    public void setContentView(@LayoutRes int layoutResID) {
        getLayoutInflater().inflate(layoutResID, (ViewGroup) findViewById(R.id.lay_super_root));
    }

}
