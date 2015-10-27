package net.qiujuer.powerback.ankey.model.xml;

import android.content.SharedPreferences;

import net.qiujuer.powerback.ankey.model.AppModel;

import java.util.UUID;

/**
 * Created by qiujuer
 */
public class UserModel extends XmlPreference {
    private UUID id = AppModel.EMPTY_ID;
    private String name;
    private String taken;
    private String key;
    private String salt;

    @Override
    protected void refresh(SharedPreferences sp) {
        id = UUID.fromString(sp.getString("id", AppModel.EMPTY_ID.toString()));
        name = sp.getString("name", name);
        taken = sp.getString("taken", taken);
        key = sp.getString("key", key);
        salt = sp.getString("salt", salt);
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
        editor.putString("salt", salt);

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

    public void setSalt(String salt) {
        this.salt = salt;
    }

    public String getSalt() {
        return salt;
    }
}
