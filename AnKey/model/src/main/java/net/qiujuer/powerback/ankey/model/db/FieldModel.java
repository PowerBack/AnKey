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
 * on 15/10/23.
 */
@Table(name = "Field")
public class FieldModel extends Model implements ModelStatus {
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
    private Date createDate;

    @Column(name = "UpdateDate")
    private Date updateDate;

    @Column(name = "Encryption")
    private int encryption;

    @Expose
    @Column(name = "Status")
    private transient int status;

    @Expose
    @Column(name = "LastDate")
    private transient long lastDate;

    public FieldModel() {
        super();
        fieldId = UUID.randomUUID();
        lastDate = System.currentTimeMillis();
        createDate = new Date(lastDate);
        updateDate = createDate;
    }

    public void setUserId(UUID userId) {
        this.userId = userId;
    }

    public void setUpdateDate(Date updateDate) {
        this.updateDate = updateDate;
    }

    public void setTag(String tag) {
        this.tag = tag;
    }

    public void setCreateDate(Date createDate) {
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

    public void setStatus(int status) {
        this.status = status;
    }

    public void setLastDate(long lastDate) {
        this.lastDate = lastDate;
    }

    public void setText(String text) {
        this.text = text;
    }

    public UUID getUserId() {
        return userId;
    }

    public Date getUpdateDate() {
        return updateDate;
    }

    public String getTag() {
        return tag;
    }

    public int getEncryption() {
        return encryption;
    }

    public Date getCreateDate() {
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

    public int getStatus() {
        return status;
    }

    public long getLastDate() {
        return lastDate;
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
