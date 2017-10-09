package com.example.user.campusapp;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

public class Help extends AppCompatActivity {
    private TextView mText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_help);

        mText=(TextView) findViewById(R.id.textView);
        mText.setText("1.To use application please make sure you are a registered user."+"\n\n2.Choose the marker where you want to go " +
                "\n\n3.Make sure you are connected to the internet every time you want to use the app.\n\n5.Make sure your GPRS is always on.\n\n" +
                "" +
                "");
    }
}
