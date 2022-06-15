package com.project.parkmycar;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.firebase.auth.FirebaseAuth;
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.views.YouTubePlayerView;

public class AboutUs extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_about_us);

        //youtube player view

        YouTubePlayerView youTubePlayerView = findViewById(R.id.youtube_player_view);
        getLifecycle().addObserver(youTubePlayerView);

        //bottom navigation code started

        //Initial and assign variable
        BottomNavigationView bottomNavigationView = findViewById(R.id.bottom_navigation);
        //set home selected
        bottomNavigationView.setSelectedItemId(R.id.AboutUs);
        //perforom item selected listener
        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {

                CharSequence title = menuItem.getTitle();
                if (title.equals("Home")) {
                    Intent intent = new Intent(AboutUs.this, HomeActivity.class);
                    startActivity(intent);
                    return true;
                } else if (title.equals("My Booking")) {

                    Intent intent = new Intent(AboutUs.this, MyBooking.class);
                    startActivity(intent);
                    //finish();
                    return true;

                } else if (title.equals("About Us")) {

                    return true;

                } else if (title.equals("Feedback")) {

                    Intent intent1 = new Intent(AboutUs.this, feedback.class);
                    startActivity(intent1);
                    return true;
                }
                return false;
            }
        });
        //Bottom Navigation Code Ended


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
                new User(AboutUs.this).removeUser();
                Intent intToMain = new Intent(AboutUs.this, LoginActivity.class);
                startActivity(intToMain);
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }

    }
    public void onBackPressed() {
        Intent backtoHome = new Intent(AboutUs.this, HomeActivity.class);
        startActivity(backtoHome);

    }
}
