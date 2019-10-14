package com.example.getandpostusingretrofit.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.getandpostusingretrofit.R;
import com.example.getandpostusingretrofit.model.ModelClass;

import java.util.List;

public class MyAdapter extends RecyclerView.Adapter<MyAdapter.CustomViewHolder> {

    Context context;
    List<ModelClass> list;

    public MyAdapter(Context context, List<ModelClass> list) {

        this.context=context;
        this.list=list;

    }

    class CustomViewHolder extends RecyclerView.ViewHolder {

        public final View mView;

        TextView tvId;
        TextView tvData;

        CustomViewHolder(View itemView) {
            super(itemView);
            mView = itemView;

            tvId = mView.findViewById(R.id.tvId);
            tvData = mView.findViewById(R.id.tvData);
        }
    }

    @Override
    public CustomViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View view = layoutInflater.inflate(R.layout.layout_recycle_this, parent, false);
        return new CustomViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull CustomViewHolder holder, int position) {
        holder.tvId.setText(list.get(position).getId());

        holder.tvData.setText(list.get(position).getData());
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

}
