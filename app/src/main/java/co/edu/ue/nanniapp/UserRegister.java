package co.edu.ue.nanniapp;

import static co.edu.ue.nanniapp.api.ValuesApiParents.BASE_URL;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import co.edu.ue.nanniapp.api.ServiceUser;
import co.edu.ue.nanniapp.model.UserRegistrationRequest;
import co.edu.ue.nanniapp.remote.ClienteRetrofit;
import retrofit2.Call;
import retrofit2.Retrofit;

public class UserRegister extends AppCompatActivity {
    private EditText etNameUser;
    private EditText etEmailUser;
    private EditText etPasswordUser;
    private EditText etPasswordUser2;
    private Button btnContinue;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_parents_main_register);
        begin();
        //btnContinue.setOnClickListener(this::createUser);
    }
    private void createUser(View view){
        UserRegistrationRequest RegisterUser = new UserRegistrationRequest();
        RegisterUser.setName(etNameUser.getText().toString());
        RegisterUser.setEmail(etEmailUser.getText().toString());
        RegisterUser.setPassword(etPasswordUser.getText().toString());
        RegisterUser.setPassword_confirmation(etPasswordUser2.getText().toString());
        Retrofit retrofit = ClienteRetrofit.getClient(BASE_URL);
        ServiceUser ServiceUser = retrofit.create(ServiceUser.class);

    }
    private void begin(){
        this.etNameUser = findViewById(R.id.etNameUser);
        this.etEmailUser = findViewById(R.id.etEmail);
        this.etPasswordUser = findViewById(R.id.etPass);
        this.etPasswordUser2 = findViewById(R.id.etPassConfirm);
        this.btnContinue = findViewById(R.id.btnContinue);
    }
}

