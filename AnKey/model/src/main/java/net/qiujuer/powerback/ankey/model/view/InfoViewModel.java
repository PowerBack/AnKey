package net.qiujuer.powerback.ankey.model.view;

import net.qiujuer.powerback.ankey.model.db.InfoModel;

import java.util.UUID;

/**
 * Created by qiujuer
 * on 15/10/29.
 */
public class InfoViewModel {
    private UUID id;

    public InfoViewModel() {

    }

    public InfoViewModel(InfoModel model) {
        set(model);
    }

    public void set(InfoModel model) {
        this.id = model.getInfoId();
    }

    public UUID getId() {
        return id;
    }
}
