package ru.arcadudu.danatest.test;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import ru.arcadudu.danatest.R;
import ru.arcadudu.danatest.topic_selector.TopicPickerActivity;

public class Test4Shuffle extends TestClass implements View.OnClickListener {

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
        setContentView(R.layout.activity_test4_shuffle);

        findComponents();
        getIntentExtras(getIntent(), tv_currentTopic);
        prepareContainers(strResources);
        setOnClicks();
        setGame();
    }

    @Override
    public void setGame() {
        if (index == 0) Collections.shuffle(listEng);
        quest = listEng.get(index);
        tv_questWord.setText(shuffleWord(quest));
        et_answerField.setText("");
        tv_wordCount.setText((index + 1) + "/" + listEng.size());
        setAnimations(tv_questWord);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
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
        if (answer.isEmpty()) answer = "нет ответа";

        if (!answer.equalsIgnoreCase(quest)) {
            mistakes++;
            mistakeList.add(quest);
            sbMistakes.append(answer).append("\n");
            sbCorrects.append(quest).append("\n");
        }
    }

    private String shuffleWord(String word) {
        StringBuilder sbResult = new StringBuilder();
        List<Character> letters = new ArrayList<>();
        for (int i = 0; i < word.length(); i++) {
            letters.add(word.charAt(i));
        }
        String res = shuffle(letters);
        Log.e("AA", "res = " + res);

        while (res.equalsIgnoreCase(word)) {
            res = shuffle(letters);
            Log.e("AA", "res = " + res);
        }
        return res;
    }

    private String shuffle(List<Character> letters) {
        Collections.shuffle(letters);
        StringBuilder sbResult = new StringBuilder();
        for (Character ch : letters) {
            sbResult.append(ch);
        }
        return sbResult.toString();
    }
}