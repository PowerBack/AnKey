package net.qiujuer.powerback.ankey.model.xml;

import android.content.Context;
import android.content.SharedPreferences;

import net.qiujuer.powerback.ankey.model.AppModel;

/**
 * Created by qiujuer
 * on 15/10/19.
 */
public abstract class XmlPreference {

    public XmlPreference() {
        refresh(getSharedPreferences());
    }

    protected SharedPreferences getSharedPreferences() {
        Context context = AppModel.getApplication();
        return context.getSharedPreferences(getPreferenceName(), Context.MODE_PRIVATE);
    }

    protected abstract void refresh(SharedPreferences sp);

    protected abstract String getPreferenceName();

    public abstract void save();
}
