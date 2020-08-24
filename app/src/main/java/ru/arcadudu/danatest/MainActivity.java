package ru.arcadudu.danatest;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private static final String TEST_PICKED = "topic_picked";
    private static int clicks = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ConstraintLayout constraintLayout = findViewById(R.id.constraint_layout);
        constraintLayout.setBackground(getResources().getDrawable(R.drawable.background_main_screen, getTheme()));

        final TextView tv_home_title = findViewById(R.id.tv_activity_title);

        // Кнопка выхода из приложения
        ImageView exit = findViewById(R.id.iv_exit_app);
        exit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();
            }
        });

        // Кнопки выбора теста
        Button test1 = findViewById(R.id.btn_test1);
        Button test2 = findViewById(R.id.btn_test2);
        Button test3 = findViewById(R.id.btn_test3);
        Button test4 = findViewById(R.id.btn_test4);

        test1.setOnClickListener(this);
        test2.setOnClickListener(this);
        test3.setOnClickListener(this);
        test4.setOnClickListener(this);


    }

    @Override
    public void onClick(View view) {
        Intent intent = new Intent(this, TopicPickerActivity.class);
        switch (view.getId()) {
            case R.id.btn_test1:
                intent.putExtra(TEST_PICKED, "test1");
                break;
            case R.id.btn_test2:
                intent.putExtra(TEST_PICKED, "test2");
                break;
            case R.id.btn_test3:
                intent.putExtra(TEST_PICKED, "test3");
                break;
            case R.id.btn_test4:
                intent.putExtra(TEST_PICKED, "test4");
                break;
        }
        startActivity(intent);
    }
}