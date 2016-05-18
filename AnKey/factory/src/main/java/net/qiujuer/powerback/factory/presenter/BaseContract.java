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
package net.qiujuer.powerback.factory.presenter;

import android.support.annotation.StringRes;

/**
 * Created by qiujuer
 * on 16/5/16.
 * Presenter base contract,
 * Defines the basic contract operation of Presenter and View,
 * and the sub class should inherit the contract and make the corresponding operation.
 */
public interface BaseContract {
    interface Presenter {
        void start();

        void destroy();
    }

    interface View<P extends Presenter> {
        void setPresenter(P presenter);
    }

    interface PassphraseView<P extends Presenter> extends View<P> {
        void gotoObtainPassphrase();
    }

    interface LoadView<P extends Presenter> extends View<P> {

        void showError(@StringRes int str);

        void showLoading();
    }
}
