package io.github.zeleven.playa.ui.module.search;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.SearchView;
import android.view.Menu;

import io.github.zeleven.playa.R;
import io.github.zeleven.playa.ui.base.BaseActivity;
import io.github.zeleven.playa.ui.module.search.result.SearchResultFragment;
import io.github.zeleven.playa.ui.module.search.suggest.SearchSuggestFragment;

public class SearchActivity extends BaseActivity {
    @Override
    public int getLayout() {
        return R.layout.activity_search;
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportFragmentManager().beginTransaction()
                .add(R.id.fragment_container, new SearchSuggestFragment())
                .commit();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.search, menu);
        SearchView searchView = (SearchView) menu.findItem(R.id.search).getActionView();
        searchView.setQueryHint(getString(R.string.search_view_hint));
        searchView.onActionViewExpanded();
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                // 跳转到搜索 fragment ，同时传入关键词
                getSupportFragmentManager().beginTransaction()
                        .replace(R.id.fragment_container,
                                SearchResultFragment.newInstance(query.toString()))
                        .commit();
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                return false;
            }
        });
        return super.onCreateOptionsMenu(menu);
    }
}
