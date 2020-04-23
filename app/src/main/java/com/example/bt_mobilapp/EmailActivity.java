package com.example.bt_mobilapp;


import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class EmailActivity extends AppCompatActivity {

    private static final int PICK_FROM_GALLERY = 101;
    private EditText EditMailTo, EditCCTo;
    private EditText EditMailSubject;
    private EditText EditMailMessage;
    private  Button buttonSend;
    private Button attachment;
    private  EditText twAttachment;
    Uri URI = null;
    String emailTo;
    String ccTo;
    String subject;
    String message;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_email);

        EditMailTo = findViewById(R.id.edtMailTo);
        EditMailSubject = findViewById(R.id.edtMailSubject);
        EditMailMessage = findViewById(R.id.edtMailMessage);
        EditCCTo = findViewById(R.id.edtCCTo);
        attachment = findViewById(R.id.btAttachment);
        twAttachment = (EditText) findViewById(R.id.twAttachment);

        buttonSend = findViewById(R.id.btnSend);
        buttonSend.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                SendMail();
            }
        });
        attachment.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openFolder();
            }
        });
    }

    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == PICK_FROM_GALLERY && resultCode == RESULT_OK) {
            URI = data.getData();
            twAttachment.setText(URI.getLastPathSegment());
            twAttachment.setVisibility(View.VISIBLE);
        }
    }

    private void SendMail() {
        try {
            emailTo = EditMailTo.getText().toString();
            ccTo = EditCCTo.getText().toString();
            subject = EditMailSubject.getText().toString();
            message = EditMailMessage.getText().toString();
            final Intent emailIntent = new Intent(android.content.Intent.ACTION_SEND);
            emailIntent.setType("plain/text");
            emailIntent.putExtra(Intent.EXTRA_EMAIL, new String[]{emailTo});
            emailIntent.putExtra(Intent.EXTRA_CC, new String[]{ccTo});
            emailIntent.putExtra(Intent.EXTRA_SUBJECT, subject);
            if (URI != null) {
                emailIntent.putExtra(Intent.EXTRA_STREAM, URI);
            }
            emailIntent.putExtra(Intent.EXTRA_TEXT, message);
            this.startActivity(Intent.createChooser(emailIntent, "Sending email..."));
        } catch (Throwable t) {
            Toast.makeText(this, "Try again: "+ t.toString(), Toast.LENGTH_LONG).show();
        }
    }
    public void openFolder()
    {
        Intent intent = new Intent();
        intent.setType("image/*");
        intent.setAction(Intent.ACTION_GET_CONTENT);
        intent.putExtra("return-data", true);
        startActivityForResult(Intent.createChooser(intent, "Complete action using"), PICK_FROM_GALLERY);
    }

}
