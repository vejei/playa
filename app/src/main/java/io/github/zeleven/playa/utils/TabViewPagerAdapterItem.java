package io.github.zeleven.playa.utils;

import android.support.v4.app.Fragment;

import java.util.ArrayList;
import java.util.List;

import io.github.zeleven.playa.data.model.Category;
import io.github.zeleven.playa.ui.module.main.hierarchy.detail.tabpage.HierarchyTabPageFragment;
import io.github.zeleven.playa.ui.module.main.project.tabpage.ProjectTabPageFragment;

public class TabViewPagerAdapterItem {
    private String title;
    private Fragment fragment;

    public TabViewPagerAdapterItem(String title, Fragment fragment) {
        this.title = title;
        this.fragment = fragment;
    }

    public String getTitle() {
        return title;
    }

    public Fragment getFragment() {
        return fragment;
    }

    public static List<TabViewPagerAdapterItem> createProjectTabFragments(List<Category> categories) {
        ArrayList<TabViewPagerAdapterItem> adapterItems = new ArrayList<>();
        for (int i = 0; i < categories.size(); i++) {
            adapterItems.add(
                    new TabViewPagerAdapterItem(categories.get(i).getName(),
                    ProjectTabPageFragment.newInstance(categories.get(i).getId()))
            );
        }
        return adapterItems;
    }

    public static List<TabViewPagerAdapterItem> createHierarchyTabFragments(List<Category> categories) {
        ArrayList<TabViewPagerAdapterItem> adapterItems = new ArrayList<>();
        for (int i = 0; i < categories.size(); i++) {
            adapterItems.add(
                    new TabViewPagerAdapterItem(categories.get(i).getName(),
                            HierarchyTabPageFragment.newInstance(categories.get(i).getId()))
            );
        }
        return adapterItems;
    }
}
