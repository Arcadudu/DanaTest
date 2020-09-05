package ru.arcadudu.danatest.topic_selector;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import ru.arcadudu.danatest.R;

public class TopicPickerActivity extends AppCompatActivity {

    TextView activityTitle;
    ImageView btn_back;
    private List<Topic> topicList = new ArrayList<>();
    private final RecyclerView.Adapter adapter = new TopicAdapter(this, topicList);

    public List<Topic> getTopicList() {
        return topicList;
    }

    public List<String> fillList(int stringResourceId) {
        return Arrays.asList(getResources().getStringArray(stringResourceId));
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_topic_picker);

        activityTitle = findViewById(R.id.tv_activity_title);

        Intent intent = getIntent();
        if (intent.hasExtra("topic_picked")) {
            String title = intent.getStringExtra("topic_picked");
            activityTitle.setText(title);
        }

        // Иконка "назад"
        btn_back = findViewById(R.id.iv_back);
        btn_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();
            }
        });


        // Великобритания
        List<String> gbRuList = fillList(R.array.gbRu);
        List<String> gbEngList = fillList(R.array.gbEng);
        // лицо и его части
        List<String> faceRuList = fillList(R.array.faceRu);
        List<String> faceEngList = fillList(R.array.faceEng);
        // части тела
        List<String> bodyRuList = fillList(R.array.bodyRu);
        List<String> bodyEngList = fillList(R.array.bodyEng);
        // временные константы
        List<String> timeRuList = fillList(R.array.timeRu);
        List<String> timeEngList = fillList(R.array.timeEng);
        // дом : базовый уровень
        List<String> houseBasicRuList = fillList(R.array.houseBasicRu);
        List<String> houseBasicEngList = fillList(R.array.houseBasicEng);


        topicList.add(new Topic("Великобритания", gbRuList, gbEngList));
        topicList.add(new Topic("Лицо и его части", faceRuList, faceEngList));
        topicList.add(new Topic("Части тела", bodyRuList, bodyEngList));
        topicList.add(new Topic("Временные константы", timeRuList, timeEngList));
        topicList.add(new Topic("Дом : базовый уровень", houseBasicRuList, houseBasicEngList));


        RecyclerView recyclerView = findViewById(R.id.recycler_view);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(adapter);


    }
}