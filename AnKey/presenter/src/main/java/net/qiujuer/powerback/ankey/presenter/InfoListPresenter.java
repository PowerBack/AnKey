package net.qiujuer.powerback.ankey.presenter;

import net.qiujuer.genius.kit.util.Tools;
import net.qiujuer.genius.kit.util.UiKit;
import net.qiujuer.powerback.ankey.model.db.InfoModel;
import net.qiujuer.powerback.ankey.model.view.InfoViewModel;
import net.qiujuer.powerback.ankey.presenter.view.InfoListView;

import java.util.List;

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
        // note: this add test data
        new InfoModel().save();

        // load data
        List<InfoModel> models = InfoModel.getAll();
        if (models == null || models.size() == 0) {
            stopLoad(false);
        } else {
            List<InfoViewModel> viewModels = mView.getDataSet();
            for (InfoModel model : models) {
                InfoViewModel viewModel = new InfoViewModel();
                viewModel.set(model);
                viewModels.add(viewModel);
            }
            mView.setDataSet(viewModels);
            stopLoad(true);
        }
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
                mView.notifyDataSetChanged();
            }
        });
    }


    public void destroy() {

    }
}
