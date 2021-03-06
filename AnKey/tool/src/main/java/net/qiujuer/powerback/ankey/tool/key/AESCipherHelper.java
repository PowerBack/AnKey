package net.qiujuer.powerback.ankey.tool.key;

import java.io.UnsupportedEncodingException;
import java.security.GeneralSecurityException;

/**
 * Created by qiujuer
 * on 15/10/23.
 */
class AESCipherHelper implements KeyTool {
    private AesCbcWithIntegrity.SecretKeys keys;

    public AESCipherHelper(String key, String salt) {
        try {
            keys = AesCbcWithIntegrity.generateKeyFromPassword(key, salt);
        } catch (GeneralSecurityException e) {
            e.printStackTrace();
        }
    }

    @Override
    public String encrypt(String src) {
        if (keys == null) {
            throw new NullPointerException("This key is null, you should create new KeyTool");
        }
        try {
            AesCbcWithIntegrity.CipherTextIvMac cipherTextIvMac = AesCbcWithIntegrity.encrypt(src, keys);
            //store or send to server
            return cipherTextIvMac.toString();
        } catch (UnsupportedEncodingException | GeneralSecurityException e) {
            e.printStackTrace();
            throw new NullPointerException("This src string isn't encrypt");
        }
    }

    @Override
    public String decrypt(String encrypted) {
        if (keys == null) {
            throw new NullPointerException("This key is null, you should create new KeyTool");
        }
        try {
            AesCbcWithIntegrity.CipherTextIvMac cipherTextIvMac = new AesCbcWithIntegrity.CipherTextIvMac(encrypted);
            return AesCbcWithIntegrity.decryptString(cipherTextIvMac, keys);
        } catch (UnsupportedEncodingException | GeneralSecurityException e) {
            e.printStackTrace();
            throw new NullPointerException("This src string isn't encrypt");
        }
    }

    @Override
    public void destroyKey() {
        keys = null;
    }
}
