package com.example.getandpostusingretrofit;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.ProgressDialog;
import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.getandpostusingretrofit.adapter.MyAdapter;
import com.example.getandpostusingretrofit.model.ModelClass;
import com.example.getandpostusingretrofit.view.ViewInterfaces;

import java.util.List;

public class MainActivity extends AppCompatActivity {

    WebView wvJson;
    Button btnGet, btnSet, btnRefresh;
    EditText etPathSet;

    String data;
    ProgressDialog progressDialog;
    MyAdapter adapter;
    RecyclerView recyclerView;

    //Presenter
    ActivityPresenter activityPresenter;

    Context context;
    String pathVar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //loading();


        initVariables();
        setViews();
        setListeners();
    }

    private void setListeners() {
        btnGet.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if(etPathSet.getText().toString().isEmpty()){
                    Toast.makeText(context,"PATH EMPTY",Toast.LENGTH_LONG).show();
                }
                else{
                    pathVar=etPathSet.getText().toString();

                    //GET INFORMATION FROM API
                    activityPresenter.getRetrofitData(pathVar, new ViewInterfaces.ApiView() {
                        @Override
                        public void onSuccess(List<ModelClass> response) {
                            generateDataList(response);
                        }

                        @Override
                        public void onLoading() {

                        }

                        @Override
                        public void offLoading() {

                        }

                        @Override
                        public void onNothingFound() {

                        }

                        @Override
                        public void noInternet() {

                        }

                        @Override
                        public void onError(String error) {

                        }
                    });

                }
            }
        });

        btnRefresh.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(etPathSet.getText().toString().isEmpty()){
                    Toast.makeText(context,"PATH EMPTY",Toast.LENGTH_LONG).show();
                }
                else{
                    setWebView();
                }
            }
        });

        btnSet.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //POST INFORMATION TO API
                //apiCall.retrofitCallSetupForPost();

            }
        });
    }



    private void loading(){
        progressDialog = new ProgressDialog(MainActivity.this);
        progressDialog.setMessage("Loading....");
        progressDialog.show();
    }



    private void generateDataList(List<ModelClass> body) {
        adapter=new MyAdapter(this, body);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(MainActivity.this);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(adapter);

    }

    private void setWebView() {
        wvJson=findViewById(R.id.wvJson);
        wvJson.setWebViewClient(new WebViewClient());
        wvJson.getSettings().setLoadsImagesAutomatically(true);
        wvJson.getSettings().setJavaScriptEnabled(true);
        wvJson.setScrollBarStyle(View.SCROLLBARS_INSIDE_OVERLAY);
        wvJson.loadUrl("https://shitab14.github.io/"+etPathSet.getText().toString()+"/jsonforretrofitimplementation.json");
    }

    private void setViews() {

        recyclerView=findViewById(R.id.rvGet);

        etPathSet=findViewById(R.id.etPathSet);
        btnGet=findViewById(R.id.btnGet);
        btnSet=findViewById(R.id.btnSet);
        btnRefresh=findViewById(R.id.btnRefresh);
    }

    private void initVariables() {
        context=this;
        data=null;
        activityPresenter=new ActivityPresenter(context);
        //apiCall=new ApiCall(context);
    }
}
