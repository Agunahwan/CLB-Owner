package com.agunahwanabsin.sitlowner.ui.kotak;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.agunahwanabsin.sitlowner.R;
import com.agunahwanabsin.sitlowner.adapter.KotakAdapter;
import com.agunahwanabsin.sitlowner.adapter.instance.OnListListener;
import com.agunahwanabsin.sitlowner.api.instance.KotakInterface;
import com.agunahwanabsin.sitlowner.api.services.KotakApiService;
import com.agunahwanabsin.sitlowner.helper.CommonLibrary;
import com.agunahwanabsin.sitlowner.helper.ConstantObject;
import com.agunahwanabsin.sitlowner.helper.SessionManager;
import com.agunahwanabsin.sitlowner.model.Kotak;
import com.agunahwanabsin.sitlowner.model.Owner;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class KotakFragment extends Fragment implements OnListListener {

    Context mContext;
    View view;
    TextView txtTanggal;
    RecyclerView rvKotak;
    ProgressDialog loading;
    SessionManager session;
    KotakAdapter kotakAdapter;
    List<Kotak> listKotak;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_kotak, container, false);

        txtTanggal = (TextView) view.findViewById(R.id.text_jadwal);
        rvKotak = (RecyclerView) view.findViewById(R.id.kotak_recycler);

        mContext = this.getActivity().getApplicationContext();
        rvKotak.setLayoutManager(new LinearLayoutManager(mContext));
        rvKotak.addItemDecoration(new DividerItemDecoration(mContext, DividerItemDecoration.VERTICAL));
        kotakAdapter = new KotakAdapter(mContext, this);

        return view;
    }

    @Override
    public void onStart() {
        super.onStart();

        // Session Manager
        session = new SessionManager(mContext);

        Owner owner = session.getUserDetails();

        // Set Tanggal
        String tanggal = CommonLibrary.getToday();
        txtTanggal.setText(tanggal);

        loadListKotak(owner.getIdOwner());
    }

    private void loadListKotak(int idOwner) {
        com.agunahwanabsin.sitlowner.api.model.request.Kotak kotak = new com.agunahwanabsin.sitlowner.api.model.request.Kotak(idOwner);
        KotakInterface service = KotakApiService.getListKotak();
        Call<List<Kotak>> call = service.getListKotak(kotak);
        Callback<List<Kotak>> callback = new Callback<List<Kotak>>() {
            @Override
            public void onResponse(Call<List<Kotak>> call, Response<List<Kotak>> response) {
                if (response.isSuccessful()) {
                    listKotak = response.body();
                    if (listKotak.size() > 0) {
                        kotakAdapter.setData(listKotak);
                        kotakAdapter.notifyDataSetChanged();

                        rvKotak.setAdapter(kotakAdapter);
                    } else {
                        Toast.makeText(mContext, "Data kosong", Toast.LENGTH_SHORT).show();
                    }
                }
            }

            @Override
            public void onFailure(Call<List<Kotak>> call, Throwable t) {
                Toast.makeText(mContext, "Error: " + t.toString(), Toast.LENGTH_LONG).show();
                Log.e("debug", "onFailure: ERROR > " + t.toString());
            }
        };
        call.enqueue(callback);
    }

    @Override
    public void OnListClick(int position) {
        Kotak kotak = listKotak.get(position);
        if (kotak.getJumlahObject() > 0) {
            Intent intent = new Intent(mContext, TanggalPengecekanActivity.class);
            intent.putExtra(ConstantObject.KODE_KOTAK, kotak.getKodeKotak());
            startActivity(intent);
        }
    }
}