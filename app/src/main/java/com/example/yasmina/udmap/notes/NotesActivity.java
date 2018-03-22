package com.example.yasmina.udmap.notes;

/**
 * Created by yasmina on 14/03/18.
 */

import android.content.Context;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;

import com.example.yasmina.udmap.R;
import com.example.yasmina.udmap.backend.BackendService;
import com.example.yasmina.udmap.model.Orientation;
import com.example.yasmina.udmap.news.NewsMenuActivity;
import com.mindorks.placeholderview.ExpandablePlaceHolderView;

import java.util.List;

public class NotesActivity extends AppCompatActivity {

    private ExpandablePlaceHolderView mExpandableView;
    private Context mContext;
    private Orientation mOrientation;
    private boolean mWithLinePadding;
    private BackendService backendService;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        backendService = BackendService.getInstance();
        setContentView(R.layout.note_activity);
        mContext = this.getApplicationContext();

        Toolbar mToolbar = (Toolbar) findViewById(R.id.toolbar);
        // mToolbar.setTitle(getString(R.string.Horaire));

        setSupportActionBar(mToolbar);
        mOrientation = (Orientation) getIntent().getSerializableExtra(NewsMenuActivity.CATEGORY);
        mWithLinePadding = getIntent().getBooleanExtra(NewsMenuActivity.EXTRA_WITH_LINE_PADDING, false);

        if(getSupportActionBar()!=null)
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        setTitle(mOrientation == Orientation.HORIZONTAL ? getResources().getString(R.string.horizontal_timeline) : getResources().getString(R.string.note));

        mExpandableView = (ExpandablePlaceHolderView) findViewById(R.id.expandableView);

        backendService.getStudentNotes(new GetStudentNotesHandler<List<Semester>>() {
            @Override
            public void notesAvailable(final List<Semester> notes) {

                for (Semester semester : notes) {
                    mExpandableView.addView(new HeadingView(mContext, semester.getHeading()));
                    for (Note note : semester.getNotes()) {
                        mExpandableView.addView(new InfoView(mContext, note));
                        mExpandableView.destroyDrawingCache();
                    }
                }
            }

            @Override
            public void notesNotExist() {

            }
        });
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