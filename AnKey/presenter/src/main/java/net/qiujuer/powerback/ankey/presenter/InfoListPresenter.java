package net.qiujuer.powerback.ankey.presenter;

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
                Tools.sleepIgnoreInterrupt(200);
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
                formatModel(viewModels, model);
            }
        }
    }

    private void formatModel(List<InfoViewModel> viewModels, InfoModel model) {
        InfoViewModel sModel = searchViewModel(viewModels, model);
        if (sModel == null) {
            sModel = new InfoViewModel(model);
            viewModels.add(sModel);
        } else {
            sModel.set(model);
        }
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


    public void destroy() {

    }
}
