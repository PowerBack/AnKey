package net.qiujuer.powerback.ankey.model.db;

import com.activeandroid.Model;
import com.activeandroid.annotation.Column;
import com.activeandroid.annotation.Table;
import com.activeandroid.query.Select;

import java.util.List;
import java.util.UUID;

/**
 * Created by wugian
 * on 15/10/24.
 */
@Table(name = "Site")
public class SiteModel extends Model{
    @Column(name = "SiteId")
    private String SiteId;
    @Column(name = "Name")
    private String Name;
    @Column(name = "MD5")
    private String MD5;
    @Column(name = "Url")
    private String Url;
    @Column(name="Icon")
    private IconModel Icon;
    @Column(name = "CreateDate")
    private String CreateDate;
    @Column(name = "UpdateDate")
    private String UpdateDate;

    public String getSiteId() {
        return SiteId;
    }

    public void setSiteId(String siteId) {
        SiteId = siteId;
    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    public String getMD5() {
        return MD5;
    }

    public void setMD5(String MD5) {
        this.MD5 = MD5;
    }

    public String getUrl() {
        return Url;
    }

    public void setUrl(String url) {
        Url = url;
    }

    public IconModel getIcon() {
        return Icon;
    }

    public void setIcon(IconModel icon) {
        Icon = icon;
    }

    public String getCreateDate() {
        return CreateDate;
    }

    public void setCreateDate(String createDate) {
        CreateDate = createDate;
    }

    public String getUpdateDate() {
        return UpdateDate;
    }

    public void setUpdateDate(String updateDate) {
        UpdateDate = updateDate;
    }

    public static SiteModel getSiteModel(UUID uuid) {
        return new Select()
                .from(SiteModel.class)
                .where("SiteId = ?",uuid.toString())
                .executeSingle();
    }

    public static SiteModel get(long id) {
        return new Select()
                .from(SiteModel.class)
                .where("id = ?", id)
                .executeSingle();
    }

    public static List<SiteModel> getAll(){
        return new Select()
                .from(SiteModel.class)
                .execute();
    }

}
