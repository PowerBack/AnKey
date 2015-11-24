package net.qiujuer.powerback.ankey.model.view;

import android.graphics.Color;
import android.text.TextUtils;

import net.qiujuer.genius.res.Resource;
import net.qiujuer.powerback.ankey.model.db.InfoModel;

import java.util.UUID;

/**
 * Created by qiujuer
 * on 15/10/29.
 */
public class InfoViewModel {
    private UUID id;
    private String description;
    private int color;
    private long lastDate;

    public InfoViewModel() {

    }

    public InfoViewModel(InfoModel model) {
        this.id = model.getInfoId();
        this.lastDate = model.getLastDate();
    }

    public UUID getId() {
        return id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
        this.color = getStringColor(this.description);
    }

    public int getColor() {
        return color;
    }

    public static int getStringColor(String name) {
        int bgColor = Color.TRANSPARENT;
        int[] colorArray = Resource.Color.COLORS;
        int index = 1;
        if (!TextUtils.isEmpty(name)) {
            index = Math.abs(name.hashCode()) % (colorArray.length - 1);
            index++;
        }
        if (index < colorArray.length)
            bgColor = colorArray[index];
        return bgColor;
    }

    public long getLastDate() {
        return lastDate;
    }

    public void setColor(int color) {
        this.color = color;
    }

    public void setLastDate(long lastDate) {
        this.lastDate = lastDate;
    }
}
