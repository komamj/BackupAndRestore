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
package com.koma.backuprestore.backup;

import com.koma.backuprestore.data.BackupRestoreRepository;
import com.koma.backuprestore.data.entities.App;
import com.koma.loglibrary.KomaLog;

import java.util.List;

import javax.inject.Inject;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;
import io.reactivex.subscribers.DisposableSubscriber;

public class BackupPresenter implements BackupContract.Presenter {
    private static final String TAG = BackupPresenter.class.getSimpleName();

    private final BackupContract.View mView;

    private final BackupRestoreRepository mRepository;

    private final CompositeDisposable mDisposables;

    @Inject
    public BackupPresenter(BackupContract.View view, BackupRestoreRepository repository) {
        mView = view;

        mRepository = repository;

        mDisposables = new CompositeDisposable();
    }

    @Inject
    void setupListeners() {
        mView.setPresenter(this);
    }

    @Override
    public void subscribe() {
        mRepository.restoreCallLog("/storage/emulated/0/call_log.json")
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeWith(new DisposableSubscriber<Integer>() {
                    @Override
                    public void onNext(Integer integer) {
                    }

                    @Override
                    public void onError(Throwable t) {
                        KomaLog.e(TAG, "onError    " + t.getMessage());
                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }

    @Override
    public void unSubscribe() {
        mDisposables.clear();
    }

    @Override
    public void loadApks() {
        Disposable disposable = mRepository.getApps()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeWith(new DisposableSubscriber<List<App>>() {
                    @Override
                    public void onNext(List<App> apks) {

                    }

                    @Override
                    public void onError(Throwable t) {
                        KomaLog.i(TAG, "loadApks error : " + t.getMessage());
                    }

                    @Override
                    public void onComplete() {

                    }
                });

        mDisposables.add(disposable);
    }
}
