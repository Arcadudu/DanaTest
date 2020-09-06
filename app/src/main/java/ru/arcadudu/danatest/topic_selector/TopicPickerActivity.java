package ru.arcadudu.danatest.topic_selector;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
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
import ru.arcadudu.danatest.test1.Test1EnterWord;

public class TopicPickerActivity extends AppCompatActivity implements TopicAdapter.OnTopicListener {

    private static final String TAG = "tag";
    TextView activityTitle;
    ImageView btn_back;
    private List<Topic> topicList = new ArrayList<>();
    private RecyclerView.Adapter adapter;

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

        prepareTopicList();
        setRecyclerAdapter();


    }

    private void setRecyclerAdapter() {
        adapter = new TopicAdapter(this, topicList, this);
        RecyclerView recyclerView = findViewById(R.id.recycler_view);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(adapter);
    }

    private void prepareTopicList() {
        // GB
        List<String> gbRuList = fillList(R.array.gbRu);
        List<String> gbEngList = fillList(R.array.gbEng);
        // face
        List<String> faceRuList = fillList(R.array.faceRu);
        List<String> faceEngList = fillList(R.array.faceEng);
        // body
        List<String> bodyRuList = fillList(R.array.bodyRu);
        List<String> bodyEngList = fillList(R.array.bodyEng);
        // time
        List<String> timeRuList = fillList(R.array.timeRu);
        List<String> timeEngList = fillList(R.array.timeEng);
        // house
        List<String> houseBasicRuList = fillList(R.array.houseBasicRu);
        List<String> houseBasicEngList = fillList(R.array.houseBasicEng);

        topicList.add(new Topic("Великобритания", gbRuList, gbEngList));
        topicList.add(new Topic("Лицо и его части", faceRuList, faceEngList));
        topicList.add(new Topic("Части тела", bodyRuList, bodyEngList));
        topicList.add(new Topic("Временные константы", timeRuList, timeEngList));
        topicList.add(new Topic("Дом : базовый уровень", houseBasicRuList, houseBasicEngList));
    }

    @Override
    public void onTopicClick(int position) {
        Log.d(TAG, "onTopicClick: clicked on"+topicList.get(position).getTitle());
        Intent intent = new Intent(this, Test1EnterWord.class);
        intent.putExtra("title", topicList.get(position).getTitle());
        startActivity(intent);
    }
}