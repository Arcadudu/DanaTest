package ru.arcadudu.danatest.topic_selector;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import ru.arcadudu.danatest.R;
import ru.arcadudu.danatest.main_selector.MainActivity;
import ru.arcadudu.danatest.test.Test1EnterWord;
import ru.arcadudu.danatest.test.Test3FourOptions;
import ru.arcadudu.danatest.test.Test4Shuffle;

public class TopicPickerActivity extends AppCompatActivity implements TopicAdapter.OnTopicListener {

    private static final String TEST_NAME = "testName";
    private static final String TAG = "tag";
    private String testName;

    TextView activityTitle;
    ImageView btn_back;
    RecyclerView recyclerView;

    ImageView ivSortTopics;

    private int sortClicks = 0;


    private List<Topic> topicList = new ArrayList<>();
    private TopicAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_topic_picker);

        findComponents();
        getPossibleExtras();
        prepareTopicList();
        setRecyclerAdapter();
        setOnClicks();
    }

    private void setRecyclerAnimation() {
        Animation fadeInFast = AnimationUtils.loadAnimation(this, R.anim.fadein_fast);
        recyclerView.startAnimation(fadeInFast);
    }

    private void setOnClicks() {
        ivSortTopics.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if (sortClicks == 0) {
                    setRecyclerAdapter();
                    Collections.sort(topicList, Topic.compareByTitle);
                    sortClicks++;
                    ivSortTopics.setImageResource(R.drawable.icon_sort_by_dark);
                } else {
                    setRecyclerAdapter();
                    Collections.sort(topicList, Topic.compareBySize);
                    sortClicks = 0;
                    ivSortTopics.setImageResource(R.drawable.icon_sort_by_name_dark);
                }
                setRecyclerAnimation();
            }
        });

        btn_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                startActivity(intent);
                finish();
            }
        });
    }

    public List<String> fillList(int stringResourceId) {
        return Arrays.asList(getResources().getStringArray(stringResourceId));
    }

    private void findComponents() {
        activityTitle = findViewById(R.id.tv_activity_title);
        btn_back = findViewById(R.id.iv_back);
        ivSortTopics = findViewById(R.id.iv_sort_topics_menu);
    }

    public void getPossibleExtras() {
        Intent incomingIntent = getIntent();
        if (incomingIntent.getExtras() != null) {
            if (incomingIntent.hasExtra(TEST_NAME)) {
                testName = incomingIntent.getStringExtra(TEST_NAME);
                activityTitle.setText(incomingIntent.getStringExtra(TEST_NAME));
            }
        }
    }

    private void setRecyclerAdapter() {
        adapter = new TopicAdapter(this, topicList, this);
        recyclerView = findViewById(R.id.recycler_view);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(adapter);
    }

    private void prepareTopicList() {
        List<String> gbRuList = new ArrayList<>();
        List<String> faceRuList = new ArrayList<>();
        List<String> bodyRuList = new ArrayList<>();
        List<String> timeRuList = new ArrayList<>();
        List<String> houseBasicRuList = new ArrayList<>();

        switch (testName) {
            case "Прямой перевод":

            case "Перемешать":
                // GB
                gbRuList = fillList(R.array.gbRuPlain);
                faceRuList = fillList(R.array.faceRuPlain);
                bodyRuList = fillList(R.array.bodyRuPlain);
                timeRuList = fillList(R.array.timeRuPlain);
                houseBasicRuList = fillList(R.array.houseBasicRuPlain);
                break;

            case "Четыре варианта":
                gbRuList = fillList(R.array.gbRuOptions);
                faceRuList = fillList(R.array.faceRuOptions);
                bodyRuList = fillList(R.array.bodyRuOptions);
                timeRuList = fillList(R.array.timeRuOptions);
                houseBasicRuList = fillList(R.array.houseRuOptions);
                break;
        }


        topicList.add(new Topic("Великобритания", gbRuList));
        topicList.add(new Topic("Лицо и его части", faceRuList));
        topicList.add(new Topic("Части тела", bodyRuList));
        topicList.add(new Topic("Временные константы", timeRuList));
        topicList.add(new Topic("Дом : базовый уровень", houseBasicRuList));
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
        } else if (testTitle.equalsIgnoreCase("Перемешать")) {
            intent = new Intent(getApplicationContext(), Test4Shuffle.class);
        }
        intent.putExtra("title", topic.getTitle());
        intent.putExtra(TEST_NAME, testTitle);
        startActivity(intent);
    }

    public List<Topic> getTopicList() {
        return topicList;
    }
}