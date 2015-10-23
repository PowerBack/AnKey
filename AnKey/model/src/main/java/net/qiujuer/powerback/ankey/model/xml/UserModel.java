package net.qiujuer.powerback.ankey.model.xml;

import android.content.SharedPreferences;

import net.qiujuer.powerback.ankey.model.Model;

import java.util.UUID;

/**
 * Created by qiujuer
 * on 15/10/19.
 */
public class UserModel extends XmlPreference {
    private UUID id = Model.EMPTY_ID;
    private String name;
    private String taken;
    private String key;


    @Override
    protected void refresh(SharedPreferences sp) {
        id = UUID.fromString(sp.getString("id", id.toString()));
        name = sp.getString("name", name);
        taken = sp.getString("taken", taken);
        key = sp.getString("key", key);
    }

    @Override
    protected String getPreferenceName() {
        return SettingModel.class.getName();
    }

    @Override
    public void save() {
        SharedPreferences sp = getSharedPreferences();
        SharedPreferences.Editor editor = sp.edit();

        editor.putString("id", id.toString());
        editor.putString("name", name);
        editor.putString("taken", taken);
        editor.putString("key", key);

        editor.apply();
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setTaken(String taken) {
        this.taken = taken;
    }

    public UUID getId() {
        return id;
    }

    public String getKey() {
        return key;
    }

    public String getName() {
        return name;
    }

    public String getTaken() {
        return taken;
    }
}
