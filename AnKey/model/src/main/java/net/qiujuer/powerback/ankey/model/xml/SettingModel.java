package net.qiujuer.powerback.ankey.model.xml;

import android.content.SharedPreferences;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;

import net.qiujuer.powerback.ankey.model.AppModel;

/**
 * Created by qiujuer
 * on 15/10/19.
 */
public class SettingModel extends XmlPreference {
    private int verCode;

    public SettingModel() {
        super();
    }

    @Override
    protected String getPreferenceName() {
        return SettingModel.class.getName();
    }

    @Override
    protected void refresh(SharedPreferences sp) {
        verCode = sp.getInt("verCode", -1);
    }

    @Override
    public void save() {
        SharedPreferences sp = getSharedPreferences();
        SharedPreferences.Editor editor = sp.edit();

        editor.putInt("verCode", verCode);

        editor.apply();
    }

    public boolean isFirstUse() {
        int verCode = getVerCode();
        if (this.verCode < verCode) {
            this.verCode = verCode;
            save();
            return true;
        } else
            return false;
    }


    public static int getVerCode() {
        PackageInfo info = getPackageInfo();
        if (info != null)
            return info.versionCode;
        return -1;
    }


    public static String getVerName() {
        PackageInfo info = getPackageInfo();
        if (info != null)
            return info.versionName;
        return null;
    }

    public static PackageInfo getPackageInfo() {
        try {
            PackageManager manager = AppModel.getApplication().getPackageManager();
            return manager.getPackageInfo(AppModel.getApplication().getPackageName(), 0);
        } catch (Exception e) {
            return null;
        }
    }
}
