package co.edu.ue.nanniapp;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import androidx.appcompat.app.AppCompatActivity;

import co.edu.ue.nanniapp.KidRegister;
import co.edu.ue.nanniapp.ParentsRegister;
import co.edu.ue.nanniapp.R;

public class NanniProfile extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_nanni_profile);

        Button btnBack = findViewById(R.id.btnBack);
        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(NanniProfile.this, KidRegister.class);
                startActivity(intent);
            }
        });

        Button btnContinue = findViewById(R.id.btnContinue);
        btnContinue.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(NanniProfile.this, ParentsRegister.class);
                startActivity(intent);
            }
        });
    }
}