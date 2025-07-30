package co.edu.ue.nanniapp;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import androidx.appcompat.app.AppCompatActivity;

public class KidRegister extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_kid_register);

        configureSpinners();

        Button btnBack = findViewById(R.id.btnBack);
        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(KidRegister.this, ParentsRegister.class);
                startActivity(intent);
            }
        });

        Button btnContinue = findViewById(R.id.btnContinue);
        btnContinue.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(KidRegister.this, NanniProfile.class);
                startActivity(intent);
            }
        });
    }
    private void configureSpinners() {
        // Spinner para alergias
        Spinner spinnerAllergies = findViewById(R.id.spinnerAllergies);
        ArrayAdapter<CharSequence> allergiesAdapter = ArrayAdapter.createFromResource(
                this,
                R.array.allergies,
                R.layout.color_spinner
        );
        allergiesAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerAllergies.setAdapter(allergiesAdapter);

        // Spinner para condiciones médicas
        Spinner spinnerMedicalConditions = findViewById(R.id.spinnerMedicalConditions);
        ArrayAdapter<CharSequence> medicalConditionsAdapter = ArrayAdapter.createFromResource(
                this,
                R.array.medical_conditions,
                R.layout.color_spinner
        );
        medicalConditionsAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerMedicalConditions.setAdapter(medicalConditionsAdapter);

        // Spinner para necesidades especiales
        Spinner spinnerSpecialNeeds = findViewById(R.id.spinnerSpecialNeeds);
        ArrayAdapter<CharSequence> specialNeedsAdapter = ArrayAdapter.createFromResource(
                this,
                R.array.special_needs,
                R.layout.color_spinner
        );
        specialNeedsAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerSpecialNeeds.setAdapter(specialNeedsAdapter);
    }
}


