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
@Table(name = "Icon")
public class IconModel extends Model {
    @Column(name = "IconId")
    private UUID iconId;

    @Column(name = "Name")
    private String name;

    @Column(name = "MD5")
    private String md5;

    @Column(name = "Src")
    private String src;

    @Column(name = "CreateDate")
    private String createDate;

    @Column(name = "UpdateDate")
    private String updateDate;

    public IconModel() {
        super();
    }

    public void setMd5(String md5) {
        this.md5 = md5;
    }

    public void setCreateDate(String createDate) {
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

    public void setUpdateDate(String updateDate) {
        this.updateDate = updateDate;
    }

    public String getMd5() {
        return md5;
    }

    public String getCreateDate() {
        return createDate;
    }

    public String getName() {
        return name;
    }

    public String getSrc() {
        return src;
    }

    public String getUpdateDate() {
        return updateDate;
    }

    public UUID getIconId() {
        return iconId;
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
