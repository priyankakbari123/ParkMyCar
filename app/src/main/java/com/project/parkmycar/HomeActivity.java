package com.project.parkmycar;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.firebase.auth.FirebaseAuth;

public class HomeActivity extends AppCompatActivity implements AdapterView.OnItemSelectedListener {

    Button feedback;
    Button btnspinner;
    Spinner spinner;
    FirebaseAuth mFirebaseAuth;
    private FirebaseAuth.AuthStateListener mAuthStateListener;
    int currentitem = 0;
    //public static final String EXTRA_EMAILID = "com.example.application.example.EXTRA_EMAILID";



    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        //Intent intent1 = getIntent();
        //final String email = intent1.getStringExtra(LoginActivity.EXTRA_EMAILID);

        //Initial and assign variable
        BottomNavigationView bottomNavigationView = findViewById(R.id.bottom_navigation);

        //set home selected
        bottomNavigationView.setSelectedItemId(R.id.home);

        //perform item selected listener
        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
                CharSequence title = menuItem.getTitle();
                if (title.equals("Home")) {
                    Intent intent1 = new Intent(HomeActivity.this, HomeActivity.class);
                    startActivity(intent1);
                    return true;
                } else if (title.equals("My Booking")) {

                    Intent intent = new Intent(HomeActivity.this, MyBooking.class);
                    startActivity(intent);
//                    finish();
                    return true;
                }
                else if (title.equals("About Us")) {

                    Intent intent = new Intent(HomeActivity.this, AboutUs.class);
                    startActivity(intent);
//                    finish();
                    return true;
                }
                else if (title.equals("Feedback")) {

                    Intent intent1 = new Intent(HomeActivity.this, feedback.class);
                    //intent1.putExtra(EXTRA_EMAILID,email);
                    startActivity(intent1);
                    return true;
                }
                return false;
            }
        });


//Spinner code started
        spinner = findViewById(R.id.spinner);
        btnspinner = findViewById(R.id.button3);
        btnspinner.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String item = spinner.getSelectedItem().toString();
                if (item.equals("INORBIT MALL")) {
                    Intent intent = new Intent(HomeActivity.this, TimeSlotActivity.class);
                    startActivity(intent);
                } else {
                    Toast toast = Toast.makeText(getApplicationContext(), "Error ! Please Select Location ", Toast.LENGTH_SHORT);
                    toast.show();

                }
            }
        });
//Spinner code End

        //Here
    }
    //Logout Button On ActionBar
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
                new User(HomeActivity.this).removeUser();
                Intent intToMain = new Intent(HomeActivity.this, LoginActivity.class);
                startActivity(intToMain);
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }


    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        String text = parent.getItemAtPosition(position).toString();
        Toast.makeText(parent.getContext(), text, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }
    public void onBackPressed() { //using backpressed Exit the App
        finishAffinity();
    }
}

