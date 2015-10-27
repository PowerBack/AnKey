package net.qiujuer.powerback.ankey.tool.key;

/**
 * Created by qiujuer
 * on 15/10/23.
 */
public interface KeyTool {
    String encrypt(String src);

    String decrypt(String encrypted);
}
