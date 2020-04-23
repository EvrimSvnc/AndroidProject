package com.example.bt_mobilapp;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.google.android.material.snackbar.Snackbar;

import java.io.ByteArrayOutputStream;

public class Register extends AppCompatActivity {
    private EditText EMail,Username,Password;
    private Button btnAdd;
    private ImageView userImage;
    private DatabaseAssistant dbAssist;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        EMail = (EditText)findViewById(R.id.txtRegisterEMail);
        Username = (EditText)findViewById(R.id.txtRegisterUsername);
        Password = (EditText)findViewById(R.id.txtRegisterPassword);
        btnAdd = (Button)findViewById(R.id.btnRegister);
        userImage = (ImageView)findViewById(R.id.profile_image);

        dbAssist = new DatabaseAssistant(this);

        userImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                galeryOpen();
            }
        });


        btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String username = Username.getText().toString().trim();
                String email = EMail.getText().toString().trim();
                String password = Password.getText().toString().trim();
                String imageURL = userImage.toString();
                if(imageURL.equals("null")){
                    Snackbar.make(v,"Image is null",Snackbar.LENGTH_SHORT).show();
                    return;
                }
                if(TextUtils.isEmpty(username) ){
                    Snackbar.make(v,"Username is null",Snackbar.LENGTH_SHORT).show();
                    return;
                }
                if(TextUtils.isEmpty(email) ){
                    Snackbar.make(v,"EMail is null",Snackbar.LENGTH_SHORT).show();
                    return;
                }
                if(TextUtils.isEmpty(password) ){
                    Snackbar.make(v,"Password is null",Snackbar.LENGTH_SHORT).show();
                    return;
                }
                //resim yükletmeyi yapamadım :( - default resim ekleniyor şuan
                new UsersDao().UserAdd(dbAssist,username,email,password,"https://upload.wikimedia.org/wikipedia/commons/2/25/Army-personnel-icon.png");
                Users lastuser =  new UsersDao().LastUserInfo(dbAssist);
                Log.e(lastuser.geteMail(), lastuser.getUsername());
                Intent intent2 = new Intent(Register.this, MainActivity.class);
                intent2.putExtra("userInfo",lastuser);
                Toast.makeText(getApplicationContext(),"Successfully!", Toast.LENGTH_LONG).show();
                startActivity(intent2);
                finish();

            }
        });
    }

    private void galeryOpen() {
        Intent intent = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
        startActivityForResult(intent,5);
    }
    public void onActivityResult(int requestCode,int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 5 && requestCode == Activity.RESULT_OK) {
            Uri filepath = data.getData();
        }
    }


}
