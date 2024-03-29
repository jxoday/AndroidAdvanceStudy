package com.example.advance.chapter1.recyclerview;

import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.view.View;
import android.widget.Toast;

import com.example.advance.R;

import java.util.ArrayList;
import java.util.List;

/**
 * @author jinxin
 */
public class RecyclerViewActivity extends AppCompatActivity {

    private static final String TAG = "RecyclerViewActivity";
    private HomeAdapter mHomeAdaper;
    private List<String> mList;
    private RecyclerView mRecyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recycler_view);


        initData();
        initView();
    }

    private void initData() {
        mList = new ArrayList<String>();
        for (int i = 1; i < 20; i++) {
            mList.add(i + "");
        }
    }

    private void initView() {
        mRecyclerView = findViewById(R.id.id_recycler_view);

        //列表形式
//        setListView();
        //网格形式
        setGridView();
        //瀑布流
//        setWaterfallView();
    }

    private void setListView() {
        //设置布局管理器
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        //设置item增加和删除时的动画
        mRecyclerView.setItemAnimator(new DefaultItemAnimator());
        mHomeAdaper = new HomeAdapter(this, mList);
        mRecyclerView.setAdapter(mHomeAdaper);

        setLister();
    }

    private void setGridView() {
        //设置布局管理器  一行四个
        mRecyclerView.setLayoutManager(new GridLayoutManager(this, 4));
        //设置item增加和删除时的动画
        mRecyclerView.setItemAnimator(new DefaultItemAnimator());
        mHomeAdaper = new HomeAdapter(this, mList);
        mRecyclerView.setAdapter(mHomeAdaper);
        setLister();
    }

    public void setWaterfallView() {
        mRecyclerView.setLayoutManager(new StaggeredGridLayoutManager(4,
                StaggeredGridLayoutManager.VERTICAL));
        mRecyclerView.setItemAnimator(new DefaultItemAnimator());
        StaggeredHomeAdapter mStaggeredHomeAdapter = new StaggeredHomeAdapter(this, mList);
        mRecyclerView.setAdapter(mStaggeredHomeAdapter);
    }

    private void setLister() {
        mHomeAdaper.setmOnItemClickListener(new HomeAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(View view, int position) {
                Toast.makeText(RecyclerViewActivity.this, "点击第" + (position + 1) + "条", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onItemLongClick(View view, final int position) {
                //长按弹出对话框
                new AlertDialog.Builder(RecyclerViewActivity.this)
                        .setTitle("确认删除吗？")
                        .setNegativeButton("取消", null)
                        .setPositiveButton("确定", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                mHomeAdaper.removeData(position);
                            }
                        })
                        .show();
            }
        });
    }
}
