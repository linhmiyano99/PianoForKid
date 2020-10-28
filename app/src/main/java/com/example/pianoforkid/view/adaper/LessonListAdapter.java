package com.example.pianoforkid.view.adaper;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.pianoforkid.R;
    import com.example.pianoforkid.data.model.Lesson;
import com.example.pianoforkid.data.model.Song;

import java.util.ArrayList;
import java.util.List;

public class LessonListAdapter extends RecyclerView.Adapter<LessonListAdapter.ViewHolder> {
    private List<Song> listLessons;
    public LessonListAdapter() {
        listLessons = new ArrayList<>();/*
        listLessons.add(new Lesson(1, "Lesson 1: Do Re Mi"));
        listLessons.add(new Lesson(2, "Lesson 2: Left Hand"));
        listLessons.add(new Lesson(3, "Lesson 3: Major Chord"));
        listLessons.add(new Lesson(4, "Lesson 4: Minor Chord"));
        listLessons.add(new Lesson(5, "Lesson 5: More practice"));*/
    }
    private LessonListAdapter.OnItemLessonClickListener listener;

    public void setOnItemLessonClickListener(LessonListAdapter.OnItemLessonClickListener listener){
        this.listener = listener;
    }

    public interface OnItemLessonClickListener{
        void onItemClick(int id);
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
        Song lesson = listLessons.get(position);
        holder.tvDetail.setText(lesson.toString());
    }

    @Override
    public int getItemCount() {
        return listLessons.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView tvDetail;
        ViewHolder(@NonNull View itemView) {
            super(itemView);
            tvDetail = itemView.findViewById(R.id.text_view_lesson_list);
            tvDetail.setOnClickListener(v -> {
                if (listener != null) {
                    listener.onItemClick((int) v.getTag());
                }
            });
        }

    }
    public void setListSongs(List<Song> listSongs) {
        this.listLessons = listSongs;
        notifyDataSetChanged();
    }
}
