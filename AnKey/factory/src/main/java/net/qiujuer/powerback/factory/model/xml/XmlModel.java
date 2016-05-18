package net.qiujuer.powerback.factory.model.xml;

import android.content.Context;
import android.content.SharedPreferences;

import net.qiujuer.powerback.common.Common;
import net.qiujuer.powerback.common.utils.Log;

import java.lang.reflect.Field;

/**
 * Created by qiujuer
 * on 16/5/18.
 */
public class XmlModel {

    public XmlModel() {
        load();
    }

    protected SharedPreferences getSharedPreferences() {
        Context context = Common.getApp();
        return context.getSharedPreferences(getPreferenceName(), Context.MODE_PRIVATE);
    }

    protected String getPreferenceName() {
        return this.getClass().getName();
    }

    public final void save() {
        // call notify
        onSaveBefore();
        // get the SharedPreferences
        SharedPreferences sp = getSharedPreferences();
        SharedPreferences.Editor editor = sp.edit();

        // put value
        for (Field field : getClass().getDeclaredFields()) {
            final String fieldName = field.getName();
            Class<?> fieldType = field.getType();

            // change the Field accessible status
            field.setAccessible(true);

            try {
                Object value = field.get(this);

                // can't know the type until runtime.
                if (value == null) {
                    // Todo
                } else if (fieldType.equals(Byte.class) || fieldType.equals(byte.class)) {
                    editor.putInt(fieldName, (Byte) value);
                } else if (fieldType.equals(Short.class) || fieldType.equals(short.class)) {
                    editor.putInt(fieldName, (Short) value);
                } else if (fieldType.equals(Integer.class) || fieldType.equals(int.class)) {
                    editor.putInt(fieldName, (Integer) value);
                } else if (fieldType.equals(Long.class) || fieldType.equals(long.class)) {
                    editor.putLong(fieldName, (Long) value);
                } else if (fieldType.equals(Float.class) || fieldType.equals(float.class)) {
                    editor.putFloat(fieldName, (Float) value);
                } else if (fieldType.equals(Double.class) || fieldType.equals(double.class)) {
                    editor.putString(fieldName, (String.valueOf((Double) value)));
                } else if (fieldType.equals(Boolean.class) || fieldType.equals(boolean.class)) {
                    editor.putBoolean(fieldName, (Boolean) value);
                } else if (fieldType.equals(Character.class) || fieldType.equals(char.class)) {
                    editor.putString(fieldName, value.toString());
                } else if (fieldType.equals(String.class)) {
                    editor.putString(fieldName, value.toString());
                } else if (fieldType.equals(Byte[].class) || fieldType.equals(byte[].class)) {
                    // Todo
                } else {
                    // Todo
                }
            } catch (IllegalArgumentException | IllegalAccessException e) {
                Log.e(e.getClass().getName(), "save", e);
            }
        }

        editor.apply();
        onSaveAfter();
    }

    protected void onSaveBefore() {

    }

    protected void onSaveAfter() {

    }


    private void load() {
        // get the SharedPreferences
        SharedPreferences sp = getSharedPreferences();

        for (Field field : getClass().getDeclaredFields()) {
            final String fieldName = field.getName();
            Class<?> fieldType = field.getType();

            // change the Field accessible status
            field.setAccessible(true);

            try {
                Object value = null;

                if (fieldType.equals(Byte.class) || fieldType.equals(byte.class)) {
                    value = sp.getInt(fieldName, 0);
                } else if (fieldType.equals(Short.class) || fieldType.equals(short.class)) {
                    value = sp.getInt(fieldName, 0);
                } else if (fieldType.equals(Integer.class) || fieldType.equals(int.class)) {
                    value = sp.getInt(fieldName, 0);
                } else if (fieldType.equals(Long.class) || fieldType.equals(long.class)) {
                    value = sp.getLong(fieldName, 0);
                } else if (fieldType.equals(Float.class) || fieldType.equals(float.class)) {
                    value = sp.getFloat(fieldName, 0);
                } else if (fieldType.equals(Double.class) || fieldType.equals(double.class)) {
                    value = Double.valueOf(sp.getString(fieldName, "0"));
                } else if (fieldType.equals(Boolean.class) || fieldType.equals(boolean.class)) {
                    value = sp.getBoolean(fieldName, false);
                } else if (fieldType.equals(Character.class) || fieldType.equals(char.class)) {
                    value = sp.getString(fieldName, "");
                } else if (fieldType.equals(String.class)) {
                    value = sp.getString(fieldName, "");
                } else if (fieldType.equals(Byte[].class) || fieldType.equals(byte[].class)) {
                    // Todo
                } else {
                    // Todo
                }

                // Set the field value
                if (value != null) {
                    field.set(this, value);
                }
            } catch (IllegalArgumentException | IllegalAccessException | SecurityException e) {
                Log.e(e.getClass().getName(), "load", e);
            }
        }
    }


}
