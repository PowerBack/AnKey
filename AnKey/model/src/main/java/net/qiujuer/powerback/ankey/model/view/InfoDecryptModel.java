package net.qiujuer.powerback.ankey.model.view;

import java.util.Date;
import java.util.UUID;

/**
 * Created by qiujuer
 * on 15/10/29.
 */
public class InfoDecryptModel {
    private UUID infoId;

    private String description;

    public String site;

    public int color;

    private String userName;

    private String email;

    private String qq;

    private String call;

    private String password;

    private String remark;

    private String tag;

    private Date createDate;

    private Date updateDate;

    private int encryption;

    private transient int status;

    private transient long lastDate;

    public void setLastDate(long lastDate) {
        this.lastDate = lastDate;
    }

    public void setColor(int color) {
        this.color = color;
    }

    public void setCall(String call) {
        this.call = call;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setEmail(String email) {
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

    public void setQQ(String qq) {
        this.qq = qq;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public void setSite(String site) {
        this.site = site;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public void setTag(String tag) {
        this.tag = tag;
    }

    public void setUpdateDate(Date updateDate) {
        this.updateDate = updateDate;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public long getLastDate() {
        return lastDate;
    }

    public Date getCreateDate() {
        return createDate;
    }

    public Date getUpdateDate() {
        return updateDate;
    }

    public int getColor() {
        return color;
    }

    public int getEncryption() {
        return encryption;
    }

    public int getStatus() {
        return status;
    }

    public String getCall() {
        return call;
    }

    public String getDescription() {
        return description;
    }

    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }

    public String getQQ() {
        return qq;
    }

    public String getRemark() {
        return remark;
    }

    public String getSite() {
        return site;
    }

    public String getTag() {
        return tag;
    }

    public String getUserName() {
        return userName;
    }

    public UUID getInfoId() {
        return infoId;
    }
}
