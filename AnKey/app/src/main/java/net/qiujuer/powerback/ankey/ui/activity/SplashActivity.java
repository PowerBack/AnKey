package net.qiujuer.powerback.ankey.ui.activity;

import android.content.Intent;
import android.os.Bundle;

import net.qiujuer.powerback.ankey.R;
import net.qiujuer.powerback.ankey.ui.SuperActivity;

public class SplashActivity extends SuperActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        Intent intent = new Intent(this, KeyVerifyActivity.class);
        startActivity(intent);
        finish();
    }
}
