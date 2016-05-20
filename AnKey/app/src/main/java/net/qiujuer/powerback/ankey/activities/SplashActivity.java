/*
 * Copyright (C) 2015-2016 Qiujuer-PowerBack <qiujuer@live.cn>
 * WebSite https://github.com/PowerBack/AnKey
 * Author Qiujuer
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *         http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package net.qiujuer.powerback.ankey.activities;

import android.os.Bundle;
import android.support.v4.app.FragmentTransaction;
import android.view.View;
import android.widget.TextView;

import net.qiujuer.powerback.ankey.R;
import net.qiujuer.powerback.ankey.fragments.IntroduceFragment;
import net.qiujuer.powerback.ankey.utils.FontFormat;
import net.qiujuer.powerback.common.app.Activity;
import net.qiujuer.powerback.factory.presenter.app.SplashContract;
import net.qiujuer.powerback.factory.presenter.app.SplashPresenter;

/**
 * Splash view and introduce view
 */
public class SplashActivity extends Activity implements IntroduceFragment.IIntroduceAction, SplashContract.View {
    private SplashContract.Presenter mPresenter;

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
            tvAppName.setText(FontFormat.getTextWithLobsterFont(this, getString(R.string.app_name)));
        }
    }

    @Override
    protected void onStart() {
        super.onStart();
        mPresenter.start();
    }

    @Override
    public void showIntroduceView() {
        IntroduceFragment introduceFragment = IntroduceFragment.newInstance(this);
        // add IntroduceFragment to main layout with anim
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.setCustomAnimations(R.anim.anim_dialog_enter, R.anim.anim_dialog_exit);
        transaction.replace(R.id.content_main, introduceFragment, "introduce");
        transaction.commit();
    }

    @Override
    public void gotoMainView() {
        MainActivity.show(this);
        finish();
    }

    @Override
    protected int getToolBarLayoutRes() {
        return View.NO_ID;
    }

    @Override
    public void onDone() {
        gotoMainView();
    }

    @Override
    public void onColorChange(int color) {
        setStatusBarColor(color);
    }

    @Override
    public void setPresenter(SplashContract.Presenter presenter) {
        mPresenter = presenter;
    }
}
