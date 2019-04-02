package com.example.module_kind.adapter;

import android.support.annotation.Nullable;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CompoundButton;
import android.widget.RadioButton;

import com.example.module_kind.R;

import java.util.List;

import nico.stytool.gson_module.SnackChildGson;
import nico.stytool.gson_module.SnackKindGson;

public class SnackChildKindAdapter extends RecyclerView.Adapter<SnackChildKindAdapter.ViewHolder> {

    private int index = 0;//标记当前选择的选项
    private List<SnackChildGson> data;

    public SnackChildKindAdapter(@Nullable List<SnackChildGson> data) {

        this.data = data;
    }

    private onItemClickListener onItemClickListener;

    public void setOnItemClickListener(onItemClickListener onItemClickListener) {
        this.onItemClickListener = onItemClickListener;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new ViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.ry_item_list_adapter, null));
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, final int position) {
        holder.rbTitle.setText(data.get(position).getSnackName());
        holder.rbTitle.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    index = position;
                    if (onItemClickListener != null)
                        onItemClickListener.onClickListener(data.get(position).getId(), data.get(position).getSnackName());
                }
                notifyDataSetChanged();
            }
        });
        if (index == position) {
            holder.indicate.setVisibility(View.VISIBLE);
            holder.rbTitle.setTextColor(0xff000000);
        } else {
            holder.indicate.setVisibility(View.GONE);
            holder.rbTitle.setTextColor(0xff9E9E9E);
        }
    }


    @Override
    public int getItemCount() {
        return data.size();
    }


    class ViewHolder extends RecyclerView.ViewHolder {
        private RadioButton rbTitle;
        private View indicate;

        public ViewHolder(View itemView) {
            super(itemView);
            rbTitle = itemView.findViewById(R.id.rb_kind_name);
            indicate = itemView.findViewById(R.id.view_indicate);
        }
    }


    public interface onItemClickListener {
        void onClickListener(int pid, String name);
    }

}