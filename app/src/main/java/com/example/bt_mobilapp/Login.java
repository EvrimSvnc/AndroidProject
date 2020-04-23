package com.example.bt_mobilapp;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class Login extends AppCompatActivity {
    private Button Login,Register;
    private EditText EMail;
    private EditText Password;
    private DatabaseAssistant dbAsistant;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        EMail = (EditText) findViewById(R.id.txtEMail);
        Password = (EditText) findViewById(R.id.txtPassword);
        Login = (Button) findViewById(R.id.btnLogin);
        Register = (Button) findViewById(R.id.btnRegister);


        Register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent2 = new Intent(Login.this, Register.class);
                startActivity(intent2);
            }
        });

        dbAsistant = new DatabaseAssistant(this);
        Login.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v){
                ArrayList<Users> arrayuserlist = new UsersDao().AllUsers(dbAsistant);
                for (Users user: arrayuserlist){
                    if(EMail.getText().toString().equals(user.geteMail()) && Password.getText().toString().equals(user.getPassword())) {
                        Intent intent = new Intent(Login.this, MainActivity.class);
                        intent.putExtra("userInfo",user);
                        Toast.makeText(getApplicationContext(),"Successfully!", Toast.LENGTH_SHORT).show();
                        startActivity(intent);
                        break;
                    }
                    else{
                        Toast.makeText(getApplicationContext(),"Kisi bilgisi kayıtlı değil!", Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });
    }
}
