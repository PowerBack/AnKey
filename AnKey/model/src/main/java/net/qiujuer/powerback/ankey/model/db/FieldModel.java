package net.qiujuer.powerback.ankey.model.db;

import com.activeandroid.Model;
import com.activeandroid.annotation.Column;
import com.activeandroid.annotation.Table;
import com.activeandroid.query.Select;

import java.util.List;
import java.util.UUID;

/**
 * Created by wugian
 * on 15/10/23.
 */
@Table(name = "Field")
public class FieldModel extends Model {
    @Column(name = "FiledId")
    private UUID FieldId;
    @Column(name = "UserId")
    public UUID UserId;
    @Column(name = "Text")
    private String Text;
    @Column(name = "MD5")
    private String MD5;
    @Column(name = "Tag")
    private String Tag;
    @Column(name = "CreateDate")
    private String CreateDate;
    @Column(name = "UpdateDate")
    private String UpdateDate;
    @Column(name = "Encryption")
    private int Encryption;

    public UUID getFieldId() {
        return FieldId;
    }

    public void setFieldId(UUID fieldId) {
        FieldId = fieldId;
    }

    public UUID getUserId() {
        return UserId;
    }

    public void setUserId(UUID userId) {
        UserId = userId;
    }

    public String getText() {
        return Text;
    }

    public void setText(String text) {
        Text = text;
    }

    public String getMD5() {
        return MD5;
    }

    public void setMD5(String MD5) {
        this.MD5 = MD5;
    }

    public String getTag() {
        return Tag;
    }

    public void setTag(String tag) {
        Tag = tag;
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

    public int getEncryption() {
        return Encryption;
    }

    public void setEncryption(int encryption) {
        Encryption = encryption;
    }

    public static FieldModel getFieldModel(UUID uuid) {
        return new Select()
                .from(FieldModel.class)
                .where("FiledId = ?",uuid.toString())
                .executeSingle();
    }

    public static FieldModel get(long id) {
        return new Select()
                .from(FieldModel.class)
                .where("id = ?", id)
                .executeSingle();
    }

    public static List<FieldModel> getAll(){
        return new Select()
                .from(FieldModel.class)
                .execute();
    }
}
