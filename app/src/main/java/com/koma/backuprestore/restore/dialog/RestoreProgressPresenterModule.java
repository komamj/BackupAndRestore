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
package com.koma.backuprestore.restore.dialog;

import dagger.Module;
import dagger.Provides;

/**
 * Created by koma on 4/8/18.
 */

@Module
public class RestoreProgressPresenterModule {
    private final RestoreProgressContract.View mView;

    public RestoreProgressPresenterModule(RestoreProgressContract.View view) {
        mView = view;
    }

    @Provides
    RestoreProgressContract.View provideRestoreProgressContractView() {
        return this.mView;
    }
}
