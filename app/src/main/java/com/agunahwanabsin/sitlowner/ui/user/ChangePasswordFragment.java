package com.agunahwanabsin.sitlowner.ui.user;

import android.content.Context;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.agunahwanabsin.sitlowner.R;
import com.agunahwanabsin.sitlowner.api.instance.KotakInterface;
import com.agunahwanabsin.sitlowner.api.instance.OwnerInterface;
import com.agunahwanabsin.sitlowner.api.model.request.ChangePasswordRequest;
import com.agunahwanabsin.sitlowner.api.model.response.ChangePasswordResponse;
import com.agunahwanabsin.sitlowner.api.services.KotakApiService;
import com.agunahwanabsin.sitlowner.api.services.OwnerApiService;
import com.agunahwanabsin.sitlowner.helper.SessionManager;
import com.agunahwanabsin.sitlowner.model.Kotak;
import com.agunahwanabsin.sitlowner.model.Owner;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ChangePasswordFragment extends Fragment {

    // Global Variable
    Context mContext;
    View view;

    // View Object
    EditText txtOldPassword;
    EditText txtNewPassword;
    EditText txtConfirmNewPassword;
    Button btnSimpan;

    // Session Manager Class
    SessionManager session;

    // Session class
    Owner owner;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.fragment_change_password, container, false);

        // Set Context
        mContext = this.getActivity().getApplicationContext();

        // Initialize view
        txtOldPassword = (EditText) view.findViewById(R.id.password_lama);
        txtNewPassword = (EditText) view.findViewById(R.id.password_baru);
        txtConfirmNewPassword = (EditText) view.findViewById(R.id.conf_password_baru);
        btnSimpan = (Button) view.findViewById(R.id.simpan);

        // Session Manager
        session = new SessionManager(mContext);

        // Get Session
        owner = session.getUserDetails();

        btnSimpan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (checkValidation()) {
                    changePassword();
                }
            }
        });

        return view;
    }

    private boolean checkValidation() {
        if (TextUtils.isEmpty(txtOldPassword.getText())) {
            txtOldPassword.setError("Password Lama is required!");
            txtOldPassword.setHint("please enter Password Lama");

            return false;
        }

        if (TextUtils.isEmpty(txtNewPassword.getText())) {
            txtNewPassword.setError("Password Baru is required!");
            txtNewPassword.setHint("please enter Password Baru");

            return false;
        }

        if (!txtNewPassword.getText().toString().equals(txtConfirmNewPassword.getText().toString())) {
            txtConfirmNewPassword.setError("Password Baru and Konfirmasi Password must same!");
            txtConfirmNewPassword.setHint("please enter Password Baru and Konfirmasi Password with same character");

            return false;
        }

        return true;
    }

    private void changePassword() {
        int idOwner = owner.getIdOwner();
        String oldPassword = txtOldPassword.getText().toString();
        String newPassword = txtNewPassword.getText().toString();
        String confirmPassword = txtConfirmNewPassword.getText().toString();

        ChangePasswordRequest changePasswordRequest = new ChangePasswordRequest(idOwner, oldPassword, newPassword, confirmPassword);
        OwnerInterface service = OwnerApiService.changePassword();
        Call<ChangePasswordResponse> call = service.changePassword(changePasswordRequest);
        Callback<ChangePasswordResponse> callback = new Callback<ChangePasswordResponse>() {
            @Override
            public void onResponse(Call<ChangePasswordResponse> call, Response<ChangePasswordResponse> response) {
                if (response.isSuccessful()) {
                    ChangePasswordResponse changePasswordResponse = response.body();
                    Toast.makeText(mContext, changePasswordResponse.getMessage(), Toast.LENGTH_LONG).show();
                }
            }

            @Override
            public void onFailure(Call<ChangePasswordResponse> call, Throwable t) {
                Toast.makeText(mContext, "Error: " + t.toString(), Toast.LENGTH_LONG).show();
                Log.e("debug", "onFailure: ERROR > " + t.toString());
            }
        };
        call.enqueue(callback);
    }
}