package net.qiujuer.powerback.ankey.model.db;

import com.activeandroid.Model;
import com.activeandroid.annotation.Column;
import com.activeandroid.annotation.Table;
import com.activeandroid.query.Select;

import java.util.List;
import java.util.UUID;

/**
 * Created by qiujuer
 * on 15/10/19.
 */
@Table(name = "info")
public class InfoModel extends Model {

    @Column(name = "InfoId")
    private UUID InfoId;
    @Column(name = "UserId")
    private UUID UserId;
    @Column(name = "Site")
    public SiteModel Site;
    @Column(name = "UserName")
    private FieldModel UserName;
    @Column(name = "Email")
    private FieldModel Email;
    @Column(name = "QQ")
    private FieldModel QQ;
    @Column(name = "Call")
    private FieldModel Call;
    @Column(name = "Password")
    private String Password;
    @Column(name = "Remark")
    private String Remark;
    @Column(name = "Tag")
    private String Tag;
    @Column(name = "CreateDate")
    private String CreateDate;
    @Column(name = "UpdateDate")
    private String UpdateDate;
    @Column(name = "Encryption")
    private int Encryption;


    public UUID getInfoId() {
        return InfoId;
    }

    public void setInfoId(UUID infoId) {
        InfoId = infoId;
    }

    public UUID getUserId() {
        return UserId;
    }

    public void setUserId(UUID userId) {
        UserId = userId;
    }

    public SiteModel getSite() {
        return Site;
    }

    public void setSite(SiteModel site) {
        Site = site;
    }

    public FieldModel getUserName() {
        return UserName;
    }

    public void setUserName(FieldModel userName) {
        UserName = userName;
    }

    public FieldModel getEmail() {
        return Email;
    }

    public void setEmail(FieldModel email) {
        Email = email;
    }

    public FieldModel getQQ() {
        return QQ;
    }

    public void setQQ(FieldModel QQ) {
        this.QQ = QQ;
    }

    public FieldModel getCall() {
        return Call;
    }

    public void setCall(FieldModel call) {
        Call = call;
    }

    public String getPassword() {
        return Password;
    }

    public void setPassword(String password) {
        Password = password;
    }

    public String getRemark() {
        return Remark;
    }

    public void setRemark(String remark) {
        Remark = remark;
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

    InfoModel() {
        super();
    }

    public static InfoModel getInfoModel(UUID uuid) {
        return new Select()
                .from(InfoModel.class)
                .where("InfoId = ?",uuid.toString())
                .executeSingle();
    }

    public static InfoModel get(long id) {
        return new Select()
                .from(InfoModel.class)
                .where("id = ?", id)
                .executeSingle();
    }

    public List<InfoModel> getAll(){
        return new Select()
                .from(InfoModel.class)
                .execute();
    }
}
