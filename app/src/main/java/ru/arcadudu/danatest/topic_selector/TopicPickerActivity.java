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
import ru.arcadudu.danatest.test.test1_enter_word.Test1EnterWord;
import ru.arcadudu.danatest.test.test3_four_options.Test3FourOptions;

public class TopicPickerActivity extends AppCompatActivity implements TopicAdapter.OnTopicListener {

    private static final String TEST_NAME = "testName";
    private static final String TAG = "tag";

    TextView activityTitle;
    ImageView btn_back;
    RecyclerView recyclerView;


    private List<Topic> topicList = new ArrayList<>();
    private RecyclerView.Adapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_topic_picker);

        findComponents();
        btn_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();
            }
        });
        getPossibleExtras();
        prepareTopicList();
        setRecyclerAdapter();


    }

    public List<Topic> getTopicList() {
        return topicList;
    }

    public List<String> fillList(int stringResourceId) {
        return Arrays.asList(getResources().getStringArray(stringResourceId));
    }

    private void findComponents() {
        activityTitle = findViewById(R.id.tv_activity_title);
        btn_back = findViewById(R.id.iv_back);
    }

    public void getPossibleExtras() {
        Intent incomingIntent = getIntent();
        if (incomingIntent.getExtras() != null) {
            if (incomingIntent.hasExtra(TEST_NAME)) {

                activityTitle.setText(incomingIntent.getStringExtra(TEST_NAME));
            }
        }
//        if (incomingIntent.hasExtra("topic_picked")) {
//            String title = incomingIntent.getStringExtra("topic_picked");
//            activityTitle.setText(title);
//        }
    }

    private void setRecyclerAdapter() {
        adapter = new TopicAdapter(this, topicList, this);
        recyclerView = findViewById(R.id.recycler_view);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(adapter);
    }

    private void prepareTopicList() {
        // GB
        List<String> gbRuList = fillList(R.array.gbRuPlain);
        List<String> gbEngList = fillList(R.array.gbEngPlain);
        // face
        List<String> faceRuList = fillList(R.array.faceRuPlain);
        List<String> faceEngList = fillList(R.array.faceEngPlain);
        // body
        List<String> bodyRuList = fillList(R.array.bodyRuPlain);
        List<String> bodyEngList = fillList(R.array.bodyEngPlain);
        // time
        List<String> timeRuList = fillList(R.array.timeRuPlain);
        List<String> timeEngList = fillList(R.array.timeEngPlain);
        // house
        List<String> houseBasicRuList = fillList(R.array.houseBasicRuPlain);
        List<String> houseBasicEngList = fillList(R.array.houseBasicEngPlain);

        topicList.add(new Topic("Великобритания", gbRuList, gbEngList));
        topicList.add(new Topic("Лицо и его части", faceRuList, faceEngList));
        topicList.add(new Topic("Части тела", bodyRuList, bodyEngList));
        topicList.add(new Topic("Временные константы", timeRuList, timeEngList));
        topicList.add(new Topic("Дом : базовый уровень", houseBasicRuList, houseBasicEngList));
    }

    @Override
    public void onTopicClick(int position) {
        // на данный момент предусмотрена логика только для перехода в первый тест "перевод слова"
        // в дальнейшем разбить слушатель для разный тестов

        Intent intent = new Intent(this, Test1EnterWord.class);
        Topic topic = topicList.get(position);

        String testTitle = getIntent().getStringExtra(TEST_NAME);
        assert testTitle != null;
        if (testTitle.equalsIgnoreCase("Прямой перевод")) {
            intent = new Intent(getApplicationContext(), Test1EnterWord.class);
        } else if (testTitle.equalsIgnoreCase("Четыре варианта")) {
            intent = new Intent(getApplicationContext(), Test3FourOptions.class);
        }
        intent.putExtra("title", topic.getTitle());
        intent.putExtra(TEST_NAME, testTitle);
        startActivity(intent);
    }
}