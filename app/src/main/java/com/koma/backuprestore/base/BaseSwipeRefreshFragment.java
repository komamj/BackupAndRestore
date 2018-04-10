package com.koma.backuprestore.base;

import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.RecyclerView;

import com.koma.backuprestore.R;
import com.koma.backuprestore.commonlibrary.base.BaseFragment;

import butterknife.BindView;

/**
 * Created by koma on 4/10/18.
 */

public class BaseSwipeRefreshFragment extends BaseFragment {
    @BindView(R.id.swipe_refresh_layout)
    SwipeRefreshLayout mSwipeRefreshLayout;
    @BindView(R.id.recycler_view)
    RecyclerView mRecyclerView;

    @Override
    public int getLayoutId() {
        return R.layout.fragment_base;
    }
}
