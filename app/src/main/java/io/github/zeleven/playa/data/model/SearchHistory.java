package io.github.zeleven.playa.data.model;

import io.realm.RealmObject;

public class SearchHistory extends RealmObject {
    private String keyword;

    public String getKeyword() {
        return keyword;
    }

    public void setKeyword(String keyword) {
        this.keyword = keyword;
    }
}
