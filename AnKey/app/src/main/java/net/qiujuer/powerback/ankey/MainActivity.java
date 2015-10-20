package net.qiujuer.powerback.ankey;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import net.qiujuer.powerback.ankey.ui.SuperActivity;

public class MainActivity extends SuperActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }
}
