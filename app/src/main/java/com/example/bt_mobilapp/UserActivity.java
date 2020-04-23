package com.example.bt_mobilapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Log;

import com.example.bt_mobilapp.recyclerview.UserAdapter;

import java.util.ArrayList;
import java.util.List;

public class UserActivity extends AppCompatActivity {

    private RecyclerView rvUserList;
    private UserAdapter userAdapter;
    private List<Users> mdata;
    private DatabaseAssistant dbAsistant;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user);
        
        initViews();
        initmdataUsers();
    }

    private void initmdataUsers() {

        dbAsistant = new DatabaseAssistant(this);

        ArrayList<Users> usersArrayList = new UsersDao().AllUsers(dbAsistant);
        for (Users u: usersArrayList){
            Log.e(u.getUsername(),u.getPassword());
        }
        userAdapter = new UserAdapter(UserActivity.this,usersArrayList);
        rvUserList.setAdapter(userAdapter);
    }


    private void initViews() {

        rvUserList =findViewById(R.id.rv_userlist);
        rvUserList.setLayoutManager(new LinearLayoutManager(this));
        rvUserList.setHasFixedSize(true);


    }
}
