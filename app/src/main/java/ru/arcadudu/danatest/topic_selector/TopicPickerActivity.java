package ru.arcadudu.danatest.topic_selector;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.PopupMenu;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import ru.arcadudu.danatest.Const;
import ru.arcadudu.danatest.R;
import ru.arcadudu.danatest.main_selector.MainActivity;
import ru.arcadudu.danatest.test.Test1EnterWord;
import ru.arcadudu.danatest.test.Test3FourOptions;
import ru.arcadudu.danatest.test.Test4Shuffle;

public class TopicPickerActivity extends AppCompatActivity implements TopicAdapter.OnTopicListener {

    private static final String TEST_NAME = "testName";
    private static final String TAG = "tag";
    private String testName;
    private int sortClicks = 0;

    TextView activityTitle;
    ImageView btn_back;
    RecyclerView recyclerView;
    ImageView ivSortTopics;

    SharedPreferences sPref;

    private List<Topic> topicList = new ArrayList<>();
    private TopicAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_topic_picker);
        sPref = getSharedPreferences(Const.spTag, Context.MODE_PRIVATE);
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

                final PopupMenu popup = new PopupMenu(getApplicationContext(), ivSortTopics);
                popup.getMenuInflater().inflate(R.menu.popup_menu, popup.getMenu());

                popup.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
                    @Override
                    public boolean onMenuItemClick(MenuItem menuItem) {
                        switch (menuItem.getItemId()){
                            case R.id.sort_by_name:
                                Collections.sort(topicList, Topic.compareByTitle);
                                adapter.notifyDataSetChanged();
                                return true;
                            case R.id.sort_by_size:
                                Collections.sort(topicList, Topic.compareBySize);
                                adapter.notifyDataSetChanged();
                                return true;
                            case R.id.sort_by_status:
                                Collections.sort(topicList, Topic.compareByStatus);
                                adapter.notifyDataSetChanged();
                                return true;
                        }
                        return false;
                    }
                });
                popup.show();
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

        topicList.clear();

        topicList.add(new Topic("Великобритания", gbRuList, sPref.getBoolean("Великобритания", false)));
        topicList.add(new Topic("Лицо и его части", faceRuList, sPref.getBoolean("Лицо и его части", false)));
        topicList.add(new Topic("Части тела", bodyRuList, sPref.getBoolean("Части тела", false)));
        Log.e("AA", "getBoolean = " + sPref.getBoolean("Части тела", false));
        topicList.add(new Topic("Временные константы", timeRuList, sPref.getBoolean("Временные константы", false)));
        topicList.add(new Topic("Дом : базовый уровень", houseBasicRuList, sPref.getBoolean("Дом : базовый уровень", false)));
    }

    @Override
    protected void onResume() {
        super.onResume();
        prepareTopicList();
        adapter.setData(topicList);
        adapter.notifyDataSetChanged();
    }

    @Override
    public void onTopicClick(int position) {
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