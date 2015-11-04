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
 * Created by qiujuer
 * on 15/10/19.
 */
@Table(name = "info")
public class InfoModel extends Model implements ModelStatus {

    @Column(name = "InfoId")
    private UUID infoId;

    @Column(name = "UserId")
    private UUID userId;

    @Column(name = "Description")
    private String description;

    @Column(name = "Site")
    public SiteModel site;

    @Column(name = "UserName")
    private FieldModel userName;

    @Column(name = "Email")
    private FieldModel email;

    @Column(name = "QQ")
    private FieldModel qq;

    @Column(name = "Call")
    private FieldModel call;

    @Column(name = "Password")
    private String password;

    @Column(name = "Remark")
    private String remark;

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

    public InfoModel() {
        super();
        infoId = UUID.randomUUID();
        lastDate = System.currentTimeMillis();
        createDate = new Date(lastDate);
        updateDate = createDate;
    }

    public void setCall(FieldModel call) {
        this.call = call;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setEmail(FieldModel email) {
        this.email = email;
    }

    public void setEncryption(int encryption) {
        this.encryption = encryption;
    }

    public void setInfoId(UUID infoId) {
        this.infoId = infoId;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setQQ(FieldModel qq) {
        this.qq = qq;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public void setSite(SiteModel site) {
        this.site = site;
    }

    public void setTag(String tag) {
        this.tag = tag;
    }

    public void setUpdateDate(Date updateDate) {
        this.updateDate = updateDate;
    }

    public void setUserId(UUID userId) {
        this.userId = userId;
    }

    public void setUserName(FieldModel userName) {
        this.userName = userName;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public void setLastDate(long lastDate) {
        this.lastDate = lastDate;
    }

    public FieldModel getCall() {
        return call;
    }

    public FieldModel getEmail() {
        return email;
    }

    public FieldModel getQQ() {
        return qq;
    }

    public FieldModel getUserName() {
        return userName;
    }

    public int getEncryption() {
        return encryption;
    }

    public SiteModel getSite() {
        return site;
    }

    public Date getCreateDate() {
        return createDate;
    }

    public String getDescription() {
        return description;
    }

    public String getPassword() {
        return password;
    }

    public String getRemark() {
        return remark;
    }

    public String getTag() {
        return tag;
    }

    public Date getUpdateDate() {
        return updateDate;
    }

    public UUID getInfoId() {
        return infoId;
    }

    public UUID getUserId() {
        return userId;
    }

    public int getStatus() {
        return status;
    }

    public long getLastDate() {
        return lastDate;
    }

    public static InfoModel getInfoModel(UUID uuid) {
        return new Select()
                .from(InfoModel.class)
                .where("InfoId = ?", uuid.toString())
                .executeSingle();
    }

    public static InfoModel get(long id) {
        return new Select()
                .from(InfoModel.class)
                .where("id = ?", id)
                .executeSingle();
    }

    public static List<InfoModel> getAll() {
        return new Select()
                .from(InfoModel.class)
                .execute();
    }
}
