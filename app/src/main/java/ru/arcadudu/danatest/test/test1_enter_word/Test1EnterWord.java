package ru.arcadudu.danatest.test.test1_enter_word;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import ru.arcadudu.danatest.R;
import ru.arcadudu.danatest.ResultActivity;
import ru.arcadudu.danatest.topic_selector.TopicPickerActivity;

public class Test1EnterWord extends AppCompatActivity implements View.OnClickListener {

    private static final String TAG = "myTag";
    private static final String TEST_NAME = "testName";
    private static final String TOPIC_NAME = "topicName";

    TextView tv_currentTest, tv_currentTopic;
    ImageView iv_endTest, iv_restartTest;
    TextView tv_questWord, tv_wordCount;
    EditText et_answerField;
    Button btnNext, btnClear;

    private List<String> listRu, listEng;
    private Map<String, String> map;

    private String quest;
    private int index = 0;
    private List<String> mistakeList = new ArrayList<>();
    private double mistakes;

    @Override

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test1_enter_word);

        findComponents(); // определили компоненты
        getIntentExtras(getIntent()); // заполнили листы
        map = getMap(listRu, listEng); // заполнили карту

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

        setGame();
    }

    private void setGame() {
        if(index == 0){
            Collections.shuffle(listRu);
        }
        quest = listRu.get(index);
        tv_questWord.setText(quest);
        et_answerField.setText("");
        tv_wordCount.setText((index + 1) + "/" + listEng.size());
    }

    private void findComponents() {
        tv_currentTest = findViewById(R.id.tv_current_test_name);
        tv_currentTopic = findViewById(R.id.tv_current_topic_name);

        iv_endTest = findViewById(R.id.iv_end_test); // иконка выйти из теста
        iv_restartTest = findViewById(R.id.iv_restart_test); // иконка начать тест заново

        tv_questWord = findViewById(R.id.tv_quest_word); // поле загаданного слова
        tv_wordCount = findViewById(R.id.tv_word_counter); // счетчик слов

        et_answerField = findViewById(R.id.et_answer_field); // поле ввода ответа

        btnNext = findViewById(R.id.btn_next); // кнопка готово

        btnClear = findViewById(R.id.btn_clear); // кнопка стереть

        Log.d(TAG, "1.findComponents: all components found!");

    }

    private void getIntentExtras(Intent incomingIntent) {
        if (incomingIntent.getExtras() != null) {
            String title = incomingIntent.getStringExtra("title");
            tv_currentTopic.setText(title);
            assert title != null;
            if ("Великобритания".equalsIgnoreCase(title)) {
                listRu = fillList(R.array.gbRuPlain);
                listEng = fillList(R.array.gbEngPlain);
            } else if ("Части тела".equalsIgnoreCase(title)) {
                listRu = fillList(R.array.bodyRuPlain);
                listEng = fillList(R.array.bodyEngPlain);
            } else if ("Лицо и его части".equalsIgnoreCase(title)) {
                listRu = fillList(R.array.faceRuPlain);
                listEng = fillList(R.array.faceEngPlain);
            } else if ("Временные константы".equalsIgnoreCase(title)) {
                listRu = fillList(R.array.timeRuPlain);
                listEng = fillList(R.array.timeEngPlain);
            } else if ("Дом : базовый уровень".equalsIgnoreCase(title)) {
                listRu = fillList(R.array.houseBasicRuPlain);
                listEng = fillList(R.array.houseBasicEngPlain);
            } else {
                Toast.makeText(this, "Произошла ошибка!", Toast.LENGTH_SHORT).show();
            }
            Log.d(TAG, "getIntentExtras: EXTRAS found withing intent : " + title);
        }
        Log.d(TAG, "getIntentExtras: results: listRu length -" + listRu.size() + "  listEng length -" + listEng.size());
    }

    public Map<String, String> getMap(List<String> ru, List<String> eng) {
        Map<String, String> map = new HashMap<>();
        if (ru.size() == eng.size()) {
            for (int i = 0; i < ru.size(); i++) {
                map.put(ru.get(i), eng.get(i));
            }
        }
        Log.d(TAG, String.valueOf("getMap: map is null: " + map == null));
        Log.d(TAG, "getMap: map size: " + map.size());
        return map;
    }

    public List<String> fillList(int stringResourceId) {
        return Arrays.asList(getResources().getStringArray(stringResourceId));
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
                index++;
                if (index < listRu.size()) {
                    setGame();
                } else {
                    Log.d(TAG, "onClick: mistakes: " + mistakes + " // mistakesList.length: " + mistakeList.size());
                    Intent intent = new Intent(getApplicationContext(), ResultActivity.class);
                    intent.putExtra(TEST_NAME, tv_currentTest.getText().toString());
                    intent.putExtra("mistakes", String.valueOf(mistakes));
                    double percentage = (mistakes / listRu.size()) * 100;
                    intent.putExtra("percentage", String.valueOf(percentage));
                    Log.d(TAG, "onClick: percentageInExtra " + intent.getStringExtra("percentage"));
                    startActivity(intent);
                    finish();
                }
                break;
        }
    }

    public void restartTest() {
        index = 0;
        mistakes = 0;
        mistakeList.clear();
        setGame();
    }

    private void checkCorrect() {
        String answer = et_answerField.getText().toString();
        String check = map.get(quest);
        Log.d(TAG, "checkCorrect: answer: " + answer + "   check: " + check + " current index: " + index);
        Log.d(TAG, "checkCorrect: answer == check ?? :" + answer.equalsIgnoreCase(check));

        if (!answer.equalsIgnoreCase(check)) {
            mistakes++;
            mistakeList.add(quest);
        }

    }

}