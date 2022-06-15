package com.project.parkmycar;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.firebase.client.DataSnapshot;
import com.firebase.client.Firebase;
import com.firebase.client.FirebaseError;
import com.firebase.client.ValueEventListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import static android.widget.Toast.LENGTH_SHORT;
import static com.project.parkmycar.TimeSlotActivity.EXTRA_DATE;
import static com.project.parkmycar.TimeSlotActivity.EXTRA_DAY;
import static com.project.parkmycar.TimeSlotActivity.EXTRA_LANNO;
import static com.project.parkmycar.TimeSlotActivity.EXTRA_TIMESLOT;

public class Details extends AppCompatActivity {
    //int lanNo=laneno;
    Firebase DetailRef,StoreDetails,MB_Viewdata,BK_id,sBK_id;
    EditText uname, uemail, umobileno,uvehicleno;
    Button confirmBooking;
    String Info,EmailInfo;
    int Booking_id;
    public static final String EXTRA_MB_DATE = "com.example.application.example.EXTRA_MB_DATE";
    public static final String EXTRA_MB_TIMESLOT = "com.example.application.example.EXTRA_MB_TIMESLOT";
    public static final String EXTRA_MB_VEHICLENO = "com.example.application.example.EXTRA_MB_VEHICLENO";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details);
        final Intent intent = getIntent();
        final int lanNo = intent.getIntExtra(EXTRA_LANNO,0);
        final String sDate = intent.getStringExtra(EXTRA_DATE);
        final String timeslot=intent.getStringExtra((EXTRA_TIMESLOT));
        final String day=intent.getStringExtra((EXTRA_DAY));


        uname = findViewById(R.id.editText3);
        uemail = findViewById(R.id.editText6);
        umobileno = findViewById(R.id.editText5);
        uvehicleno = findViewById(R.id.editText4);
        confirmBooking=findViewById(R.id.button6);

        DetailRef=new Firebase("https://parkmycar-f36c7.firebaseio.com/ParkingSlots/"+day+"/"+timeslot+"/Lane"+lanNo);
        confirmBooking=findViewById(R.id.button6);

        final String uid,Location="INORBIT MALL";
        FirebaseUser currentFirebaseUser = FirebaseAuth.getInstance().getCurrentUser() ;
        uid=currentFirebaseUser.getUid();
        StoreDetails = new Firebase("https://parkmycar-f36c7.firebaseio.com/user/USERS"+uid+"/Booking");
        MB_Viewdata=new Firebase("https://parkmycar-f36c7.firebaseio.com/user/USERS"+uid+"/Booking/MyBooking_Viewdata");
        BK_id=new Firebase("https://parkmycar-f36c7.firebaseio.com/Booking_ID");
        sBK_id=new Firebase("https://parkmycar-f36c7.firebaseio.com");
        BK_id.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                Booking_id= dataSnapshot.getValue(Integer.class);
                //Status = Integer.parseInt(s);
            }

            @Override
            public void onCancelled(FirebaseError firebaseError) {
            }
        });

        confirmBooking.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final String name=uname.getText().toString();
                final String email=uemail.getText().toString();
                final String mobileno=umobileno.getText().toString();
                final String vehicleno=uvehicleno.getText().toString();


                //Get Booking_ID
                //BK_id = new Firebase("https://parkmycar-f36c7.firebaseio.com/ParkingSlots/Today/8_to_10AM/Lane" + i + "/Status");


                //DetailRef = new Firebase("parkmycar-f36c7.firebaseio.com/ParkingSlots/Tomorrow/"+timeslot+"/Lane"+lanNo);

                if (email.isEmpty()) {
                    uemail.setError("This Field is Required,Email");
                    confirmBooking.setEnabled(false);
                } else {
                    uemail.setError(null);
                    confirmBooking.setEnabled(true);
                }
                if(mobileno.isEmpty()){
                    umobileno.setError("This Field is Required,MobileNo");
                    confirmBooking.setEnabled(false);
                }else {
                    umobileno.setError(null);
                    confirmBooking.setEnabled(true);
                }
                if (vehicleno.isEmpty()) {
                    uvehicleno.setError("This Field is Required,VehicleNo");
                    confirmBooking.setEnabled(false);
                } else {
                    uvehicleno.setError(null);
                    confirmBooking.setEnabled(true);
                }
                if (name.isEmpty()) {
                    uname.setError("This Field is Required,Name");
                    confirmBooking.setEnabled(false);
                } else {
                    uname.setError(null);
                    confirmBooking.setEnabled(true);
                }

               // NotificationCompat.Builder builder=new NotificationCompat.Builder(
               //         Details.this
                //)
                //        .setSmallIcon(R.drawable.ic_booked)
                //        .setContentTitle("Parking Booked Successfull")
                //        .setContentText(Info)
                //        .setAutoCancel(true);


                if(email.isEmpty()|| vehicleno.isEmpty()|| mobileno.isEmpty()){
                    Toast toast2 = Toast.makeText(getApplicationContext(), "Error ! Please Fill All Fields", Toast.LENGTH_SHORT);
                    toast2.show();
                }
                else {
                    //Booking Details Store in Firebase
                    Firebase DateRef=DetailRef.child("Date");
                    DateRef.setValue(sDate);
                    Firebase EmailRef=DetailRef.child("Email");
                    EmailRef.setValue(email);
                    Firebase MobileNoRef=DetailRef.child("MobileNo");
                    MobileNoRef.setValue(mobileno);
                    Firebase NameRef=DetailRef.child("Name");
                    NameRef.setValue(name);
                    Firebase StatusRef=DetailRef.child("Status");
                    StatusRef.setValue(1);
                    Firebase VehicleNoRef=DetailRef.child("VehicleNo");
                    VehicleNoRef.setValue(vehicleno);

                    //Booking Details For MyBooking
                    Booking_id=Booking_id+1;
                    Info="Date_of_booking: "+sDate+"\nTimeslots: "+timeslot+"\nLocation: "+Location+"\nVehicleNo: "+vehicleno+"\nBookingID: "+Booking_id;

                    Firebase child_Date = StoreDetails.child("Date_of_Booking");
                    child_Date.setValue(sDate);
                    Firebase child_Timeslot = StoreDetails.child("Time_Slot");
                    child_Timeslot.setValue(timeslot);
                    Firebase child_VehicleNo = StoreDetails.child("VehicleNo");
                    child_VehicleNo.setValue(vehicleno);
                    Firebase child_Location = StoreDetails.child("Location");
                    child_Location.setValue(Location);
                    Firebase child_Info = MB_Viewdata.child("Info"+Booking_id);
                    child_Info.setValue(Info);
                    Firebase child_Booking_id=sBK_id.child("Booking_ID");
                    child_Booking_id.setValue(Booking_id);

                    //Mail Sent
                    EmailInfo="Thank You "+name+" for book parking on ParkMyCar App.\n\nBooking Details:\n"+Info+"\n\nPaying in cash, when come to destination.\n"+
                            "Your feedback is important to us, so we can improve our application."+"\n\nThank you, \nTeam Parkmycar "+"\n\nTerms and conditions:\n" +
                            "1. Make sure that you can come in time at destination of parking.\n" +
                            "2. Cancellation of booking is not available.\n" +
                            "3. Make sure that you left the slot of parking before your timing limit.";
                    new Thread(new Runnable() {
                        @Override
                        public void run() {
                            try {
                                GMailSender sender = new GMailSender("parkmycar.parking@gmail.com", "ParkMyCar@123");
                                sender.sendMail("Parking Booking Details", EmailInfo, email, email);
                            } catch (Exception e) {
                                Log.e("SendMail", e.getMessage(), e);
                            }
                        }
                    }).start();
                    //Mail code Ended

                    Toast toast = Toast.makeText(getApplicationContext(), "Parking Booking Successful, Booking Details will sent via Email ", LENGTH_SHORT);
                    toast.show();
                    Intent intentMB = new Intent(Details.this, MyBooking.class);
                    intentMB.putExtra(EXTRA_MB_DATE, sDate);
                    intentMB.putExtra(EXTRA_MB_TIMESLOT, timeslot);
                    intentMB.putExtra(EXTRA_MB_VEHICLENO, vehicleno);
                    //PendingIntent pendingIntent=PendingIntent.getActivities(Details.this,0, new Intent[]{intent},PendingIntent.FLAG_UPDATE_CURRENT);
                    //builder.setContentIntent(pendingIntent);
                    //NotificationManager notificationManager=(NotificationManager)getSystemService(NOTIFICATION_SERVICE);
                    startActivity(intentMB);
                }
            }
        });


    }
}
