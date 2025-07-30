package co.edu.ue.nanniapp;

import static co.edu.ue.nanniapp.api.ValuesApiParents.BASE_URL;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.text.InputType;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;

import co.edu.ue.nanniapp.api.ServiceUser;
import co.edu.ue.nanniapp.model.ResponseParentsRegister;
import co.edu.ue.nanniapp.model.UserRegistrationRequest;
import co.edu.ue.nanniapp.remote.ClienteRetrofit;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class ParentsMainRegister extends AppCompatActivity {
    private EditText etNameUser;
    private EditText etEmailUser;
    private EditText etPass;
    private EditText etPassConfirm;
    private Button btnContinue;
    private Button btnBack;
    private Drawable passwordVisible;
    private Drawable passwordHidden;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_parents_main_register);
        begin();
        btnContinue.setOnClickListener(this::createUser);
        btnBack.setOnClickListener(this::goBack);
        passwordVisible = getResources().getDrawable(R.drawable.password);
        passwordHidden = getResources().getDrawable(R.drawable.hide_password);
        etPass.setOnTouchListener((v, event) -> {
            final int DRAWABLE_RIGHT = 2;
            if (event.getAction() == MotionEvent.ACTION_UP) {
                if (event.getRawX() >= (etPass.getRight() - etPass.getCompoundDrawables()[DRAWABLE_RIGHT].getBounds().width())) {
                    if (etPass.getInputType() == InputType.TYPE_TEXT_VARIATION_VISIBLE_PASSWORD) {
                        etPass.setInputType(InputType.TYPE_CLASS_TEXT | InputType.TYPE_TEXT_VARIATION_PASSWORD);
                        etPass.setCompoundDrawablesWithIntrinsicBounds(null, null, passwordHidden, null);
                    } else {
                        etPass.setInputType(InputType.TYPE_TEXT_VARIATION_VISIBLE_PASSWORD);
                        etPass.setCompoundDrawablesWithIntrinsicBounds(null, null, passwordVisible, null);
                    }
                    etPass.setSelection(etPass.getText().length());
                    return true;
                }
            }
            return false;
        });
    }
    private void createUser(View view) {
        String password = etPass.getText().toString();
        String confirmPassword = etPassConfirm.getText().toString();
        if (!password.equals(confirmPassword)) {
            Toast.makeText(getApplicationContext(), "Las contraseñas no coinciden", Toast.LENGTH_SHORT).show();
        }else {
            UserRegistrationRequest registerUser = new UserRegistrationRequest();
            registerUser.setName(etNameUser.getText().toString());
            registerUser.setEmail(etEmailUser.getText().toString());
            registerUser.setPassword(etPass.getText().toString());
            registerUser.setPassword_confirmation(etPassConfirm.getText().toString());
            Log.i("API_RESPONSE", registerUser.toString());
            Retrofit retrofit = ClienteRetrofit.getClient(BASE_URL);
            ServiceUser serviceUser = retrofit.create(ServiceUser.class);
            Call<ResponseParentsRegister> call = serviceUser.registerUser(registerUser);
            call.enqueue(new Callback<ResponseParentsRegister>() {
                @Override
                public void onResponse(Call<ResponseParentsRegister> call, Response<ResponseParentsRegister> response) {
                        if (response.isSuccessful()) {
                        ResponseParentsRegister body = response.body();
                        String mensaje = body.getMsg();
                        String status = body.getStatus();
                        Log.d("API_RESPONSE", "Respuesta exitosa. Mensaje: " + mensaje + "   " + status + "   " + response.body().toString());
                        Toast.makeText(getApplicationContext(), "Usuario registrado con éxito", Toast.LENGTH_SHORT).show();
                        goTo();
                    } else {
                        Toast.makeText(getApplicationContext(), "Error en el registro. Inténtalo de nuevo.", Toast.LENGTH_SHORT).show();
                        Log.e("API_ERROR", "Error en la respuesta: " + response.message());
                    }
                }

                @Override
                public void onFailure(Call<ResponseParentsRegister> call, Throwable t) {
                    Toast.makeText(getApplicationContext(), "Error de servidor. Comuníquese con el administrador.", Toast.LENGTH_SHORT).show();
                    Log.e("NETWORK_ERROR", "Error de red: " + t.getMessage());
                }
            });
        }
    }
    private void goTo() {
        try {
            Intent intent = new Intent(this, ParentsRegister.class);
            startActivity(intent);
        } catch (Exception e) {
            Log.e("Error", "Excepción al iniciar ParentsRegister: " + e.getMessage());
        }
    }
    private void goBack(View view) {
        try {
            Intent intent = new Intent(this, MainActivity.class);
            startActivity(intent);
        }catch (Exception e){
            Log.e("Error", "Excepción al iniciar ParentsRegister: " + e.getMessage());
        }
    }
    private void begin(){
        this.etNameUser = findViewById(R.id.etNameUser);
        this.etEmailUser = findViewById(R.id.etEmail);
        this.etPass = findViewById(R.id.etPass);
        this.etPassConfirm = findViewById(R.id.etPassConfirm);
        this.btnContinue = findViewById(R.id.btnContinue);
        this.btnBack = findViewById(R.id.btnBack);
    }

    /*private void alertView(String mensaje) {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Login");
        builder.setMessage(mensaje);
        builder.setPositiveButton("ACEPTAR", null);
        builder.create();
        builder.show();
    }

    public static boolean isNullOrEmpty(Object obj) {
        if (obj == null) return true;
        if (obj instanceof String) return ((String) obj).trim().equals("") || ((String) obj).equalsIgnoreCase("NULL");
        if (obj instanceof Integer) return ((Integer) obj) == 0;
        if (obj instanceof Long) return ((Long) obj).equals(0L);
        if (obj instanceof Double) return ((Double) obj).equals(0.0);
        if (obj instanceof Collection) return (((Collection<?>) obj).isEmpty());
        return false;
    }*/

}