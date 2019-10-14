package com.example.getandpostusingretrofit.view;

import com.example.getandpostusingretrofit.model.ModelClass;

import java.util.List;

public class ViewInterfaces {

    public interface ApiView {
        void onSuccess(List<ModelClass> response);
        void onLoading();
        void offLoading();
        void onNothingFound();
        void noInternet();
        void onError(String error);
    }

}
