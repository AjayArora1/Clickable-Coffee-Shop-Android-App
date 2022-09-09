package com.example.clickablecoffeeshopandroidedition;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.res.Configuration;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class ReportBugActivity extends AppCompatActivity {

    //Declarations.
    private EditText txtTo;
    private EditText txtSubject;
    private EditText txtMessage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_report_bug);

        //Instantiating the Controls.
        txtTo = findViewById(R.id.txtTo);
        txtSubject = findViewById(R.id.txtSubject);
        txtMessage = findViewById(R.id.txtMessage);
        Button btnSend = findViewById(R.id.btnSend);

        //onClickListener for the Send button.
        btnSend.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sendBugReport(); //Calls the method below.
            }
        });
    }

    private void sendBugReport() {
        String recipient = txtTo.getText().toString();
        //Can send to more than one recipient, separated by commas.
        String[] recipients = recipient.split(",");

        //Other declarations for sending the email; Recipient, Subject, and Message.
        String subject = txtSubject.getText().toString();
        String message = txtMessage.getText().toString();
        Intent intent = new Intent(Intent.ACTION_SEND);

        //The content of the text boxes will already be loaded into the mail client when the user
        //opens it.
        intent.putExtra(Intent.EXTRA_EMAIL, recipients);
        intent.putExtra(Intent.EXTRA_SUBJECT, subject);
        intent.putExtra(Intent.EXTRA_TEXT, message);

        //Allows the user to select an e-mail client to use when sending the bug report.
        intent.setType("message/rfc822");
        startActivity(Intent.createChooser(intent,"Choose an email client to send this with:"));
    }
}
