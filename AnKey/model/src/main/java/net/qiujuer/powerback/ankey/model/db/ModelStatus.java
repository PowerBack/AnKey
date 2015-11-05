package net.qiujuer.powerback.ankey.model.db;

/**
 * Created by qiujuer
 * on 15/11/4.
 */
public interface ModelStatus {
    int KEY_TOOL_VERSION = 1;

    int STATUS_DELETE = -1;
    int STATUS_CREATE = 0;
    int STATUS_EDIT = 2;
    int STATUS_SYNC = 4;
}
