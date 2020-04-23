package com.example.bt_mobilapp.recyclerview;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.bt_mobilapp.R;
import com.example.bt_mobilapp.UserActivity;
import com.example.bt_mobilapp.Users;

import java.util.List;

public class UserAdapter extends RecyclerView.Adapter<UserAdapter.userviewholder> {


    List<Users> userdata;

    public UserAdapter(UserActivity userActivity, List<Users> userdata) {
        this.userdata = userdata;
    }

    @NonNull
    @Override
    public userviewholder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_user,parent,false);

        return new userviewholder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull userviewholder holder, int position) {

        holder.username.setText(userdata.get(position).getUsername());
        holder.email.setText(userdata.get(position).geteMail());
        holder.password.setText(userdata.get(position).getPassword());
    }

    @Override
    public int getItemCount() {
        return userdata.size();
    }

    public class userviewholder extends RecyclerView.ViewHolder{

        ImageView imguser;
        TextView username,email,password;
        public userviewholder(@NonNull View itemView) {
            super(itemView);

            imguser = itemView.findViewById(R.id.userimg_ImageView);
            username = itemView.findViewById(R.id.txt_user_username_rc);
            password = itemView.findViewById(R.id.txt_user_password_rc);
            email = itemView.findViewById(R.id.txt_user_email_rc);
        }
    }
}
