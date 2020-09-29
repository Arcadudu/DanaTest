package ru.arcadudu.danatest.test;

import android.content.Intent;
import android.util.Log;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import ru.arcadudu.danatest.R;
import ru.arcadudu.danatest.ResultActivity;

public abstract class TestClass extends AppCompatActivity {

    protected static final String TAG = "myTag";
    protected static final String TEST_NAME = "testName";
    protected static final String TOPIC_NAME = "topicName";

    protected List<String> listRu, listEng;
    protected Map<String, String> map, plainMap;

    protected StringBuilder sbMistakes = new StringBuilder("");
    protected String topicName;
    protected String quest;
    protected int index = 0;
    protected List<String> mistakeList = new ArrayList<>();
    protected double mistakes;
    protected boolean isGoToNext = false;

    protected void getIntentExtras(Intent incomingIntent, TextView tv_currentTopic) {
        if (incomingIntent.getExtras() != null) {
            topicName = incomingIntent.getStringExtra("title");
            Log.d(TAG, "getIntentExtras: topicName found: " + topicName);
            tv_currentTopic.setText(topicName);
            assert topicName != null;
            Log.d(TAG, "Test1 getIntentExtras: EXTRAS found withing intent : " + topicName);
        }
    }

    protected void prepareContainers(int[] strResources) {
        switch (topicName) {
            case "Великобритания":
                listRu = fillList(strResources[0]);
                listEng = fillList(strResources[1]);
                break;
            case "Части тела":
                listRu = fillList(strResources[2]);
                listEng = fillList(strResources[3]);
                break;
            case "Лицо и его части":
                listRu = fillList(strResources[4]);
                listEng = fillList(strResources[5]);
                break;
            case "Временные константы":
                listRu = fillList(strResources[6]);
                listEng = fillList(strResources[7]);
                break;
            case "Дом : базовый уровень":
                listRu = fillList(strResources[8]);
                listEng = fillList(strResources[9]);
                break;
        }
        map = getMap(listRu, listEng);
    }

    protected void prepareMoreContainers(int[] strResources, List<String> keys) {
        switch (topicName) {
            case "Великобритания":
                keys = fillList(strResources[0]);
                break;
            case "Части тела":
                keys = fillList(strResources[1]);
                break;
            case "Лицо и его части":
                keys = fillList(strResources[2]);
                break;
            case "Временные константы":
                keys = fillList(strResources[3]);
                break;
            case "Дом : базовый уровень":
                keys = fillList(strResources[4]);
                break;
        }
        plainMap = getMap(listRu, keys);
    }

    protected void setGame() {
        // нужен ли этот пустой метод???
    }

//    void setGame(TextView tv_wordCount){
//        if (index == 0) Collections.shuffle(listRu);
//        quest = listRu.get(index);
//        tv_wordCount.setText((index + 1) + "/" + listEng.size());
//    }
//
//    protected void setGame(EditText et_answerField, TextView tv_wordCount) {
//        if (index == 0) Collections.shuffle(listRu);
//        quest = listRu.get(index);
//        et_answerField.setText("");
//        tv_wordCount.setText((index + 1) + "/" + listEng.size());
//    }

    protected List<String> fillList(int stringResourceId) {
        return Arrays.asList(getResources().getStringArray(stringResourceId));
    }

    protected Map<String, String> getMap(List<String> ru, List<String> eng) {
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

    protected void restartTest() {
        index = 0;
        mistakes = 0;
        mistakeList.clear();
        sbMistakes.setLength(0);
        setGame();
    }

    protected void checkReadinessForResult(TextView tv_currentTest) {
        index++;
        if (index < listRu.size()) {
            setGame();
        } else {
            if (!isGoToNext) {
                isGoToNext = true;
                Log.d(TAG, "onClick: mistakes: " + mistakes + " // mistakesList.length: " + mistakeList.size());
                Intent intent = new Intent(getApplicationContext(), ResultActivity.class);
                intent.putExtra(TEST_NAME, tv_currentTest.getText().toString());
                intent.putExtra(TOPIC_NAME, topicName);
                intent.putExtra("mistakes", String.valueOf(mistakes));
                double percentage = (mistakes / listRu.size()) * 100;
                intent.putExtra("percentage", String.valueOf(percentage));
                Log.d(TAG, "onClick: percentageInExtra " + intent.getStringExtra("percentage"));
                intent.putExtra("sbMistakes", sbMistakes.toString());
                startActivity(intent);
                finish();
            }
        }
    }

    protected void setAnimations(TextView tv_questWord) {
        Animation fadeInFast = AnimationUtils.loadAnimation(this, R.anim.fadein_fast);
        tv_questWord.startAnimation(fadeInFast);
    }


}
