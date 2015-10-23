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
    private UUID IconId;
    @Column(name = "Name")
    private String Name;
    @Column(name = "MD5")
    private String MD5;
    @Column(name = "Src")
    private String Src;
    @Column(name = "CreateDate")
    private String CreateDate;
    @Column(name = "UpdateDate")
    private String UpdateDate;

    public UUID getIconId() {
        return IconId;
    }

    public void setIconId(UUID iconId) {
        IconId = iconId;
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

    public String getSrc() {
        return Src;
    }

    public void setSrc(String src) {
        Src = src;
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

    public static IconModel getIconModel(UUID uuid) {
        return new Select()
                .from(IconModel.class)
                .where("IconId = ?",uuid.toString())
                .executeSingle();
    }

    public static IconModel get(long id) {
        return new Select()
                .from(IconModel.class)
                .where("id = ?", id)
                .executeSingle();
    }

    public static List<IconModel> getAll(){
        return new Select()
                .from(IconModel.class)
                .execute();
    }
}
