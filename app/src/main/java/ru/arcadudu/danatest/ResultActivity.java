package ru.arcadudu.danatest;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import ru.arcadudu.danatest.topic_selector.TopicPickerActivity;

//import android.transition.AutoTransition;
//import android.transition.TransitionManager;
//import android.transition.Visibility;

public class ResultActivity extends AppCompatActivity implements View.OnClickListener {

    private static final String TAG = "myTag";
    private static final String TEST_NAME = "testName";
    private static final String TOPIC_NAME = "topicName";


    private String titleExtra, topicExtra;
    private String sbMistakes, sbCorrects;
    private double mistakes;
    private double percentage;
    private boolean isChecked = false;


    public static final String excellent = "A";
    public static final String good = "B";


    ImageView iv_activityBackground;

    // expandable part
    ConstraintLayout expandableView;
    // CardView emptyScene;
    ImageView ivCorrectIcon, ivWrongIcon;


    TextView tv_activityTitle, tv_gradeDescription;
    TextView tv_sbMistakes, tv_sbCorrects;
    ImageView iv_innerResultIcon, iv_outerCircle;
    ImageView iv_restartTopic, iv_to_troubleShooting, iv_toTopicList;

    Animation rotateCircleAnimation, rotateToggleAnimation, fadeInAnimation, fadeInFastAnimation, fadeOutFastAnimation;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result);

        findComponents();
        getExtras();
        showMistakes(sbMistakes, sbCorrects);
        setAnimation();
        setDecoration();
    }

    private void setAnimation() {
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
        tv_sbCorrects = findViewById(R.id.tv_sb_corrects);
        iv_outerCircle = findViewById(R.id.iv_result_img_circle);
        iv_innerResultIcon = findViewById(R.id.iv_result_inner_img);
        iv_restartTopic = findViewById(R.id.iv_restart_topic);
        iv_to_troubleShooting = findViewById(R.id.iv_to_trouble_shooting);
        iv_toTopicList = findViewById(R.id.iv_to_topic_list);
        iv_activityBackground = findViewById(R.id.iv_result_background_image);
        expandableView = findViewById(R.id.expandable_constraint);
        ivCorrectIcon = findViewById(R.id.iv_correct_icon);
        ivWrongIcon = findViewById(R.id.iv_wrong_icon);
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
                if (mistakesString != null) {
                    mistakes = Double.parseDouble(mistakesString);
                }
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
            if (intent.hasExtra("sbMistakes")) {
                sbMistakes = intent.getStringExtra("sbMistakes");
            }
            if (intent.hasExtra("sbCorrects") && intent.getStringExtra("sbCorrects") != null) {
                sbCorrects = intent.getStringExtra("sbCorrects");
            }

        }
    }

    private void showMistakes(String str1, String str2) {
        tv_sbMistakes.setText(str1);
        tv_sbCorrects.setText(str2);
        ivCorrectIcon.setImageResource(R.drawable.material_icon_correct_answer);
        ivWrongIcon.setImageResource(R.drawable.material_icon_wrong_answer);
    }

    private void setDecoration() {
        Window window = getWindow();
        SharedPreferences sharedPreferences = getSharedPreferences(Const.spTag, MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();


        iv_toTopicList.setOnClickListener(this);

        Log.e("AA", "put topic = " + topicExtra);


        if (percentage == 0.0) {
            expandableView.setBackgroundColor(getColor(R.color.material_status_bar_green));
            window.setStatusBarColor(getColor(R.color.material_status_bar_green));
            iv_activityBackground.setBackgroundColor(getColor(R.color.material_status_bar_green));
            iv_restartTopic.setImageResource(R.drawable.material_icon_restart_this_topic_green);
            iv_toTopicList.setImageResource(R.drawable.material_move_to_topic_green);
            editor.putString(topicExtra, excellent);
            tv_activityTitle.setText("Тест пройден");
            tv_gradeDescription.setText("Отлично");
            iv_innerResultIcon.setImageResource(R.drawable.material_drawable_inner_result_excellent);
            iv_to_troubleShooting.setEnabled(false);
            iv_to_troubleShooting.setVisibility(View.INVISIBLE);
            ivCorrectIcon.setVisibility(View.INVISIBLE);
            ivWrongIcon.setVisibility(View.INVISIBLE);
            // < 20 %
        } else if (percentage <= 20) {
            expandableView.setBackgroundColor(getColor(R.color.material_status_bar_yellow));
            window.setStatusBarColor(getColor(R.color.material_status_bar_yellow));
            iv_activityBackground.setBackgroundColor(getColor(R.color.material_status_bar_yellow));
            iv_restartTopic.setImageResource(R.drawable.material_icon_restart_this_topic_yellow);
            iv_toTopicList.setImageResource(R.drawable.material_move_to_topic_yellow);
            iv_to_troubleShooting.setImageResource(R.drawable.material_icon_troubleshooting_yellow);
            editor.putString(topicExtra, good);
            tv_activityTitle.setText("Тест пройден");
            tv_gradeDescription.setText("Хорошо. Ошибок: " + (int) mistakes);
            iv_innerResultIcon.setImageResource(R.drawable.material_drawable_inner_result_good);
            // > 20 %
        } else if (percentage > 20.0) {
            expandableView.setBackgroundColor(getColor(R.color.material_status_bar_red));
            window.setStatusBarColor(getColor(R.color.material_status_bar_red));
            iv_activityBackground.setBackgroundColor(getColor(R.color.material_status_bar_red));
            iv_restartTopic.setImageResource(R.drawable.material_icon_restart_this_topic_red);
            iv_toTopicList.setImageResource(R.drawable.material_move_to_topic_red);
            iv_to_troubleShooting.setImageResource(R.drawable.material_icon_troubleshooting_red);
            tv_activityTitle.setText("Тест не пройден");
            tv_gradeDescription.setText("Ошибок : " + (int) mistakes);
            iv_innerResultIcon.setImageResource(R.drawable.material_drawable_inner_result_failed);
        }

        editor.apply();
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
}

