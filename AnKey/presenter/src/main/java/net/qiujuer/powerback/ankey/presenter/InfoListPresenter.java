package net.qiujuer.powerback.ankey.presenter;

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
    private long mDate = 0;

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
                loadData();
                mThread = null;
            }
        };
        mThread.start();
    }

    private void loadData() {
        // load data
        List<InfoModel> models = InfoModel.getAll(mDate);
        if (models == null || models.size() == 0) {
            List<InfoViewModel> viewModels = mView.getDataSet();
            stopLoad(viewModels.size() > 0);
        } else {
            stopLoad(true);
            mDate = models.get(0).getLastDate();
            List<InfoViewModel> viewModels = mView.getDataSet();
            for (InfoModel model : models) {
                if (!formatModel(viewModels, model))
                    break;
            }
        }
    }

    private boolean formatModel(List<InfoViewModel> viewModels, InfoModel model) {
        InfoViewModel vModel = searchViewModel(viewModels, model);
        if (vModel == null) {
            vModel = new InfoViewModel(model);
            if (decryptModel(model, vModel)) {
                int index = getInsertIndex(viewModels, vModel);
                viewModels.add(index, vModel);
                notifyInsert(index);
                return true;
            }
        } else {
            if (decryptModel(model, vModel)) {
                int index = getChangeIndex(viewModels, model);
                notifyChange(index);
                return true;
            }
        }
        return false;
    }

    private int getInsertIndex(List<InfoViewModel> viewModels, InfoViewModel vModel) {
        int index = 0;
        for (InfoViewModel viewModel : viewModels) {
            if (vModel.getLastDate() >= viewModel.getLastDate())
                break;
            index++;
        }
        return index;
    }

    private int getChangeIndex(List<InfoViewModel> viewModels, InfoModel model) {
        int index = 0;
        for (InfoViewModel i : viewModels) {
            if (i.getId().equals(model.getInfoId()))
                break;
            index++;
        }
        return index;
    }

    private InfoViewModel searchViewModel(List<InfoViewModel> viewModels, InfoModel model) {
        UUID id = model.getInfoId();
        for (InfoViewModel viewModel : viewModels) {
            if (viewModel.getId().equals(id))
                return viewModel;
        }
        return null;
    }

    private boolean decryptModel(InfoModel src, InfoViewModel result) {
        try {
            result.setLastDate(src.getLastDate());
            result.setDescription(AppPresenter.decrypt(src.getDescription()));
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    private void notifyInsert(final int index) {
        if (index < 0)
            return;
        UiKit.runOnMainThreadAsync(new Runnable() {
            @Override
            public void run() {
                mView.notifyItemChanged(index);
            }
        });
    }

    private void notifyChange(final int index) {
        if (index < 0)
            return;
        UiKit.runOnMainThreadAsync(new Runnable() {
            @Override
            public void run() {
                mView.notifyItemChanged(index);
            }
        });
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
