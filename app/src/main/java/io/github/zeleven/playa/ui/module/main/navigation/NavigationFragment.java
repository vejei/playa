package io.github.zeleven.playa.ui.module.main.navigation;

import android.content.Context;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import java.util.List;

import butterknife.BindView;
import io.github.zeleven.playa.R;
import io.github.zeleven.playa.data.model.NavCategory;
import io.github.zeleven.playa.ui.adapter.NavigationAdapter;
import io.github.zeleven.playa.ui.base.BaseFragment;
import q.rorbin.verticaltablayout.VerticalTabLayout;
import q.rorbin.verticaltablayout.adapter.TabAdapter;
import q.rorbin.verticaltablayout.widget.ITabView;
import q.rorbin.verticaltablayout.widget.TabView;

public class NavigationFragment extends BaseFragment<NavigationPresenter>
        implements NavigationContract.View {
    @BindView(R.id.vertical_tab_layout) VerticalTabLayout verticalTabLayout;
    @BindView(R.id.recycler_view) RecyclerView recyclerView;

    private NavigationAdapter adapter;
    private LinearLayoutManager layoutManager;

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        fragmentComponent.inject(this);
    }

    @Override
    public int getLayout() {
        return R.layout.fragment_navigation;
    }

    @Override
    public void onFragmentViewCreated() {
        super.onFragmentViewCreated();
        if (presenter == null) {
            return;
        }
        presenter.attachView(this);

        adapter = new NavigationAdapter();
        layoutManager = new LinearLayoutManager(context);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(adapter);

        verticalTabLayout.addOnTabSelectedListener(new VerticalTabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabView tab, int position) {
                verticalTabLayout.setTabSelected(position);
                smoothScrollToPosition(position);
            }

            @Override
            public void onTabReselected(TabView tab, int position) {

            }
        });

        presenter.getNavCategories();
    }

    @Override
    public void showNavCategories(final List<NavCategory> categories) {
        verticalTabLayout.setTabAdapter(new TabAdapter() {
            @Override
            public int getCount() {
                return categories != null ? categories.size() : 0;
            }

            @Override
            public ITabView.TabBadge getBadge(int position) {
                return null;
            }

            @Override
            public ITabView.TabIcon getIcon(int position) {
                return null;
            }

            @Override
            public ITabView.TabTitle getTitle(int position) {
                return new ITabView.TabTitle.Builder()
                        .setContent(categories.get(position).getName())
                        .setTextColor(ContextCompat.getColor(context, R.color.colorAccent),
                                ContextCompat.getColor(context, android.R.color.black))
                        .build();
            }

            @Override
            public int getBackground(int position) {
                return -1;
            }
        });
        verticalTabLayout.setTabSelected(0);
        adapter.setData(categories);
    }

    public void smoothScrollToPosition(int position) {
        int firstVisibleItemPosition = layoutManager.findFirstVisibleItemPosition();
        int lastVisibleItemPosition = layoutManager.findLastVisibleItemPosition();
        if (position <= firstVisibleItemPosition) {
            recyclerView.smoothScrollToPosition(position);
        } else if (position <= lastVisibleItemPosition) {
            int top = recyclerView.getChildAt(position - firstVisibleItemPosition).getTop();
            recyclerView.scrollBy(0, top);
        } else {
           recyclerView.smoothScrollToPosition(position);
        }
    }
}
