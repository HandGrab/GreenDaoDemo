package com.sandy.blog.greendaodemo;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.sandy.blog.greendaodemo.dao.Singer;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by Sandy Luo on 2017/1/17.
 */

public class SingerAdapter extends RecyclerView.Adapter<SingerAdapter.SingerHolder> {


    private List<Singer> mData;

    public SingerAdapter(List<Singer> mData) {
        this.mData = mData;
    }

    @Override
    public SingerHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_item_singer, null);
        return new SingerHolder(view);
    }

    @Override
    public void onBindViewHolder(SingerHolder holder, int position) {
        if (mData.size() > 0 && position < mData.size()) {
            holder.mItemName.setText("姓名:" + mData.get(position).getName());
            holder.mItemSex.setText("性别:" + mData.get(position).getSex());
            holder.mItemCountry.setText("国家:" + mData.get(position).getCountry());
        }
    }

    @Override
    public int getItemCount() {
        return mData.size();
    }


    class SingerHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.item_name)
        TextView mItemName;
        @BindView(R.id.item_sex)
        TextView mItemSex;
        @BindView(R.id.item_country)
        TextView mItemCountry;
        @BindView(R.id.item_edit)
        Button mItemEdit;
        @BindView(R.id.item_delete)
        Button mItemDelete;

        public SingerHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }

        @OnClick({R.id.item_edit, R.id.item_delete})
        public void onClick(View view) {
            switch (view.getId()) {
                case R.id.item_edit:
                    if (onDataControlListener != null) {
                        onDataControlListener.onEdit(getAdapterPosition());
                    }
                    break;

                case R.id.item_delete:
                    if (onDataControlListener != null) {
                        onDataControlListener.onDelete(mData.get(getAdapterPosition()));
                    }
                    break;
            }
        }


    }

    public OnDataControlListener onDataControlListener;

    public void setOnDataControlListener(OnDataControlListener onDataControlListener) {
        this.onDataControlListener = onDataControlListener;
    }

    public interface OnDataControlListener {

        void onDelete(Singer singer);

        void onEdit(int position);
    }
}
