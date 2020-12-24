package com.example.pianoforkid.adaper;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.pianoforkid.R;

import java.util.ArrayList;
import java.util.List;

public class LessonListAdapter extends RecyclerView.Adapter<LessonListAdapter.ViewHolder> {
    private List<String> listLessons;
    public LessonListAdapter() {
        listLessons = new ArrayList<>();
    }
    private OnItemClickListener listener;


    public void setOnItemLessonClickListener(OnItemClickListener listener){
        this.listener = listener;
    }
     public interface OnItemClickListener{
        void onItemLesson(String lesson);
    }
    @NonNull
    @Override
    public LessonListAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(
                parent.getContext());
        View view = inflater.inflate(R.layout.all_list_adapter_item, parent, false);
        return new LessonListAdapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull LessonListAdapter.ViewHolder holder, int position) {
        String lesson = listLessons.get(position);
        holder.tvDetail.setText(lesson);
        holder.tvDetail.setTag(lesson);
    }

    @Override
    public int getItemCount() {
        return listLessons.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView tvDetail;
        ImageView image_view_download;
        ImageView image_view_hearth;
        ViewHolder(@NonNull View itemView) {
            super(itemView);
            tvDetail = itemView.findViewById(R.id.text_view_lesson_list);

            tvDetail.setOnClickListener(v -> {
                if (listener != null) {
                    listener.onItemLesson(String.valueOf(v.getTag()));
                }
            });

        }

    }
    public void setListLessons(List<String> listLessons) {
        this.listLessons = listLessons;
        notifyDataSetChanged();
    }

}
