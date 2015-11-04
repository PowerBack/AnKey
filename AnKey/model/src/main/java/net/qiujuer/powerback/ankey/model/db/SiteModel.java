package net.qiujuer.powerback.ankey.model.db;

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
@Table(name = "Site")
public class SiteModel extends Model implements ModelStatus {
    @Column(name = "SiteId")
    private UUID siteId;

    @Column(name = "Name")
    private String name;

    @Column(name = "MD5")
    private String md5;

    @Column(name = "Url")
    private String url;

    @Column(name = "Icon")
    private IconModel icon;

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

    public SiteModel() {
        super();
        siteId = UUID.randomUUID();
        lastDate = System.currentTimeMillis();
        createDate = new Date(lastDate);
        updateDate = createDate;
    }

    public void setUpdateDate(Date updateDate) {
        this.updateDate = updateDate;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    public void setIcon(IconModel icon) {
        this.icon = icon;
    }

    public void setMd5(String md5) {
        this.md5 = md5;
    }

    public void setSiteId(UUID siteId) {
        this.siteId = siteId;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public void setLastDate(long lastDate) {
        this.lastDate = lastDate;
    }

    public Date getUpdateDate() {
        return updateDate;
    }

    public Date getCreateDate() {
        return createDate;
    }

    public String getName() {
        return name;
    }

    public String getMd5() {
        return md5;
    }

    public IconModel getIcon() {
        return icon;
    }

    public UUID getSiteId() {
        return siteId;
    }

    public String getUrl() {
        return url;
    }

    public int getStatus() {
        return status;
    }

    public long getLastDate() {
        return lastDate;
    }

    public static SiteModel getSiteModel(UUID uuid) {
        return new Select()
                .from(SiteModel.class)
                .where("SiteId = ?", uuid.toString())
                .executeSingle();
    }

    public static SiteModel get(long id) {
        return new Select()
                .from(SiteModel.class)
                .where("id = ?", id)
                .executeSingle();
    }

    public static List<SiteModel> getAll() {
        return new Select()
                .from(SiteModel.class)
                .execute();
    }

}
