package io.github.zeleven.playa.ui.adapter;

import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import butterknife.BindView;
import io.github.zeleven.playa.R;
import io.github.zeleven.playa.data.model.HotKey;
import io.github.zeleven.playa.ui.base.BaseRecyclerViewAdapter;
import io.github.zeleven.playa.ui.base.BaseViewHolder;
import io.github.zeleven.playa.ui.module.search.result.SearchResultFragment;

public class HotKeyAdapter extends BaseRecyclerViewAdapter<HotKey, HotKeyAdapter.ViewHolder> {

    @NonNull
    @Override
    public HotKeyAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.textview_tag, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull HotKeyAdapter.ViewHolder holder, int position) {
        holder.bind(data.get(position));
    }

    public class ViewHolder extends BaseViewHolder<HotKey> {
        @BindView(R.id.tag) TextView textViewTag;

        public ViewHolder(View itemView) {
            super(itemView);
        }

        @Override
        public void bind(final HotKey item) {
            textViewTag.setText(item.getName());
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    ((AppCompatActivity) itemView.getContext())
                            .getSupportFragmentManager()
                            .beginTransaction()
                            .replace(R.id.fragment_container,
                                    SearchResultFragment.newInstance(item.getName()))
                            .commit();
                }
            });
        }
    }
}
