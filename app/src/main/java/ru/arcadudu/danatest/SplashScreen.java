package ru.arcadudu.danatest;

import android.app.ActionBar;
import android.content.Intent;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.View;
import android.view.Window;

import androidx.appcompat.app.AppCompatActivity;

import ru.arcadudu.danatest.main_selector.MainActivity;

public class SplashScreen extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen);
        Window window = getWindow();
        window.setStatusBarColor(getColor(R.color.material_darkest_background));

        CountDownTimer countDownTimer = new CountDownTimer(2000, 500) {
            @Override
            public void onTick(long l) {

            }
            @Override
            public void onFinish() {
                Intent intent = new Intent(SplashScreen.this, MainActivity.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
                startActivity(intent);
            }
        }.start();


    }
}