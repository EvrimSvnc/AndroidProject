package com.example.bt_mobilapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    private DatabaseAssistant dbAsistant;
    private Users user;
    private CardView userlist_card,sensor_card,sharedPref_card,logout_card,email_card;
    private TextView txtUserInfo;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        userlist_card = (CardView) findViewById(R.id.userList_card);
       sensor_card = (CardView) findViewById(R.id.Sensors_card);
       sharedPref_card = (CardView) findViewById(R.id.SharedPref_card);
       logout_card = (CardView) findViewById(R.id.Logout_card);
       email_card = (CardView) findViewById(R.id.EMailSend_card);
       txtUserInfo = (TextView) findViewById(R.id.txtuserInfo);

       dbAsistant = new DatabaseAssistant(this);
       user = (Users)getIntent().getSerializableExtra("userInfo");

       txtUserInfo.setText("Hello, " + user.getUsername());

        email_card.setOnClickListener(this);
        sensor_card.setOnClickListener(this);
        sharedPref_card.setOnClickListener(this);
        userlist_card.setOnClickListener(this);

        logout_card.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent_card = new Intent(MainActivity.this, Login.class);
                startActivity(intent_card);
            }
        });
        sharedPref_card.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent_rgCard = new Intent(MainActivity.this, SharedPrefActivity.class);
                intent_rgCard.putExtra("userInfo",user);
                startActivity(intent_rgCard);
            }
        });
     }

    @Override
    public void onClick(View v) {
        Intent i;
        switch (v.getId()){
            case R.id.EMailSend_card : i = new Intent(this,EmailActivity.class); startActivity(i); break;
            case R.id.Sensors_card : i = new Intent(this,SensorActivity.class); startActivity(i); break;
            case R.id.userList_card : i = new Intent(this,UserActivity.class); startActivity(i); break;
            case R.id.Logout_card : i = new Intent(this,Login.class); startActivity(i); break;
            default:break;
        }

    }
}
