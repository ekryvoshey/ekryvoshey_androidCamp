package example.com.androidcampproject.adapters;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AccelerateInterpolator;
import android.view.animation.DecelerateInterpolator;
import android.widget.ImageView;
import android.widget.TextView;
import android.support.v7.widget.Toolbar;

import com.bumptech.glide.Glide;

import java.util.ArrayList;
import java.util.List;

import de.greenrobot.event.EventBus;
import example.com.androidcampproject.activities.DetailsActivity;
import example.com.androidcampproject.activities.MainActivity;
import example.com.androidcampproject.MyUtilities;
import example.com.androidcampproject.events.PhotoClickEvent;
import example.com.androidcampproject.objects.Photo;
import example.com.androidcampproject.R;
import example.com.androidcampproject.fragments.PhotoListFragment;

/**
 * Created by Esmond on 03.08.2015.
 */
public class PhotoListAdapter extends RecyclerView.Adapter<PhotoListAdapter.PhotoViewHolder> {

    private List<Photo> photos = new ArrayList<>(0);
    private Toolbar toolbar = MainActivity.toolbar;

    public Context context = PhotoListFragment.photoListFragmentContext;
    public PhotoListAdapter(List<Photo> photos) {
        this.photos = photos;
    }

    @Override
    public PhotoViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).
                inflate(R.layout.photo_card_view_layout, viewGroup, false);
        PhotoViewHolder pvh = new PhotoViewHolder(view);
        return pvh;
    }

    @Override
    public void onBindViewHolder(PhotoViewHolder holder, final int i) {
        holder.imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String src = photos.get(i).getSrc_xxbig();
                String text = photos.get(i).getText();
                EventBus.getDefault().post(new PhotoClickEvent(src, text));
                if (toolbar.getTranslationY() == 0)
                    toolbarHide(toolbar, MyUtilities.TOOLBAR_ANIMATION_SPEED);
                if (toolbar.getTranslationY() != 0)
                    toolbarShow(toolbar, MyUtilities.TOOLBAR_ANIMATION_SPEED);
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

    public void toolbarHide(Toolbar toolbar, int duration) {
        toolbar.animate()
                .translationY(-toolbar.getBottom())
                .setDuration(duration)
                .setInterpolator(new AccelerateInterpolator())
                .start();
    }

    public void toolbarShow(Toolbar toolbar, int duration) {
        toolbar.animate()
                .translationY(0)
                .setDuration(duration)
                .setInterpolator(new DecelerateInterpolator())
                .start();
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
