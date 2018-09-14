package io.github.zeleven.playa.ui.module.main.hierarchy;

import android.content.Context;
import android.support.v4.widget.SwipeRefreshLayout;

import java.util.List;

import io.github.zeleven.playa.R;
import io.github.zeleven.playa.data.model.Category;
import io.github.zeleven.playa.ui.adapter.HierarchyAdapter;
import io.github.zeleven.playa.ui.base.BaseListFragment;

public class HierarchyFragment extends BaseListFragment<HierarchyPresenter>
        implements HierarchyContract.View {

    private HierarchyAdapter hierarchyAdapter;

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        fragmentComponent.inject(this);
    }

    @Override
    public int getLayout() {
        return R.layout.fragment_hierarchy;
    }

    @Override
    public void onFragmentViewCreated() {
        super.onFragmentViewCreated();

        if (presenter == null) {
            return;
        }
        presenter.attachView(this);

        hierarchyAdapter = new HierarchyAdapter();
        recyclerView.setAdapter(hierarchyAdapter);
        swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                // reload data
                presenter.getHierarchyCategories();
            }
        });
        swipeRefreshLayout.setRefreshing(true);
        presenter.getHierarchyCategories();
    }

    @Override
    public void showHierarchyCategories(List<Category> data) {
        swipeRefreshLayout.setRefreshing(false);
        hierarchyAdapter.setData(data);
    }
}
