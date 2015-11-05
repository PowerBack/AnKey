package net.qiujuer.powerback.ankey.presenter;

import android.util.Log;

import net.qiujuer.genius.kit.util.Tools;
import net.qiujuer.genius.kit.util.UiKit;
import net.qiujuer.powerback.ankey.model.db.InfoModel;
import net.qiujuer.powerback.ankey.model.view.InfoViewModel;
import net.qiujuer.powerback.ankey.presenter.view.InfoListView;

import java.util.List;
import java.util.UUID;

/**
 * Created by qiujuer
 * on 15/10/29.
 */
public class InfoListPresenter {
    private InfoListView mView;
    private Thread mThread;

    public InfoListPresenter(InfoListView view) {
        mView = view;
    }

    public void refresh() {
        if (mThread != null)
            return;

        startLoad();
        mThread = new Thread("InfoListPresenter-Thread") {
            @Override
            public void run() {
                Tools.sleepIgnoreInterrupt(1000);
                loadData();
                mThread = null;
            }
        };
        mThread.start();
    }

    private void loadData() {
        // load data
        List<InfoModel> models = InfoModel.getAll();
        if (models == null || models.size() == 0) {
            stopLoad(false);
        } else {
            stopLoad(true);
            List<InfoViewModel> viewModels = mView.getDataSet();
            for (InfoModel model : models) {
                Tools.sleepIgnoreInterrupt(20);
                if (!formatModel(viewModels, model))
                    break;
            }
        }
    }

    private void notifyInsert(final int index) {
        UiKit.runOnMainThreadAsync(new Runnable() {
            @Override
            public void run() {
                mView.notifyItemChanged(index);
            }
        });
    }

    private void notifyChange(final int index) {
        UiKit.runOnMainThreadAsync(new Runnable() {
            @Override
            public void run() {
                mView.notifyItemChanged(index);
            }
        });
    }

    private boolean formatModel(List<InfoViewModel> viewModels, InfoModel model) {
        InfoViewModel sModel = searchViewModel(viewModels, model);
        boolean isCreate = false;
        if (sModel == null) {
            sModel = new InfoViewModel(model.getInfoId());
            viewModels.add(sModel);
            isCreate = true;
        }
        if (decryptModel(model, sModel)) {
            int index = getModelIndex(viewModels, model);
            Log.e(InfoListPresenter.class.getSimpleName(), viewModels.size() + " " + index + " " + isCreate);
            if (index >= 0) {
                if (isCreate)
                    notifyInsert(index);
                else
                    notifyChange(index);
            }
            return true;
        }
        return false;
    }

    private int getModelIndex(List<InfoViewModel> viewModels, InfoModel model) {
        int index = 0;
        for (InfoViewModel i : viewModels) {
            if (i.getId().equals(model.getInfoId()))
                return index;
            index++;
        }
        return -1;
    }

    private InfoViewModel searchViewModel(List<InfoViewModel> viewModels, InfoModel model) {
        UUID id = model.getInfoId();
        for (InfoViewModel viewModel : viewModels) {
            if (viewModel.getId().equals(id))
                return viewModel;
        }
        return null;
    }

    private void startLoad() {
        mView.hideNull();
        mView.showLoading();
    }

    private void stopLoad(final boolean haveData) {
        UiKit.runOnMainThreadAsync(new Runnable() {
            @Override
            public void run() {
                mView.hideLoading();
                if (haveData)
                    mView.hideNull();
                else
                    mView.showNull();
            }
        });
    }

    private boolean decryptModel(InfoModel src, InfoViewModel result) {
        try {
            result.setDescription(AppPresenter.decrypt(src.getDescription()));
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    public void destroy() {

    }
}
