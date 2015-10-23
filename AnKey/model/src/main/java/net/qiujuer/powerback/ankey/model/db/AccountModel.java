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
@Table(name = "Account")
public class AccountModel extends Model {
    @Column(name = "AccountId")
    private UUID AccountId;
    @Column(name = "UserName")
    private String UserName;
    @Column(name = "Password")
    private UUID Password;
    //@Column(name="")")"//Taken
    //private String Taken

    @Column(name = "Key")
    private String Key;
    @Column(name = "CreateDate")
    private String CreateDate;
    @Column(name = "UpdateDate")
    private String UpdateDate;

    public UUID getAccountId() {
        return AccountId;
    }

    public void setAccountId(UUID accountId) {
        AccountId = accountId;
    }

    public String getUserName() {
        return UserName;
    }

    public void setUserName(String userName) {
        UserName = userName;
    }

    public UUID getPassword() {
        return Password;
    }

    public void setPassword(UUID password) {
        Password = password;
    }

    public String getKey() {
        return Key;
    }

    public void setKey(String key) {
        Key = key;
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

    public static AccountModel getAccountModel(UUID uuid) {
        return new Select()
                .from(AccountModel.class)
                .where("AccountId = ?",uuid.toString())
                .executeSingle();
    }

    public static AccountModel get(long id) {
        return new Select()
                .from(InfoModel.class)
                .where("id = ?", id)
                .executeSingle();
    }

    public static List<AccountModel> getAll(){
        return new Select()
                .from(AccountModel.class)
                .execute();
    }
}
