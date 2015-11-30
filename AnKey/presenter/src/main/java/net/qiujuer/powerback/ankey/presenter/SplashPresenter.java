package net.qiujuer.powerback.ankey.presenter;

import net.qiujuer.powerback.ankey.presenter.view.SplashView;

/**
 * Created by GuDong on 10/25/15 15:40.
 * Contact with 1252768410@qq.com.
 */
public class SplashPresenter {
    private SplashView mSplashView;

    public SplashPresenter(SplashView splashView) {
        mSplashView = splashView;
    }

    /**
     * load view finished
     */
    public void timeOut(){
        if(AppPresenter.isFirstUse()){
            mSplashView.showIntroduceView();
        }else{
            mSplashView.showIntroduceView();
        }
    }
}
