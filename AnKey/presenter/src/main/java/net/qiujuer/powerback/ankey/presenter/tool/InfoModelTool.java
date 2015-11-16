package net.qiujuer.powerback.ankey.presenter.tool;

import android.text.TextUtils;

import net.qiujuer.genius.kit.util.HashKit;
import net.qiujuer.powerback.ankey.model.db.FieldModel;
import net.qiujuer.powerback.ankey.model.db.InfoModel;
import net.qiujuer.powerback.ankey.model.db.SiteModel;
import net.qiujuer.powerback.ankey.model.view.InfoDecryptModel;
import net.qiujuer.powerback.ankey.presenter.AppPresenter;

/**
 * Created by qiujuer
 * on 15/11/16.
 */
public class InfoModelTool {
    public static InfoDecryptModel decryptModel(InfoModel model) {
        return decryptModel(model, new InfoDecryptModel());
    }

    public static InfoDecryptModel decryptModel(InfoModel model, InfoDecryptModel decryptModel) {
        // Copy same date and status
        decryptModel.setStatus(model.getStatus());
        decryptModel.setCreateDate(model.getCreateDate());
        decryptModel.setUpdateDate(model.getUpdateDate());
        decryptModel.setLastDate(model.getLastDate());

        // Set default value
        decryptModel.setDescription(AppPresenter.decrypt(model.getDescription()));
        decryptModel.setPassword(AppPresenter.decrypt(model.getPassword()));
        decryptModel.setRemark(AppPresenter.decrypt(model.getRemark()));
        decryptModel.setColor(model.getColor());

        String text = getDecryptFieldText(model.getUserName());
        decryptModel.setUserName(text);

        text = getDecryptFieldText(model.getEmail());
        decryptModel.setEmail(text);

        text = getDecryptFieldText(model.getCall());
        decryptModel.setCall(text);

        text = getDecryptFieldText(model.getQQ());
        decryptModel.setQQ(text);

        // Site
        text = null;
        SiteModel siteModel = model.getSite();
        if (siteModel != null)
            text = siteModel.getUrl();
        decryptModel.setSite(text);

        return decryptModel;
    }

    public static InfoModel encryptModel(InfoDecryptModel decryptModel) {
        return encryptModel(decryptModel, new InfoModel());
    }

    public static InfoModel encryptModel(InfoDecryptModel decryptModel, InfoModel model) {
        // Set model values
        model.setDescription(AppPresenter.encrypt(decryptModel.getDescription()));
        model.setPassword(AppPresenter.encrypt(decryptModel.getPassword()));
        model.setColor(decryptModel.getColor());

        // Fields
        String text = decryptModel.getUserName();
        FieldModel field = getFieldModel(AppPresenter.encrypt(text), FieldModel.TAG_USERNAME);
        model.setUserName(field);

        text = decryptModel.getEmail();
        field = getFieldModel(AppPresenter.encrypt(text), FieldModel.TAG_EMAIL);
        model.setEmail(field);

        text = decryptModel.getCall();
        field = getFieldModel(AppPresenter.encrypt(text), FieldModel.TAG_CALL);
        model.setCall(field);


        text = decryptModel.getQQ();
        field = getFieldModel(AppPresenter.encrypt(text), FieldModel.TAG_QQ);
        model.setQQ(field);

        // Site
        text = decryptModel.getSite();
        model.setSite(getSiteModel(text));

        // Remark
        text = decryptModel.getRemark();
        if (!TextUtils.isEmpty(text)) {
            model.setRemark(AppPresenter.encrypt(text));
        }

        // Set last time
        model.setLastDate(System.currentTimeMillis());

        return model;
    }

    private static String getDecryptFieldText(FieldModel model) {
        if (model != null) {
            String text = model.getText();
            return AppPresenter.decrypt(text);
        }
        return null;
    }

    private static String getFormatUrl(String url) {
        return url;
    }

    private static String getSampleUrl(String url) {
        return url;
    }

    private static SiteModel getSiteModel(String url) {
        if (isEmpty(url))
            return null;
        // Trim
        url = url.trim();
        url = getFormatUrl(url);
        String md5 = HashKit.getMD5String(url);
        SiteModel model = SiteModel.get(md5);
        if (model == null) {
            model = new SiteModel();
            model.setMd5(md5);
            model.setUrl(url);
            model.setName(getSampleUrl(url));
            model.save();
        }
        return model;
    }

    private static FieldModel getFieldModel(String enText, int tag) {
        if (isEmpty(enText))
            return null;
        // Trim
        enText = enText.trim();
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

    private static boolean isEmpty(String str) {
        if (str == null)
            return true;
        else {
            str = str.trim();
            return str.length() == 0;
        }
    }
}
