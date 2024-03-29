package com.example.getandpostusingretrofit;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.ProgressDialog;
import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.getandpostusingretrofit.Common.CommonUtilities;
import com.example.getandpostusingretrofit.adapter.MyAdapter;
import com.example.getandpostusingretrofit.model.ModelClass;
import com.example.getandpostusingretrofit.presenter.ActivityPresenter;
import com.example.getandpostusingretrofit.view.ViewInterfaces;

import java.util.List;

public class MainActivity extends AppCompatActivity {

    WebView wvJson;
    Button btnGet, btnSet;
    EditText etPathSet;
    TextView tvUrl, tvWarning;

    String data;
    ProgressDialog progressDialog;
    MyAdapter adapter;
    RecyclerView rvGet;

    //Presenter
    ActivityPresenter activityPresenter;

    Context context;
    String pathVar;
    boolean internetHas;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //loading();

        initVariables();
        setViews();
        checkForInternet();
        setListeners();
    }

    private boolean checkForInternet() {
        return CommonUtilities.checkInternetStatus(context);
    }

    private void setListeners() {
        btnGet.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if(!checkForInternet()){
                    Toast.makeText(context,"Internet Disconnected", Toast.LENGTH_LONG);
                }

                if(etPathSet.getText().toString().isEmpty()){
                    Toast.makeText(context,"PATH EMPTY",Toast.LENGTH_LONG).show();
                }
                else{
                    pathVar=etPathSet.getText().toString();

                    //GET INFORMATION FROM API
                    activityPresenter.getRetrofitData(pathVar, new ViewInterfaces.ApiView() {
                        @Override
                        public void onSuccess(List<ModelClass> response) {
                            if (response != null) {
                                tvWarning.setVisibility(View.INVISIBLE);
                                generateDataList(response);
                                setWebView();
                            }
                            else{
                                Toast.makeText(context,"RESPONSE NULL !!!", Toast.LENGTH_LONG).show();
                                rvGet.setVisibility(View.INVISIBLE);
                                tvWarning.setVisibility(View.VISIBLE);
                                setWebView();
                            }

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

        /*btnRefresh.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(etPathSet.getText().toString().isEmpty()){
                    Toast.makeText(context,"PATH EMPTY",Toast.LENGTH_LONG).show();
                }
                else{
                    setWebView();
                }
            }
        });*/

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
        rvGet.setVisibility(View.VISIBLE);
        adapter=new MyAdapter(this, body);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(context);
        rvGet.setLayoutManager(layoutManager);
        rvGet.setAdapter(adapter);

    }

    private void setWebView() {
        wvJson=findViewById(R.id.wvJson);
        wvJson.setWebViewClient(new WebViewClient());
        wvJson.getSettings().setLoadsImagesAutomatically(true);
        wvJson.getSettings().setJavaScriptEnabled(true);
        wvJson.setScrollBarStyle(View.SCROLLBARS_INSIDE_OVERLAY);
        String string="https://shitab14.github.io/jsons/"+etPathSet.getText().toString()+"/retrofit.json";
        wvJson.loadUrl(string);
        tvUrl.setText(string);
    }

    private void setViews() {

        rvGet=findViewById(R.id.rvGet);

        etPathSet=findViewById(R.id.etPathSet);
        tvUrl=findViewById(R.id.tvUrl);
        btnGet=findViewById(R.id.btnGet);
        btnSet=findViewById(R.id.btnSet);
        tvWarning=findViewById(R.id.tvWarning);
    }

    private void initVariables() {
        context=this;
        data=null;
        activityPresenter=new ActivityPresenter(context);
        //apiCall=new ApiCall(context);
    }
}
