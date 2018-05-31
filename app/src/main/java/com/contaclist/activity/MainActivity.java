package com.contaclist.activity;

import java.util.ArrayList;
import android.content.Intent;
import android.os.Bundle;
import android.app.Activity;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.contactlist.customgridview.Contact;
import com.contactlist.customgridview.CustomGridViewAdapter;
import com.contactlist.customgridview.R;
import com.opensource.widget.HorizontalGridView;
import com.opensource.widget.OnChildSelectedListener;
import com.opensource.widget.RecyclerView;

public class MainActivity extends Activity {
    private static final String TAG = "MainActivity";
    private static final boolean DEBUG = true;
    private static final String SELECT_ACTION = "android.test.leanback.widget.SELECT";

    private HorizontalGridView mHorizontalGridView;
    private CustomGridViewAdapter mCustomGridViewAdapter;
    ArrayList<Contact> gridArray = new ArrayList<>();
    private int mScrollState = RecyclerView.SCROLL_STATE_IDLE;

    private RecyclerView.OnScrollListener mScrollListener = new RecyclerView.OnScrollListener() {
        @Override
        public void onScrollStateChanged(int newState) {
            if (DEBUG) {
                final String[] stateNames = { "IDLE", "DRAGGING", "SETTLING" };
                Log.v(TAG, "onScrollStateChanged "
                        + (newState < stateNames.length ? stateNames[newState] : newState));
            }
            mScrollState = newState;
        }

        @Override
        public void onScrolled(int dx, int dy) {

        }
    };

    private View createView() {
        View view = getLayoutInflater().inflate(R.layout.activity_main, null, false);
        mHorizontalGridView = view.findViewById(R.id.gridview);
        mHorizontalGridView.setNumRows(2);
        mHorizontalGridView.setWindowAlignment(HorizontalGridView.WINDOW_ALIGN_BOTH_EDGE);
        mHorizontalGridView.setRowHeight(400);
        mHorizontalGridView.setWindowAlignmentOffsetPercent(45);
        mHorizontalGridView.setOnChildSelectedListener(new OnChildSelectedListener() {
            @Override
            public void onChildSelected(ViewGroup parent, View view, int position, long id) {
                if (DEBUG) Log.d(TAG, "onChildSelected position=" + position +  " id="+id);
            }
        });
        return view;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if (DEBUG) Log.v(TAG, "onCreate");
        View view = createView();


		gridArray.add(new Contact("","http://seo-1255598498.file.myqcloud.com/full/4bf1043b771a9fc6129b2cfee0a2355d331d4d68.jpg","Home"));
		gridArray.add(new Contact("","http://seo-1255598498.file.myqcloud.com/full/4bf1043b771a9fc6129b2cfee0a2355d331d4d68.jpg","User"));
		gridArray.add(new Contact("","http://seo-1255598498.file.myqcloud.com/full/4bf1043b771a9fc6129b2cfee0a2355d331d4d68.jpg","House"));
		gridArray.add(new Contact("","http://seo-1255598498.file.myqcloud.com/full/4bf1043b771a9fc6129b2cfee0a2355d331d4d68.jpg","Friend"));
		gridArray.add(new Contact("","http://seo-1255598498.file.myqcloud.com/full/4bf1043b771a9fc6129b2cfee0a2355d331d4d68.jpg","Home"));
		gridArray.add(new Contact("","http://seo-1255598498.file.myqcloud.com/full/4bf1043b771a9fc6129b2cfee0a2355d331d4d68.jpg","Personal"));
		gridArray.add(new Contact("","http://seo-1255598498.file.myqcloud.com/full/4bf1043b771a9fc6129b2cfee0a2355d331d4d68.jpg","Home"));
		gridArray.add(new Contact("","http://seo-1255598498.file.myqcloud.com/full/4bf1043b771a9fc6129b2cfee0a2355d331d4d68.jpg","User"));
		gridArray.add(new Contact("","http://seo-1255598498.file.myqcloud.com/full/4bf1043b771a9fc6129b2cfee0a2355d331d4d68.jpg","Building"));
		gridArray.add(new Contact("","http://seo-1255598498.file.myqcloud.com/full/4bf1043b771a9fc6129b2cfee0a2355d331d4d68.jpg","User"));
		gridArray.add(new Contact("","http://seo-1255598498.file.myqcloud.com/full/4bf1043b771a9fc6129b2cfee0a2355d331d4d68.jpg","Home"));
		gridArray.add(new Contact("","http://seo-1255598498.file.myqcloud.com/full/4bf1043b771a9fc6129b2cfee0a2355d331d4d68.jpg","xyz"));

        mCustomGridViewAdapter = new CustomGridViewAdapter(this, R.layout.contact_item, gridArray);
        mCustomGridViewAdapter.setHeaderSize(200, 200);
        mHorizontalGridView.setAdapter(mCustomGridViewAdapter);

        setContentView(view);

        mCustomGridViewAdapter.setOnItemClickLitener(new CustomGridViewAdapter.OnItemClickLitener() {
            @Override
            public void onItemClick(View view, int position) {
                Toast.makeText(MainActivity.this, "点击事件", Toast.LENGTH_SHORT).show();
                mCustomGridViewAdapter.addData(0, new Contact("","http://seo-1255598498.file.myqcloud.com/full/4bf1043b771a9fc6129b2cfee0a2355d331d4d68.jpg","xyz"));
            }

            @Override
            public void onItemLongClick(View view, int position) {
                Toast.makeText(MainActivity.this, "长按事件", Toast.LENGTH_SHORT).show();
            }
        });
        mHorizontalGridView.setOnScrollListener(mScrollListener);
    }

    @Override
    protected void onNewIntent(Intent intent) {
        if (DEBUG) Log.v(TAG, "onNewIntent ");
        if (intent.getAction().equals(SELECT_ACTION)) {
            int position = intent.getIntExtra("SELECT_POSITION", -1);
            if (position >= 0) {
                mHorizontalGridView.setSelectedPosition(position);
            }
        }
        super.onNewIntent(intent);
    }

}
