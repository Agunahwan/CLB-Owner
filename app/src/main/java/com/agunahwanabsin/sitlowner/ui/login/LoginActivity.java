package com.agunahwanabsin.sitlowner.ui.login;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.agunahwanabsin.sitlowner.MainActivity;
import com.agunahwanabsin.sitlowner.R;
import com.agunahwanabsin.sitlowner.api.instance.OwnerInterface;
import com.agunahwanabsin.sitlowner.api.model.response.Login;
import com.agunahwanabsin.sitlowner.api.services.OwnerApiService;
import com.agunahwanabsin.sitlowner.helper.SessionManager;
import com.agunahwanabsin.sitlowner.model.Owner;
import com.google.gson.Gson;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LoginActivity extends AppCompatActivity {

    EditText txtUsername, txtPassword;
    Button btnLogin;
    ProgressDialog loading;

    // Session Manager Class
    SessionManager session;

    // Context class
    Context mContext;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        mContext = this;
        initComponents();
    }

    private void initComponents() {
        // Session Manager
        session = new SessionManager(mContext);

        // Checking if is loggin, then move to MainActivity
        if (session.isLoggedIn()) {
            moveToMainActivity();
        }

        // Declaration objects
        txtUsername = (EditText) findViewById(R.id.username);
        txtPassword = (EditText) findViewById(R.id.password);
        btnLogin = (Button) findViewById(R.id.login);

        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Login validation = validate();
                if (validation.isSuccess()) {
                    loading = new ProgressDialog(mContext);
                    loading.setMessage(getString(R.string.msg_loading));
                    loading.show();
                    requestLogin();
                } else {
                    Toast.makeText(mContext, validation.getMessage(), Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    private Login validate() {
        String username = txtUsername.getText().toString();
        String password = txtPassword.getText().toString();
        Login responseLogin = new Login(true, "", null);
        if (username.trim().equals("") || password.trim().equals("")) {
            responseLogin.setSuccess(false);
            responseLogin.setMessage(getString(R.string.prompt_empty));
        }

        return responseLogin;
    }

    private void requestLogin() {
        OwnerInterface service = OwnerApiService.login();
        String username = txtUsername.getText().toString();
        String password = txtPassword.getText().toString();
        Call<ResponseBody> call = service.login(username, password);
        Callback<ResponseBody> callback = new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                if (response.isSuccessful()) {
                    loading.dismiss();
                    try {
                        JSONObject jsonRESULTS = new JSONObject(response.body().string());
                        if (jsonRESULTS != null && !jsonRESULTS.toString().equals("{}")) {
                            Login responseLogin = new Gson().fromJson(String.valueOf(jsonRESULTS), Login.class);
                            if (responseLogin.isSuccess()) {
                                // Jika login berhasil maka data nama yang ada di response API
                                // akan diparsing ke activity selanjutnya.
                                Toast.makeText(mContext, getString(R.string.prompt_success_login), Toast.LENGTH_SHORT).show();
                                Owner owner = responseLogin.getData();
                                session.createLoginSession(owner);

                                moveToMainActivity();
                            } else {
                                Toast.makeText(mContext, responseLogin.getMessage(), Toast.LENGTH_SHORT).show();
                            }
                        } else {
                            // Jika login gagal
                            String error_message = jsonRESULTS.getString(getString(R.string.prompt_failed_response));
                            Toast.makeText(mContext, error_message, Toast.LENGTH_SHORT).show();
                        }
                    } catch (JSONException e) {
                        Toast.makeText(mContext, "Error: " + e.toString(), Toast.LENGTH_SHORT).show();
                        e.printStackTrace();
                    } catch (IOException e) {
                        Toast.makeText(mContext, "Error: " + e.toString(), Toast.LENGTH_SHORT).show();
                        e.printStackTrace();
                    }
                } else {
                    loading.dismiss();
                }
            }

            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {
                Toast.makeText(mContext, "Error: " + t.toString(), Toast.LENGTH_LONG).show();
                Log.e("debug", "onFailure: ERROR > " + t.toString());
                loading.dismiss();
            }
        };
        call.enqueue(callback);
    }

    private void moveToMainActivity() {
        Intent intent = new Intent(mContext, MainActivity.class);
        startActivity(intent);
        finish();
    }
}