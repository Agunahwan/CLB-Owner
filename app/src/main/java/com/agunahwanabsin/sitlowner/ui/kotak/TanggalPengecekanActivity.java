package com.agunahwanabsin.sitlowner.ui.kotak;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.agunahwanabsin.sitlowner.R;
import com.agunahwanabsin.sitlowner.adapter.TanggalPengecekanAdapter;
import com.agunahwanabsin.sitlowner.adapter.instance.OnListListener;
import com.agunahwanabsin.sitlowner.api.instance.TanggalPengecekanInterface;
import com.agunahwanabsin.sitlowner.api.model.request.TanggalPengecekanRequest;
import com.agunahwanabsin.sitlowner.api.services.TanggalPengecekanApiService;
import com.agunahwanabsin.sitlowner.helper.ConstantObject;
import com.agunahwanabsin.sitlowner.helper.SessionManager;
import com.agunahwanabsin.sitlowner.model.TanggalPengecekan;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class TanggalPengecekanActivity extends AppCompatActivity implements OnListListener {

    // View Object
    TextView txtBlokKotak;
    RecyclerView rvTanggalPengecekan;

    // Global Object
    TanggalPengecekanAdapter tanggalPengecekanAdapter;
    List<TanggalPengecekan> listTanggalPengecekan;
    Context mContext;

    // Session Manager Class
    SessionManager session;

    // Extra data
    Bundle extra;
    String kodeKotak;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tanggal_pengecekan);

        // Add Back Button
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);

        // Set Context
        mContext = getApplicationContext();

        // Session class instance
        session = new SessionManager(mContext);

        // Declare object view
        txtBlokKotak = (TextView) findViewById(R.id.blok_kotak);
        rvTanggalPengecekan = (RecyclerView) findViewById(R.id.pengecekan_recycler);

        // Set view Recycler
        rvTanggalPengecekan.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
        rvTanggalPengecekan.addItemDecoration(new DividerItemDecoration(getApplicationContext(), DividerItemDecoration.VERTICAL));

        // Get extra
        extra = getIntent().getExtras();

        if (extra != null) {
            kodeKotak = extra.getString(ConstantObject.KODE_KOTAK);

            txtBlokKotak.setText(kodeKotak);
            tanggalPengecekanAdapter = new TanggalPengecekanAdapter(getApplicationContext(), this);
            loadDataTanggalPengecekan(kodeKotak);
        }
    }

    private void loadDataTanggalPengecekan(String kodeKotak) {
        TanggalPengecekanRequest tanggalPengecekanRequest = new TanggalPengecekanRequest(kodeKotak);
        TanggalPengecekanInterface service = TanggalPengecekanApiService.getListTanggalPengecekan();
        Call<List<TanggalPengecekan>> call = service.getListTanggalPengecekan(tanggalPengecekanRequest);
        Callback<List<TanggalPengecekan>> callback = new Callback<List<TanggalPengecekan>>() {
            @Override
            public void onResponse(Call<List<TanggalPengecekan>> call, Response<List<TanggalPengecekan>> response) {
                if (response.isSuccessful()) {
                    listTanggalPengecekan = response.body();
                    if (listTanggalPengecekan.size() > 0) {
                        tanggalPengecekanAdapter.setData(listTanggalPengecekan);
                        tanggalPengecekanAdapter.notifyDataSetChanged();

                        rvTanggalPengecekan.setAdapter(tanggalPengecekanAdapter);
                    } else {
                        Toast.makeText(mContext, "Data kosong", Toast.LENGTH_SHORT).show();
                    }
                }
            }

            @Override
            public void onFailure(Call<List<TanggalPengecekan>> call, Throwable t) {
                Toast.makeText(mContext, "Error: " + t.toString(), Toast.LENGTH_LONG).show();
                Log.e("debug", "onFailure: ERROR > " + t.toString());
            }
        };
        call.enqueue(callback);
    }

    @Override
    public void OnListClick(int position) {
        TanggalPengecekan tanggalPengecekan = listTanggalPengecekan.get(position);
        Intent intent = new Intent(mContext, ObjectActivity.class);
        intent.putExtra(ConstantObject.ID_HASIL_PENGECEKAN, tanggalPengecekan.getIdHasilPengecekan());
        intent.putExtra(ConstantObject.KODE_KOTAK, tanggalPengecekan.getKodeKotak());
        startActivity(intent);
    }

    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return true;
    }
}