package com.example.advance.chapter1.recyclerview;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.TextView;

import com.example.advance.R;

import java.util.List;

/**
 * @author jinxin
 */
public class HomeAdapter extends RecyclerView.Adapter<HomeAdapter.MyViewHolder> implements View.OnClickListener, View.OnLongClickListener {

    private Context mContext;
    private List<String> mList;

    public HomeAdapter(Context mContext, List<String> mList) {
        this.mContext = mContext;
        this.mList = mList;
    }

    public void removeData(int position) {
        mList.remove(position);
        notifyItemRemoved(position);
    }

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

    /**
     * 加载条目布局
     *
     * @param viewGroup
     * @param position
     * @return
     */
    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int position) {

        View view = LayoutInflater.from(mContext).inflate(R.layout.item_recycler, viewGroup, false);
        MyViewHolder holder = new MyViewHolder(view);

        //点击事件的监听
        view.setOnClickListener(this);
        view.setOnLongClickListener(this);
        return holder;
    }

    /**
     * 将视图与数据进行绑定
     *
     * @param myViewHolder
     * @param position
     */
    @Override
    public void onBindViewHolder(@NonNull MyViewHolder myViewHolder, int position) {
        myViewHolder.itemView.setTag(position);
        myViewHolder.textView.setText(mList.get(position));
    }

    /**
     * 点击事件回调
     * @param view 视图
     */
    @Override
    public void onClick(View view) {
        if (mOnItemClickListener != null) {
            mOnItemClickListener.onItemClick(view, (int) view.getTag());
        }
    }

    /**
     * 长按点击事件回调
     * @param view
     * @return
     */
    @Override
    public boolean onLongClick(View view) {
        if (mOnItemClickListener != null) {
            mOnItemClickListener.onItemLongClick(view, (int) view.getTag());
        }
        return true;
    }


    @Override
    public int getItemCount() {
        return mList.size();
    }

    class MyViewHolder extends RecyclerView.ViewHolder {

        TextView textView;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            textView = itemView.findViewById(R.id.tv_item);
        }
    }
}
