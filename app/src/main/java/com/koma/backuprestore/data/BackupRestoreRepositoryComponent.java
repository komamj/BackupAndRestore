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
package com.koma.backuprestore.data;

import javax.inject.Singleton;

import dagger.Component;

/**
 * Created by koma on 2/28/18.
 */
@Singleton
@Component(modules = {BackupRestoreRepositoryModule.class, ApplicationModule.class})
public interface BackupRestoreRepositoryComponent {
    BackupRestoreRepository getRepository();
}
