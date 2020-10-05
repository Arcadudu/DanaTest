package ru.arcadudu.danatest;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
//import android.transition.AutoTransition;
//import android.transition.TransitionManager;
//import android.transition.Visibility;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.constraintlayout.widget.ConstraintLayout;

import ru.arcadudu.danatest.topic_selector.TopicPickerActivity;

public class ResultActivity extends AppCompatActivity implements View.OnClickListener {

    private static final String TAG = "myTag";
    private static final String TEST_NAME = "testName";
    private static final String TOPIC_NAME = "topicName";


    private String titleExtra, topicExtra;
    private String sbMistakes;
    private double mistakes;
    private double percentage;
    private boolean isChecked = false;


    public static final String excellent = "A";
    public static final String good = "B";


    ImageView iv_activityBackground;

    // expandable part
    ConstraintLayout expandableView;
    CardView emptyScene;
    ImageView ivCorrectIcon, ivWrongIcon;


    TextView tv_activityTitle, tv_gradeDescription;
    //    TextView tv_actionTitle1, tv_actionTitle2, tv_actionTitle3;
    TextView tv_sbMistakes;
    ImageView iv_innerResultIcon, iv_outerCircle;
//    ImageView iv_show_mistakes_eye_icon;
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
        iv_outerCircle = findViewById(R.id.iv_result_img_circle);
        iv_innerResultIcon = findViewById(R.id.iv_result_inner_img);
//        iv_show_mistakes_eye_icon = findViewById(R.id.iv_show_mistakes2);
        iv_restartTopic = findViewById(R.id.iv_restart_topic);
        iv_nextOrCorrect = findViewById(R.id.iv_next_topic_or_correct_mistakes);
        iv_toTopicList = findViewById(R.id.iv_to_topic_list);
//        tv_actionTitle1 = findViewById(R.id.tv_action_title1);
//        tv_actionTitle2 = findViewById(R.id.tv_action_title2);
//        tv_actionTitle3 = findViewById(R.id.tv_action_title3);
        iv_activityBackground = findViewById(R.id.iv_result_background_image);

        expandableView = findViewById(R.id.expandable_constraint);
//        emptyScene = findViewById(R.id.cardview_empty_scene);
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

        }
    }

    private void setDecoration() {
        Window window = getWindow();
        iv_restartTopic.setImageResource(R.drawable.material_icon_restart_this_topic);
        iv_toTopicList.setImageResource(R.drawable.material_move_to_topic);
//        tv_actionTitle1.setText(R.string.result_action_again_title);
//        tv_actionTitle3.setText(R.string.result_action_to_topics_title);
        SharedPreferences sharedPreferences = getSharedPreferences(Const.spTag, MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();


//        iv_show_mistakes_eye_icon.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                if(expandableView.getVisibility()== View.GONE){
//                    TransitionManager.beginDelayedTransition(emptyScene, new AutoTransition());
//                    expandableView.setVisibility(View.VISIBLE);
//                    tv_sbMistakes.setText(sbMistakes.replace("*", "\n").trim());
//                    tv_sbMistakes.startAnimation(fadeInFastAnimation);
//                    ivCorrectIcon.setImageResource(R.drawable.material_icon_correct_answer);
//                    ivWrongIcon.setImageResource(R.drawable.material_icon_wrong_answer);
//                    iv_show_mistakes_eye_icon.setImageDrawable(getResources().getDrawable(R.drawable.material_icon_show_mistakes_passive, null));
//                }else{
//                    TransitionManager.beginDelayedTransition(emptyScene, new AutoTransition());
//                    expandableView.setVisibility(View.INVISIBLE);
//                    tv_sbMistakes.setText("");
//                    iv_show_mistakes_eye_icon.setImageDrawable(getResources().getDrawable(R.drawable.material_icon_show_mistakes_active, null));
//                }
////                isChecked = !isChecked;
////                checkChanged();
//            }
//        });


        iv_toTopicList.setOnClickListener(this);

        Log.e("AA", "put topic = " + topicExtra);


        if (percentage == 0.0) {
            expandableView.setBackgroundColor(getColor(R.color.material_status_bar_green));
            window.setStatusBarColor(getColor(R.color.material_status_bar_green));
            iv_activityBackground.setImageResource(R.drawable.material_result_green_background);
            editor.putString(topicExtra, excellent);
            tv_activityTitle.setText("Тест пройден");
            tv_gradeDescription.setText("Отлично");
//            iv_show_mistakes_eye_icon.setEnabled(false);
//            iv_show_mistakes_eye_icon.setVisibility(View.INVISIBLE);
            iv_innerResultIcon.setImageResource(R.drawable.material_drawable_inner_result_excellent);
            iv_nextOrCorrect.setEnabled(false);
            iv_nextOrCorrect.setVisibility(View.INVISIBLE);
//            tv_actionTitle2.setText(getResources().getString(R.string.result_action_forward_title));
            // < 20 %
        } else if (percentage <= 20) {
            expandableView.setBackgroundColor(getColor(R.color.material_status_bar_yellow));
            window.setStatusBarColor(getColor(R.color.material_status_bar_yellow));
            iv_activityBackground.setImageResource(R.drawable.material_result_yellow_background);
            editor.putString(topicExtra, good);
            tv_activityTitle.setText("Тест пройден");
            tv_gradeDescription.setText("Хорошо. Ошибок: " + (int) mistakes);
            iv_innerResultIcon.setImageResource(R.drawable.material_drawable_inner_result_good);
            iv_nextOrCorrect.setImageResource(R.drawable.material_icon_troubleshooting);
//            tv_actionTitle2.setText(getResources().getString(R.string.result_action_troubleshooting_title));
            // > 20 %
        } else if (percentage > 20.0) {
            expandableView.setBackgroundColor(getColor(R.color.material_status_bar_red));
            window.setStatusBarColor(getColor(R.color.material_status_bar_red));
            iv_activityBackground.setImageResource(R.drawable.material_result_red_background);
            tv_activityTitle.setText("Тест не пройден");
            tv_gradeDescription.setText("Ошибок : " + (int) mistakes);
            iv_innerResultIcon.setImageResource(R.drawable.material_drawable_inner_result_failed);
            iv_nextOrCorrect.setImageResource(R.drawable.material_icon_troubleshooting);
//            tv_actionTitle2.setText(getResources().getString(R.string.result_action_troubleshooting_title));
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

//    private void checkChanged() {
//            // открыть ошибки
//        if (isChecked) {
//            TransitionManager.beginDelayedTransition(emptyScene, new AutoTransition());
//            expandableView.setVisibility(View.VISIBLE);
//            tv_sbMistakes.setText(sbMistakes.replace("*", "\n").trim());
//            tv_sbMistakes.startAnimation(fadeInFastAnimation);
//            iv_show_mistakes_eye_icon.setImageDrawable(getResources().getDrawable(R.drawable.material_icon_show_mistakes_passive, null));
//            // закрыть ошибки
//        } else {
//            TransitionManager.beginDelayedTransition(emptyScene, new AutoTransition());
//            expandableView.setVisibility(View.INVISIBLE);
//            tv_sbMistakes.setText("");
//            iv_show_mistakes_eye_icon.setImageDrawable(getResources().getDrawable(R.drawable.material_icon_show_mistakes_active, null));
//        }
//    }
}