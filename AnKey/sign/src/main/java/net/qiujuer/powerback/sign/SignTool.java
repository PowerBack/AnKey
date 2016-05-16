package net.qiujuer.powerback.sign;

/**
 * Created by qiujuer
 * on 16/5/16.
 */
public interface SignTool {
    String encrypt(String src);

    String decrypt(String encrypted);

    void destroy();
}
