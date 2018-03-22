package com.example.yasmina.udmap.news;

/**
 * Created by yasmina on 12/03/18.
 */


import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;

import com.example.yasmina.udmap.R;
import com.example.yasmina.udmap.model.Orientation;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by HP-HP on 07-06-2016.
 */
public class NewsMenuActivity extends AppCompatActivity {

    public final static String CATEGORY = "CATEGORY";
    public final static String EXTRA_WITH_LINE_PADDING = "EXTRA_WITH_LINE_PADDING";


    private Orientation mOrientation;
    private boolean mWithLinePadding;
    private ProgressBar progressBar;
    @BindView(R.id.btn1)
    Button mbtn1;
    @BindView(R.id.btn2)
    Button mbtn2;
    @BindView(R.id.btn3)
    Button mbtn3;
    @BindView(R.id.btn4)
    Button mbtn4;

   /* @BindView(R.id.verticalTimeLineButtonWPadding)
    Button mVerticalTimeLineButtonWPadding;
    @BindView(R.id.horizontalTimeLineButton)
    Button mHorizontalTimeLineButton;
    @BindView(R.id.horizontalTimeLineButtonWPadding)
    Button mHorizontalTimeLineButtonWPadding;*/

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.listitem3_activity);

        progressBar=(ProgressBar) findViewById(R.id.progressBar);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        // mToolbar.setTitle(getString(R.string.Horaire));

        setSupportActionBar(toolbar);
        mOrientation = (Orientation) getIntent().getSerializableExtra(NewsMenuActivity.CATEGORY);
        mWithLinePadding = getIntent().getBooleanExtra(NewsMenuActivity.EXTRA_WITH_LINE_PADDING, false);

        if(getSupportActionBar()!=null)
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        setTitle(getResources().getString(R.string.actuality));


        //progressBar.setVisibility(View.VISIBLE);

        ButterKnife.bind(this);

        mbtn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onButtonClick(Category.GENERAL, false);
            }
        });

        mbtn2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onButtonClick(Category.DEPARTEMENT, false);
            }
        });

        mbtn3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onButtonClick(Category.FILIERE, true);
            }
        });

        mbtn4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onButtonClick(Category.CLASSE, true);
            }
        });



    }


    private void onButtonClick(Category category, boolean withLinePadding) {
        Intent intent = new Intent(this, TimeLineActivity.class);
        intent.putExtra(CATEGORY, category);
        intent.putExtra(EXTRA_WITH_LINE_PADDING, withLinePadding);
        startActivity(intent);
        progressBar.setVisibility(View.GONE);
    }

    private LinearLayoutManager getLinearLayoutManager() {
        if (mOrientation == Orientation.HORIZONTAL) {
            return new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false);
        } else {
            return new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        }
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
        if(mOrientation!=null)
            savedInstanceState.putSerializable(NewsMenuActivity.CATEGORY, mOrientation);
        super.onSaveInstanceState(savedInstanceState);
    }

    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        if (savedInstanceState != null) {
            if (savedInstanceState.containsKey(NewsMenuActivity.CATEGORY)) {
                mOrientation = (Orientation) savedInstanceState.getSerializable(NewsMenuActivity.CATEGORY);
            }
        }
        super.onRestoreInstanceState(savedInstanceState);
    }


}