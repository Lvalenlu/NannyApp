package co.edu.ue.nanniapp;

import static co.edu.ue.nanniapp.api.ValuesApiParents.BASE_URL;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.service.autofill.UserData;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import co.edu.ue.nanniapp.api.ServiceLoginParents;
import co.edu.ue.nanniapp.api.ServiceParents;
import co.edu.ue.nanniapp.model.Credentials;
import co.edu.ue.nanniapp.model.Loger;
import co.edu.ue.nanniapp.model.ResponseCredentials;
import co.edu.ue.nanniapp.model.UserRegistrationRequest;
import co.edu.ue.nanniapp.remote.ClienteRetrofit;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ParentsLogin extends AppCompatActivity {
    private Retrofit retrofit;
    private EditText etParentEmail;
    private EditText etParentPassword;
    private Button btnLogin;
    private Button btnBack;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_parents_login);
        linkInit();
        btnLogin = findViewById(R.id.btnLogin);
        btnBack = findViewById(R.id.btnBack);
        btnLogin.setOnClickListener(this::processLogin);
        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ParentsLogin.this, MainActivity.class);
                startActivity(intent);
            }
        });

    }

    private void processLogin(View view) {
        if (!validEmail(etParentEmail.getText().toString()) && etParentPassword.getText().length()<=3){
            alertView("ERROR EN LOS DATOS");
        }else{
            Loger loger = new Loger();
            loger.setEmail(etParentEmail.getText().toString());
            loger.setPassword(etParentPassword.getText().toString());
            retrofit = ClienteRetrofit.getClient(BASE_URL);
            ServiceLoginParents serviceLogin = retrofit.create(ServiceLoginParents.class);
            Call<ResponseCredentials> call = serviceLogin.accessLogin(loger);
            call.enqueue(new Callback<ResponseCredentials>() {
                @Override
                public void onResponse(Call<ResponseCredentials> call, Response<ResponseCredentials> response) {
                    if(response.isSuccessful()){
                        Toast.makeText(ParentsLogin.this, "Inicio Sesión correctamente"+response.body(), Toast.LENGTH_SHORT).show();
                        goTo();
                    }else{
                        Toast.makeText(ParentsLogin.this, "Error en los datos", Toast.LENGTH_SHORT).show();
                    }

                }

                @Override
                public void onFailure(Call<ResponseCredentials> call, Throwable t) {
                    Toast.makeText(ParentsLogin.this, ""+t.getMessage(), Toast.LENGTH_SHORT).show();

                }
            });
        }
    }

    private void goTo(){
        try {
            Intent intent = new Intent(this, PrincipalParents.class);
            startActivities(new Intent[]{intent});
            finish();
        }catch (Exception e){
            System.err.println(e.getMessage());
        }
    }
    private void alertView(String mensaje){
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Login");
        builder.setMessage(mensaje);
        builder.setPositiveButton("ACEPTAR", null);
        builder.create();
        builder.show();

    }
    public boolean validEmail(String data){
        Pattern pattern =
                Pattern.compile("^[a-zA-Z0-9.!#$%&'*+/=?^_`{|}~\\-]+@[a-zA-Z0-9-]+(?:\\.[a-zA-Z0-9-]+)*$");
        Matcher mather = pattern.matcher(data);
        if (mather.find() == true) {
            System.out.println("El email ingresado es válido.");
            return true;
        } else {
            System.out.println("El email ingresado es inválido.");
        }
        return false;
    }
    public static boolean isNullOrEmpty(Object obj){
        if(obj==null)return true;
        if(obj instanceof String) return ((String)obj).trim().equals("") || ((String)obj).equalsIgnoreCase("NULL");
        if(obj instanceof Integer) return ((Integer)obj)==0;
        if(obj instanceof Long) return ((Long)obj).equals(new Long(0));
        if(obj instanceof Double) return ((Double)obj).equals(0.0);
        if(obj instanceof Collection) return (((Collection)obj).isEmpty());
        return false;
    }
    private void linkInit(){
        this.etParentEmail = findViewById(R.id.etParentEmail);
        this.etParentPassword = findViewById(R.id.etParentPassword);
    }
}