package ru.arcadudu.danatest.main_selector;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import ru.arcadudu.danatest.R;
import ru.arcadudu.danatest.topic_selector.TopicPickerActivity;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private static final String TEST_NAME = "testName";

    ConstraintLayout constraintLayout;
    Button test1, test2, test3, test4;
    ImageView exitApp;

    @SuppressLint("UseCompatLoadingForDrawables")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        findComponents();
        setClickListeners();
    }

    private void setClickListeners() {
        exitApp.setOnClickListener(this);
        test1.setOnClickListener(this);
        test2.setOnClickListener(this);
        test3.setOnClickListener(this);
        test4.setOnClickListener(this);
    }

    private void findComponents() {
        constraintLayout = findViewById(R.id.constraint_layout);
//        constraintLayout.setBackground(getResources().getDrawable
//                (R.drawable.background_main_screen_dark, getTheme()));

        exitApp = findViewById(R.id.iv_exit_app);
        test1 = findViewById(R.id.btn_test1);
        test2 = findViewById(R.id.btn_test2);
        test3 = findViewById(R.id.btn_test3);
        test4 = findViewById(R.id.btn_test4);

    }

    @Override
    public void onClick(View view) {
        Intent intent = new Intent(this, TopicPickerActivity.class);
        switch (view.getId()) {
            case R.id.iv_exit_app:
                onBackPressed();
                break;
            case R.id.btn_test1:
                intent.putExtra(TEST_NAME, "Прямой перевод");
                startActivity(intent);
                break;
            case R.id.btn_test2:
                Toast.makeText(this, "Раздел \"Вставить слово\" в разработке...", Toast.LENGTH_SHORT).show();
                intent.putExtra(TEST_NAME, "Вставить слово");
                break;
            case R.id.btn_test3:
                intent.putExtra(TEST_NAME, "Четыре варианта");
                startActivity(intent);
                break;
            case R.id.btn_test4:
                intent.putExtra(TEST_NAME, "Перемешать");
                startActivity(intent);
                break;
        }

    }
}