package net.qiujuer.powerback.factory.data;

/**
 * Created by qiujuer
 * on 16/5/16.
 */
public class DataSource {
    /**
     * Data callback use to Presenter~DataSource
     *
     * @param <Data> AnyCallback
     */
    public interface Callback<Data> {
        /**
         * Call the data on request success
         *
         * @param data {@link Data}
         */
        void onDataLoaded(Data data);

        /**
         * Notify error on request failed
         */
        void onDataNotAvailable();
    }
}
