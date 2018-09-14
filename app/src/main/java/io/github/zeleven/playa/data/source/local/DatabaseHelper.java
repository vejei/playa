package io.github.zeleven.playa.data.source.local;

import java.util.List;

import javax.inject.Inject;
import javax.inject.Singleton;

import io.github.zeleven.playa.data.model.SearchHistory;
import io.github.zeleven.playa.data.model.User;
import io.realm.Realm;
import io.realm.RealmResults;

@Singleton
public class DatabaseHelper {
    private Realm realm = Realm.getDefaultInstance();

    @Inject
    public DatabaseHelper() {
    }

    public void saveSearchHistory(String keyword) {
        SearchHistory searchHistory = realm.where(SearchHistory.class).equalTo("keyword", keyword)
                .findFirst();
        realm.beginTransaction();
        if (searchHistory != null) {
            searchHistory.deleteFromRealm();
        }
        SearchHistory history = realm.createObject(SearchHistory.class);
        history.setKeyword(keyword);
        realm.commitTransaction();
    }

    public List<SearchHistory> querySearchHistory() {
        RealmResults<SearchHistory> realmResults = realm.where(SearchHistory.class).findAll();
        return realm.copyFromRealm(realmResults);
    }

    public void deleteSearchHistory() {
        realm.beginTransaction();
        realm.where(SearchHistory.class).findAll().deleteAllFromRealm();
        realm.commitTransaction();
    }

    public void saveLoggedInUser(String username, String password, boolean isLogin) {
        realm.beginTransaction();
        User user = realm.createObject(User.class);
        user.setUsername(username);
        user.setPassword(password);
        user.setLogin(isLogin);
        realm.commitTransaction();
    }

    public User getLoggedInUser() {
        return realm.where(User.class).equalTo("isLogin", true).findFirst();
    }

    public boolean isLogin() {
        return getLoggedInUser() != null;
    }

    public void deleteLoggedInUser() {
        realm.beginTransaction();
        getLoggedInUser().deleteFromRealm();
        realm.commitTransaction();
    }
}
