package com.project.parkmycar;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.firebase.client.Firebase;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class feedback extends AppCompatActivity {

    EditText namedata, emaildata, messagedata;
    Button submit;
    Firebase firebase;
    //public String emailid;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_feedback);
        //Intent intent1 = getIntent();
        //final String email = intent1.getStringExtra(HomeActivity.EXTRA_EMAILID);

        //bottom navigation code started

        //Initial and assign variable
        BottomNavigationView bottomNavigationView = findViewById(R.id.bottom_navigation);
        //set home selected
        bottomNavigationView.setSelectedItemId(R.id.feedback);
        //perforom item selected listener
        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {

                CharSequence title = menuItem.getTitle();
                if (title.equals("Home")) {
                    Intent intent = new Intent(feedback.this, HomeActivity.class);
                    startActivity(intent);
                    return true;
                } else if (title.equals("My Booking")) {

                    Intent intent = new Intent(feedback.this, MyBooking.class);
                    startActivity(intent);
                    //finish();
                    return true;

                } else if (title.equals("About Us")) {

                    Intent intent = new Intent(feedback.this, AboutUs.class);
                    startActivity(intent);
                    //finish();
                    return true;
                } else if (title.equals("Feedback")) {

                    Intent intent1 = new Intent(feedback.this, feedback.class);
                    startActivity(intent1);
                    return true;
                }
                return false;
            }
        });
        //Bottom Navigation Code Ended

        namedata = findViewById(R.id.namedata);
        emaildata = findViewById(R.id.emaildata);
        messagedata = findViewById(R.id.messagedata);
        submit = findViewById(R.id.btn_submit);
        String uid;
        FirebaseUser currentFirebaseUser = FirebaseAuth.getInstance().getCurrentUser() ;
        uid=currentFirebaseUser.getUid();
        //
        //String Uniqe_ID = Settings.Secure.getString(getApplicationContext().getContentResolver(), Settings.Secure.ANDROID_ID);
        firebase = new Firebase("https://parkmycar-f36c7.firebaseio.com/user/USERS" + uid);

        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final String name = namedata.getText().toString();
                final String email = emaildata.getText().toString();
                final String message = messagedata.getText().toString();

                Firebase child_name = firebase.child("Name");
                child_name.setValue(name);
                if (name.isEmpty()) {
                    namedata.setError("This is Required Field !!");
                    submit.setEnabled(false);
                } else {
                    namedata.setError(null);
                    submit.setEnabled(true);
                }

                Firebase child_email = firebase.child("Email");
                child_email.setValue(email);
                if (email.isEmpty()) {
                    emaildata.setError("This is Required Field !!");
                    submit.setEnabled(false);
                } else {
                    emaildata.setError(null);
                    submit.setEnabled(true);
                }

                Firebase child_message = firebase.child("Feedback");
                child_message.setValue(message);
                if (message.isEmpty()) {
                    messagedata.setError("This is Requred Fiels !!");
                    submit.setEnabled(false);
                } else {
                    messagedata.setError(null);
                    submit.setEnabled(true);
                }
                Toast.makeText(feedback.this, "Thank You For Submitting Feedback", Toast.LENGTH_SHORT).show();

            }

        });
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.logout_menu, menu);
        return true;
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.logout:
                FirebaseAuth.getInstance().signOut();
                Intent intToMain = new Intent(feedback.this, LoginActivity.class);
                startActivity(intToMain);
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }
    public void onBackPressed() {
        Intent backtoHome = new Intent(feedback.this, HomeActivity.class);
        startActivity(backtoHome);
    }
}
