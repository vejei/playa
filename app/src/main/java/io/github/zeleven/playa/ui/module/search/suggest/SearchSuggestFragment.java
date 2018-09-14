package io.github.zeleven.playa.ui.module.search.suggest;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.LinearLayout;

import com.google.android.flexbox.FlexboxLayoutManager;

import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;
import io.github.zeleven.playa.R;
import io.github.zeleven.playa.data.model.HotKey;
import io.github.zeleven.playa.data.model.SearchHistory;
import io.github.zeleven.playa.ui.adapter.HotKeyAdapter;
import io.github.zeleven.playa.ui.adapter.SearchHistoryAdapter;
import io.github.zeleven.playa.ui.base.BaseFragment;

public class SearchSuggestFragment extends BaseFragment<SearchSuggestPresenter>
        implements SearchSuggestContract.View {
    @BindView(R.id.hot_search_list) RecyclerView hotSearchRecyclerView;
    @BindView(R.id.search_history_list) RecyclerView searchHistoryRecyclerView;
    @BindView(R.id.search_history_layout) LinearLayout searchHistoryLayout;

    private HotKeyAdapter hotKeyAdapter;
    private SearchHistoryAdapter searchHistoryAdapter;

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        fragmentComponent.inject(this);
    }

    @Override
    public int getLayout() {
        return R.layout.fragment_search_suggest;
    }

    @Override
    public void onFragmentViewCreated() {
        super.onFragmentViewCreated();
        if (presenter == null) {
            return;
        }
        presenter.attachView(this);

        hotSearchRecyclerView.setLayoutManager(new FlexboxLayoutManager(context));
        hotKeyAdapter = new HotKeyAdapter();
        hotSearchRecyclerView.setAdapter(hotKeyAdapter);

        searchHistoryRecyclerView.setLayoutManager(new LinearLayoutManager(context));
        searchHistoryAdapter = new SearchHistoryAdapter();
        searchHistoryRecyclerView.setAdapter(searchHistoryAdapter);

        presenter.getHotKey();
        presenter.getSearchHistory();
    }

    @OnClick(R.id.clear_history)
    public void clearHistory() {
        // 清涂所有搜索历史，弹出确认对话框，确认后清除
        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        builder.setMessage(R.string.clear_history_alert_dialog_message);
        builder.setPositiveButton(R.string.dialog_button_ok, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                // 清除所有搜索记录并关闭对话框
                presenter.clearSearchHistory();
                dialogInterface.dismiss();
            }
        });
        builder.setNegativeButton(R.string.dialog_button_cancel, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                dialogInterface.dismiss();
            }
        });
        builder.create().show();
    }

    @Override
    public void showHotKey(List<HotKey> data) {
        hotKeyAdapter.setData(data);
    }

    @Override
    public void showSearchHistory(List<SearchHistory> data) {
        if (data == null || (data != null && data.size() == 0)) {
            searchHistoryLayout.setVisibility(View.GONE);
        } else {
            searchHistoryLayout.setVisibility(View.VISIBLE);
            searchHistoryAdapter.setData(data);
        }
    }

    @Override
    public void updateHistoryLayout() {
        searchHistoryAdapter.clear();
        searchHistoryLayout.setVisibility(View.GONE);
    }
}
