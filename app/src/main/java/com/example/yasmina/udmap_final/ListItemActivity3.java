package com.example.yasmina.udmap_final;

import android.content.Context;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.mindorks.placeholderview.ExpandablePlaceHolderView;


/**
 * Created by yasmina on 28/02/18.
 */
public class ListItemActivity3 extends AppCompatActivity {

    private ExpandablePlaceHolderView mExpandableView;
    private Context mContext;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.listitem3_activity);
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
