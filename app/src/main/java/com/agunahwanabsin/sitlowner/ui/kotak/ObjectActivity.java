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
import com.agunahwanabsin.sitlowner.adapter.ObjectKotakAdapter;
import com.agunahwanabsin.sitlowner.adapter.instance.OnListListener;
import com.agunahwanabsin.sitlowner.api.instance.ObjectKotakInterface;
import com.agunahwanabsin.sitlowner.api.model.request.ObjectKotakRequest;
import com.agunahwanabsin.sitlowner.api.services.ObjectKotakApiService;
import com.agunahwanabsin.sitlowner.helper.ConstantObject;
import com.agunahwanabsin.sitlowner.helper.SessionManager;
import com.agunahwanabsin.sitlowner.model.ObjectKotak;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ObjectActivity extends AppCompatActivity implements OnListListener {

    // View Object
    TextView txtKotak;
    RecyclerView rvObject;

    // Global Object
    ObjectKotakAdapter objectKotakAdapter;
    List<ObjectKotak> listObjectKotak;
    Context mContext;

    // Session Manager Class
    SessionManager session;

    // Extra data
    Bundle extra;
    String kodeKotak;
    int idHasilPengecekan;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_object);

        // Add Back Button
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);

        // Set Context
        mContext = getApplicationContext();

        // Session class instance
        session = new SessionManager(mContext);

        // Declare object view
        txtKotak = (TextView) findViewById(R.id.kotak);
        rvObject = (RecyclerView) findViewById(R.id.object_recycler);

        // Set view Recycler
        rvObject.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
        rvObject.addItemDecoration(new DividerItemDecoration(getApplicationContext(), DividerItemDecoration.VERTICAL));

        // Get extra
        extra = getIntent().getExtras();

        if (extra != null) {
            kodeKotak = extra.getString(ConstantObject.KODE_KOTAK);
            idHasilPengecekan = extra.getInt(ConstantObject.ID_HASIL_PENGECEKAN);

            txtKotak.setText(kodeKotak);
            objectKotakAdapter = new ObjectKotakAdapter(getApplicationContext(), this);
            loadDataObjectKotak();
        }
    }

    private void loadDataObjectKotak() {
        ObjectKotakRequest objectKotakRequest = new ObjectKotakRequest(idHasilPengecekan, kodeKotak);
        ObjectKotakInterface service = ObjectKotakApiService.getListObject();
        Call<List<ObjectKotak>> call = service.getListObject(objectKotakRequest);
        Callback<List<ObjectKotak>> callback = new Callback<List<ObjectKotak>>() {
            @Override
            public void onResponse(Call<List<ObjectKotak>> call, Response<List<ObjectKotak>> response) {
                if (response.isSuccessful()) {
                    listObjectKotak = response.body();
                    if (listObjectKotak.size() > 0) {
                        objectKotakAdapter.setData(listObjectKotak);
                        objectKotakAdapter.notifyDataSetChanged();

                        rvObject.setAdapter(objectKotakAdapter);
                    } else {
                        Toast.makeText(mContext, "Data kosong", Toast.LENGTH_SHORT).show();
                    }
                }
            }

            @Override
            public void onFailure(Call<List<ObjectKotak>> call, Throwable t) {
                Toast.makeText(mContext, "Error: " + t.toString(), Toast.LENGTH_LONG).show();
                Log.e("debug", "onFailure: ERROR > " + t.toString());
            }
        };
        call.enqueue(callback);
    }

    @Override
    public void OnListClick(int position) {
        ObjectKotak objectKotak = listObjectKotak.get(position);
        Intent intent = new Intent(mContext, DetailPengecekanActivity.class);
        intent.putExtra(ConstantObject.ID_HASIL_PENGECEKAN, idHasilPengecekan);
        intent.putExtra(ConstantObject.ID_OBJECT, objectKotak.getIdObject());
        startActivity(intent);
    }

    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return true;
    }
}