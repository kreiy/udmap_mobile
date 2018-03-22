package com.example.yasmina.udmap;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.widget.TableLayout;

import com.example.yasmina.udmap.model.Orientation;


public class ListItemActivity2 extends AppCompatActivity
{

    private Orientation mOrientation;
    private boolean mWithLinePadding;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.listitem2_activity);
        TableLayout tl = (TableLayout) findViewById(R.id.tl);

        Toolbar mToolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(mToolbar);
        mOrientation = (Orientation) getIntent().getSerializableExtra(ListItemActivity3.EXTRA_ORIENTATION);
        mWithLinePadding = getIntent().getBooleanExtra(ListItemActivity3.EXTRA_WITH_LINE_PADDING, false);

        if (getSupportActionBar() != null)
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        setTitle(mOrientation == Orientation.HORIZONTAL ? getResources().getString(R.string.horizontal_timeline) : getResources().getString(R.string.note));
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
        if (mOrientation != null)
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
