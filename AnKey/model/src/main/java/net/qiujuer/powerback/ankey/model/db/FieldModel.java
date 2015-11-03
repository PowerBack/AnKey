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
    @Column(name = "FieldId")
    private UUID fieldId;

    @Column(name = "UserId")
    public UUID userId;

    @Column(name = "Text")
    private String text;

    @Column(name = "MD5")
    private String md5;

    @Column(name = "Tag")
    private String tag;

    @Column(name = "CreateDate")
    private String createDate;

    @Column(name = "UpdateDate")
    private String updateDate;

    @Column(name = "Encryption")
    private int encryption;

    public FieldModel() {
        super();
    }

    public void setUserId(UUID userId) {
        this.userId = userId;
    }

    public void setUpdateDate(String updateDate) {
        this.updateDate = updateDate;
    }

    public void setTag(String tag) {
        this.tag = tag;
    }

    public void setCreateDate(String createDate) {
        this.createDate = createDate;
    }

    public void setEncryption(int encryption) {
        this.encryption = encryption;
    }

    public void setFieldId(UUID fieldId) {
        this.fieldId = fieldId;
    }

    public void setMd5(String md5) {
        this.md5 = md5;
    }

    public void setText(String text) {
        this.text = text;
    }

    public UUID getUserId() {
        return userId;
    }

    public String getUpdateDate() {
        return updateDate;
    }

    public String getTag() {
        return tag;
    }

    public int getEncryption() {
        return encryption;
    }

    public String getCreateDate() {
        return createDate;
    }

    public String getMd5() {
        return md5;
    }

    public String getText() {
        return text;
    }

    public UUID getFieldId() {
        return fieldId;
    }

    public static FieldModel getFieldModel(UUID uuid) {
        return new Select()
                .from(FieldModel.class)
                .where("FiledId = ?", uuid.toString())
                .executeSingle();
    }

    public static FieldModel get(long id) {
        return new Select()
                .from(FieldModel.class)
                .where("id = ?", id)
                .executeSingle();
    }

    public static List<FieldModel> getAll() {
        return new Select()
                .from(FieldModel.class)
                .execute();
    }
}
