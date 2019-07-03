package com.pankaj.Simpragma.View;

import android.os.Bundle;

import android.support.v4.view.MenuItemCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SearchView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.google.gson.Gson;
import com.pankaj.Simpragma.Model.DataAdapter;
import com.pankaj.Simpragma.R;
import com.pankaj.Simpragma.Retrofit.APIService;
import com.pankaj.Simpragma.Retrofit.ApiUtils;
import com.pankaj.Simpragma.Model.SearchModel;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {

   private RecyclerView mRecyclerView;

    private DataAdapter mAdapter;
    ProgressBar _progressBarsearch;
    private APIService mAPIService;
    public static final String TAG = MainActivity.class.getSimpleName();
    List<SearchModel.Hits> dataList = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
         Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        initViews();

    }

    private void initViews(){
        mAPIService = ApiUtils.getAPIService();
        mAdapter = new DataAdapter(MainActivity.this, dataList);
        mRecyclerView = (RecyclerView)findViewById(R.id.card_recycler_view);
        _progressBarsearch= (ProgressBar) findViewById(R.id.progressBarsearch);
        _progressBarsearch.setVisibility(View.GONE);
        mRecyclerView.setHasFixedSize(true);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this);
        mRecyclerView.setLayoutManager(layoutManager);

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        MenuItem search = menu.findItem(R.id.search);
        SearchView searchView = (SearchView) MenuItemCompat.getActionView(search);
        search(searchView);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        return super.onOptionsItemSelected(item);
    }

    private void search(SearchView searchView) {

        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {

                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                if (newText.length()>0){
                    makeApiCall(newText);
                }else{
                   mAdapter.clearList();
                }

                return true;
            }
        });
    }


    private void makeApiCall(String searvalue) {
       Call<SearchModel> call = mAPIService.getData(searvalue,"561fa65f","94696a13326069141ac29edc52770d3b");
        ApiCall(call);
    }

    private void ApiCall(Call<SearchModel> call) {
        _progressBarsearch.setVisibility(View.VISIBLE);
        //_iv_search.setVisibility(View.GONE);
        call.enqueue(new Callback<SearchModel>() {
            @Override
            public void onResponse(Call<SearchModel> call, Response<SearchModel> response) {
                try {
                    Log.i(TAG +"Jsonvaluresponse", new Gson().toJson(response));
                    Search(response);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void onFailure(Call<SearchModel> call, Throwable t) {
                call.cancel();
                t.printStackTrace();
                Toast.makeText(MainActivity.this, "Connection timeout", Toast.LENGTH_SHORT).show();
               // Common.dialog_dismiss();
            }
        });
    }

    private void Search(Response<SearchModel> response) {
        SearchModel responsedata = response.body();
        dataList=new ArrayList<>();
        dataList= responsedata.getHits;
        mAdapter = new DataAdapter(MainActivity.this,dataList);
        mRecyclerView.setAdapter(mAdapter);
        mAdapter.notifyDataSetChanged();
        _progressBarsearch.setVisibility(View.GONE);

    }
}
