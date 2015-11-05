package net.qiujuer.powerback.ankey.presenter;

import android.text.TextUtils;

import net.qiujuer.genius.kit.util.HashKit;
import net.qiujuer.powerback.ankey.model.db.FieldModel;
import net.qiujuer.powerback.ankey.model.db.InfoModel;
import net.qiujuer.powerback.ankey.model.db.SiteModel;
import net.qiujuer.powerback.ankey.presenter.view.CreateView;

/**
 * Created by qiujuer
 */
public class CreatePresenter {
    private CreateView mView;

    public CreatePresenter(CreateView view) {
        mView = view;
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
            try {
                InfoModel model = new InfoModel();
                model.setDescription(AppPresenter.encrypt(mView.getDescription()));
                model.setPassword(AppPresenter.encrypt(mView.getPassword()));
                model.setColor(mView.getColor());

                String text = mView.getUsername();
                if (!TextUtils.isEmpty(text)) {
                    FieldModel field = getFieldModel(AppPresenter.encrypt(text), FieldModel.TAG_USERNAME);
                    model.setUserName(field);
                }

                text = mView.getEmail();
                if (!TextUtils.isEmpty(text)) {
                    FieldModel field = getFieldModel(AppPresenter.encrypt(text), FieldModel.TAG_EMAIL);
                    model.setEmail(field);
                }

                text = mView.getCall();
                if (!TextUtils.isEmpty(text)) {
                    FieldModel field = getFieldModel(AppPresenter.encrypt(text), FieldModel.TAG_CALL);
                    model.setCall(field);
                }

                text = mView.getQQ();
                if (!TextUtils.isEmpty(text)) {
                    FieldModel field = getFieldModel(AppPresenter.encrypt(text), FieldModel.TAG_QQ);
                    model.setQQ(field);
                }

                text = mView.getRemarks();
                if (!TextUtils.isEmpty(text)) {
                    model.setRemark(AppPresenter.encrypt(text));
                }

                // Site
                text = mView.getSite();
                if (!TextUtils.isEmpty(text)) {
                    model.setSite(getSiteModel(text));
                }

                // Save Model
                if (model.save() != 0)
                    mView.setOk();

            } catch (Exception e) {
                e.printStackTrace();
                mView.needKey();
            }


        }
    }

    private String getFormatUrl(String url) {
        return url;
    }

    private String getSampleUrl(String url) {
        return url;
    }

    private SiteModel getSiteModel(String url) {
        url = getFormatUrl(url);
        String md5 = HashKit.getMD5String(url);
        SiteModel model = SiteModel.get(md5);
        if (model == null) {
            model = new SiteModel();
            model.setMd5(md5);
            model.setUrl(url);
            model.setName(getSampleUrl(url));
        }
        return model;
    }

    private FieldModel getFieldModel(String enText, int tag) {
        String md5 = HashKit.getMD5String(enText);
        FieldModel field = FieldModel.get(md5, tag);
        if (field == null) {
            field = new FieldModel(tag);
            field.setMd5(md5);
            field.setText(enText);
            field.save();
        }
        return field;
    }
}
