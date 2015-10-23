package net.qiujuer.powerback.ankey.tool;

/**
 * Created by qiujuer
 * on 15/10/23.
 */
public interface KeyTool {
    String encrypt(String src);

    String decrypt(String encrypted);
}
