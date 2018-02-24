package com.example.ganhuotest;

import android.graphics.Rect;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.ganhuotest.bean.InfoGson;
import com.google.gson.Gson;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class MainActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private String categroy = "all/";
    private String count = "40/";
    private String page = "1";
    private InfoGson infoGson;
    private static final int UPDATE=0;
    private static final int IMAGE=1;
    private List<InfoGson.Info> mListInfon = new ArrayList<>();
    private InfoAdapter adapter;
    private ImageView Load;
    private List<InfoGson.Info> mListInage = new ArrayList<>();
    private ImageAdapter imgAdapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setTheme(R.style.AppTheme);
        setContentView(R.layout.activity_main);
        Load = (ImageView) findViewById(R.id.load);
        Load.setImageResource(R.mipmap.plus_2);

        RequestInfo();
        Load.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int p = Integer.valueOf(page);
                page = String.valueOf(p+1);
                if (categroy == "福利/"){
                    RequestImage();
                }else {
                    RequestInfo();
                }
            }
        });
    }


    private void RequestInfo() {
        final Request request = new Request.Builder()
                .url("http://gank.io/api/data/" + categroy + count + page)
                .build();
        OkHttpClient client = new OkHttpClient();
        Call call = client.newCall(request);
        call.enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {

                if (response.isSuccessful()){
                    String result = response.body().string();
                    Log.i("获取的信息",result);
                    Gson gson = new Gson();
                    infoGson = gson.fromJson(result,InfoGson.class);
                    handler.sendEmptyMessage(UPDATE);
                }
            }
        });
    }

    private Handler handler = new Handler(){
        public void handleMessage(Message msg){
            super.handleMessage(msg);
            switch (msg.what){
                case UPDATE:
                    initRecycler();
                    break;
                case IMAGE:
                    initImage();
            }

        }
    };

    private void initRecycler(){
        mListInfon.clear();
        recyclerView = (RecyclerView) findViewById(R.id.recycler);
        recyclerView.setLayoutManager(new LinearLayoutManager(this,LinearLayoutManager.VERTICAL,false));
        mListInfon = infoGson.getResults();
            adapter = new InfoAdapter(this,mListInfon);
            adapter.notifyDataSetChanged();
            recyclerView.setAdapter(adapter);

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main,menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case R.id.title_0:
                categroy = "all/";
                page = "1";
                RequestInfo();
                break;
            case R.id.title_1:
                categroy = "福利/";
                page = "1";
                RequestImage();
                break;
            case R.id.title_2:
                categroy = "Android/";
                page = "1";
                RequestInfo();
                break;
            case R.id.title_3:
                categroy = "iOS/";
                page = "1";
                RequestInfo();
                break;
            case R.id.title_4:
                categroy = "休息视频/";
                page = "1";
                RequestInfo();
                break;
            case R.id.title_5:
                categroy = "拓展资源/";
                page = "1";
                RequestInfo();
                break;
            case R.id.title_6:
                categroy = "前端/";
                page = "1";
                RequestInfo();
                break;
            default:
                break;
        }
        return true;
    }

    private void RequestImage(){
        final Request request = new Request.Builder()
                .url("http://gank.io/api/data/" + categroy + "40/" + page)
                .build();
        OkHttpClient client = new OkHttpClient();
        Call call = client.newCall(request);
        call.enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {

            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                if (response.isSuccessful()){
                    String result = response.body().string();
                    Log.i("获取的信息",result);
                    Gson gson = new Gson();
                    infoGson = gson.fromJson(result,InfoGson.class);
                    handler.sendEmptyMessage(IMAGE);
                }

            }
        });
    }

    private void initImage(){
        mListInage.clear();
        StaggeredGridLayoutManager layoutRecey = new StaggeredGridLayoutManager(2,StaggeredGridLayoutManager.VERTICAL);
        recyclerView.setLayoutManager(layoutRecey);
        mListInage = infoGson.getResults();
        if (mListInage == null){
            Toast.makeText(getApplicationContext(),"已经到头了，看看别的吧",Toast.LENGTH_SHORT).show();
        }else {
            imgAdapter = new ImageAdapter(this,mListInage);
            recyclerView.setAdapter(imgAdapter);
        }
    }
}
