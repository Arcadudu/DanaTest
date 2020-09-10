package ru.arcadudu.danatest.test;

import android.content.Intent;
import android.util.Log;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import ru.arcadudu.danatest.R;

public class Test extends AppCompatActivity {

    private static final String TAG = "myTag";
    private static final String TEST_NAME = "testName";
    private static final String TOPIC_NAME = "topicName";

    private String quest;
    private int index = 0;
    private List<String> mistakeList = new ArrayList<>();
    private double mistakes;

    List<String> listRu, listEng;
    Map<String, String> map;

    public Test(List<String> ru, List<String> eng, Map<String, String> map) {
        this.listRu = ru;
        this.listEng = eng;
        this.map = map;
    }

    public void setGame(TextView tv_questWord, TextView tv_wordAmount, boolean hasEditTextAnswer, TextView et_answer) {
        quest = listRu.get(index);
        tv_questWord.setText(quest);
        tv_wordAmount.setText((index + 1) + "/" + listRu.size());
        if (hasEditTextAnswer) {
            et_answer.setText("");
        }
    }

    public Intent getIntentExtras() {
        Intent incomingIntent = getIntent();
        if (incomingIntent.getExtras() != null) {
            String title = incomingIntent.getStringExtra("title");
            assert title != null;
            if ("Великобритания".equalsIgnoreCase(title)) {
                listRu = fillList(R.array.gbRu);
                listEng = fillList(R.array.gbEng);
            } else if ("Части тела".equalsIgnoreCase(title)) {
                listRu = fillList(R.array.bodyRu);
                listEng = fillList(R.array.bodyEng);
            } else if ("Лицо и его части".equalsIgnoreCase(title)) {
                listRu = fillList(R.array.faceRu);
                listEng = fillList(R.array.faceEng);
            } else if ("Временные константы".equalsIgnoreCase(title)) {
                listRu = fillList(R.array.timeRu);
                listEng = fillList(R.array.timeEng);
            } else if ("Дом : базовый уровень".equalsIgnoreCase(title)) {
                listRu = fillList(R.array.houseBasicRu);
                listEng = fillList(R.array.houseBasicEng);
            } else {
                Toast.makeText(this, "Произошла ошибка!", Toast.LENGTH_SHORT).show();
            }
            Log.d(TAG, "getIntentExtras: EXTRAS found withing intent : " + title);
        }
        Log.d(TAG, "getIntentExtras: results: listRu length -" + listRu.size() + "  listEng length -" + listEng.size());
        return incomingIntent;

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


}
