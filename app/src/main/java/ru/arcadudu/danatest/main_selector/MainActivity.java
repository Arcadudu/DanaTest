package ru.arcadudu.danatest.main_selector;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import ru.arcadudu.danatest.R;
import ru.arcadudu.danatest.test1.Test1EnterWord;
import ru.arcadudu.danatest.topic_selector.TopicPickerActivity;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private static final String TEST_PICKED = "topic_picked";

    @SuppressLint("UseCompatLoadingForDrawables")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ConstraintLayout constraintLayout = findViewById(R.id.constraint_layout);
        constraintLayout.setBackground(getResources().getDrawable(R.drawable.background_main_screen_dark, getTheme()));

        final TextView tv_home_title = findViewById(R.id.tv_activity_title);

        // Иконка выхода из приложения
        ImageView exit = findViewById(R.id.iv_exit_app);
        exit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();
            }
        });

        // Каталог словарей
        ImageView dictionaries = findViewById(R.id.iv_dictionaries);





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

    // закоментил до момента создания TopicPickerActivity
    @Override
    public void onClick(View view) {
        Intent intent;
        switch (view.getId()) {
            case R.id.btn_test1:
                intent = new Intent(this, TopicPickerActivity.class);
                intent.putExtra(TEST_PICKED, "Прямой перевод");
                startActivity(intent);
                break;
            case R.id.btn_test2:
                Toast.makeText(this, "Раздел \"Вставить слово\" в разработке...", Toast.LENGTH_SHORT).show();
//                intent.putExtra(TEST_PICKED, "Вставить слово");
                break;
            case R.id.btn_test3:
                Toast.makeText(this, "Раздел \"Четыре варианта\" в разработке...", Toast.LENGTH_SHORT).show();
//                intent.putExtra(TEST_PICKED, "Четыре варианта");
                break;
            case R.id.btn_test4:
                Toast.makeText(this, "Раздел \"Перемешать\" в разработке...", Toast.LENGTH_SHORT).show();
//                intent.putExtra(TEST_PICKED, "Перемешать");
                break;
        }

    }
}