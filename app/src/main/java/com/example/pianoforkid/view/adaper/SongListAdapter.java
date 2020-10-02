package com.example.pianoforkid.view.adaper;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
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
    }
    private OnItemSongClickListener listenerSong;
    private OnItemHeartClickListener listenerHeart;

    public void setOnItemSongClickListener(OnItemSongClickListener listener){
        this.listenerSong = listener;
    }
    public void setOnItemHeartClickListener(OnItemHeartClickListener listener){
        this.listenerHeart = listener;
    }
    public interface OnItemSongClickListener{
        void onItemClick(int id);
    }
    public interface OnItemHeartClickListener{
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
        ImageView image_heart;
        ViewHolder(@NonNull View itemView) {
            super(itemView);
            tvDetail = itemView.findViewById(R.id.tvDetail);
            image_heart = itemView.findViewById(R.id.image_heart);
            tvDetail.setOnClickListener(v -> {
                if (listenerSong != null) {
                    listenerSong.onItemClick((int) v.getTag());
                }
            });
            image_heart.setOnClickListener(v -> {
                if (listenerHeart != null) {
                    listenerHeart.onItemClick((int) v.getTag());
                }
            });
        }
    }

    public void setListSongs(List<Song> listSongs) {
        this.listSongs = listSongs;
    }

    public Song getSongById(int songId){
        for (Song song: listSongs
             ) {
            if(song.songId == songId)
                return song;
        }
        return null;
    }
}