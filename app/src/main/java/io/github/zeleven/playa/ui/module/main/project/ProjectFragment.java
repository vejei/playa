package io.github.zeleven.playa.ui.module.main.project;

import android.content.Context;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;

import java.util.List;

import butterknife.BindView;
import io.github.zeleven.playa.R;
import io.github.zeleven.playa.data.model.Category;
import io.github.zeleven.playa.ui.adapter.TabViewPagerAdapter;
import io.github.zeleven.playa.ui.base.BaseFragment;
import io.github.zeleven.playa.utils.TabViewPagerAdapterItem;

public class ProjectFragment extends BaseFragment<ProjectPresenter> implements ProjectContract.View {
    @BindView(R.id.tab_layout) TabLayout tabLayout;
    @BindView(R.id.view_pager) ViewPager viewPager;

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        fragmentComponent.inject(this);
    }

    @Override
    public int getLayout() {
        return R.layout.tab_viewpager;
    }

    @Override
    public void onFragmentViewCreated() {
        super.onFragmentViewCreated();

        if (presenter == null) {
            return;
        }
        presenter.attachView(this);
        presenter.getProjectCategories();
    }

    @Override
    public void createTabs(List<Category> categories) {
        TabViewPagerAdapter tabViewPagerAdapter = new TabViewPagerAdapter(
                getChildFragmentManager(),
                TabViewPagerAdapterItem.createProjectTabFragments(categories)
        );
        viewPager.setAdapter(tabViewPagerAdapter);
        tabLayout.setupWithViewPager(viewPager);
    }
}
