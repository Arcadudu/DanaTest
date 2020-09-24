package ru.arcadudu.danatest.test;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.Collections;

import ru.arcadudu.danatest.R;
import ru.arcadudu.danatest.topic_selector.TopicPickerActivity;

public class Test1EnterWord extends TestClass implements View.OnClickListener {

    TextView tv_currentTest, tv_currentTopic;
    ImageView iv_endTest, iv_restartTest;
    TextView tv_questWord, tv_wordCount;
    EditText et_answerField;
    Button btnNext, btnClear;

    private int[] strResources = {R.array.gbRuPlain, R.array.gbEngPlain,
            R.array.bodyRuPlain, R.array.bodyEngPlain, R.array.faceRuPlain, R.array.faceEngPlain,
            R.array.timeRuPlain, R.array.timeEngPlain, R.array.houseBasicRuPlain, R.array.houseBasicEngPlain};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test1_enter_word);

        findComponents(); // определили компоненты
        getIntentExtras(getIntent(), tv_currentTopic); // получили экстрас
        prepareContainers(strResources); // заполнили листы и карту
        setOnClicks(); // установили слушатели
        setGame();
    }

    @Override
    public void setGame() {
        if (index == 0) Collections.shuffle(listRu);
        quest = listRu.get(index);
        tv_questWord.setText(quest);
        et_answerField.setText("");
        tv_wordCount.setText((index + 1) + "/" + listEng.size());
        setAnimations(tv_questWord);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            // кнопки
            case R.id.btn_clear:
                et_answerField.setText("");
                break;
            case R.id.btn_next:
                checkCorrect();
                checkReadinessForResult(tv_currentTest);
                break;
        }
    }

    private void findComponents() {
        tv_currentTest = findViewById(R.id.tv_current_test_name); // текущий тест
        tv_currentTopic = findViewById(R.id.tv_current_topic_name); // текущий топик
        iv_endTest = findViewById(R.id.iv_end_test); // иконка выйти из теста
        iv_restartTest = findViewById(R.id.iv_restart_test); // иконка начать тест заново
        tv_questWord = findViewById(R.id.tv_quest_word); // поле загаданного слова
        tv_wordCount = findViewById(R.id.tv_word_counter); // счетчик слов
        et_answerField = findViewById(R.id.et_answer_field); // поле ввода ответа
        btnNext = findViewById(R.id.btn_next); // кнопка готово
        btnClear = findViewById(R.id.btn_clear); // кнопка стереть
        Log.d(TAG, "1.findComponents: all components found!");
    }

    private void setOnClicks() {
        btnNext.setOnClickListener(this);
        btnClear.setOnClickListener(this);
        iv_restartTest.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                restartTest();
            }
        });
        iv_endTest.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), TopicPickerActivity.class);
                startActivity(intent);
                finish();
            }
        });
    }

    private void checkCorrect() {
        String answer = et_answerField.getText().toString();
        Log.d(TAG, "checkCorrect: answer " + answer);
        Log.d(TAG, "checkCorrect: map is null: " + (map == null));
        if (answer.isEmpty()) answer = "нет ответа";
        String check = map.get(quest);
        Log.d(TAG, "checkCorrect: answer: " + answer + "   check: " + check + " current index: " + index);
        Log.d(TAG, "checkCorrect: answer == check ?? :" + answer.equalsIgnoreCase(check));

        if (!answer.equalsIgnoreCase(check)) {
            mistakes++;
            mistakeList.add(quest);
            sbMistakes.append("✗ ").append(answer).append(" ✓ ").append(check).append("*");
        }

    }

}