package example.com.androidcampproject.adapters;

import android.content.Context;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import java.util.ArrayList;
import java.util.List;

import de.greenrobot.event.EventBus;
import example.com.androidcampproject.R;
import example.com.androidcampproject.events.AlbumClickEvent;
import example.com.androidcampproject.fragments.AlbumGridFragment;
import example.com.androidcampproject.fragments.AlbumListFragment;
import example.com.androidcampproject.objects.Album;

/**
 * Created by Esmond on 22.08.2015.
 */
public class AlbumListAdapter extends RecyclerView.Adapter<AlbumListAdapter.AlbumViewHolder>{
    private List<Album> albums = new ArrayList<>(0);
    public Context context = AlbumListFragment.albumListFragmentContext;
    public AlbumListAdapter(List<Album> albums){
        this.albums = albums;
    }

    @Override
    public AlbumViewHolder onCreateViewHolder(ViewGroup viewGroup, int i){
        View view = LayoutInflater.from(viewGroup.getContext()).
                inflate(R.layout.albums_list_view_layout, viewGroup, false);
        AlbumViewHolder avh = new AlbumViewHolder(view);
        return avh;
    }

    @Override
    public void onBindViewHolder(AlbumViewHolder holder, int i){
        final long ownerId = albums.get(i).getOwner_id();
        final long albumId = albums.get(i).getAid();
        holder.cardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                EventBus.getDefault().post(new AlbumClickEvent(ownerId, albumId));
            }
        });
        holder.textView.setText(albums.get(i).getTitle());
        Glide.with(context)
                .load(albums.get(i).getThumb_src())
                .into(holder.imageView);
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

    public static class AlbumViewHolder extends RecyclerView.ViewHolder{
        CardView cardView;
        TextView textView;
        ImageView imageView;

        AlbumViewHolder(View itemView){
            super(itemView);
            cardView = (CardView)itemView.findViewById(R.id.albums_list_view);
            textView = (TextView)itemView.findViewById(R.id.album_name);
            imageView = (ImageView)itemView.findViewById(R.id.album_image);
        }
    }

    public void setData(List<Album> albums){
        this.albums = albums;
    }
}
