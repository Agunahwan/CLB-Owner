package com.agunahwanabsin.sitlowner.adapter.view;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.agunahwanabsin.sitlowner.R;
import com.agunahwanabsin.sitlowner.adapter.instance.OnListListener;

public class ObjectKotakAdapterViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
    public TextView txtObject;
    public ImageView imgMore;
    OnListListener onListListener;

    public ObjectKotakAdapterViewHolder(@NonNull View itemView, OnListListener onListListener) {
        super(itemView);

        txtObject = (TextView) itemView.findViewById(R.id.object);
        imgMore = (ImageView) itemView.findViewById(R.id.imageMore);

        this.onListListener = onListListener;

        itemView.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        onListListener.OnListClick(getAdapterPosition());
    }
}
