package com.example.yasmina.udmap.news;

/**
 * Created by yasmina on 12/03/18.
 */

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;

import com.example.yasmina.udmap.R;
import com.example.yasmina.udmap.backend.BackendService;
import com.example.yasmina.udmap.model.Orientation;
import com.example.yasmina.udmap.model.TimeLineModel;


import java.util.ArrayList;
import java.util.List;

/**
 * Created by HP-HP on 05-12-2015.
 */
public class TimeLineActivity extends AppCompatActivity {

    private RecyclerView mRecyclerView;
    private TimeLineAdpter mTimeLineAdapter;
    private List<TimeLineModel> mDataList = new ArrayList<>();
    private Category category;
    private boolean mWithLinePadding;
    private BackendService backendService;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        backendService = BackendService.getInstance();
        setContentView(R.layout.activity_timeline);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);



        if(getSupportActionBar()!=null)
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        category = (Category) getIntent().getSerializableExtra(NewsMenuActivity.CATEGORY);
        mWithLinePadding = getIntent().getBooleanExtra(NewsMenuActivity.EXTRA_WITH_LINE_PADDING, false);

        setTitle(category.name());

        mRecyclerView = (RecyclerView) findViewById(R.id.recyclerView);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));
        mRecyclerView.setHasFixedSize(true);

        backendService.getNews(category, new NewsHandler<List<TimeLineModel>>() {
            @Override
            public void onData(List<TimeLineModel> data) {
                for(TimeLineModel news : data){
                    mDataList.add(news);
                }
                mTimeLineAdapter.notifyDataSetChanged();
            }

            @Override
            public void onError(String errorMessage) {

            }
        });

        initView();
    }

    private void initView() {
        mTimeLineAdapter = new TimeLineAdpter(mDataList, Orientation.VERTICAL, mWithLinePadding);
        mRecyclerView.setAdapter(mTimeLineAdapter);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        //Menu
        switch (item.getItemId()) {
            //When home is clicked
            case android.R.id.home:
                onBackPressed();
                return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onSaveInstanceState(Bundle savedInstanceState) {
        if(category !=null)
            savedInstanceState.putSerializable(NewsMenuActivity.CATEGORY, category);
        super.onSaveInstanceState(savedInstanceState);
    }

    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        if (savedInstanceState != null) {
            if (savedInstanceState.containsKey(NewsMenuActivity.CATEGORY)) {
                category = (Category) savedInstanceState.getSerializable(NewsMenuActivity.CATEGORY);
            }
        }
        super.onRestoreInstanceState(savedInstanceState);
    }


}