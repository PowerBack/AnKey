package net.qiujuer.powerback.factory.presenter;

/**
 * Created by qiujuer
 * on 16/5/16.
 */
public abstract class BasePresenter<T extends BaseContract.View> implements BaseContract.Presenter {
    T mView;

    protected BasePresenter(T view) {
        setView(view);
    }

    protected void setView(T view) {
        mView = view;
    }

    protected T getView() {
        return (T) mView;
    }

    @Override
    public void start() {
        // do..
    }

    @Override
    public void destroy() {
        mView = null;
    }
}
