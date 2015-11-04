package net.qiujuer.powerback.ankey.model.db;

import android.os.SystemClock;

import com.activeandroid.Model;
import com.activeandroid.annotation.Column;
import com.activeandroid.annotation.Table;
import com.activeandroid.query.Select;
import com.google.gson.annotations.Expose;

import java.util.Date;
import java.util.List;
import java.util.UUID;

/**
 * Created by wugian
 * on 15/10/24.
 */
@Table(name = "Icon")
public class IconModel extends Model implements ModelStatus {
    @Column(name = "IconId")
    private UUID iconId;

    @Column(name = "Name")
    private String name;

    @Column(name = "MD5")
    private String md5;

    @Column(name = "Src")
    private String src;

    @Column(name = "CreateDate")
    private Date createDate;

    @Column(name = "UpdateDate")
    private Date updateDate;

    @Expose
    @Column(name = "Status")
    private transient int status;

    @Expose
    @Column(name = "LastDate")
    private transient long lastDate;

    public IconModel() {
        super();
        iconId = UUID.randomUUID();
        lastDate = SystemClock.uptimeMillis();
        createDate = new Date(lastDate);
        updateDate = createDate;
    }

    public void setMd5(String md5) {
        this.md5 = md5;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    public void setIconId(UUID iconId) {
        this.iconId = iconId;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setSrc(String src) {
        this.src = src;
    }

    public void setUpdateDate(Date updateDate) {
        this.updateDate = updateDate;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public void setLastDate(long lastDate) {
        this.lastDate = lastDate;
    }

    public String getMd5() {
        return md5;
    }

    public Date getCreateDate() {
        return createDate;
    }

    public String getName() {
        return name;
    }

    public String getSrc() {
        return src;
    }

    public Date getUpdateDate() {
        return updateDate;
    }

    public UUID getIconId() {
        return iconId;
    }

    public int getStatus() {
        return status;
    }

    public long getLastDate() {
        return lastDate;
    }

    public static IconModel getIconModel(UUID uuid) {
        return new Select()
                .from(IconModel.class)
                .where("IconId = ?", uuid.toString())
                .executeSingle();
    }

    public static IconModel get(long id) {
        return new Select()
                .from(IconModel.class)
                .where("id = ?", id)
                .executeSingle();
    }

    public static List<IconModel> getAll() {
        return new Select()
                .from(IconModel.class)
                .execute();
    }
}
