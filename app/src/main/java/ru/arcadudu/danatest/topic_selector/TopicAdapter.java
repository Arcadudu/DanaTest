package ru.arcadudu.danatest.topic_selector;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import ru.arcadudu.danatest.R;

public class TopicAdapter extends RecyclerView.Adapter<TopicAdapter.MyTopicViewHolder> {

    private final List<Topic> topicList;
    Context context;

    public TopicAdapter(Context ct, List<Topic> topicList) {
        this.topicList = topicList;
        this.context = ct;
    }

    @NonNull
    @Override
    public TopicAdapter.MyTopicViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.my_view_holder_topic_picker, parent, false);
        return new MyTopicViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull TopicAdapter.MyTopicViewHolder holder, int position) {
        holder.btn_title.setText(topicList.get(position).getTitle());
        holder.tv_preview.setText(topicList.get(position).getPreview());
        holder.tv_amount.setText("Количество слов: " + topicList.get(position).getSize());
    }

    @Override
    public int getItemCount() {
        return topicList.size();
    }

    public static class MyTopicViewHolder extends RecyclerView.ViewHolder {

        Button btn_title;
        TextView tv_preview, tv_amount;

        public MyTopicViewHolder(@NonNull View itemView) {
            super(itemView);
            btn_title = itemView.findViewById(R.id.btn_topic_title);
            tv_preview = itemView.findViewById(R.id.tv_preview);
            tv_amount = itemView.findViewById(R.id.amount);
        }
    }
}
