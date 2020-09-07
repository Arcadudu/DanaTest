package ru.arcadudu.danatest;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;

public class ResultActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result);

        ImageView iv_circle = findViewById(R.id.iv_result_img_circle);
        Animation rotateCircle = AnimationUtils.loadAnimation(this, R.anim.rotate);
        iv_circle.startAnimation(rotateCircle);

        ImageView iv_innerImage = findViewById(R.id.iv_result_inner_img);
        Animation fadeIn = AnimationUtils.loadAnimation(this, R.anim.fadein);
        iv_innerImage.startAnimation(fadeIn);
    }
}