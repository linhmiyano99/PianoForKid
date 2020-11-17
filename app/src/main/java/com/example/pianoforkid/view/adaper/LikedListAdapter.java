package com.example.pianoforkid.view.adaper;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.pianoforkid.R;
import com.example.pianoforkid.data.model.LikedSong;

import java.util.ArrayList;
import java.util.List;

public class LikedListAdapter extends RecyclerView.Adapter<LikedListAdapter.ViewHolder>{
    private List<LikedSong> listSongs;
    public LikedListAdapter() {
        listSongs = new ArrayList<>();
    }
    private OnItemSongClickListener listener;

    public void setOnItemSongClickListener(OnItemSongClickListener listener){
        this.listener = listener;
    }
    public interface OnItemSongClickListener{
        void onItemClick(int id);
        void onHeartClick(int id);
    }
    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(
                parent.getContext());
        View view = inflater.inflate(R.layout.saved_adapter_item, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        LikedSong song = listSongs.get(position);
        holder.tvDetail.setText(song.songName);
        holder.tvDetail.setTag(song.songId);
        holder.image_view_hearth.setTag(song.songId);
    }


    @Override
    public int getItemCount() {
        return listSongs.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView tvDetail;
        ImageView image_view_hearth;
        ViewHolder(@NonNull View itemView) {
            super(itemView);
            tvDetail = itemView.findViewById(R.id.text_view_song);
            image_view_hearth = itemView.findViewById(R.id.image_view_hearth);

            tvDetail.setOnClickListener(v -> {
                if (listener != null) {
                    listener.onItemClick((int) v.getTag());
                }
            });

            image_view_hearth.setOnClickListener(v->{
                if(listener != null){
                    listener.onHeartClick((int)v.getTag());
                }
            });
        }
    }
    public void setListSongs(List<LikedSong> listSongs) {
        this.listSongs = listSongs;
        notifyDataSetChanged();
    }
}
