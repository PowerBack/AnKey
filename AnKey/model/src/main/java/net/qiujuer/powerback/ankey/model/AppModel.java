package net.qiujuer.powerback.ankey.model;

import android.app.Application;

import com.activeandroid.ActiveAndroid;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.Volley;

import java.util.UUID;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * Created by qiujuer
 * on 15/10/19.
 */
public class AppModel {
    public final static UUID EMPTY_ID = new UUID(0, 0);
    public static boolean DEBUG = false;

    private static Application APPLICATION;
    private static RequestQueue REQUEST_QUEUE;
    private static ExecutorService EXECUTORSERVICE;

    public static Application getApplication() {
        return APPLICATION;
    }

    public static void setApplication(Application application) {
        if (application != null && application != APPLICATION) {
            APPLICATION = application;
            ActiveAndroid.initialize(APPLICATION);

            stopRequestQueue();
            REQUEST_QUEUE = Volley.newRequestQueue(APPLICATION);
        }
    }

    public static void dispose() {
        // ThreadPool
        stopThreadPool();

        // Network
        stopRequestQueue();

        // DataBase
        ActiveAndroid.dispose();
        APPLICATION = null;
    }

    private static void stopRequestQueue() {
        RequestQueue queue = REQUEST_QUEUE;
        REQUEST_QUEUE = null;
        if (queue != null) {
            queue.stop();
        }
    }

    private static void stopThreadPool() {
        ExecutorService service = EXECUTORSERVICE;
        EXECUTORSERVICE = null;
        if (service != null && !service.isShutdown()) {
            try {
                service.shutdownNow();
                service.shutdown();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    public static RequestQueue getRequestQueue() {
        return REQUEST_QUEUE;
    }

    public static ExecutorService getThreadPool() {
        // Check and init executor
        if (EXECUTORSERVICE == null) {
            synchronized (AppModel.class) {
                if (EXECUTORSERVICE == null) {
                    // Init threads executor
                    int size = Runtime.getRuntime().availableProcessors();
                    EXECUTORSERVICE = Executors.newFixedThreadPool(size > 0 ? size : 1);
                }
            }
        }
        return EXECUTORSERVICE;
    }
}
