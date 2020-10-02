package com.example.pianoforkid.view.adaper;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.pianoforkid.R;
import com.example.pianoforkid.data.model.LikedSong;

import java.util.ArrayList;
import java.util.List;

public class LikedSongAdapter extends RecyclerView.Adapter<LikedSongAdapter.ViewHolder> {
    private List<LikedSong> listSongs;
    public LikedSongAdapter() {
        listSongs = new ArrayList<>();
    }
    private LikedSongAdapter.OnItemSongClickListener listener;

    public void setOnItemSongClickListener(LikedSongAdapter.OnItemSongClickListener listener){
        this.listener = listener;
    }
    public interface OnItemSongClickListener{
        void onItemClick(int id);
    }

    @NonNull
    @Override
    public LikedSongAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(
                parent.getContext());
        View view = inflater.inflate(R.layout.liked_song_sdapter_item, parent, false);
        return new LikedSongAdapter.ViewHolder(view);
    }
    @Override
    public void onBindViewHolder(@NonNull LikedSongAdapter.ViewHolder holder, int position) {
        LikedSong song = listSongs.get(position);
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

    public void setListSongs(List<LikedSong> listSongs) {
        this.listSongs = listSongs;
    }
}