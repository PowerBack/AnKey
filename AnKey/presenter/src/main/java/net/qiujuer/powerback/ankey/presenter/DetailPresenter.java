package net.qiujuer.powerback.ankey.presenter;

import android.content.ClipboardManager;
import android.content.Context;

import net.qiujuer.genius.kit.util.Tools;
import net.qiujuer.genius.kit.util.UiKit;
import net.qiujuer.powerback.ankey.model.db.InfoModel;
import net.qiujuer.powerback.ankey.model.view.InfoDecryptModel;
import net.qiujuer.powerback.ankey.presenter.tool.InfoModelTool;
import net.qiujuer.powerback.ankey.presenter.view.DetailView;
import net.qiujuer.powerback.ankey.presenter.view.EditView;

/**
 * Created by qiujuer
 * on 15/11/24.
 */
public class DetailPresenter {
    private DetailView mView;
    private InfoDecryptModel mDModel;

    public DetailPresenter(DetailView view) {
        mView = view;
    }

    public void copy() {
        ClipboardManager cmb = (ClipboardManager) mView.getContext().getSystemService(Context.CLIPBOARD_SERVICE);
        cmb.setText(mDModel.getPassword());
    }

    public void load() {
        mView.showLoading();
        Thread thread = new Thread("AnKey-EditPresenter-Load-Thread") {
            @Override
            public void run() {
                Tools.sleepIgnoreInterrupt(1000);
                loadData();
                hideLoading();
            }
        };
        thread.start();
    }

    private void loadData() {
        try {
            InfoModel model = InfoModel.get(mView.getInfoId());
            if (model == null) {
                finish();
            } else {
                mDModel = InfoModelTool.decryptModel(model);
                UiKit.runOnMainThreadAsync(new Runnable() {
                    @Override
                    public void run() {
                        mView.setColor(mDModel.getColor());
                        mView.setUsername(mDModel.getUserName());
                        mView.setEmail(mDModel.getEmail());
                        mView.setSite(mDModel.getSite());
                        mView.setQQ(mDModel.getQQ());
                        mView.setCall(mDModel.getCall());
                        mView.setRemarks(mDModel.getRemark());
                        mView.setDescription(mDModel.getDescription());
                        mView.setPassword(mDModel.getPassword());
                    }
                });
            }
        } catch (Exception e) {
            e.printStackTrace();
            needKey();
        }
    }

    private void finish() {
        UiKit.runOnMainThreadAsync(new Runnable() {
            @Override
            public void run() {
                mView.setError(EditView.ERROR_NOT_FOUND_MODEL);
            }
        });
    }

    private void hideLoading() {
        UiKit.runOnMainThreadAsync(new Runnable() {
            @Override
            public void run() {
                mView.hideLoading();
            }
        });
    }

    private void needKey() {
        UiKit.runOnMainThreadAsync(new Runnable() {
            @Override
            public void run() {
                mView.needKey();
            }
        });
    }
}
