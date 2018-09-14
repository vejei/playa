package io.github.zeleven.playa.data.source;

import java.util.List;

import javax.inject.Inject;
import javax.inject.Singleton;

import io.github.zeleven.playa.data.model.ArticleListResponse;
import io.github.zeleven.playa.data.model.Banner;
import io.github.zeleven.playa.data.model.BaseResponse;
import io.github.zeleven.playa.data.model.Category;
import io.github.zeleven.playa.data.model.HotKey;
import io.github.zeleven.playa.data.model.LoginResponse;
import io.github.zeleven.playa.data.model.NavCategory;
import io.github.zeleven.playa.data.model.SearchHistory;
import io.github.zeleven.playa.data.model.User;
import io.github.zeleven.playa.data.source.local.DatabaseHelper;
import io.github.zeleven.playa.data.source.remote.WanAndroidService;
import io.reactivex.Flowable;
import io.reactivex.Observable;
import io.realm.RealmResults;

@Singleton
public class DataManager {
    private WanAndroidService wanAndroidService;
    private DatabaseHelper databaseHelper;

    @Inject
    public DataManager(WanAndroidService wanAndroidService, DatabaseHelper databaseHelper) {
        this.wanAndroidService = wanAndroidService;
        this.databaseHelper = databaseHelper;
    }

    public Observable<BaseResponse<List<Banner>>> getBannerData() {
        return wanAndroidService.getBannerData();
    }

    public Observable<BaseResponse<ArticleListResponse>> getArticles(int page) {
        return wanAndroidService.getArticles(page);
    }

    public Observable<BaseResponse<List<Category>>> getProjectCategories() {
        return wanAndroidService.getProjectCategories();
    }

    public Observable<BaseResponse<ArticleListResponse>> getProjectArticles(int page, int cid) {
        return wanAndroidService.getProjectArticles(page, cid);
    }

    public Observable<BaseResponse<List<Category>>> getHierarchyCategories() {
        return wanAndroidService.getHierarchyCategories();
    }

    public Observable<BaseResponse<ArticleListResponse>> getHierarchyArticles(int page, int cid) {
        return wanAndroidService.getHierarchyArticles(page, cid);
    }

    public Observable<BaseResponse<List<NavCategory>>> getNavCategories() {
        return wanAndroidService.getNavCategories();
    }

    public Observable<BaseResponse<List<HotKey>>> getHotKey() {
        return wanAndroidService.getHotKey();
    }

    public Observable<BaseResponse<ArticleListResponse>> searchArticles(int page, String keyword) {
        return wanAndroidService.searchArticles(page, keyword);
    }

    public Observable<BaseResponse<LoginResponse>> signin(String username, String password) {
        return wanAndroidService.signin(username, password);
    }

    public Observable<BaseResponse<LoginResponse>> signup(
            String username, String password, String repassword) {
        return wanAndroidService.signup(username, password, repassword);
    }

    public void saveLoggedInUser(String username, String password, boolean isLogin) {
        databaseHelper.saveLoggedInUser(username, password, isLogin);
    }

    public User getLoggedInUser() {
        return databaseHelper.getLoggedInUser();
    }

    public boolean isLogin() {
        return databaseHelper.isLogin();
    }

    public void deleteLoggedInUser() {
        databaseHelper.deleteLoggedInUser();
    }

    public void saveSearchHistory(String keyword) {
        databaseHelper.saveSearchHistory(keyword);
    }

    public List<SearchHistory> querySearchHistory() {
        return databaseHelper.querySearchHistory();
    }

    public void deleteSearchHistory() {
        databaseHelper.deleteSearchHistory();
    }
}
