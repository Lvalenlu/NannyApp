package co.edu.ue.nanniapp;

import static co.edu.ue.nanniapp.api.ValuesApiParents.BASE_URL;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import java.util.Collection;

import co.edu.ue.nanniapp.api.ServiceParents;
import co.edu.ue.nanniapp.model.RegisterParents;
import co.edu.ue.nanniapp.model.ResponseParents;
import co.edu.ue.nanniapp.remote.ClienteRetrofit;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class ParentsRegister extends AppCompatActivity {


    private EditText etNameParent;
    private EditText etDocumentParent;
    private EditText etAddressParent;
    private EditText etCellphoneParent;
    private EditText etDescriptionParents;
    private Button btnFather;
    private Button btnMother;
    private Button btnBack;
    private Button btnContinue;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_parents_register);
        begin();
        configureSpinners();
        btnContinue.setOnClickListener(this::createParent);
    }
     /**private void capData(){
        this.name = etNameParent.getText().toString();
        this.document = String.valueOf(Integer.parseInt(etDocumentParent.getText().toString()));
        this.email = etEmailParent.getText().toString();
        this.password = etPassword.getText().toString();

    }*/
    private void updateButtonStates(Button selectedButton) {
        btnFather.setSelected(selectedButton == btnFather);
        btnMother.setSelected(selectedButton == btnMother);
    }
    private void configureSpinners() {
        Spinner spinnerDocs = findViewById(R.id.spinnerDocs);
        ArrayAdapter<CharSequence> docsAdapter = ArrayAdapter.createFromResource(
                this,
                R.array.docs,
                R.layout.color_spinner
        );
        docsAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerDocs.setAdapter(docsAdapter);
    }
    private void createParent(View view) {
        RegisterParents parentRequest = new RegisterParents();
        parentRequest.setPers_name(etNameParent.getText().toString());
        parentRequest.setPers_numdoc(etDocumentParent.getText().toString());
        parentRequest.setPers_description(etDescriptionParents.getText().toString());
        parentRequest.setPhon_number(etCellphoneParent.getText().toString());
        parentRequest.setAddr_name(etAddressParent.getText().toString());
        Log.i("API_RESPONSE", parentRequest.toString());
        Retrofit retrofit = ClienteRetrofit.getClient(BASE_URL);
        ServiceParents serviceParents = retrofit.create(ServiceParents.class);
        Call<ResponseParents> call = serviceParents.createParent(parentRequest);
        call.enqueue(new Callback<ResponseParents>(){
            @Override
            public void onResponse(Call<ResponseParents> call, Response<ResponseParents> response) {
                if (response.isSuccessful()) {
                    ResponseParents body = response.body();
                    String mensaje = body.getMsg();
                    Log.d("API_RESPONSE", "Respuesta exitosa. Mensaje: " + mensaje + "   " + response.body().toString());
                    Toast.makeText(getApplicationContext(), "Usuario registrado con éxito", Toast.LENGTH_SHORT).show();
                    goTo();
                } else {
                    Toast.makeText(getApplicationContext(), "Error en el registro. Inténtalo de nuevo.", Toast.LENGTH_SHORT).show();
                    Log.e("API_ERROR", "Error en la respuesta: " + response.message());
                }

            }

            @Override
            public void onFailure(Call<ResponseParents> call, Throwable t) {
                Log.i("response", t.getMessage());
                alertView("Error en Servicio comuniquese con el desarrollo");
            }
        });

    }
    private void goTo() {
        try {
            Intent intent = new Intent(this, PrincipalParents.class);
            startActivity(intent);
            finish();
        } catch (Exception e) {
            Log.e("Error", "Excepción al iniciar ParentsRegister: " + e.getMessage());
        }
    }
    private void alertView(String mensaje){
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Registro Padre");
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
    }
    private void begin() {
        this.btnFather = findViewById(R.id.btnFather);
        this.btnMother = findViewById(R.id.btnMother);
        this.etNameParent = findViewById(R.id.etNameParent);
        this.etDocumentParent = findViewById(R.id.etDocumentParent);
        this.etAddressParent = findViewById(R.id.etAddressParent);
        this.etCellphoneParent = findViewById(R.id.etCellphoneParent);
        this.etDescriptionParents = findViewById(R.id.etDescriptionParents);
        this.btnContinue = findViewById(R.id.btnContinue);
        this.btnBack = findViewById(R.id.btnBack);
  /*
        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ParentsRegister.this, MainActivity.class);
                startActivity(intent);
            }
        });

        btnContinue.setOnClickListener(this::createParent);
        btnFather.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                updateButtonStates(btnFather);
            }
        });

        btnMother.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                updateButtonStates(btnMother);
            }
        });
*/
    }
}
