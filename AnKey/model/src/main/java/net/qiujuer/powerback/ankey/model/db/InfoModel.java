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
    public UUID Site;
    @Column(name = "UserName")
    private UUID UserName;
    @Column(name = "Email")
    private UUID Email;
    @Column(name = "QQ")
    private UUID QQ;
    @Column(name = "Call")
    private UUID Call;
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


    InfoModel() {
        super();
    }

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

    public UUID getSite() {
        return Site;
    }

    public void setSite(UUID site) {
        Site = site;
    }

    public UUID getUserName() {
        return UserName;
    }

    public void setUserName(UUID userName) {
        UserName = userName;
    }

    public UUID getEmail() {
        return Email;
    }

    public void setEmail(UUID email) {
        Email = email;
    }

    public UUID getQQ() {
        return QQ;
    }

    public void setQQ(UUID QQ) {
        this.QQ = QQ;
    }

    public UUID getCall() {
        return Call;
    }

    public void setCall(UUID call) {
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

    public static List<InfoModel> getAll(){
        return new Select()
                .from(InfoModel.class)
                .execute();
    }
}
