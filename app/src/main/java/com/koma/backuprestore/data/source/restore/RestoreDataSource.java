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
package com.koma.backuprestore.data.source.restore;

import android.accounts.Account;
import android.content.Context;
import android.net.Uri;

import com.koma.backuprestore.data.source.restore.helper.CalendarHelper;
import com.koma.loglibrary.KomaLog;
import com.koma.vcalendarlibrary.VCalParser;
import com.koma.vcardlibrary.VCardConfig;
import com.koma.vcardlibrary.VCardEntry;
import com.koma.vcardlibrary.VCardEntryCommitter;
import com.koma.vcardlibrary.VCardEntryConstructor;
import com.koma.vcardlibrary.VCardParser;
import com.koma.vcardlibrary.VCardParser_V21;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

import javax.inject.Inject;
import javax.inject.Singleton;

import io.reactivex.BackpressureStrategy;
import io.reactivex.Flowable;
import io.reactivex.FlowableEmitter;
import io.reactivex.FlowableOnSubscribe;

/**
 * Created by koma on 3/20/18.
 */

@Singleton
public class RestoreDataSource implements IRestoreDataSource {
    private static final String TAG = RestoreDataSource.class.getSimpleName();

    private final Context mContext;

    @Inject
    public RestoreDataSource(Context context) {
        mContext = context;
    }

    @Override
    public Flowable<Integer> getContactCount(final String fileName) {
        return Flowable.create(new FlowableOnSubscribe<Integer>() {
            @Override
            public void subscribe(FlowableEmitter<Integer> emitter) throws Exception {
                int count = 0;

                try {
                    InputStream instream = new FileInputStream(fileName);
                    InputStreamReader inreader = new InputStreamReader(instream);
                    BufferedReader buffreader = new BufferedReader(inreader);
                    String line = null;
                    while ((line = buffreader.readLine()) != null) {
                        if (line.contains("END:VCARD")) {
                            ++count;
                        }
                    }
                    buffreader.close();
                    inreader.close();
                    instream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }

                emitter.onNext(count);
                emitter.onComplete();
            }
        }, BackpressureStrategy.LATEST);
    }

    @Override
    public Flowable<Integer> restoreContacts(final String fileName) {
        return Flowable.create(new FlowableOnSubscribe<Integer>() {
            @Override
            public void subscribe(final FlowableEmitter<Integer> emitter) throws Exception {
                InputStream inputStream = null;

                try {
                    inputStream = new FileInputStream(fileName);

                    Account account = new Account("Phone", "Local Phone Account");
                    VCardEntryConstructor constructor = new VCardEntryConstructor(
                            VCardConfig.VCARD_TYPE_V21_GENERIC, account);
                    constructor.addEntryHandler(new VCardEntryCommitter(mContext.getContentResolver()) {
                        int count = 0;

                        /**
                         * Describe <code>onEntryCreated</code> method here.
                         *
                         * @param vcardEntry a <code>VCardEntry</code> value
                         */
                        @Override
                        public void onEntryCreated(final VCardEntry vcardEntry) {
                            super.onEntryCreated(vcardEntry);

                            ++count;

                            KomaLog.i(TAG, "onEntryCreated count : " + count);

                            emitter.onNext(count);
                        }
                    });

                    VCardParser vCardParser = new VCardParser_V21(VCardConfig.VCARD_TYPE_V21_GENERIC);
                    vCardParser.addInterpreter(constructor);
                    vCardParser.parse(inputStream);
                } catch (IOException e) {
                    e.printStackTrace();
                } finally {
                    if (inputStream != null) {
                        inputStream.close();
                    }
                }
            }
        }, BackpressureStrategy.LATEST);
    }

    @Override
    public Flowable<Integer> restoreCalendarEvents(final String fileName) {
        return Flowable.create(new FlowableOnSubscribe<Integer>() {
            @Override
            public void subscribe(final FlowableEmitter<Integer> emitter) throws Exception {
                VCalParser vCalParser;
                File file = new File(fileName);
                String uriFileName = null;
                Uri uri = null;
                if (file.exists() && file.isFile()) {
                    uriFileName = Uri.encode(fileName);
                    Uri.Builder uriBuilder = new Uri.Builder().scheme("file")
                            .appendEncodedPath(uriFileName);
                    uri = uriBuilder.build();
                    vCalParser = new VCalParser(uri, mContext, new CalendarHelper() {
                        @Override
                        public void vCalOperationStarted(int totalCnt) {
                            KomaLog.i(TAG, "vCalOperationStarted totalCount : " + totalCnt);
                        }

                        @Override
                        public void vCalOperationFinished(int successCnt, int totalCnt, Object obj) {
                            KomaLog.i(TAG, "vCalOperationFinished successCount : " + successCnt);
                            emitter.onNext(successCnt);

                            emitter.onComplete();
                        }

                        @Override
                        public void vCalProcessStatusUpdate(int currentCnt, int totalCnt) {
                            KomaLog.i(TAG, "vCalProcessStatusUpdate currentCount : " + currentCnt);

                            emitter.onNext(currentCnt);
                        }

                        @Override
                        public void vCalOperationCanceled(int finishedCnt, int totalCnt) {
                            KomaLog.i(TAG, "vCalOperationCanceled finishedCount : " + finishedCnt);
                        }

                        @Override
                        public void vCalOperationExceptionOccured(int finishedCnt, int totalCnt, int type) {
                            KomaLog.i(TAG, "vCalOperationExceptionOccured finishedCount : "
                                    + finishedCnt + ",totalCount : " + totalCnt + ",type : " + type);
                        }
                    });
                    vCalParser.startParse();
                }
            }
        }, BackpressureStrategy.LATEST);
    }
}
