package net.qiujuer.powerback.ankey.presenter;

import android.text.TextUtils;

import net.qiujuer.genius.kit.util.Tools;
import net.qiujuer.genius.kit.util.UiKit;
import net.qiujuer.powerback.ankey.model.db.InfoModel;
import net.qiujuer.powerback.ankey.model.view.InfoDecryptModel;
import net.qiujuer.powerback.ankey.presenter.tool.InfoModelTool;
import net.qiujuer.powerback.ankey.presenter.view.CreateView;
import net.qiujuer.powerback.ankey.presenter.view.EditView;

/**
 * Created by qiujuer
 */
public class EditPresenter {
    private EditView mView;

    public EditPresenter(EditView view) {
        mView = view;
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
                final InfoDecryptModel decryptModel = InfoModelTool.decryptModel(model);
                UiKit.runOnMainThreadAsync(new Runnable() {
                    @Override
                    public void run() {
                        mView.setColor(decryptModel.getColor());
                        mView.setUsername(decryptModel.getUserName());
                        mView.setEmail(decryptModel.getEmail());
                        mView.setSite(decryptModel.getSite());
                        mView.setQQ(decryptModel.getQQ());
                        mView.setCall(decryptModel.getCall());
                        mView.setRemarks(decryptModel.getRemark());
                        mView.setDescription(decryptModel.getDescription());
                        mView.setPassword(decryptModel.getPassword());
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
            Thread thread = new Thread("AnKey-EditPresenter-Save-Thread") {
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
            InfoModel model = InfoModel.get(mView.getInfoId());
            if (model == null) {
                finish();
                return;
            }

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

            InfoModelTool.encryptModel(decryptModel, model);
            if (model.save() > 0)
                ok();

        } catch (Exception e) {
            e.printStackTrace();
            needKey();
        }
    }
}
