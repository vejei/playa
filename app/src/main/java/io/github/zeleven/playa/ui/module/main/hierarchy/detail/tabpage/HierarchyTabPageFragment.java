package io.github.zeleven.playa.ui.module.main.hierarchy.detail.tabpage;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.RecyclerView;

import java.util.List;

import io.github.zeleven.playa.R;
import io.github.zeleven.playa.data.model.Article;
import io.github.zeleven.playa.ui.adapter.TabArticleAdapter;
import io.github.zeleven.playa.ui.base.BaseListFragment;
import io.github.zeleven.playa.ui.listener.EndlessRecyclerViewScrollListener;

public class HierarchyTabPageFragment extends BaseListFragment<HierarchyTabPagePresenter>
        implements HierarchyTabPageContract.View {
    private int cid;
    private TabArticleAdapter articleAdapter;

    public static HierarchyTabPageFragment newInstance(int cid) {
        Bundle args = new Bundle();
        args.putInt("CID", cid);
        HierarchyTabPageFragment fragment = new HierarchyTabPageFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        fragmentComponent.inject(this);
    }

    @Override
    public int getLayout() {
        return R.layout.fragment_hierarchy_tab_page;
    }

    @Override
    public void onFragmentViewCreated() {
        super.onFragmentViewCreated();
        if (presenter == null) {
            return;
        }
        presenter.attachView(this);

        cid = getArguments().getInt("CID");

        articleAdapter = new TabArticleAdapter();
        recyclerView.setAdapter(articleAdapter);

        recyclerViewScrollListener = new EndlessRecyclerViewScrollListener(layoutManager, 0) {
            @Override
            public void onLoadMore(int page, int totalItemsCount, RecyclerView view) {
                // load more
                articleAdapter.setLoading();
                presenter.getHierarchyArticles(page, cid);
            }
        };
        recyclerView.addOnScrollListener(recyclerViewScrollListener);
        swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                // reload data
                presenter.getHierarchyArticles(0, cid);
                recyclerViewScrollListener.resetState();
                articleAdapter.resetFooterState();
            }
        });
        swipeRefreshLayout.setRefreshing(true);
        presenter.getHierarchyArticles(0, cid);
    }

    @Override
    public void showHierarchyArticles(int page, List<Article> data) {
        if (page == 0) {
            swipeRefreshLayout.setRefreshing(false);
            articleAdapter.setData(data);
        } else {
            if ((data != null && data.size() == 0) || data == null) {
                articleAdapter.setNoMore();
            } else {
                articleAdapter.appendItems(data);
            }
        }
    }
}
