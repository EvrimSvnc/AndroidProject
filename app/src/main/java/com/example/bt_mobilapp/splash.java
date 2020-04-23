package com.example.bt_mobilapp;
import android.app.Activity;
import android.os.Bundle;
import android.content.Intent;


public class splash extends Activity {
    @Override
    protected void onCreate( Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.splash);

        Thread threadTime = new Thread(){
            @Override
            public void run() {
                try {
                    sleep(3000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }finally {
                    Intent intent = new Intent(splash.this, Login.class);
                    startActivity(intent);
                }
            }
        };
        threadTime.start();
    }
}
