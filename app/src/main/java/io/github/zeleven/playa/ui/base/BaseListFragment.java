package io.github.zeleven.playa.ui.base;

import android.graphics.drawable.Drawable;
import android.support.annotation.Nullable;
import android.support.v4.content.ContextCompat;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import butterknife.BindView;
import io.github.zeleven.playa.R;
import io.github.zeleven.playa.ui.listener.EndlessRecyclerViewScrollListener;

public abstract class BaseListFragment<P extends BaseContract.Presenter> extends BaseFragment<P> {
    @Nullable @BindView(R.id.swipe_refresh_layout) protected SwipeRefreshLayout swipeRefreshLayout;
    @BindView(R.id.recycler_view) protected RecyclerView recyclerView;

    protected LinearLayoutManager layoutManager;
    protected EndlessRecyclerViewScrollListener recyclerViewScrollListener;

    @Override
    public void onFragmentViewCreated() {
        super.onFragmentViewCreated();
        layoutManager = new LinearLayoutManager(context);
        recyclerView.setLayoutManager(layoutManager);
        DividerItemDecoration itemDecoration = new DividerItemDecoration(context,
                DividerItemDecoration.VERTICAL);
        Drawable drawable = ContextCompat.getDrawable(context, R.drawable.list_divider);
        itemDecoration.setDrawable(drawable);
        recyclerView.addItemDecoration(itemDecoration);
    }
}
