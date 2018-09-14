package io.github.zeleven.playa.ui.adapter;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.google.android.flexbox.FlexboxLayout;
import com.google.gson.Gson;

import java.util.List;

import butterknife.BindView;
import io.github.zeleven.playa.R;
import io.github.zeleven.playa.data.model.Category;
import io.github.zeleven.playa.ui.base.BaseRecyclerViewAdapter;
import io.github.zeleven.playa.ui.base.BaseViewHolder;
import io.github.zeleven.playa.ui.module.main.hierarchy.detail.HierarchyDetailActivity;

public class HierarchyAdapter extends BaseRecyclerViewAdapter<Category, HierarchyAdapter.ViewHolder> {
    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(
                R.layout.hierarchy_list_item, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.bind(data.get(position));
    }

    public class ViewHolder extends BaseViewHolder<Category> {
        @BindView(R.id.parent_name) TextView parentName;
        @BindView(R.id.flex_box) FlexboxLayout flexboxLayout;

        public ViewHolder(View itemView) {
            super(itemView);
        }

        @Override
        public void bind(final Category data) {
            parentName.setText(data.getName());

            List<Category> childList = data.getChildren();
            LayoutInflater layoutInflater = LayoutInflater.from(itemView.getContext());
            TextView textView;
            for (int i = 0; i < childList.size(); i++) {
                textView = (TextView) layoutInflater.inflate(
                        R.layout.hierarchy_child_list_item, null);
                textView.setText(childList.get(i).getName());
                flexboxLayout.addView(textView);

                FlexboxLayout.LayoutParams layoutParams =
                        (FlexboxLayout.LayoutParams) textView.getLayoutParams();
                layoutParams.setMargins(0, 0, 16, 16);
                textView.setLayoutParams(layoutParams);
            }

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent intent = new Intent(itemView.getContext(), HierarchyDetailActivity.class);
                    intent.putExtra("CATEGORY_JSON", new Gson().toJson(data));
                    itemView.getContext().startActivity(intent);
                }
            });
        }
    }
}
