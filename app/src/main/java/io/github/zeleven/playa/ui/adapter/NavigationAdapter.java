package io.github.zeleven.playa.ui.adapter;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.google.android.flexbox.FlexboxLayout;

import java.util.List;

import butterknife.BindView;
import io.github.zeleven.playa.R;
import io.github.zeleven.playa.data.model.Article;
import io.github.zeleven.playa.data.model.NavCategory;
import io.github.zeleven.playa.ui.base.BaseRecyclerViewAdapter;
import io.github.zeleven.playa.ui.base.BaseViewHolder;
import io.github.zeleven.playa.ui.module.browser.BrowserActivity;

public class NavigationAdapter extends BaseRecyclerViewAdapter<NavCategory, NavigationAdapter.ViewHolder> {

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.navigation_list_item, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.bind(data.get(position));
    }

    public class ViewHolder extends BaseViewHolder<NavCategory> {
        @BindView(R.id.nav_title) TextView navTitle;
        @BindView(R.id.flex_box) FlexboxLayout flexboxLayout;

        public ViewHolder(View itemView) {
            super(itemView);
        }

        @Override
        public void bind(NavCategory data) {
            navTitle.setText(data.getName());
            final List<Article> articleList = data.getArticles();
            LayoutInflater layoutInflater = LayoutInflater.from(itemView.getContext());
            TextView textView;
            for (int i = 0; i < articleList.size(); i++) {
                final Article article = articleList.get(i);

                textView = (TextView) layoutInflater.inflate(
                        R.layout.textview_tag, null);
                textView.setText(article.getTitle());
                flexboxLayout.addView(textView);

                FlexboxLayout.LayoutParams layoutParams =
                        (FlexboxLayout.LayoutParams) textView.getLayoutParams();
                layoutParams.setMargins(0, 0, 16, 16);
                textView.setLayoutParams(layoutParams);

                textView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        Intent intent = new Intent(itemView.getContext(), BrowserActivity.class);
                        intent.putExtra("URL", article.getLink());
                        intent.putExtra("TITLE", article.getTitle());
                        itemView.getContext().startActivity(intent);
                    }
                });
            }
        }
    }
}
