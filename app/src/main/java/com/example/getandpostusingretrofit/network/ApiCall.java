package com.example.getandpostusingretrofit.network;

import android.content.Context;
import android.util.Log;
import android.widget.Toast;

import com.example.getandpostusingretrofit.model.ModelClass;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ApiCall {

    Context context;
    List<ModelClass> body;

    public ApiCall(Context context) {
        this.context=context;
    }

    public void retrofitCallSetupForGet(final String pathVar, final ApiCallBack<List<ModelClass>> apiCallBack){

        //activityPresenter.getRetrofitData();

        /*Create handle for the RetrofitInstance interface*/

        //Call <List<ModelClass>> call=ApiServiceGenerator.createService("",ClientDataService.class).getAll("Jsons");
        ClientDataService service = ApiServiceGenerator.createService("header", ClientDataService.class);
        Call<List<ModelClass>> call = service.getAll(pathVar);
        call.enqueue(new Callback<List<ModelClass>>() {
            @Override
            public void onResponse(Call<List<ModelClass>> call, Response<List<ModelClass>> response) {
                //progressDialog.dismiss();
                if(response!=null){
                    Toast.makeText(context, "Works like a charm! For Path:"+pathVar, Toast.LENGTH_SHORT).show();
                    apiCallBack.onSuccess(response.body());
                }
                else {
                    Toast.makeText(context, "Null response received", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<List<ModelClass>> call, Throwable t) {
                //progressDialog.dismiss();
                Toast.makeText(context, "Something went wrong...Please try later! " +t, Toast.LENGTH_SHORT).show();
                Log.e("SHITab", String.valueOf(t));
                //body=null;
                apiCallBack.onFailed(t);
            }
        });

        /*ClientDataService service = ApiServiceGenerator.createService("header", ClientDataService.class);
        Call<List<ModelClass>> call = service.getAll("Jsons");
        call.enqueue(new Callback<List<ModelClass>>() {
            @Override
            public void onResponse(Call<List<ModelClass>> call, Response<List<ModelClass>> response) {
                //progressDialog.dismiss();
                Toast.makeText(context, "Works like a charm! ", Toast.LENGTH_SHORT).show();
                //body=response.body();
                apiCallBack.onSuccess(response.body());
            }

            @Override
            public void onFailure(Call<List<ModelClass>> call, Throwable t) {
                //progressDialog.dismiss();
                Toast.makeText(context, "Something went wrong...Please try later! " +t, Toast.LENGTH_SHORT).show();
                Log.e("SHITab", String.valueOf(t));
                //body=null;
                apiCallBack.onFailed(t);
            }
        });
*/
    }


    private void retrofitCallSetupForPost() {
        /*Create handle for the RetrofitInstance interface*/
        ClientDataService service = ApiServiceGenerator.createService("header", ClientDataService.class);
        Call<List<ModelClass>> call = service.setAll();
        call.enqueue(new Callback<List<ModelClass>>() {
            @Override
            public void onResponse(Call<List<ModelClass>> call, Response<List<ModelClass>> response) {
                //progressDialog.dismiss();
                //generateDataList(response.body());
            }

            @Override
            public void onFailure(Call<List<ModelClass>> call, Throwable t) {
                //progressDialog.dismiss();
                Toast.makeText(context, "Something went wrong...Please try later!", Toast.LENGTH_SHORT).show();
            }
        });

    }


}
