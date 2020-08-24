package ru.arcadudu.danatest;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

public class TopicPickerActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ConstraintLayout constraintLayout = findViewById(R.id.constraint_layout);
        constraintLayout.setBackground(getDrawable(R.drawable.background_main_screen));

        TextView activity_title = findViewById(R.id.tv_activity_title);
        activity_title.setText(getResources().getString(R.string.topic_picker_title));


        ImageView backIcon = findViewById(R.id.iv_exit_app);
        backIcon.setImageResource(R.drawable.icon_back_white);

        backIcon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();
            }
        });
    }
}