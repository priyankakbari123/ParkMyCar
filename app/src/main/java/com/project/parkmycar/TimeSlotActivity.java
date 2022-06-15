package com.project.parkmycar;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.DialogFragment;

import com.firebase.client.DataSnapshot;
import com.firebase.client.Firebase;
import com.firebase.client.FirebaseError;
import com.firebase.client.ValueEventListener;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

public class TimeSlotActivity extends AppCompatActivity implements DatePickerDialog.OnDateSetListener {
    public Date sdate = new Date(), mdate = new Date(), cdate = new Date();
    public Spinner spinner1;
    public Firebase slotRef, autoclear;
    //public int Today[];
    //public  int Tomorrow[];
    public int i, Empty = 0, laneno=0,IntStatus;
    public String s = "", timeslot = "", Status = "", Day = "";
    public static final String EXTRA_LANNO = "com.example.application.example.EXTRA_LANNO";
    public static final String EXTRA_DATE = "com.example.application.example.EXTRA_DATE";
    public static final String EXTRA_TIMESLOT = "com.example.application.example.EXTRA_TIMESLOT";
    public static final String EXTRA_DAY = "com.example.application.example.EXTRA_DAY";
    String temp = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_time_slot);

//Calender Code Started (UP Spinner code was there)
        Button button = findViewById(R.id.button5);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DialogFragment datePicker = new DatePickerFragment();
                datePicker.show(getSupportFragmentManager(), "date picker");
            }

        });

    }

    @Override
    public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
        Calendar c = Calendar.getInstance();//selected date
        Calendar cal = Calendar.getInstance();//current date for MaxDate
        Calendar current = Calendar.getInstance();//Current Date
        c.set(Calendar.YEAR, year);
        c.set(Calendar.MONTH, month);
        c.set(Calendar.DAY_OF_MONTH, dayOfMonth);
        //Date sdate = new Date(),mdate = new Date(), cdate=new Date();
        SimpleDateFormat df = new SimpleDateFormat("dd/MM/yy");
        final String selectedDate = df.format(c.getTime());
        String currentDateString = df.format(cal.getTime());
        String cdateString = df.format(current.getTime());   //Current Date in String
        //String selectedDateString=selectedDate;//For TextView
        spinner1 = findViewById(R.id.spinner1);//Spinner Declaration
        Button btnspinner = findViewById(R.id.button4);//Button For Spinner

        try {
            c.setTime(df.parse(currentDateString));
        } catch (ParseException e) {
            e.printStackTrace();
        }
        cal.add(Calendar.DATE, 1);

        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yy");
        Date resultdate = new Date(cal.getTimeInMillis());
        String maxDate = sdf.format(resultdate);        //Current Date+3 in String

        try {
            sdate = sdf.parse(selectedDate);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        try {
            mdate = sdf.parse(maxDate);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        try {
            cdate = sdf.parse(cdateString);
        } catch (ParseException e) {
            e.printStackTrace();
        }


        if (sdate.before(cdate)) {    //Error If Selected Date is Before Current Date
            Toast toast = Toast.makeText(getApplicationContext(), "Error ! Please Select Date Today or Tomorrow", Toast.LENGTH_SHORT);
            toast.show();
        } else if (sdate.after(mdate)) {        //Error If Selected Date is After Max Date(Currentdate+3)
            Toast toast = Toast.makeText(getApplicationContext(), "Error ! Please Select Date Today or Tomorrow", Toast.LENGTH_SHORT);
            toast.show();

        } else {                        //Allow to Park
            Toast toast = Toast.makeText(getApplicationContext(), "You Can Book Parking", Toast.LENGTH_SHORT);
            toast.show();
            //Date currentTime =current.getInstance().getTime();
            SimpleDateFormat tdf = new SimpleDateFormat("HH", Locale.getDefault());
            String ctimeS = tdf.format(new Date());   //CurrentTime in Hour
            final int ctime = Integer.parseInt(ctimeS);

            //Automatic TimeSlot Empty (autoclear)
            //autoclear=new Firebase("https://parkmycar-f36c7.firebaseio.com/ParkingSlots/Today/10_to_12AM/Lane1");
            if (ctime >= 10) {
                autoclear = new Firebase("https://parkmycar-f36c7.firebaseio.com/ParkingSlots/Today/8_to_10AM/Lane1");
                Firebase child_8_10 = autoclear.child("Status");
                child_8_10.setValue("0");
            }
            if (ctime >= 12) {
                autoclear = new Firebase("https://parkmycar-f36c7.firebaseio.com/ParkingSlots/Today/10_to_12AM/Lane1");
                Firebase child_10_12 = autoclear.child("Status");
                child_10_12.setValue("0");
            }
            if (ctime >= 14) {
                autoclear = new Firebase("https://parkmycar-f36c7.firebaseio.com/ParkingSlots/Today/12_to_2PM/Lane1");
                Firebase child_12_2 = autoclear.child("Status");
                child_12_2.setValue("0");
            }
            if (ctime >= 16) {
                autoclear = new Firebase("https://parkmycar-f36c7.firebaseio.com/ParkingSlots/Today/2_to_4PM/Lane1");
                Firebase child_2_4 = autoclear.child("Status");
                child_2_4.setValue("0");
            }
            if (ctime >= 18) {
                autoclear = new Firebase("https://parkmycar-f36c7.firebaseio.com/ParkingSlots/Today/4_to_6PM/Lane1");
                Firebase child_4_6 = autoclear.child("Status");
                child_4_6.setValue("0");
            }
            if (ctime >= 20) {
                autoclear = new Firebase("https://parkmycar-f36c7.firebaseio.com/ParkingSlots/Today/6_to_8PM/Lane1");
                Firebase child_6_8 = autoclear.child("Status");
                child_6_8.setValue("0");
            }
            if (ctime >= 20) {
                autoclear = new Firebase("https://parkmycar-f36c7.firebaseio.com/ParkingSlots/Today/8_to_10PM/Lane1");
                Firebase child_8_10 = autoclear.child("Status");
                child_8_10.setValue("0");
            }
            TextView textView = findViewById(R.id.textView8);
            textView.setText(selectedDate);

            //Spinner Code Strated
            btnspinner.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    String item = spinner1.getSelectedItem().toString();
                    if (sdate.equals(cdate)) {
                        Day = "Today";
                        if (item.equals("8AM to 10AM")) {
                            if (ctime >= 8) {
                                Toast toast = Toast.makeText(getApplicationContext(), "Error ! Please Select Valid Timeslot ", Toast.LENGTH_SHORT);
                                toast.show();
                                return;
                            } else {
                                slotRef = new Firebase("https://parkmycar-f36c7.firebaseio.com/ParkingSlots/Today/8_to_10AM/Lane1/Status");
                                slotRef.addValueEventListener(new ValueEventListener() {
                                    @Override
                                    public void onDataChange(DataSnapshot dataSnapshot) {
                                        IntStatus = dataSnapshot.getValue(Integer.class);
                                        //Status=Integer.parseInt(s);
                                    }

                                    @Override
                                    public void onCancelled(FirebaseError firebaseError) {
                                    }
                                });
                                if(IntStatus==0) {
                                    laneno = 1;
                                    timeslot = "8_to_10AM";
                                    Intent intent = new Intent(TimeSlotActivity.this, Details.class);
                                    intent.putExtra(EXTRA_LANNO, laneno);
                                    intent.putExtra(EXTRA_DAY, Day);
                                    intent.putExtra(EXTRA_DATE, selectedDate);
                                    intent.putExtra(EXTRA_TIMESLOT, timeslot);
                                    startActivity(intent);
                                    //break;
                                } else {
                                    Toast toast = Toast.makeText(getApplicationContext(), "No Slots Available for parking ", Toast.LENGTH_SHORT);
                                    toast.show();
                                }
                            }
                        }
                        if (item.equals("10AM to 12AM")) {
                            if (ctime >= 10 && ctime >= 8) {
                                Toast toast = Toast.makeText(getApplicationContext(), "Error ! Please Select Valid Timeslot ", Toast.LENGTH_SHORT);
                                toast.show();
                                return;
                            } else {
                                slotRef = new Firebase("https://parkmycar-f36c7.firebaseio.com/ParkingSlots/Today/10_to_12AM/Lane1/Status");
                                slotRef.addValueEventListener(new ValueEventListener() {
                                    @Override
                                    public void onDataChange(DataSnapshot dataSnapshot) {
                                        IntStatus = dataSnapshot.getValue(Integer.class);
                                        //Status=Integer.parseInt(s);
                                    }

                                    @Override
                                    public void onCancelled(FirebaseError firebaseError) {
                                    }
                                });
                                if (IntStatus==0) {
                                    laneno = 1;
                                    timeslot = "10_to_12AM";
                                    Intent intent = new Intent(TimeSlotActivity.this, Details.class);
                                    intent.putExtra(EXTRA_LANNO, laneno);
                                    intent.putExtra(EXTRA_DAY, Day);
                                    intent.putExtra(EXTRA_DATE, selectedDate);
                                    intent.putExtra(EXTRA_TIMESLOT, timeslot);
                                    startActivity(intent);
                                    //break;
                                }else{
                                    Toast toast = Toast.makeText(getApplicationContext(), "No Slots Available for parking ", Toast.LENGTH_SHORT);
                                    toast.show();
                                }
                            }
                        }
                        if (item.equals("12PM to 2PM")) {
                            if (sdate.equals(cdate)) {
                                if (ctime >= 12 && ctime >= 8) {
                                    Toast toast = Toast.makeText(getApplicationContext(), "Error ! Please Select Valid Timeslot ", Toast.LENGTH_SHORT);
                                    toast.show();
                                    return;
                                } else {
                                    slotRef = new Firebase("https://parkmycar-f36c7.firebaseio.com/ParkingSlots/Today/12_to_2PM/Lane1/Status");
                                    slotRef.addValueEventListener(new ValueEventListener() {
                                        @Override
                                        public void onDataChange(DataSnapshot dataSnapshot) {
                                            IntStatus = dataSnapshot.getValue(Integer.class);
                                            //Status=Integer.parseInt(s);
                                        }

                                        @Override
                                        public void onCancelled(FirebaseError firebaseError) {
                                        }
                                    });
                                    if (IntStatus==0) {
                                        Empty = 1;
                                        laneno = 1;
                                        timeslot = "12_to_2PM";
                                        Intent intent = new Intent(TimeSlotActivity.this, Details.class);
                                        intent.putExtra(EXTRA_LANNO, laneno);
                                        intent.putExtra(EXTRA_DAY, Day);
                                        intent.putExtra(EXTRA_DATE, selectedDate);
                                        intent.putExtra(EXTRA_TIMESLOT, timeslot);
                                        startActivity(intent);
                                        //break;
                                    } else {
                                        Toast toast = Toast.makeText(getApplicationContext(), "No Slots Available for parking ", Toast.LENGTH_SHORT);
                                        toast.show();
                                    }
                                }
                            }
                        }
                        if (item.equals("2PM to 4PM")) {
                            if (sdate.equals(cdate)) {
                                if (ctime >= 14 && ctime >= 8) {
                                    Toast toast = Toast.makeText(getApplicationContext(), "Error ! Please Select Valid Timeslot ", Toast.LENGTH_SHORT);
                                    toast.show();
                                    return;
                                } else {
                                    slotRef = new Firebase("https://parkmycar-f36c7.firebaseio.com/ParkingSlots/Today/2_to_4PM/Lane1/Status");
                                    slotRef.addValueEventListener(new ValueEventListener() {
                                        @Override
                                        public void onDataChange(DataSnapshot dataSnapshot) {
                                            IntStatus = dataSnapshot.getValue(Integer.class);
                                            //Status=Integer.parseInt(s);
                                        }

                                        @Override
                                        public void onCancelled(FirebaseError firebaseError) {
                                        }
                                    });
                                    if (IntStatus==0) {
                                        Empty = 1;
                                        laneno = 1;
                                        timeslot = "2_to_4PM";
                                        Intent intent = new Intent(TimeSlotActivity.this, Details.class);
                                        intent.putExtra(EXTRA_LANNO, laneno);
                                        intent.putExtra(EXTRA_DAY, Day);
                                        intent.putExtra(EXTRA_DATE, selectedDate);
                                        intent.putExtra(EXTRA_TIMESLOT, timeslot);
                                        startActivity(intent);
                                        //break;
                                    } else {
                                        Toast toast = Toast.makeText(getApplicationContext(), "No Slots Available for parking ", Toast.LENGTH_SHORT);
                                        toast.show();
                                    }
                                }
                            }
                        }
                        if (item.equals("4PM to 6PM")) {
                            if (ctime >= 16 && ctime >= 8) {
                                Toast toast = Toast.makeText(getApplicationContext(), "Error ! Please Select Valid Timeslot ", Toast.LENGTH_SHORT);
                                toast.show();
                                return;
                            } else {
                                slotRef = new Firebase("https://parkmycar-f36c7.firebaseio.com/ParkingSlots/Today/4_to_6PM/Lane1/Status");
                                slotRef.addValueEventListener(new ValueEventListener() {
                                    @Override
                                    public void onDataChange(DataSnapshot dataSnapshot) {
                                        IntStatus = dataSnapshot.getValue(Integer.class);
                                        //Status=Integer.parseInt(s);
                                    }

                                    @Override
                                    public void onCancelled(FirebaseError firebaseError) {
                                    }
                                });
                                if (IntStatus==0) {
                                    Empty = 1;
                                    laneno = 1;
                                    timeslot = "4_to_6PM";
                                    Intent intent = new Intent(TimeSlotActivity.this, Details.class);
                                    intent.putExtra(EXTRA_LANNO, laneno);
                                    intent.putExtra(EXTRA_DAY, Day);
                                    intent.putExtra(EXTRA_DATE, selectedDate);
                                    intent.putExtra(EXTRA_TIMESLOT, timeslot);
                                    startActivity(intent);
                                    //break;
                                } else {
                                    Toast toast = Toast.makeText(getApplicationContext(), "No Slots Available for parking ", Toast.LENGTH_SHORT);
                                    toast.show();
                                }
                            }

                        }
                        if (item.equals("6PM to 8PM")) {
                            if (sdate.equals(cdate)) {
                                if (ctime >= 18 && ctime >= 8) {
                                    Toast toast = Toast.makeText(getApplicationContext(), "Error ! Please Select Valid Timeslot ", Toast.LENGTH_SHORT);
                                    toast.show();
                                    return;
                                } else {
                                    slotRef = new Firebase("https://parkmycar-f36c7.firebaseio.com/ParkingSlots/Today/6_to_8PM/Lane1/Status");
                                    slotRef.addValueEventListener(new ValueEventListener() {
                                        @Override
                                        public void onDataChange(DataSnapshot dataSnapshot) {
                                            IntStatus = dataSnapshot.getValue(Integer.class);
                                            //Status=Integer.parseInt(s);
                                        }

                                        @Override
                                        public void onCancelled(FirebaseError firebaseError) {
                                        }
                                    });
                                    if (IntStatus==0) {
                                        Empty = 1;
                                        laneno = 1;
                                        timeslot = "6_to_8PM";
                                        Intent intent = new Intent(TimeSlotActivity.this, Details.class);
                                        intent.putExtra(EXTRA_LANNO, laneno);
                                        intent.putExtra(EXTRA_DAY, Day);
                                        intent.putExtra(EXTRA_DATE, selectedDate);
                                        intent.putExtra(EXTRA_TIMESLOT, timeslot);
                                        startActivity(intent);
                                        //break;
                                    } else {
                                        Toast toast = Toast.makeText(getApplicationContext(), "No Slots Available for parking ", Toast.LENGTH_SHORT);
                                        toast.show();
                                    }
                                }
                            }
                        }
                        if (item.equals("8PM to 10PM")) {
                            if (ctime >= 20 && ctime >= 8) {
                                Toast toast = Toast.makeText(getApplicationContext(), "Error ! Please Select Valid Timeslot", Toast.LENGTH_SHORT);
                                toast.show();
                                return;
                            }else{
                                slotRef = new Firebase("https://parkmycar-f36c7.firebaseio.com/ParkingSlots/Today/8_to_10PM/Lane1/Status");
                                slotRef.addValueEventListener(new ValueEventListener() {
                                    @Override
                                    public void onDataChange(DataSnapshot dataSnapshot) {
                                        IntStatus = dataSnapshot.getValue(Integer.class);
                                        //Status=Integer.parseInt(s);
                                    }

                                    @Override
                                    public void onCancelled(FirebaseError firebaseError) {
                                    }
                                });
                                if(IntStatus==0){
                                    laneno=1;
                                    timeslot = "8_to_10PM";
                                    Intent intent = new Intent(TimeSlotActivity.this, Details.class);
                                    intent.putExtra(EXTRA_LANNO, laneno);
                                    intent.putExtra(EXTRA_DAY, Day);
                                    intent.putExtra(EXTRA_DATE, selectedDate);
                                    intent.putExtra(EXTRA_TIMESLOT, timeslot);
                                    startActivity(intent);
                                    //break;
                                }else{
                                    Toast toast = Toast.makeText(getApplicationContext(), "No Slots Available for parking ", Toast.LENGTH_SHORT);
                                    toast.show();
                                }
                            }
                        }
                    } //End SelectedDate is equal to CurrentDate
                    else {//Book For Tomorrow
                        Day = "Tomorrow";
                        if (item.equals("8AM to 10AM")) {
                            slotRef = new Firebase("https://parkmycar-f36c7.firebaseio.com/ParkingSlots/Tomorrow/8_to_10AM/Lane1/Status");
                            slotRef.addValueEventListener(new ValueEventListener() {
                                @Override
                                public void onDataChange(DataSnapshot dataSnapshot) {
                                    IntStatus = dataSnapshot.getValue(Integer.class);
                                    //Status=Integer.parseInt(s);
                                }

                                @Override
                                public void onCancelled(FirebaseError firebaseError) {
                                }
                            });
                            if (IntStatus==0) {
                                laneno = 1;
                                timeslot = "8_to_10AM";
                                Intent intent = new Intent(TimeSlotActivity.this, Details.class);
                                intent.putExtra(EXTRA_LANNO, laneno);
                                intent.putExtra(EXTRA_DAY, Day);
                                intent.putExtra(EXTRA_DATE, selectedDate);
                                intent.putExtra(EXTRA_TIMESLOT, timeslot);
                                startActivity(intent);
                                //break;
                            } else {
                                Toast toast = Toast.makeText(getApplicationContext(), "No Slots Available for parking ", Toast.LENGTH_SHORT);
                                toast.show();
                            }
                        }
                        if (item.equals("10AM to 12AM")) {
                            slotRef = new Firebase("https://parkmycar-f36c7.firebaseio.com/ParkingSlots/Tomorrow/10_to_12AM/Lane1/Status");
                            slotRef.addValueEventListener(new ValueEventListener() {
                                @Override
                                public void onDataChange(DataSnapshot dataSnapshot) {
                                    IntStatus = dataSnapshot.getValue(Integer.class);
                                    //Status=Integer.parseInt(s);
                                }

                                @Override
                                public void onCancelled(FirebaseError firebaseError) {
                                }
                            });
                            if (IntStatus==0) {
                                laneno = 1;
                                timeslot = "10_to_12AM";
                                Intent intent = new Intent(TimeSlotActivity.this, Details.class);
                                intent.putExtra(EXTRA_LANNO, laneno);
                                intent.putExtra(EXTRA_DAY, Day);
                                intent.putExtra(EXTRA_DATE, selectedDate);
                                intent.putExtra(EXTRA_TIMESLOT, timeslot);
                                startActivity(intent);
                                //break;
                            } else {
                                Toast toast = Toast.makeText(getApplicationContext(), "No Slots Available for parking ", Toast.LENGTH_SHORT);
                                toast.show();
                            }
                        }
                        if (item.equals("12PM to 2PM")) {
                            slotRef = new Firebase("https://parkmycar-f36c7.firebaseio.com/ParkingSlots/Tomorrow/12_to_2PM/Lane1/Status");
                            slotRef.addValueEventListener(new ValueEventListener() {
                                @Override
                                public void onDataChange(DataSnapshot dataSnapshot) {
                                    IntStatus = dataSnapshot.getValue(Integer.class);
                                }

                                @Override
                                public void onCancelled(FirebaseError firebaseError) {
                                }
                            });
                            if (IntStatus==0) {
                                laneno = 1;
                                timeslot = "12_to_2PM";
                                Intent intent = new Intent(TimeSlotActivity.this, Details.class);
                                intent.putExtra(EXTRA_LANNO, laneno);
                                intent.putExtra(EXTRA_DAY, Day);
                                intent.putExtra(EXTRA_DATE, selectedDate);
                                intent.putExtra(EXTRA_TIMESLOT, timeslot);
                                startActivity(intent);
                                //break;
                            } else {
                                Toast toast = Toast.makeText(getApplicationContext(), "No Slots Available for parking ", Toast.LENGTH_SHORT);
                                toast.show();
                            }
                        }
                        if (item.equals("2PM to 4PM")) {
                            slotRef = new Firebase("https://parkmycar-f36c7.firebaseio.com/ParkingSlots/Tomorrow/2_to_4PM/Lane1/Status");
                            slotRef.addValueEventListener(new ValueEventListener() {
                                @Override
                                public void onDataChange(DataSnapshot dataSnapshot) {
                                    IntStatus = dataSnapshot.getValue(Integer.class);
                                    //Status=Integer.parseInt(s);
                                }

                                @Override
                                public void onCancelled(FirebaseError firebaseError) {
                                }
                            });
                            if (IntStatus==0) {
                                Empty = 1;
                                laneno = 1;
                                timeslot = "2_to_4PM";
                                Intent intent = new Intent(TimeSlotActivity.this, Details.class);
                                intent.putExtra(EXTRA_LANNO, laneno);
                                intent.putExtra(EXTRA_DAY, Day);
                                intent.putExtra(EXTRA_DATE, selectedDate);
                                intent.putExtra(EXTRA_TIMESLOT, timeslot);
                                startActivity(intent);
                                //break;
                            } else {
                                Toast toast = Toast.makeText(getApplicationContext(), "No Slots Available for parking ", Toast.LENGTH_SHORT);
                                toast.show();
                            }
                        }
                        if (item.equals("4PM to 6PM")) {
                            slotRef = new Firebase("https://parkmycar-f36c7.firebaseio.com/ParkingSlots/Tomorrow/4_to_6PM/Lane1/Status");
                            slotRef.addValueEventListener(new ValueEventListener() {
                                @Override
                                public void onDataChange(DataSnapshot dataSnapshot) {
                                    IntStatus = dataSnapshot.getValue(Integer.class);
                                    //Status=Integer.parseInt(s);
                                }

                                @Override
                                public void onCancelled(FirebaseError firebaseError) {
                                }
                            });
                            if (IntStatus==0) {
                                laneno = 1;
                                timeslot = "4_to_6PM";
                                Intent intent = new Intent(TimeSlotActivity.this, Details.class);
                                intent.putExtra(EXTRA_LANNO, laneno);
                                intent.putExtra(EXTRA_DAY, Day);
                                intent.putExtra(EXTRA_DATE, selectedDate);
                                intent.putExtra(EXTRA_TIMESLOT, timeslot);
                                startActivity(intent);
                                //break;
                            } else {
                                Toast toast = Toast.makeText(getApplicationContext(), "No Slots Available for parking ", Toast.LENGTH_SHORT);
                                toast.show();
                            }
                        }
                        if (item.equals("6PM to 8PM")) {
                            slotRef = new Firebase("https://parkmycar-f36c7.firebaseio.com/ParkingSlots/Tomorrow/6_to_8PM/Lane1/Status");
                            slotRef.addValueEventListener(new ValueEventListener() {
                                @Override
                                public void onDataChange(DataSnapshot dataSnapshot) {
                                    IntStatus = dataSnapshot.getValue(Integer.class);
                                    //Status=Integer.parseInt(s);
                                }

                                @Override
                                public void onCancelled(FirebaseError firebaseError) {
                                }
                            });
                            if (IntStatus==0) {
                                laneno = 1;
                                timeslot = "6_to_8PM";
                                Intent intent = new Intent(TimeSlotActivity.this, Details.class);
                                intent.putExtra(EXTRA_LANNO, laneno);
                                intent.putExtra(EXTRA_DAY, Day);
                                intent.putExtra(EXTRA_DATE, selectedDate);
                                intent.putExtra(EXTRA_TIMESLOT, timeslot);
                                startActivity(intent);
                                //break;
                            } else {
                                Toast toast = Toast.makeText(getApplicationContext(), "No Slots Available for parking ", Toast.LENGTH_SHORT);
                                toast.show();
                            }
                        }
                        if (item.equals("8PM to 10PM")) {
                            //for (i = 1; i < 2; i++) { //(i=1;i<6;i++) for 5 lane
                                slotRef = new Firebase("https://parkmycar-f36c7.firebaseio.com/ParkingSlots/Tomorrow/8_to_10PM/Lane1/Status");
                                slotRef.addValueEventListener(new ValueEventListener() {
                                    @Override
                                    public void onDataChange(DataSnapshot dataSnapshot) {
                                        IntStatus = dataSnapshot.getValue(Integer.class);
                                        //Status=Integer.parseInt(s);
                                    }

                                    @Override
                                    public void onCancelled(FirebaseError firebaseError) {
                                    }
                                });
                                if (IntStatus==0) {
                                    laneno = 1;
                                    timeslot = "8_to_10PM";
                                    Intent intent = new Intent(TimeSlotActivity.this, Details.class);
                                    intent.putExtra(EXTRA_LANNO, laneno);
                                    intent.putExtra(EXTRA_DAY, Day);
                                    intent.putExtra(EXTRA_DATE, selectedDate);
                                    intent.putExtra(EXTRA_TIMESLOT, timeslot);
                                    startActivity(intent);
                                    //break;
                                } else {
                                    Toast toast = Toast.makeText(getApplicationContext(), "No Slots Available for parking ", Toast.LENGTH_SHORT);
                                    toast.show();
                                    }
                                }
                            //}
                        }


                        //Intent intent = new Intent(TimeSlotActivity.this, Details.class);
                        //startActivity(intent);
                    }

            });//End Else Part of date(Allowing for parking)
    }

}}




