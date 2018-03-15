package com.example.yasmina.udmap_final;

/**
 * Created by yasmina on 15/03/18.
 */

import android.content.Context;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.Toolbar;
import android.R.drawable;
import android.view.MenuItem;

import com.example.yasmina.udmap_final.model.Orientation;
import com.mindorks.placeholderview.ExpandablePlaceHolderView;

public class ContactActivity extends AppCompatActivity {

    private ExpandablePlaceHolderView mExpandableView;
    private Context mContext;
    private Orientation mOrientation;
    private boolean mWithLinePadding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.contact_activity);
        mContext = this.getApplicationContext();

        Toolbar mToolbar = (Toolbar) findViewById(R.id.toolbar);
        // mToolbar.setTitle(getString(R.string.Horaire));

        setSupportActionBar(mToolbar);
        mOrientation = (Orientation) getIntent().getSerializableExtra(ListItemActivity3.EXTRA_ORIENTATION);
        mWithLinePadding = getIntent().getBooleanExtra(ListItemActivity3.EXTRA_WITH_LINE_PADDING, false);

        if(getSupportActionBar()!=null)
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        setTitle(mOrientation == Orientation.HORIZONTAL ? getResources().getString(R.string.horizontal_timeline) : getResources().getString(R.string.contact));

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
