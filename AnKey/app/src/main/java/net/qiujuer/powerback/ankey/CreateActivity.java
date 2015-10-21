package net.qiujuer.powerback.ankey;

import android.os.Bundle;

import net.qiujuer.powerback.ankey.ui.SuperBackActivity;

public class CreateActivity extends SuperBackActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create);

        setTitle("Create");
    }
}
