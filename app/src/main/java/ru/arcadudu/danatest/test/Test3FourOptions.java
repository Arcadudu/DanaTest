package ru.arcadudu.danatest.test;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import ru.arcadudu.danatest.R;
import ru.arcadudu.danatest.topic_selector.TopicPickerActivity;

public class Test3FourOptions extends TestClass {

    TextView tv_currentTest, tv_currentTopic;
    ImageView iv_endTest, iv_restartTest;
    TextView tv_questWord, tv_wordCount;
    Button btnOption1, btnOption2, btnOption3, btnOption4;

    private List<String> listEngKeys;

    private int[] strResources = {R.array.gbRuOptions, R.array.gbEngOptions,
            R.array.bodyRuOptions, R.array.bodyEngOptions, R.array.faceRuOptions,
            R.array.faceEngOptions, R.array.timeRuOptions, R.array.timeEngOptions,
            R.array.houseRuOptions, R.array.houseEngOptions};

    private int[] strAnswersResources = {R.array.gbEngAnswers, R.array.bodyEngAnswers,
            R.array.faceEngAnswers, R.array.timeEngAnswers, R.array.houseEngAnswers};


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test3_four_options);

        findComponents();
        getIntentExtras(getIntent(), tv_currentTopic);
        prepareContainers(strResources);
        prepareMoreContainers(strAnswersResources, listEngKeys);
        setButtons();
        setGame();
    }

    @Override
    public void setGame() {
        if (index == 0) Collections.shuffle(listRu);
        quest = listRu.get(index);
        tv_wordCount.setText((index + 1) + "/" + listEng.size());
        tv_questWord.setText(quest);
        List<String> options = Arrays.asList(map.get(quest).split("/"));

        Collections.shuffle(options);
        btnOption1.setText(options.get(0));
        btnOption2.setText(options.get(1));
        btnOption3.setText(options.get(2));
        btnOption4.setText(options.get(3));

        setAnimations(tv_questWord);
    }

    public void findComponents() {
        tv_currentTest = findViewById(R.id.tv_current_test_name);
        tv_currentTopic = findViewById(R.id.tv_current_topic_name);

        iv_endTest = findViewById(R.id.iv_end_test);
        iv_restartTest = findViewById(R.id.iv_restart_test);

        tv_questWord = findViewById(R.id.tv_quest_word);
        tv_wordCount = findViewById(R.id.tv_word_counter);

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
                checkReadinessForResult(tv_currentTest);
            }
        });

        btnOption2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.d(TAG, "onClick: button2 pushed");
                checkCorrect((Button) view);
                checkReadinessForResult(tv_currentTest);
            }
        });

        btnOption3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.d(TAG, "onClick: button3 pushed");
                checkCorrect((Button) view);
                checkReadinessForResult(tv_currentTest);
            }
        });

        btnOption4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.d(TAG, "onClick: button4 pushed");
                checkCorrect((Button) view);
                checkReadinessForResult(tv_currentTest);
            }
        });

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

    public void checkCorrect(Button btnOption) {
        String answer = btnOption.getText().toString();
        String check = plainMap.get(quest);
        Log.d(TAG, "Test3FourOption: checkCorrect: answer: " + answer + "   check: " + check + " current index: " + index);
        Log.d(TAG, "Test3FourOption: checkCorrect: answer == check ?? :" + answer.equalsIgnoreCase(check));

        if (!answer.equalsIgnoreCase(check)) {
            mistakes++;
            mistakeList.add(quest);
            sbMistakes.append("✗ ").append(answer).append("  ||  ").append("✓ ").append(check).append("*");
//            Toast.makeText(this, "Ошибка! Правильный ответ: "+check, Toast.LENGTH_SHORT).show();
        }
    }


}