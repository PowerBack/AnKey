package net.qiujuer.powerback.ankey.model.view;

import android.graphics.Color;
import android.text.TextUtils;

import net.qiujuer.genius.res.Resource;

import java.util.UUID;

/**
 * Created by qiujuer
 * on 15/10/29.
 */
public class InfoViewModel {
    private UUID id;
    private String description;
    private int color;

    public InfoViewModel() {

    }

    public InfoViewModel(UUID id) {
        this.id = id;
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
}
