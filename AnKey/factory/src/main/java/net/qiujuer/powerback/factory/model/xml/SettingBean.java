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
package net.qiujuer.powerback.factory.model.xml;

import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;

import net.qiujuer.powerback.common.Common;

/**
 * Created by qiujuer
 * on 16/5/18.
 */
public class SettingBean extends XmlModel {
    private int verCode;

    public boolean isFirstUse() {
        int verCode = getVerCode();
        if (this.verCode < verCode) {
            this.verCode = verCode;
            save();
            return true;
        } else
            return false;
    }

    public static int getVerCode() {
        PackageInfo info = getPackageInfo();
        if (info != null)
            return info.versionCode;
        return -1;
    }


    public static String getVerName() {
        PackageInfo info = getPackageInfo();
        if (info != null)
            return info.versionName;
        return null;
    }

    public static PackageInfo getPackageInfo() {
        try {
            PackageManager manager = Common.getApp().getPackageManager();
            return manager.getPackageInfo(Common.getApp().getPackageName(), 0);
        } catch (Exception e) {
            return null;
        }
    }
}
