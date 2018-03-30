/*
 * Copyright 2017 Koma
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.koma.backuprestore.restorelibrary;

import com.koma.backuprestore.modellibrary.BackupRestoreRepository;

import javax.inject.Inject;

import io.reactivex.disposables.CompositeDisposable;

/**
 * Created by koma on 3/29/18.
 */

public class RestorePresenter implements RestoreContract.Presenter {
    private static final String TAG = RestorePresenter.class.getSimpleName();

    private final RestoreContract.View mView;

    private final BackupRestoreRepository mRepository;

    private final CompositeDisposable mDisposables;

    @Inject
    public RestorePresenter(RestoreContract.View view, BackupRestoreRepository restoreRepository) {
        mView = view;

        mRepository = restoreRepository;

        mDisposables = new CompositeDisposable();
    }

    @Override
    public void subscribe() {

    }

    @Override
    public void unSubscribe() {
        mDisposables.clear();
    }
}
