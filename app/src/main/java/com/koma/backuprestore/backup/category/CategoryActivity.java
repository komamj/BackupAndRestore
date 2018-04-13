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
package com.koma.backuprestore.backup.category;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;

import com.koma.backuprestore.R;
import com.koma.backuprestore.commonlibrary.base.BaseActivity;
import com.koma.backuprestore.commonlibrary.util.Constants;
import com.koma.loglibrary.KomaLog;

import butterknife.BindView;

/**
 * Created by koma on 4/11/18.
 */

public class CategoryActivity extends BaseActivity {
    private static final String TAG = CategoryActivity.class.getSimpleName();

    @BindView(R.id.toolbar)
    Toolbar mToolbar;

    private int mCategory;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        KomaLog.i(TAG, "onCreate");
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_base;
    }

    @Override
    protected String[] getPermissions() {
        return new String[0];
    }

    @Override
    protected void onPermissonGranted() {
        KomaLog.i(TAG, "onPermissionGranted");

        mCategory = getIntent().getIntExtra(Constants.CATEGORY_TAG, Constants.CATEGORY_CANTACT);

        setSupportActionBar(mToolbar);
    }

    @Override
    protected void onNewIntent(Intent newIntent) {
        super.onNewIntent(newIntent);

        setIntent(newIntent);

        KomaLog.i(TAG, "onNewIntent");
    }
}
