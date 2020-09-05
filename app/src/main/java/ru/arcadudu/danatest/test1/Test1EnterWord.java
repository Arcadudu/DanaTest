package ru.arcadudu.danatest.test1;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

import ru.arcadudu.danatest.R;
import ru.arcadudu.danatest.topic_selector.Topic;
import ru.arcadudu.danatest.topic_selector.TopicPickerActivity;

public class Test1EnterWord extends AppCompatActivity {

    TextView currentTest, currentTopic;

    ImageView endTest, restartTest;
    TextView questWord, wordCounter;
    EditText answerField;
    Button btnNext, btnClear;

    List<Topic> list;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test1_enter_word);

        // текущий тест
        currentTest = findViewById(R.id.tv_current_test_name);
        // текущая тема
        currentTopic = findViewById(R.id.tv_current_topic_name);

        // иконка выйти из теста
        endTest = findViewById(R.id.iv_end_test);
        // иконка начать тест заново
        restartTest = findViewById(R.id.iv_restart_test);

        // поле загаданного слова
        questWord = findViewById(R.id.tv_quest_word);

        // счетчик слов
        wordCounter = findViewById(R.id.tv_word_counter);

        // поле ввода ответа
        answerField = findViewById(R.id.et_answer_field);

        // кнопка готово
        btnNext = findViewById(R.id.btn_next);
        // кнопка стереть
        btnClear = findViewById(R.id.btn_clear);

        btnClear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                answerField.setText("");
            }
        });

        // Получаем лист с темами
        list = new TopicPickerActivity().getTopicList();




    }
}