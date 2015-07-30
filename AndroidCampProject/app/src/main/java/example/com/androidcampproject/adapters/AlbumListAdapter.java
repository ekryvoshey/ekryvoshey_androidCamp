package example.com.androidcampproject.adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.bumptech.glide.Glide;

import java.util.ArrayList;
import java.util.List;

import example.com.androidcampproject.Album;
import example.com.androidcampproject.fragments.FriendsListFragment;
import example.com.androidcampproject.R;

/**
 * Created by Esmond on 30.07.2015.
 */
public class AlbumListAdapter extends RecyclerView.Adapter<AlbumListAdapter.AlbumViewHolder>{
    Context context = FriendsListFragment.context;
    List<Album> albums = new ArrayList<>(0);
    public AlbumListAdapter(List<Album> albums){
        this.albums = albums;
    }

    @Override
    public AlbumViewHolder onCreateViewHolder(ViewGroup viewGroup, int i){
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.card_view_layout, viewGroup, false);
        AlbumViewHolder avh = new AlbumViewHolder(view);
        return avh;
    }

    public void onBindViewHolder(AlbumViewHolder holder, int i){
        Glide.with(context)
                .load(albums.get(i).getThumb_id())
                .into(holder.album);
    }

    @Override
    public void onAttachedToRecyclerView(RecyclerView recyclerView) {
        super.onAttachedToRecyclerView(recyclerView);
    }

    @Override
    public int getItemCount(){
        if(albums == null) return 0;
        return albums.size();
    }

    public void setData(List<Album> albums){
        this.albums = albums;
    }

    public static class AlbumViewHolder extends RecyclerView.ViewHolder{
        ImageView album;

        AlbumViewHolder(View itemView){
            super(itemView);
            album = (ImageView)itemView.findViewById(R.id.person_photo);
        }
    }
}
