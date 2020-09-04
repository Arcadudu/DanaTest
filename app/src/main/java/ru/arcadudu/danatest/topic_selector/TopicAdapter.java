package ru.arcadudu.danatest.topic_selector;

import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import ru.arcadudu.danatest.R;

public class TopicAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private final List<Topic> topicList;

    public TopicAdapter(List<Topic> topicList) {
        this.topicList = topicList;
    }


    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new RecyclerView.ViewHolder
                (LayoutInflater.from(parent.getContext()).inflate
                        (R.layout.my_view_holder_topic_picker, parent, false)){};
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        Button btn_title = holder.itemView.findViewById(R.id.btn_topic_title);
        btn_title.setText(topicList.get(position).getTitle());
        TextView tv_preview = holder.itemView.findViewById(R.id.tv_preview);
        tv_preview.setText(topicList.get(position).getPreview());
        TextView tv_amount = holder.itemView.findViewById(R.id.amount);
        tv_amount.setText("Количество слов: " + topicList.get(position).getSize());

    }

    @Override
    public int getItemCount() {
        return topicList.size();
    }
}