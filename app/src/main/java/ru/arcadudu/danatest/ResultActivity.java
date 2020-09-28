package ru.arcadudu.danatest;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.ToggleButton;

import androidx.appcompat.app.AppCompatActivity;

import ru.arcadudu.danatest.topic_selector.TopicPickerActivity;

public class ResultActivity extends AppCompatActivity implements View.OnClickListener, CompoundButton.OnCheckedChangeListener {

    private static final String TAG = "myTag";
    private static final String TEST_NAME = "testName";
    private static final String TOPIC_NAME = "topicName";


    private String titleExtra, topicExtra;
    private String sbMistakes;
    private double mistakes;
    private double percentage;
    private boolean isChecked = false;

    TextView tv_activityTitle, tv_gradeDescription;
    TextView tv_actionTitle1, tv_actionTitle2, tv_actionTitle3;
    TextView tv_sbMistakes;
    ImageView iv_innerResultIcon, iv_outerCircle;
    ToggleButton iv_showMistakes;
    ImageView iv_show_mistakes2;
//    MyToggle iv_showMistakes;
    ImageView iv_restartTopic, iv_nextOrCorrect, iv_toTopicList;

    Animation rotateCircleAnimation, rotateToggleAnimation, fadeInAnimation, fadeInFastAnimation, fadeOutFastAnimation;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result);

        findComponents();
        getExtras();
        setAnimation();
        setDecoration();
    }


    private void setAnimation(){
        rotateCircleAnimation = AnimationUtils.loadAnimation(this, R.anim.rotate);
        fadeInAnimation = AnimationUtils.loadAnimation(this, R.anim.fadein);
        fadeInFastAnimation = AnimationUtils.loadAnimation(this, R.anim.fadein_fast);
        fadeOutFastAnimation = AnimationUtils.loadAnimation(this, R.anim.fadeout_fast);
        rotateToggleAnimation = AnimationUtils.loadAnimation(this, R.anim.rotate_toggle);
    }

    private void findComponents() {
        tv_activityTitle = findViewById(R.id.tv_test_passed_title);
        tv_gradeDescription = findViewById(R.id.tv_result_grade_description);
        tv_sbMistakes = findViewById(R.id.tv_sb_mistakes);
        iv_outerCircle = findViewById(R.id.iv_result_img_circle);
        iv_innerResultIcon = findViewById(R.id.iv_result_inner_img);
        iv_show_mistakes2 = findViewById(R.id.iv_show_mistakes2);
        iv_restartTopic = findViewById(R.id.iv_restart_topic);
        iv_nextOrCorrect = findViewById(R.id.iv_next_topic_or_correct_mistakes);
        iv_toTopicList = findViewById(R.id.iv_to_topic_list);
        tv_actionTitle1 = findViewById(R.id.tv_action_title1);
        tv_actionTitle2 = findViewById(R.id.tv_action_title2);
        tv_actionTitle3 = findViewById(R.id.tv_action_title3);
    }

    private void getExtras() {

        Intent intent = getIntent();

        if (intent.getExtras() != null) {

            // название теста
            if (intent.hasExtra(TEST_NAME)) {
                titleExtra = intent.getStringExtra(TEST_NAME);
            }
            // название темы
            if (intent.hasExtra(TOPIC_NAME)) {
                topicExtra = intent.getStringExtra(TOPIC_NAME);
            }
            // количество ошибок
            if (intent.hasExtra("mistakes")) {
                String mistakesString = intent.getStringExtra("mistakes");
                assert mistakesString != null;
                mistakes = Double.parseDouble(mistakesString);
                Log.d(TAG, "getDoubleExtras: mistakesString: " + mistakesString + "    mistakesDouble:" + mistakes);
            }
            // процент ошибок
            if (intent.hasExtra("percentage")) {
                String percentageString = intent.getStringExtra("percentage");
                assert percentageString != null;
                percentage = Double.parseDouble(percentageString);
                Log.d(TAG, "getDoubleExtras: percentageString: " + percentageString + "    percentage:" + percentage);
            }

            // список ошибок для отображения
            if(intent.hasExtra("sbMistakes")){
                sbMistakes = intent.getStringExtra("sbMistakes");
            }

        }
    }

    private void setDecoration() {
        iv_restartTopic.setImageResource(R.drawable.icon_restart_dark1);
        iv_toTopicList.setImageResource(R.drawable.icon_to_topics_dark1);
        tv_actionTitle1.setText(R.string.result_action_again_title);
        tv_actionTitle3.setText(R.string.result_action_to_topics_title);



        iv_show_mistakes2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                isChecked = !isChecked;
                checkChanged();
            }
        });
        iv_toTopicList.setOnClickListener(this);

        //no mistakes
        if (percentage == 0.0) {
            tv_activityTitle.setText("Тест пройден");
            tv_gradeDescription.setText("Отлично");
            iv_showMistakes.setEnabled(false);
            iv_showMistakes.setVisibility(View.INVISIBLE);
            iv_innerResultIcon.setImageResource(R.drawable.icon_result_perfect);
            iv_nextOrCorrect.setImageResource(R.drawable.icon_forward_dark);
            tv_actionTitle2.setText(getResources().getString(R.string.result_action_forward_title));
            // < 20 %
        } else if (percentage <= 20) {
            tv_activityTitle.setText("Тест пройден");
            tv_gradeDescription.setText("Хорошо. Ошибок: " + (int) mistakes);
            iv_innerResultIcon.setImageResource(R.drawable.icon_result_good_dark);
            iv_nextOrCorrect.setImageResource(R.drawable.icon_troubleshooting_dark);
            tv_actionTitle2.setText(getResources().getString(R.string.result_action_troubleshooting_title));
            // > 20 %
        } else if (percentage > 20.0) {
            tv_activityTitle.setText("Тест не пройден");
            tv_gradeDescription.setText("Ошибок : " + (int) mistakes);
            iv_innerResultIcon.setImageResource(R.drawable.icon_result_bad_dark);
            iv_nextOrCorrect.setImageResource(R.drawable.icon_troubleshooting_dark);
            tv_actionTitle2.setText(getResources().getString(R.string.result_action_troubleshooting_title));
        }

        //animations
        iv_outerCircle.startAnimation(rotateCircleAnimation);
        iv_innerResultIcon.startAnimation(fadeInAnimation);
    }

    @Override
    public void onClick(View view) {
        Intent intent;
        switch (view.getId()) {
            case R.id.iv_exit_app:
                onDestroy();
            case R.id.iv_to_topic_list:
                intent = new Intent(getApplicationContext(), TopicPickerActivity.class);
                intent.putExtra(TEST_NAME, titleExtra);
                startActivity(intent);
                finish();
                break;

        }
    }

    @Override
    public void onCheckedChanged(CompoundButton compoundButton, boolean isChecked) {
        if(isChecked){
            tv_sbMistakes.setText(sbMistakes.replace("*", "\n").trim());
            tv_sbMistakes.startAnimation(fadeInFastAnimation);
            iv_showMistakes.startAnimation(fadeInFastAnimation);
            iv_showMistakes.setBackgroundResource(R.drawable.icon_eye_passive_dark);

            // добавить анимацию
        }else{
            tv_sbMistakes.setText("");
            iv_showMistakes.startAnimation(rotateToggleAnimation);
            iv_showMistakes.setBackgroundResource(R.drawable.icon_eye_active_dark);

        }
    }

    private void checkChanged(){
        if(isChecked){

            tv_sbMistakes.setText(sbMistakes.replace("*", "\n").trim());
            tv_sbMistakes.startAnimation(fadeInFastAnimation);
            iv_show_mistakes2.startAnimation(fadeInFastAnimation);
            iv_show_mistakes2.setImageDrawable(getResources().getDrawable(R.drawable.icon_eye_passive_dark, null));

            // добавить анимацию
        }else{
            tv_sbMistakes.setText("");
            iv_show_mistakes2.startAnimation(rotateToggleAnimation);
            iv_show_mistakes2.setImageDrawable(getResources().getDrawable(R.drawable.icon_eye_active_dark, null));
        }
    }
}