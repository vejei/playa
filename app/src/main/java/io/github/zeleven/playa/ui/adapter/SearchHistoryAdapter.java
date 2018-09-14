package io.github.zeleven.playa.ui.adapter;

import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import butterknife.BindView;
import io.github.zeleven.playa.R;
import io.github.zeleven.playa.data.model.SearchHistory;
import io.github.zeleven.playa.ui.base.BaseRecyclerViewAdapter;
import io.github.zeleven.playa.ui.base.BaseViewHolder;
import io.github.zeleven.playa.ui.module.search.result.SearchResultFragment;

public class SearchHistoryAdapter extends BaseRecyclerViewAdapter<SearchHistory, SearchHistoryAdapter.ViewHolder> {

    @NonNull
    @Override
    public SearchHistoryAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.search_history_list_item, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull SearchHistoryAdapter.ViewHolder holder, int position) {
        holder.bind(data.get(position));
    }

    public class ViewHolder extends BaseViewHolder<SearchHistory> {
        @BindView(R.id.history_keyword) TextView historyKeyword;
        @BindView(R.id.clear_history) ImageView clearHistory;

        public ViewHolder(View itemView) {
            super(itemView);
        }

        @Override
        public void bind(final SearchHistory item) {
            historyKeyword.setText(item.getKeyword());
            clearHistory.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    // 删除当前搜索历史
                    removeItem(item);
                }
            });

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    // 跳转到搜索结果页面
                    ((AppCompatActivity) itemView.getContext())
                            .getSupportFragmentManager()
                            .beginTransaction()
                            .replace(R.id.fragment_container,
                                    SearchResultFragment.newInstance(item.getKeyword()))
                            .commit();
                }
            });
        }
    }
}
