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

import de.greenrobot.event.EventBus;
import example.com.androidcampproject.R;
import example.com.androidcampproject.events.PhotoClickEvent;
import example.com.androidcampproject.fragments.PhotoListFragment;
import example.com.androidcampproject.objects.Photo;

/**
 * Created by Esmond on 03.08.2015.
 */
public class PhotoGridAdapter extends RecyclerView.Adapter<PhotoGridAdapter.PhotoViewHolder> {

    public static List<Photo> photos = new ArrayList<>(0);
    public Context context = PhotoListFragment.photoListFragmentContext;

    public PhotoGridAdapter(List<Photo> photos) {
        this.photos = photos;
    }

    @Override
    public PhotoViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        View rootView  = LayoutInflater.from(viewGroup.getContext()).
                inflate(R.layout.photo_grid_card_view_layout, viewGroup, false);
        PhotoViewHolder pvh = new PhotoViewHolder(rootView);
        return pvh;
    }

    @Override
    public void onBindViewHolder(PhotoViewHolder holder, final int i) {
        holder.imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String src = photos.get(i).getSrc_big();
                String text = photos.get(i).getText();
                int position = photos.indexOf(photos.get(i));
                EventBus.getDefault().post(new PhotoClickEvent(src, text, position));
            }
        });
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
    public int getItemCount() {
        if (photos == null) return 0;
        return photos.size();
    }

    public static class PhotoViewHolder extends RecyclerView.ViewHolder {
        TextView textView;
        ImageView imageView;

        PhotoViewHolder(View itemView) {
            super(itemView);
            textView = (TextView) itemView.findViewById(R.id.photo_name);
            imageView = (ImageView) itemView.findViewById(R.id.photo_image);
        }
    }

    public void setData(List<Photo> photos) {
        this.photos = photos;
    }
}
