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

public class SavedListAdapter extends RecyclerView.Adapter<SavedListAdapter.ViewHolder>{
    private List<Song> listSongs;
    public SavedListAdapter() {
        listSongs = new ArrayList<>();
    }
    private OnItemSongClickListener listener;

    public void setOnItemLikeClickListener(OnItemSongClickListener listener){
        this.listener = listener;
    }
    public interface OnItemSongClickListener{
        void onItemClick(int id);
        void onHeartClick(int id);
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
                if (listener != null) {
                    listener.onHeartClick((int) v.getTag());
                }
            });
        }
    }
    public void setListSongs(List<Song> listSongs) {
        this.listSongs = listSongs;
        notifyDataSetChanged();
    }

    public Song getSong(int songId){
        for (Song song: listSongs
        ) {
            if(song.songId == songId)
                return song;
        }
        return null;
    }
}
