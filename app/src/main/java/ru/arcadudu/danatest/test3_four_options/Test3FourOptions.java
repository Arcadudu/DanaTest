package ru.arcadudu.danatest.test3_four_options;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;
import java.util.Map;

import ru.arcadudu.danatest.R;

public class Test3FourOptions extends AppCompatActivity {

    TextView tv_currentTest, tv_currentTopic;
    ImageView iv_endTest, iv_restartTest;
    TextView tv_questWord;
    Button btnOption1, btnOption2, btnOption3, btnOption4;

    private List<String> listRu, listEng;
    private Map<String, String> map;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test3_four_options);
    }
}