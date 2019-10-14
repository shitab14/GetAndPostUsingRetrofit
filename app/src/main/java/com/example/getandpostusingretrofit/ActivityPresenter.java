package com.example.getandpostusingretrofit;

import android.content.Context;

import com.example.getandpostusingretrofit.model.ModelClass;
import com.example.getandpostusingretrofit.network.ApiCall;
import com.example.getandpostusingretrofit.network.ApiCallBack;
import com.example.getandpostusingretrofit.view.ViewInterfaces;

import java.util.List;

public class ActivityPresenter {

    Context context;
    ApiCall apiCall;
    ApiCallBack apiCallBack;


    public ActivityPresenter(Context context) {
        apiCall=new ApiCall(context);
    }

    public void getRetrofitData(String pathVar, final ViewInterfaces.ApiView apiView){

        //apiView.onLoading();

        apiCall.retrofitCallSetupForGet(pathVar, new ApiCallBack<List<ModelClass>>() {
            @Override
            public void onSuccess(List<ModelClass> successItem) {
                apiView.onSuccess(successItem);
            }

            @Override
            public void onError(String errorItem) {

            }

            @Override
            public void onFailed(Throwable throwable) {

            }
        });

    }

}
