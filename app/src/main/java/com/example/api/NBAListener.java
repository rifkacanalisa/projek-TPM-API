package com.example.api;

public interface NBAListener<T> {
    void onSuccess(T items);
    void onFailed(String msg);
}
