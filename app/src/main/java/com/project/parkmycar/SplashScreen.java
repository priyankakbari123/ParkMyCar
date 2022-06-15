package com.project.parkmycar;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class SplashScreen extends AppCompatActivity {

    private static int SPLASH_SCREEN = 4000;
    ImageView image;
    TextView logo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen);
        image = findViewById(R.id.imageView);
        logo = findViewById(R.id.textView);
        Animation animation_top = AnimationUtils.loadAnimation(getApplicationContext(),
                R.anim.top_animation);
        Animation animation_bottom = AnimationUtils.loadAnimation(getApplicationContext(),
                R.anim.bottom_animation);
        image.startAnimation(animation_top);
        logo.startAnimation(animation_bottom);
        final User user=new User(SplashScreen.this);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);//Remove Statusbar From Splash Screen

        //Splash Screen
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                if(user.getEmail()!=""){
                    Intent intent = new Intent(SplashScreen.this, HomeActivity.class);
                    startActivity(intent);
                    finish();
                }
                else {
                    Intent intent = new Intent(SplashScreen.this, LoginActivity.class);
                    startActivity(intent);
                    finish();
                }
            }
        }, SPLASH_SCREEN);

        }
    }


