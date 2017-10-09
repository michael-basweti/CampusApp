package com.example.user.campusapp;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class Feedback extends AppCompatActivity {
    private EditText mDescription;
    private Button mSend;
    private ProgressDialog mProgress;
    private DatabaseReference mDatabaseUsers;
    private FirebaseAuth mAuth;
    private EditText mShopping;
    TextView textLat;
    TextView textLong;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_feedback);
        mSend=(Button)findViewById(R.id.send1);
        mDescription=(EditText)findViewById(R.id.description);
        mProgress=new ProgressDialog(this);
        mDatabaseUsers= FirebaseDatabase.getInstance().getReference().child("Feedback");
        mAuth= FirebaseAuth.getInstance();




        mSend.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                send();
            }
        });


    }

    private void send() {


        final String description = mDescription.getText().toString().trim();



        if (!TextUtils.isEmpty(description)) {
            mProgress.setMessage("Posting");
            mProgress.show();
            final DatabaseReference newPost = mDatabaseUsers.push();


            mDatabaseUsers.addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(DataSnapshot dataSnapshot) {

                    newPost.child("desc").setValue("Message:"+description);




                }

                @Override
                public void onCancelled(DatabaseError databaseError) {

                }
            });


            mProgress.dismiss();

            Intent mainIntent = new Intent(Feedback.this, home.class);
            mainIntent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
            startActivity(mainIntent);
            Toast.makeText(Feedback.this,"your Post Has Been Posted",Toast.LENGTH_LONG).show();
        }

    }


}

