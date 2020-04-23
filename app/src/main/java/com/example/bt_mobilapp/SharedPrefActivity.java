package com.example.bt_mobilapp;


import androidx.appcompat.app.AppCompatActivity;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

public class SharedPrefActivity extends AppCompatActivity {
    EditText txtusername,txtemail,txtage,txtweight,txtheight;
    Switch appMode;
    CheckBox chkmale;
    CheckBox chkfemale;
    Button saveButton;
    Button showButton;
    Switch switchApp;

    String username,email,age,weight,height;
    Boolean male;
    Boolean female;
    Boolean swhApp;
    private DatabaseAssistant dbAsistant;
    private Users user;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shared_pref);

        dbAsistant = new DatabaseAssistant(this);
        user = (Users)getIntent().getSerializableExtra("userInfo");

        txtusername = findViewById(R.id.username);
        txtemail = findViewById(R.id.email);
        txtage =findViewById(R.id.age);
        txtweight = findViewById(R.id.weight);
        txtheight = findViewById(R.id.height);
        saveButton = findViewById(R.id.btnSave);
        showButton = findViewById(R.id.btnShow);
        chkmale = findViewById(R.id.chkMale);
        chkfemale = findViewById(R.id.chkFemale);
        appMode = findViewById(R.id.appmode);
        switchApp = findViewById(R.id.appmode);

        txtusername.setText(user.getUsername());
        txtemail.setText(user.geteMail());

        saveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                SharedPreferences sharedPref = getSharedPreferences("Enter",MODE_PRIVATE);
                SharedPreferences.Editor editor = sharedPref.edit();

                username = txtusername.getText().toString();
                age = txtage.getText().toString();
                email = txtemail.getText().toString();
                weight = txtweight.getText().toString();
                height = txtheight.getText().toString();
                male = chkmale.isChecked();
                female = chkfemale.isChecked();
                swhApp = switchApp.isChecked();

                editor.putString("UserName",username);
                editor.putString("EMail",email);
                editor.putString("Age",age);
                editor.putString("Weight",weight);
                editor.putString("Height", height);
                editor.putBoolean("Male",male);
                editor.putBoolean("FeMale",female);
                editor.putBoolean("SwitchApp",swhApp);

                editor.commit();

                txtusername.getText().clear();
                txtemail.getText().clear();
                txtage.getText().clear();
                txtheight.getText().clear();
                txtweight.getText().clear();
                chkfemale.setChecked(false);
                chkmale.setChecked(false);
                switchApp.setChecked(false);

                Toast.makeText(getApplicationContext(),"Kayit Yapilmistir!!",Toast.LENGTH_LONG).show();
            }
        });

        showButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SharedPreferences sharedVeriOku = getSharedPreferences("Enter",MODE_PRIVATE);
                txtusername.setText(sharedVeriOku.getString("UserName",""));
                txtemail.setText(sharedVeriOku.getString("EMail",""));
                txtage.setText(sharedVeriOku.getString("Age",""));
                txtweight.setText(sharedVeriOku.getString("Weight",""));
                txtheight.setText(sharedVeriOku.getString("Height",""));
                chkmale.setChecked(sharedVeriOku.getBoolean("Male",false));
                chkfemale.setChecked(sharedVeriOku.getBoolean("FeMale",false));
                switchApp.setChecked(sharedVeriOku.getBoolean("SwitchApp",false));
            }
        });
    }

}
