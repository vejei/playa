package io.github.zeleven.playa.ui.module.search.result;

import android.content.Context;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;

import java.util.List;

import io.github.zeleven.playa.R;
import io.github.zeleven.playa.data.model.Article;
import io.github.zeleven.playa.ui.adapter.SearchResultAdapter;
import io.github.zeleven.playa.ui.base.BaseListFragment;
import io.github.zeleven.playa.ui.listener.EndlessRecyclerViewScrollListener;

public class SearchResultFragment extends BaseListFragment<SearchResultPresenter>
        implements SearchResultContract.View {
    private String query;

    private SearchResultAdapter searchResultAdapter;
    private EndlessRecyclerViewScrollListener recyclerViewScrollListener;

    public static SearchResultFragment newInstance(String keyword) {
        Bundle args = new Bundle();
        args.putString("QUERY", keyword);
        SearchResultFragment fragment = new SearchResultFragment();
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
        return R.layout.fragment_search_result;
    }

    @Override
    public void onFragmentViewCreated() {
        super.onFragmentViewCreated();

        if (presenter == null) {
            return;
        }
        presenter.attachView(this);

        query = getArguments().getString("QUERY");
        presenter.saveSearchHistory(query);

        searchResultAdapter = new SearchResultAdapter();
        recyclerView.setAdapter(searchResultAdapter);

        recyclerViewScrollListener = new EndlessRecyclerViewScrollListener(layoutManager, 0) {
            @Override
            public void onLoadMore(int page, int totalItemsCount, RecyclerView view) {
                // load more
                searchResultAdapter.setLoading();
                presenter.search(page, query);
            }
        };
        recyclerView.addOnScrollListener(recyclerViewScrollListener);

        presenter.search(0, query);
    }

    @Override
    public void showSearchResult(int page, List<Article> data) {
        if (page == 0) {
            searchResultAdapter.setData(data);
        } else if (page > 0) {
            if ((data != null && data.size() == 0) || data == null) {
                searchResultAdapter.setNoMore();
            } else {
                searchResultAdapter.appendItems(data);
            }
        }
    }
}
