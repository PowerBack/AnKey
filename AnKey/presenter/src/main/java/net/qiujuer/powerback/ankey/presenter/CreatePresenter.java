package net.qiujuer.powerback.ankey.presenter;

import android.text.TextUtils;

import net.qiujuer.genius.kit.util.UiKit;
import net.qiujuer.powerback.ankey.model.db.InfoModel;
import net.qiujuer.powerback.ankey.model.view.InfoDecryptModel;
import net.qiujuer.powerback.ankey.presenter.tool.InfoModelTool;
import net.qiujuer.powerback.ankey.presenter.view.CreateView;

/**
 * Created by qiujuer
 */
public class CreatePresenter {
    private CreateView mView;

    public CreatePresenter(CreateView view) {
        mView = view;
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

    private void ok() {
        UiKit.runOnMainThreadAsync(new Runnable() {
            @Override
            public void run() {
                mView.setOk();
            }
        });
    }

    private boolean checkValue() {
        if (TextUtils.isEmpty(mView.getDescription()))
            mView.setError(CreateView.ERROR_NULL_DES);
        else if (TextUtils.isEmpty(mView.getPassword()))
            mView.setError(CreateView.ERROR_NULL_PASSWORD);
        else {
            return true;
        }
        return false;
    }

    public void submit() {
        if (checkValue()) {
            mView.showLoading();
            Thread thread = new Thread("AnKey-CreatePresenter-Save-Thread") {
                @Override
                public void run() {
                    save();
                    hideLoading();
                }
            };
            thread.start();
        }
    }

    private void save() {
        try {
            InfoDecryptModel decryptModel = new InfoDecryptModel();
            decryptModel.setDescription(mView.getDescription());
            decryptModel.setPassword(mView.getPassword());
            decryptModel.setColor(mView.getColor());
            decryptModel.setUserName(mView.getUsername());
            decryptModel.setEmail(mView.getEmail());
            decryptModel.setCall(mView.getCall());
            decryptModel.setQQ(mView.getQQ());
            decryptModel.setRemark(mView.getRemarks());
            decryptModel.setSite(mView.getSite());

            InfoModel model = InfoModelTool.encryptModel(decryptModel);
            if (model.save() > 0)
                ok();

        } catch (Exception e) {
            e.printStackTrace();
            needKey();
        }
    }
}
