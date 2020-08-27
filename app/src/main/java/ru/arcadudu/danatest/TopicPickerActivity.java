package ru.arcadudu.danatest;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;


import java.util.List;

public class TopicPickerActivity extends AppCompatActivity {

    RecyclerView rv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_topic_picker);

        rv = findViewById(R.id.rv_main);
        TestList.init();
        rv.setAdapter(new MyAdapter(TestList.list, this));
        Log.e("error", "test list size: "+ TestList.list.size());

    }
}

// класс адаптера для управления входными данными и отображением
class MyAdapter extends RecyclerView.Adapter<MyViewHolder> {

    private List<Topic> dataTopics;
    private Context context;

    public MyAdapter(List<Topic> dataTopics, Context context) {
        this.dataTopics = dataTopics;
        this.context = context;
    }

    // метод на создание элементов вьюХолдера
    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(this.context).inflate(R.layout.item_topic, parent, false);
        Log.e("error", "created view Holder");
        return new MyViewHolder(view);
    }

    // метод для привязки данных в конктетную вьюшку
    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        holder.bind(dataTopics.get(position));
        Log.e("error", "bind method");
    }

    // счетчик элементов
    @Override
    public int getItemCount() {
        return dataTopics.size();
    }
}

// создали отделельный класс для работы с вьюХолдерами
// для хранения ссылки на элементы интерфейса каждого отдельного холдера
class MyViewHolder extends RecyclerView.ViewHolder {
    Button btn;
    TextView tv;

    public MyViewHolder(@NonNull View itemView) {
        super(itemView);
        btn = itemView.findViewById(R.id.btn_topic);
        tv = itemView.findViewById(R.id.tv_description);
    }

    public void bind(Topic topic) {
        btn.setText(topic.getTitle());
        tv.setText(topic.getDescription());
        Log.e("error", "binding");
    }
}


