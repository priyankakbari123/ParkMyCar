package com.project.parkmycar;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.firebase.client.ChildEventListener;
import com.firebase.client.DataSnapshot;
import com.firebase.client.Firebase;
import com.firebase.client.FirebaseError;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import java.util.ArrayList;

public class MyBooking extends AppCompatActivity {
    Firebase MB_storedata;
    ListView storedata_Listview;
    String uid;
    ArrayList<String> BookingDetails=new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_booking);
        FirebaseUser currentFirebaseUser = FirebaseAuth.getInstance().getCurrentUser() ;
        uid=currentFirebaseUser.getUid();

        MB_storedata=new Firebase("https://parkmycar-f36c7.firebaseio.com/user/USERS"+uid+"/Booking/MyBooking_Viewdata");
        storedata_Listview=(ListView)findViewById(R.id.MB_List);
        final ArrayAdapter<String>arrayAdapter=new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1,BookingDetails);
        storedata_Listview.setAdapter(arrayAdapter);
        MB_storedata.addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(DataSnapshot dataSnapshot, String s) {
                String value=dataSnapshot.getValue(String.class);
                BookingDetails.add(value);
                arrayAdapter.notifyDataSetChanged();
            }

            @Override
            public void onChildChanged(DataSnapshot dataSnapshot, String s) {

            }

            @Override
            public void onChildRemoved(DataSnapshot dataSnapshot) {

            }

            @Override
            public void onChildMoved(DataSnapshot dataSnapshot, String s) {

            }

            @Override
            public void onCancelled(FirebaseError firebaseError) {

            }
        });

        //Bottom navigation
        //Initial and assign variable
        BottomNavigationView bottomNavigationView = findViewById(R.id.bottom_navigation);
        //set home selected
        bottomNavigationView.setSelectedItemId(R.id.my_booking);
        //perforom item selected listener
        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
                CharSequence title = menuItem.getTitle();
                if (title.equals("Home")) {
                    Intent intent = new Intent(MyBooking.this, HomeActivity.class);
                    startActivity(intent);
                    return true;
                } else if (title.equals("My Booking")) {
//                    finish();
                    return true;
                } else if (title.equals("Feedback")) {

                    Intent intent1 = new Intent(MyBooking.this, feedback.class);
                    startActivity(intent1);
                    return true;
                }
                else if (title.equals("About Us")) {

                    Intent intent1 = new Intent(MyBooking.this, AboutUs.class);
                    startActivity(intent1);
                    return true;
                }
                return false;
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
                Intent intToMain = new Intent(MyBooking.this, LoginActivity.class);
                startActivity(intToMain);
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }
    public void onBackPressed() {
        Intent backtoHome = new Intent(MyBooking.this, HomeActivity.class);
        startActivity(backtoHome);
    }
}
