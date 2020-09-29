package ru.arcadudu.danatest.topic_selector;

import android.content.Context;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import ru.arcadudu.danatest.R;

//------------------------------------------Adapter-------------------------------------------------
public class TopicAdapter extends RecyclerView.Adapter<TopicAdapter.MyTopicViewHolder> {

    private List<Topic> topicList;
    Context context;
    private OnTopicListener listener;

    public TopicAdapter(Context ct, List<Topic> topicList, OnTopicListener listener) {
        this.topicList = topicList;
        this.context = ct;
        this.listener = listener;
    }

    public void setData(List<Topic> topicList1){
        this.topicList = topicList1;
    }

    @NonNull
    @Override
    public TopicAdapter.MyTopicViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.my_view_holder_topic_picker, parent, false);
        return new MyTopicViewHolder(view, listener);
    }

    @Override
    public void onBindViewHolder(@NonNull TopicAdapter.MyTopicViewHolder holder, int position) {
        Log.d("AA", "onBindViewHolder: isPassed: " + topicList.get(position).isTestPassed());
        if (topicList.get(position).isTestPassed()) {
            holder.btn_title.setBackgroundColor(context.getResources().getColor(R.color.red_incorrect, null));
        } else {
            holder.btn_title.setBackgroundColor(Color.TRANSPARENT);
        }
        holder.btn_title.setText(topicList.get(position).getTitle());
        holder.tv_preview.setText(topicList.get(position).getPreview());
        holder.tv_amount.setText("Количество слов: " + topicList.get(position).getSize());

    }

    @Override
    public int getItemCount() {
        return topicList.size();
    }


    //--------------------------------------ViewHolder----------------------------------------------
    public static class MyTopicViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        // переменные
        private Button btn_title;
        private TextView tv_preview, tv_amount;
        OnTopicListener listener;

        // конструктор
        public MyTopicViewHolder(@NonNull View itemView, OnTopicListener listener) {
            super(itemView);
            btn_title = itemView.findViewById(R.id.btn_topic_title);
            tv_preview = itemView.findViewById(R.id.tv_preview);
            tv_amount = itemView.findViewById(R.id.amount);
            this.listener = listener;
            btn_title.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {
            listener.onTopicClick(getAdapterPosition());
        }
    }

    //--------------------------------OnTopicListener Interface-------------------------------------
    public interface OnTopicListener {
        void onTopicClick(int position);
    }
}
