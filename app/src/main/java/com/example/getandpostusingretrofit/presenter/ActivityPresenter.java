package com.example.getandpostusingretrofit.presenter;

import android.content.Context;
import android.widget.Toast;

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
                //Toast.makeText(context,"Exception Received: "+throwable,Toast.LENGTH_LONG).show();
            }
        });

    }

}
