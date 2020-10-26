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

public class SongListAdapter extends RecyclerView.Adapter<SongListAdapter.ViewHolder> {
    private List<Song> listSongs;
    public SongListAdapter() {
        listSongs = new ArrayList<>();
        listSongs.add(new Song(100, "Only you"));
        listSongs.add(new Song(101, "Only you2"));
    }
    private OnItemSongClickListener listener;

    public void setOnItemSongClickListener(OnItemSongClickListener listener){
        this.listener = listener;
    }
    public interface OnItemSongClickListener{
        void onItemClick(int id);
    }

    @NonNull
    @Override
    public SongListAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(
                parent.getContext());
        View view = inflater.inflate(R.layout.song_adapter_item, parent, false);
        return new ViewHolder(view);
    }
    @Override
    public void onBindViewHolder(@NonNull SongListAdapter.ViewHolder holder, int position) {
        Song song = listSongs.get(position);
        holder.tvDetail.setText(song.toString());
        holder.tvDetail.setTag(song.songId);
    }
    @Override
    public int getItemCount() {
        return listSongs.size();
    }
    class ViewHolder extends RecyclerView.ViewHolder {
        TextView tvDetail;
        ViewHolder(@NonNull View itemView) {
            super(itemView);
            tvDetail = itemView.findViewById(R.id.tvDetail);
            tvDetail.setOnClickListener(v -> {
                if (listener != null) {
                    listener.onItemClick((int) v.getTag());
                }
            });
        }
    }

    public void setListSongs(List<Song> listSongs) {
        this.listSongs = listSongs;
    }
}