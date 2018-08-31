package com.kerimovalex.test;

import android.annotation.SuppressLint;
import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.kerimovalex.test.adapter.DataAdapter;
import com.kerimovalex.test.api.SingletonRest;
import com.kerimovalex.test.errors.Error;
import com.kerimovalex.test.model.DataModel;

import net.danlew.android.joda.JodaTimeAndroid;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import butterknife.BindView;
import butterknife.ButterKnife;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

public class MainActivity extends AppCompatActivity {
    @BindView(R.id.recycler_view)
    RecyclerView recyclerView;
    @BindView(R.id.symptom_progressBar)
    ProgressBar progressBar;
    @BindView(R.id.count_item)
    TextView countItem;
    private Map<String, Object> map;
    private DataAdapter dataAdapter;
    private LinearLayoutManager linearLayoutManager;
    private Context context;
    private int currentPage, lastPage, visibleItemCount, totalItemCount, firstVisibleItem;
    private boolean loading = true;
    private Error error;
    private String errorMessage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        JodaTimeAndroid.init(this);
        ButterKnife.bind(this);
        error = new Error(MainActivity.this);
        context = this;
        getData();

    }

    @SuppressLint("CheckResult")
    private void getData() {
        map = new HashMap<>();
        map.put("tags", "story");
        map.put("page", 0);
        SingletonRest.getInstance()
                .getDataInfo(map)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(this::initRecycleView, throwable -> {
                    errorMessage = error.handleError(throwable);
                    Toast.makeText(context, errorMessage, Toast.LENGTH_SHORT).show();

                });
    }


    private void initRecycleView(DataModel dataModel) {
        lastPage = dataModel.getNbPages();
        currentPage = dataModel.getPage() + 1;
        dataAdapter = new DataAdapter(dataModel.getHits());
        recyclerView.setHasFixedSize(true);
        linearLayoutManager = new LinearLayoutManager(context);
        recyclerView.setLayoutManager(linearLayoutManager);
        recyclerView.setAdapter(dataAdapter);
        recyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @SuppressLint("CheckResult")
            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
                int count = linearLayoutManager.findLastVisibleItemPosition() + 1;
                countItem.setText("Total displaying posts " + count);
                visibleItemCount = linearLayoutManager.getChildCount();
                totalItemCount = linearLayoutManager.getItemCount();
                firstVisibleItem = linearLayoutManager.findFirstVisibleItemPosition();
                if (loading && (visibleItemCount + firstVisibleItem) >= totalItemCount) {
                    loading = false;
                    if (currentPage <= lastPage) {
                        progressBar.setVisibility(View.VISIBLE);
                        io.reactivex.Observable.timer(500, TimeUnit.MILLISECONDS).subscribe(aLong -> {
                            getDataPagination();
                        });
                    }
                }
            }


        });
    }

    @SuppressLint("CheckResult")
    private void getDataPagination() {
        map.put("page", currentPage);
        map.put("hitsPerPage", 20);
        SingletonRest.getInstance()
                .getDataInfo(map)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(dataModel1 -> {
                    lastPage = dataModel1.getNbPages();
                    currentPage = dataModel1.getPage() + 1;
                    dataAdapter.updateDataPagination(dataModel1.getHits());
                    progressBar.setVisibility(View.GONE);
                    loading = true;
                }, throwable -> {
                    errorMessage = error.handleError(throwable);
                    Toast.makeText(context, errorMessage, Toast.LENGTH_SHORT).show();
                    progressBar.setVisibility(View.GONE);
                    loading = true;
                });

    }
}
