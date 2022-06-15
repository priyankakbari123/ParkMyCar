package com.project.parkmycar;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.firebase.client.DataSnapshot;
import com.firebase.client.Firebase;
import com.firebase.client.FirebaseError;
import com.firebase.client.ValueEventListener;

public class AdminLogin extends AppCompatActivity {
    EditText email,password;
    Button loginbtn;
    String admin_email,admin_password;
    Firebase fadmin_email,fadmin_password;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_login);

        email=findViewById(R.id.editText7);
        password=findViewById(R.id.editText8);
        loginbtn=findViewById(R.id.button);


        fadmin_email=new Firebase("https://parkmycar-f36c7.firebaseio.com/Admin/Email");
        fadmin_password=new Firebase("https://parkmycar-f36c7.firebaseio.com/Admin/Password");
        fadmin_password.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                admin_password= dataSnapshot.getValue(String.class);
                //Status = Integer.parseInt(s);
            }

            @Override
            public void onCancelled(FirebaseError firebaseError) {
            }
        });
        fadmin_email.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                admin_email=dataSnapshot.getValue(String.class);
            }

            @Override
            public void onCancelled(FirebaseError firebaseError) {

            }
        });

        loginbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String Semail=email.getText().toString();
                String Spass=password.getText().toString();
                if(Semail.equals(admin_email)&&Spass.equals(admin_password)){
                    Intent intentAL = new Intent(AdminLogin.this, AdminPage.class);
                    startActivity(intentAL);
                }
                else {
                    Toast toast = Toast.makeText(getApplicationContext(), "Error ! Invalid Email OR Password", Toast.LENGTH_SHORT);
                    toast.show();
                }

            }
        });

    }
}
