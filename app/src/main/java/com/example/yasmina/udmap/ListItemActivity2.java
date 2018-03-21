package com.example.yasmina.udmap;

import android.content.Context;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.widget.TableLayout;

import com.example.yasmina.udmap.model.Orientation;
import com.mindorks.placeholderview.ExpandablePlaceHolderView;


public class ListItemActivity2 extends AppCompatActivity{
    private ExpandablePlaceHolderView mExpandableView;
    private Context mContext;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mContext = this.getApplicationContext();
        mExpandableView = (ExpandablePlaceHolderView)findViewById(R.id.expandableView);
        for(Feed feed : Utils.loadFeeds(this.getApplicationContext())){
            mExpandableView.addView(new HeadingView(mContext, feed.getHeading()));
            for(Info info : feed.getInfoList()){
                mExpandableView.addView(new InfoView(mContext, info));
            }
        }
    }
}


