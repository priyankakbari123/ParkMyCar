package com.project.parkmycar;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.firebase.client.DataSnapshot;
import com.firebase.client.Firebase;
import com.firebase.client.FirebaseError;
import com.firebase.client.ValueEventListener;

public class AdminPage extends AppCompatActivity {
    Button viewslot;
    TextView Today,TodayData,Tomorrow,TomorrowData;
    String todayData="",data,tomorrowData="";
    Firebase today;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_page);

        viewslot=findViewById(R.id.button7);
        Today=findViewById(R.id.textView12);
        TodayData=findViewById(R.id.todaydata);
        Tomorrow=findViewById(R.id.textView14);
        TomorrowData=findViewById(R.id.tomorrowdata);

        //Today
        {
            today = new Firebase("https://parkmycar-f36c7.firebaseio.com/ParkingSlots/Today/8_to_10AM/Lane1/Status");
            today.addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(DataSnapshot dataSnapshot) {
                    data = dataSnapshot.getValue(String.class);
                    if (data.equals("0")) {
                        todayData = todayData+ "8 to 10AM \n";
                    }
                }

                @Override
                public void onCancelled(FirebaseError firebaseError) {

                }
            });

            today = new Firebase("https://parkmycar-f36c7.firebaseio.com/ParkingSlots/Today/10_to_12AM/Lane1/Status");
            today.addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(DataSnapshot dataSnapshot) {
                    data = dataSnapshot.getValue(String.class);
                    if (data.equals("0")) {
                        todayData = todayData + "10 to 12AM \n";
                    }
                }

                @Override
                public void onCancelled(FirebaseError firebaseError) {

                }
            });

            today = new Firebase("https://parkmycar-f36c7.firebaseio.com/ParkingSlots/Today/12_to_2PM/Lane1/Status");
            today.addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(DataSnapshot dataSnapshot) {
                    data = dataSnapshot.getValue(String.class);
                    if (data.equals("0")) {
                        todayData = todayData + "12 to 2PM \n";
                    }
                }

                @Override
                public void onCancelled(FirebaseError firebaseError) {

                }
            });

            today = new Firebase("https://parkmycar-f36c7.firebaseio.com/ParkingSlots/Today/2_to_4PM/Lane1/Status");
            today.addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(DataSnapshot dataSnapshot) {
                    data = dataSnapshot.getValue(String.class);
                    if (data.equals("0")) {
                        todayData = todayData + "2 to 4PM \n";
                    }
                }

                @Override
                public void onCancelled(FirebaseError firebaseError) {

                }
            });

            today = new Firebase("https://parkmycar-f36c7.firebaseio.com/ParkingSlots/Today/4_to_6PM/Lane1/Status");
            today.addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(DataSnapshot dataSnapshot) {
                    data = dataSnapshot.getValue(String.class);
                    if (data.equals("0")) {
                        todayData = todayData + "4 to 6PM \n";
                    }
                }

                @Override
                public void onCancelled(FirebaseError firebaseError) {

                }
            });

            today = new Firebase("https://parkmycar-f36c7.firebaseio.com/ParkingSlots/Today/6_to_8PM/Lane1/Status");
            today.addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(DataSnapshot dataSnapshot) {
                    data = dataSnapshot.getValue(String.class);
                    if (data.equals("0")) {
                        todayData = todayData + "6 to 8PM \n";
                    }
                }

                @Override
                public void onCancelled(FirebaseError firebaseError) {

                }
            });

            today = new Firebase("https://parkmycar-f36c7.firebaseio.com/ParkingSlots/Today/8_to_10PM/Lane1/Status");
            today.addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(DataSnapshot dataSnapshot) {
                    data = dataSnapshot.getValue(String.class);
                    if (data.equals("0")) {
                        todayData = todayData + "8 to 10PM ";
                    }
                }

                @Override
                public void onCancelled(FirebaseError firebaseError) {

                }
            });
        }
        //Tomorrow
        {
            today = new Firebase("https://parkmycar-f36c7.firebaseio.com/ParkingSlots/Tomorrow/8_to_10AM/Lane1/Status");
            today.addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(DataSnapshot dataSnapshot) {
                    data = dataSnapshot.getValue(String.class);
                    if (data.equals("0")) {
                        tomorrowData = tomorrowData+ "8 to 10AM \n";
                    }
                }

                @Override
                public void onCancelled(FirebaseError firebaseError) {

                }
            });

            today = new Firebase("https://parkmycar-f36c7.firebaseio.com/ParkingSlots/Tomorrow/10_to_12AM/Lane1/Status");
            today.addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(DataSnapshot dataSnapshot) {
                    data = dataSnapshot.getValue(String.class);
                    if (data.equals("0")) {
                        tomorrowData = tomorrowData + "10 to 12AM \n";
                    }
                }

                @Override
                public void onCancelled(FirebaseError firebaseError) {

                }
            });

            today = new Firebase("https://parkmycar-f36c7.firebaseio.com/ParkingSlots/Tomorrow/12_to_2PM/Lane1/Status");
            today.addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(DataSnapshot dataSnapshot) {
                    data = dataSnapshot.getValue(String.class);
                    if (data.equals("0")) {
                        tomorrowData = tomorrowData + "12 to 2PM \n";
                    }
                }

                @Override
                public void onCancelled(FirebaseError firebaseError) {

                }
            });

            today = new Firebase("https://parkmycar-f36c7.firebaseio.com/ParkingSlots/Tomorrow/2_to_4PM/Lane1/Status");
            today.addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(DataSnapshot dataSnapshot) {
                    data = dataSnapshot.getValue(String.class);
                    if (data.equals("0")) {
                        tomorrowData = tomorrowData + "2 to 4PM \n";
                    }
                }

                @Override
                public void onCancelled(FirebaseError firebaseError) {

                }
            });

            today = new Firebase("https://parkmycar-f36c7.firebaseio.com/ParkingSlots/Tomorrow/4_to_6PM/Lane1/Status");
            today.addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(DataSnapshot dataSnapshot) {
                    data = dataSnapshot.getValue(String.class);
                    if (data.equals("0")) {
                        tomorrowData = tomorrowData + "4 to 6PM \n";
                    }
                }

                @Override
                public void onCancelled(FirebaseError firebaseError) {

                }
            });

            today = new Firebase("https://parkmycar-f36c7.firebaseio.com/ParkingSlots/Tomorrow/6_to_8PM/Lane1/Status");
            today.addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(DataSnapshot dataSnapshot) {
                    data = dataSnapshot.getValue(String.class);
                    if (data.equals("0")) {
                        tomorrowData = tomorrowData + "6 to 8PM \n";
                    }
                }

                @Override
                public void onCancelled(FirebaseError firebaseError) {

                }
            });

            today = new Firebase("https://parkmycar-f36c7.firebaseio.com/ParkingSlots/Tomorrow/8_to_10PM/Lane1/Status");
            today.addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(DataSnapshot dataSnapshot) {
                    data = dataSnapshot.getValue(String.class);
                    if (data.equals("0")) {
                        tomorrowData = tomorrowData + "8 to 10PM ";
                    }
                }

                @Override
                public void onCancelled(FirebaseError firebaseError) {

                }
            });
        }

        viewslot.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                TodayData.setText(todayData);
                TomorrowData.setText(tomorrowData);
                //Toast toast = Toast.makeText(getApplicationContext(),todayData, Toast.LENGTH_SHORT);
                //toast.show();
                //Intent viewslot = new Intent(AdminPage.this, ViewSlot.class);
                //viewslot.putExtra(EXTRA_TODAY_SLOT,todayData);
                //startActivity(viewslot);
            }
        });

    }
}
