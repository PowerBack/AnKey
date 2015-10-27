package net.qiujuer.powerback.ankey.tool.key;

/**
 * Created by qiujuer
 * on 15/10/23.
 */
public class KeyToolHelper {
    public static KeyTool createDefaultKeyTool(String key, String salt) {
        return new AESCipherHelper(key, salt);
    }
}
