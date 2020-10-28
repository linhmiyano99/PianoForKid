package com.example.pianoforkid.view.adaper;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.pianoforkid.R;
import com.example.pianoforkid.data.model.Song;

import java.util.ArrayList;
import java.util.List;

public class SavedListAdapter extends RecyclerView.Adapter<SavedListAdapter.ViewHolder>{
    private List<Song> listSongs;
    public SavedListAdapter() {
        listSongs = new ArrayList<>();
   /*     listSongs.add(new Song(1, "Merrily We Roll Along"));
        listSongs.add(new Song(2, "Ode To Joy"));
        listSongs.add(new Song(3, "Twinkle Twinkle Little Star"));
        listSongs.add(new Song(4, "Left Hand Warm-Up"));*/
    }
    private SavedListAdapter.OnItemSongClickListener listener;

    public void setOnItemSongClickListener(SavedListAdapter.OnItemSongClickListener listener){
        this.listener = listener;
    }
    public interface OnItemSongClickListener{
        void onItemClick(int id);
    }
    @NonNull
    @Override
    public SavedListAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(
                parent.getContext());
        View view = inflater.inflate(R.layout.saved_adapter_item, parent, false);
        return new SavedListAdapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull SavedListAdapter.ViewHolder holder, int position) {
        Song song = listSongs.get(position);
        holder.tvDetail.setText(song.toString());
        holder.tvDetail.setTag(song.songId);
    }

    @Override
    public int getItemCount() {
        return listSongs.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView tvDetail;
        ViewHolder(@NonNull View itemView) {
            super(itemView);
            tvDetail = itemView.findViewById(R.id.text_view_song);
            tvDetail.setOnClickListener(v -> {
                if (listener != null) {
                    listener.onItemClick((int) v.getTag());
                }
            });
        }
    }
    public void setListSongs(List<Song> listSongs) {
        this.listSongs = listSongs;
        notifyDataSetChanged();
    }
}
