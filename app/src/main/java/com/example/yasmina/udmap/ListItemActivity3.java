package com.example.yasmina.udmap;

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

import com.example.yasmina.udmap.model.Orientation;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by HP-HP on 07-06-2016.
 */
public class ListItemActivity3 extends AppCompatActivity {

    public final static String EXTRA_ORIENTATION = "EXTRA_ORIENTATION";
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
        mOrientation = (Orientation) getIntent().getSerializableExtra(ListItemActivity3.EXTRA_ORIENTATION);
        mWithLinePadding = getIntent().getBooleanExtra(ListItemActivity3.EXTRA_WITH_LINE_PADDING, false);

        if(getSupportActionBar()!=null)
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        setTitle(getResources().getString(R.string.actuality));


        //progressBar.setVisibility(View.VISIBLE);

        ButterKnife.bind(this);

        mbtn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onButtonClick(Orientation.VERTICAL, false);
            }
        });

        mbtn2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onButtonClick(Orientation.HORIZONTAL, false);
            }
        });

        mbtn3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onButtonClick(Orientation.VERTICAL, true);
            }
        });

        mbtn4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onButtonClick(Orientation.HORIZONTAL, true);
            }
        });



    }


    private void onButtonClick(Orientation orientation, boolean withLinePadding) {
        Intent intent = new Intent(this, TimeLineActivity.class);
        intent.putExtra(EXTRA_ORIENTATION, orientation);
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
            savedInstanceState.putSerializable(ListItemActivity3.EXTRA_ORIENTATION, mOrientation);
        super.onSaveInstanceState(savedInstanceState);
    }

    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        if (savedInstanceState != null) {
            if (savedInstanceState.containsKey(ListItemActivity3.EXTRA_ORIENTATION)) {
                mOrientation = (Orientation) savedInstanceState.getSerializable(ListItemActivity3.EXTRA_ORIENTATION);
            }
        }
        super.onRestoreInstanceState(savedInstanceState);
    }


}