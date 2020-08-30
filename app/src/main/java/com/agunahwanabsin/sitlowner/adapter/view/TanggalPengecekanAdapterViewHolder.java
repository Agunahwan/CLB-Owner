package com.agunahwanabsin.sitlowner.adapter.view;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.agunahwanabsin.sitlowner.R;
import com.agunahwanabsin.sitlowner.adapter.instance.OnListListener;

public class TanggalPengecekanAdapterViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{
    public TextView txtTanggalPengecekan;
    public TextView txtBeekeeper;
    public ImageView imgMore;
    OnListListener onListListener;

    public TanggalPengecekanAdapterViewHolder(@NonNull View itemView, OnListListener onListListener) {
        super(itemView);

        txtTanggalPengecekan = (TextView) itemView.findViewById(R.id.tanggal_pengecekan);
        txtBeekeeper = (TextView) itemView.findViewById(R.id.beekeeper);
        imgMore = (ImageView) itemView.findViewById(R.id.imageMore);

        this.onListListener = onListListener;

        itemView.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        onListListener.OnListClick(getAdapterPosition());
    }
}
