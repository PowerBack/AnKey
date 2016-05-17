/*
 * Copyright (C) 2015-2016 Qiujuer-PowerBack <qiujuer@live.cn>
 * WebSite https://github.com/PowerBack/AnKey
 * Author Qiujuer
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *         http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package net.qiujuer.powerback.common;


import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;

import net.qiujuer.powerback.common.app.Application;
import net.qiujuer.powerback.common.utils.Log;

public class Common {
    private static boolean DEBUG = true;
    private static Application APP = null;

    public static void init(Application application) {
        APP = application;
        if (APP == null)
            return;
        try {
            ApplicationInfo appInfo = APP.getPackageManager()
                    .getApplicationInfo(APP.getPackageName(), PackageManager.GET_META_DATA);
            DEBUG = appInfo.metaData.getBoolean("APP_IS_DEBUG", true);
            if (DEBUG)
                Log.LEVEL = Log.LEVEL_ALL;
            else
                Log.LEVEL = Log.LEVEL_NONE;

        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
            Log.e(Common.class.getName(), "init error");
        }
    }

    public static boolean isDebug() {
        return DEBUG;
    }

    public static Application getApp() {
        return APP;
    }
}
