package io.github.zeleven.playa.ui.module.main.home;

import android.content.Context;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.RecyclerView;

import java.util.List;

import io.github.zeleven.playa.R;
import io.github.zeleven.playa.data.model.Article;
import io.github.zeleven.playa.data.model.Banner;
import io.github.zeleven.playa.ui.adapter.HomeAdapter;
import io.github.zeleven.playa.ui.base.BaseListFragment;
import io.github.zeleven.playa.ui.listener.EndlessRecyclerViewScrollListener;

public class HomeFragment extends BaseListFragment<HomePresenter> implements HomeContract.View {

    private HomeAdapter homeAdapter;

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        fragmentComponent.inject(this);
    }

    @Override
    public int getLayout() {
        return R.layout.fragment_home;
    }

    @Override
    public void onFragmentViewCreated() {
        super.onFragmentViewCreated();

        if (presenter == null) {
            return;
        }
        presenter.attachView(this);

        homeAdapter = new HomeAdapter();
        recyclerView.setAdapter(homeAdapter);

        recyclerViewScrollListener = new EndlessRecyclerViewScrollListener(layoutManager, 0) {
            @Override
            public void onLoadMore(int page, int totalItemsCount, RecyclerView view) {
                // load more
                homeAdapter.setLoading();
                presenter.getArticles(page);
            }
        };
        recyclerView.addOnScrollListener(recyclerViewScrollListener);
        swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                // reload data
                presenter.getArticles(0);
                recyclerViewScrollListener.resetState();
                homeAdapter.resetFooterState();
            }
        });
        swipeRefreshLayout.setRefreshing(true);
        presenter.getBannerData();
        presenter.getArticles(0);
    }

    @Override
    public void showArticles(int page, List<Article> data) {
        if (page == 0) {
            swipeRefreshLayout.setRefreshing(false);
            homeAdapter.setData(data);
        } else if (page > 0) {
            if ((data != null && data.size() == 0) || data == null) {
                homeAdapter.setNoMore();
            } else {
                homeAdapter.appendItems(data);
            }
        }
    }

    @Override
    public void showBannerData(List<Banner> data) {
        homeAdapter.setBannerData(data);
    }
}
