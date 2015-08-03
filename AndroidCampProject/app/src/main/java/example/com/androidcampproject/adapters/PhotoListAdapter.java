package example.com.androidcampproject.adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import java.util.ArrayList;
import java.util.List;

import example.com.androidcampproject.Album;
import example.com.androidcampproject.Photo;
import example.com.androidcampproject.R;
import example.com.androidcampproject.fragments.PhotoListFragment;

/**
 * Created by Esmond on 03.08.2015.
 */
public class PhotoListAdapter extends RecyclerView.Adapter<PhotoListAdapter.PhotoViewHolder> {
    private List<Photo> photos = new ArrayList<>(0);
    public Context context = PhotoListFragment.photoListFragmentContext;

    public PhotoListAdapter(List<Photo> photos) {
        this.photos = photos;
    }

    @Override
    public PhotoViewHolder onCreateViewHolder(ViewGroup viewGroup, int i){
        View view = LayoutInflater.from(viewGroup.getContext()).
                inflate(R.layout.grid_item_layout, viewGroup, false);
        PhotoViewHolder pvh = new PhotoViewHolder(view);
        return pvh;
    }

    @Override
    public void onBindViewHolder(PhotoViewHolder holder, int i) {
        holder.textView.setText(photos.get(i).getText());
        Glide.with(context).
                load(photos.get(i).getSrc()).
                into(holder.imageView);
    }

    @Override
    public void onAttachedToRecyclerView(RecyclerView recyclerView) {
        super.onAttachedToRecyclerView(recyclerView);
    }

    @Override
    public int getItemCount(){
        if(photos == null) return 0;
        return photos.size();
    }

    public static class PhotoViewHolder extends RecyclerView.ViewHolder{
        TextView textView;
        ImageView imageView;

        PhotoViewHolder(View itemView){
            super(itemView);
            textView = (TextView)itemView.findViewById(R.id.gridText);
            imageView = (ImageView)itemView.findViewById(R.id.gridImage);
        }
    }

    public void setData(List<Photo> photos){
        this.photos = photos;
    }
}
