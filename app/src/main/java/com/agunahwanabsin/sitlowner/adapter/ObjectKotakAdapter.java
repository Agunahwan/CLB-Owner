package com.agunahwanabsin.sitlowner.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.agunahwanabsin.sitlowner.R;
import com.agunahwanabsin.sitlowner.adapter.instance.OnListListener;
import com.agunahwanabsin.sitlowner.adapter.view.ObjectKotakAdapterViewHolder;
import com.agunahwanabsin.sitlowner.model.ObjectKotak;

import java.util.List;

public class ObjectKotakAdapter extends RecyclerView.Adapter<ObjectKotakAdapterViewHolder>{
    Context mContext;
    List<ObjectKotak> mListObjectKotak;
    private OnListListener mOnListListener;

    public ObjectKotakAdapter(Context mContext, OnListListener mOnListListener) {
        this.mContext = mContext;
        this.mOnListListener = mOnListListener;
    }

    public void setData(List<ObjectKotak> mListObjectKotak) {
        this.mListObjectKotak = mListObjectKotak;
    }

    @NonNull
    @Override
    public ObjectKotakAdapterViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.item_object, parent, false);
        ObjectKotakAdapterViewHolder viewHolder = new ObjectKotakAdapterViewHolder(view, mOnListListener);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ObjectKotakAdapterViewHolder holder, int position) {
        String object = "Object : " + mListObjectKotak.get(position).getObject();

        holder.txtObject.setText(object);
    }

    @Override
    public int getItemCount() {
        return (mListObjectKotak != null ? mListObjectKotak.size() : 0);
    }
}
