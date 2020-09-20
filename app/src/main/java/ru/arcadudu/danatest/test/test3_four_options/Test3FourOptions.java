package ru.arcadudu.danatest.test.test3_four_options;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
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

public class Test3FourOptions extends AppCompatActivity{

    private final static String TAG = "myTag";
    private static final String TEST_NAME = "testName";
    private static final String TOPIC_NAME = "topicName";

    TextView tv_currentTest, tv_currentTopic;
    ImageView iv_endTest, iv_restartTest;
    TextView tv_questWord, tv_wordCounter;
    Button btnOption1, btnOption2, btnOption3, btnOption4;

    private List<String> listRu, listEng, listEngKeys;
    private Map<String, String> plainMap, optionMap;

    private String quest;
    private int index = 0;
    private List<String> mistakeList = new ArrayList<>();
    private double mistakes;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test3_four_options);

        findComponents();
        getIntentExtras(getIntent());
        setButtons();
        optionMap = getMap(listRu, listEng);
        plainMap = getMap(listRu, listEngKeys);
        setGame();
    }

    public void getIntentExtras(Intent incomingIntent) {
        if (incomingIntent.getExtras() != null) {
            String title = incomingIntent.getStringExtra("title");
            tv_currentTopic.setText(title);
            assert title != null;
            if ("Великобритания".equalsIgnoreCase(title)) {
                listRu = fillList(R.array.gbRuOptions);
                listEng = fillList(R.array.gbEngOptions);
                listEngKeys = fillList(R.array.gbEngAnswers);
            } else if ("Части тела".equalsIgnoreCase(title)) {
                listRu = fillList(R.array.bodyRuOptions);
                listEng = fillList(R.array.bodyEngOptions);
                listEngKeys = fillList(R.array.bodyEngAnswers);
            } else if ("Лицо и его части".equalsIgnoreCase(title)) {
                listRu = fillList(R.array.faceRuOptions);
                listEng = fillList(R.array.faceEngOptions);
                listEngKeys = fillList(R.array.faceEngAnswers);
            } else if ("Временные константы".equalsIgnoreCase(title)) {
                listRu = fillList(R.array.timeRuOptions);
                listEng = fillList(R.array.timeEngOptions);
                listEngKeys = fillList(R.array.timeEngAnswers);
            } else if ("Дом : базовый уровень".equalsIgnoreCase(title)) {
                listRu = fillList(R.array.houseRuOptions);
                listEng = fillList(R.array.houseEngOptions);
                listEngKeys = fillList(R.array.houseEngAnswers);
            } else {
                Toast.makeText(this, "Произошла ошибка!", Toast.LENGTH_SHORT).show();
            }
            Log.d(TAG, "getIntentExtras: EXTRAS found withing intent : " + title);
        }

    }

    public List<String> fillList(int stringResourceId) {
        return Arrays.asList(getResources().getStringArray(stringResourceId));
    }

    public void findComponents() {
        tv_currentTest = findViewById(R.id.tv_current_test_name);
        tv_currentTopic = findViewById(R.id.tv_current_topic_name);

        iv_endTest = findViewById(R.id.iv_end_test);
        iv_restartTest = findViewById(R.id.iv_restart_test);

        tv_questWord = findViewById(R.id.tv_quest_word);
        tv_wordCounter = findViewById(R.id.tv_word_counter);

        btnOption1 = findViewById(R.id.btn_option1);
        btnOption2 = findViewById(R.id.btn_option2);
        btnOption3 = findViewById(R.id.btn_option3);
        btnOption4 = findViewById(R.id.btn_option4);

        Log.d(TAG, "Test3FourOption: findComponents: all components found!");

    }

    public void setButtons() {
        btnOption1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.d(TAG, "onClick: button1 pushed");
                checkCorrect((Button) view);
                checkReadinessForResult();
            }
        });

        btnOption2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.d(TAG, "onClick: button2 pushed");
                checkCorrect((Button) view);
                checkReadinessForResult();
            }
        });

        btnOption3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.d(TAG, "onClick: button3 pushed");
                checkCorrect((Button) view);
                checkReadinessForResult();
            }
        });

        btnOption4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.d(TAG, "onClick: button4 pushed");
                checkCorrect((Button) view);
                checkReadinessForResult();
            }
        });

        iv_restartTest.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                restartTest();
            }
        });

    }

    public void setAnimations(){
        Animation fadeInFast = AnimationUtils.loadAnimation(this, R.anim.fadein_fast);
        tv_questWord.startAnimation(fadeInFast);
    }

    public void setGame() {
        if (index == 0) {
            Collections.shuffle(listRu);
        }
        quest = listRu.get(index);
        tv_questWord.setText(quest);

//        String [] vars = plainMap.
        List<String> options = Arrays.asList(optionMap.get(quest).split("/"));

        Collections.shuffle(options);
        btnOption1.setText(options.get(0));
        btnOption2.setText(options.get(1));
        btnOption3.setText(options.get(2));
        btnOption4.setText(options.get(3));

        tv_wordCounter.setText((index + 1) + "/" + listEng.size());
        setAnimations();
    }

    public void restartTest() {
        index = 0;
        mistakes = 0;
        mistakeList.clear();
        setGame();
    }

    public Map<String, String> getMap(List<String> ru, List<String> eng) {
        Map<String, String> map = new HashMap<>();
        if (ru.size() == eng.size()) {
            for (int i = 0; i < ru.size(); i++) {
                map.put(ru.get(i), eng.get(i));
            }
        }
        Log.d(TAG, "getMap: map size: " + map.size());
        return map;
    }

    public void checkCorrect(Button btnOption) {
        String answer = btnOption.getText().toString();

        String check = plainMap.get(quest);
        Log.d(TAG, "Test3FourOption: checkCorrect: answer: " + answer + "   check: " + check + " current index: " + index);
        Log.d(TAG, "Test3FourOption: checkCorrect: answer == check ?? :" + answer.equalsIgnoreCase(check));

        if (!answer.equalsIgnoreCase(check)) {
            mistakes++;
            mistakeList.add(quest);
            Toast.makeText(this, "Ошибка! Правильный ответ: "+check, Toast.LENGTH_LONG).show();
        }
    }

    private void checkReadinessForResult() {
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
    }


}