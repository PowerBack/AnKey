package net.qiujuer.powerback.ankey.tool;

/**
 * Created by qiujuer
 * on 15/10/23.
 */
public class KeyToolHelper {
    public static KeyTool createDefaultKeyTool(String key){
        return new AESCipherHelper(key);
    }
}
