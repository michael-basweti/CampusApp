package com.example.user.campusapp;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.support.v4.app.ActivityCompat;
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

public class PostActivity extends AppCompatActivity {


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
        setContentView(R.layout.activity_post);
        mSend=(Button)findViewById(R.id.send);
        mDescription=(EditText)findViewById(R.id.description);
        mProgress=new ProgressDialog(this);
        mDatabaseUsers= FirebaseDatabase.getInstance().getReference().child("Map_Addresses");
        mAuth= FirebaseAuth.getInstance();
        mShopping=(EditText)findViewById(R.id.shopping_self);



        mSend.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                send();
            }
        });

        textLat = (TextView) findViewById(R.id.textLat);
        textLong = (TextView) findViewById(R.id.textLong);

        LocationManager lm = (LocationManager) getSystemService(Context.LOCATION_SERVICE);
        LocationListener ll = new mylocationlistener();
        if (ActivityCompat.checkSelfPermission(this, android.Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, android.Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            // TODO: Consider calling
            //    ActivityCompat#requestPermissions
            // here to request the missing permissions, and then overriding
            //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
            //                                          int[] grantResults)
            // to handle the case where the user grants the permission. See the documentation
            // for ActivityCompat#requestPermissions for more details.
            return;
        }
        lm.requestLocationUpdates(LocationManager.GPS_PROVIDER, 10, 5, ll);
    }

    private void send() {


        final String description = mDescription.getText().toString().trim();
        final String shopping_centre = mShopping.getText().toString().trim();
        final String Latitude = textLat.getText().toString().trim();
        final String Longitude = textLong.getText().toString().trim();

        if (!TextUtils.isEmpty(description)&&!TextUtils.isEmpty(shopping_centre)&&!TextUtils.isEmpty(Latitude)
                &&!TextUtils.isEmpty(Longitude)) {
            mProgress.setMessage("Posting");
            mProgress.show();
            final DatabaseReference newPost = mDatabaseUsers.push();


            mDatabaseUsers.addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(DataSnapshot dataSnapshot) {

                    newPost.child("title").setValue("Place:"+shopping_centre);
                    newPost.child("desc").setValue("Description:"+description);
                    newPost.child("latitude").setValue(Latitude);
                    newPost.child("longitude").setValue(Longitude);



                }

                @Override
                public void onCancelled(DatabaseError databaseError) {

                }
            });


            mProgress.dismiss();

            Intent mainIntent = new Intent(PostActivity.this, PostActivity.class);
            mainIntent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
            startActivity(mainIntent);
            Toast.makeText(PostActivity.this,"your Post Has Been Posted",Toast.LENGTH_LONG).show();
        }

    }
    class mylocationlistener implements LocationListener{

        @Override
        public void onLocationChanged(Location location) {

            if(location!=null){
                double pLong = location.getLongitude();
                double pLat=location.getLatitude();

                textLat.setText(Double.toString(pLat));
                textLong.setText(Double.toString(pLong));

            }
        }

        @Override
        public void onStatusChanged(String s, int status, Bundle bundle) {

        }

        @Override
        public void onProviderEnabled(String provider) {

        }

        @Override
        public void onProviderDisabled(String provider) {

        }
    }

}

