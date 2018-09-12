package io.github.zeleven.playa.ui.module.main;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.Menu;
import android.view.MenuItem;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import io.github.zeleven.playa.R;
import io.github.zeleven.playa.ui.base.BaseActivity;
import io.github.zeleven.playa.ui.module.main.hierarchy.HierarchyFragment;
import io.github.zeleven.playa.ui.module.main.home.HomeFragment;
import io.github.zeleven.playa.ui.module.main.mine.MineFragment;
import io.github.zeleven.playa.ui.module.main.navigation.NavigationFragment;
import io.github.zeleven.playa.ui.module.main.project.ProjectFragment;
import io.github.zeleven.playa.ui.module.search.SearchActivity;
import io.github.zeleven.playa.ui.widget.BottomNavigationViewEx;

public class MainActivity extends BaseActivity<MainPresenter> implements MainContract.View {
    @BindView(R.id.bottom_nav) BottomNavigationViewEx bottomNavigationView;

    private FragmentManager fragmentManager;
    private List<Fragment> fragmentList = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // 依赖注入
        activityComponent.inject(this);

        // presenter 关联 view
        if (presenter == null) {
            return;
        }
        presenter.attachView(this);

        fragmentManager = getSupportFragmentManager();

        // 创建 fragment
        createFragments();
        // 默认选中第一个 fragment，即 BottomNavigationView 的第一项
        selectFragment(0);
        getSupportActionBar().setTitle(R.string.home);

        setupBottomNavigationView();
    }

    @Override
    public int getLayout() {
        return R.layout.activity_main;
    }

    /**
     * 设置 BottomNavigationView
     */
    public void setupBottomNavigationView() {
        bottomNavigationView.enableAnimation(false);
        bottomNavigationView.enableShiftingMode(false);
        bottomNavigationView.enableItemShiftingMode(false);
        bottomNavigationView.setOnNavigationItemSelectedListener(
                new BottomNavigationView.OnNavigationItemSelectedListener() {
                    @Override
                    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                        boolean setElevation = true;
                        int titleResId = R.string.home;
                        switch (item.getItemId()) {
                            case R.id.home:
                                selectFragment(0);
                                titleResId = R.string.home;
                                break;
                            case R.id.project:
                                selectFragment(1);
                                setElevation = false;
                                titleResId = R.string.project;
                                break;
                            case R.id.hierarchy:
                                selectFragment(2);
                                titleResId = R.string.hierarchy;
                                break;
                            case R.id.navigation:
                                selectFragment(3);
                                titleResId = R.string.navigation;
                                break;
                            case R.id.mine:
                                selectFragment(4);
                                titleResId = R.string.mine;
                                break;
                        }
                        enableAppBarElevation(setElevation);
                        getSupportActionBar().setTitle(titleResId);
                        return true;
                    }
                });
    }

    /**
     * 创建 fragment
     */
    public void createFragments() {
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();

        HomeFragment homeFragment = new HomeFragment();
        fragmentList.add(homeFragment);
        fragmentTransaction.add(R.id.fragment_container, homeFragment);

        ProjectFragment projectFragment = new ProjectFragment();
        fragmentList.add(projectFragment);
        fragmentTransaction.add(R.id.fragment_container, projectFragment);

        HierarchyFragment hierarchyFragment = new HierarchyFragment();
        fragmentList.add(hierarchyFragment);
        fragmentTransaction.add(R.id.fragment_container, hierarchyFragment);

        NavigationFragment navigationFragment = new NavigationFragment();
        fragmentList.add(navigationFragment);
        fragmentTransaction.add(R.id.fragment_container, navigationFragment);

        MineFragment mineFragment = new MineFragment();
        fragmentList.add(mineFragment);
        fragmentTransaction.add(R.id.fragment_container, mineFragment);

        fragmentTransaction.commit();
    }

    /**
     * 根据给定下标选中对应的 fragment
     * @param index fragment 在列表中的下标
     */
    public void selectFragment(int index) {
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        for (int i = 0; i < fragmentList.size(); i++) {
            if (i == index) {
                fragmentTransaction.show(fragmentList.get(i));
            } else {
                fragmentTransaction.hide(fragmentList.get(i));
            }
        }
        fragmentTransaction.commit();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.search:
                startActivity(new Intent(this, SearchActivity.class));
                break;
        }
        return super.onOptionsItemSelected(item);
    }
}
