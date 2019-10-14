package com.example.getandpostusingretrofit.network;

public interface ApiCallBack<P> {
    void onSuccess(P successItem);
    void onError(String errorItem);
    void onFailed(Throwable throwable);
}
