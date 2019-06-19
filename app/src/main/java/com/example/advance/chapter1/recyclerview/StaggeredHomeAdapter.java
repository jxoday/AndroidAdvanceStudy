package com.example.advance.chapter1.recyclerview;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.advance.R;

import java.util.ArrayList;
import java.util.List;

/**
 * @author jinxin
 */
public class StaggeredHomeAdapter extends RecyclerView.Adapter<StaggeredHomeAdapter.MyViewHolder> {

    private List<String> mDatas;
    private LayoutInflater mInflater;

    private List<Integer> mHeights;


    /**
     * 定义条目点击事件
     */
    public interface OnItemClickListener {
        /**
         * 定义条目点击事件
         *
         * @param view     视图
         * @param position 条目
         */
        void onItemClick(View view, int position);

        /**
         * 定义条目长按点击事件
         *
         * @param view     视图
         * @param position 条目
         */
        void onItemLongClick(View view, int position);
    }

    private OnItemClickListener mOnItemClickListener;

    public void setmOnItemClickListener(OnItemClickListener mOnItemClickListener) {
        this.mOnItemClickListener = mOnItemClickListener;
    }

    public StaggeredHomeAdapter(Context context, List<String> datas) {
        mInflater = LayoutInflater.from(context);
        mDatas = datas;

        // 随机的高度控制每个item的高度
        mHeights = new ArrayList<Integer>();
        for (int i = 0; i < mDatas.size(); i++) {
            mHeights.add((int) (100 + Math.random() * 300));
        }
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int i) {
        MyViewHolder holder = new MyViewHolder(mInflater.inflate(
                R.layout.item_recycler, parent, false));
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        ViewGroup.LayoutParams lp = holder.tv.getLayoutParams();
        lp.height = mHeights.get(position);

        holder.tv.setLayoutParams(lp);
        holder.tv.setText(mDatas.get(position));
    }

    @Override
    public int getItemCount() {
        return mDatas.size();
    }

    /**
     * 新增条目
     *
     * @param position
     */
    public void addData(int position) {
        mDatas.add(position, "Insert One");
        mHeights.add((int) (100 + Math.random() * 300));
        notifyItemInserted(position);
    }

    /**
     * 删除条目
     * @param position
     */
    public void removeData(int position) {
        mDatas.remove(position);
        notifyItemRemoved(position);
    }

    class MyViewHolder extends RecyclerView.ViewHolder {

        TextView tv;

        public MyViewHolder(View view) {
            super(view);
            tv = (TextView) view.findViewById(R.id.tv_item);

        }
    }
}
