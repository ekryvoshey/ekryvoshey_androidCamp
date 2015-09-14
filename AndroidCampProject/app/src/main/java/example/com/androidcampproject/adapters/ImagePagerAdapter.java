package example.com.androidcampproject.adapters;

import android.content.Context;
import android.support.v4.view.PagerAdapter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.bumptech.glide.Glide;

import java.util.List;

import example.com.androidcampproject.R;
import example.com.androidcampproject.objects.Photo;

/**
 * Created by Esmond on 13.09.2015.
 */
public class ImagePagerAdapter extends PagerAdapter {

    private List<Photo> photos;

    public ImagePagerAdapter(List<Photo> photos) {
        this.photos = photos;
    }

    @Override
    public Object instantiateItem(ViewGroup container, int position) {
        LayoutInflater li = (LayoutInflater) container.getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        ImageView imageView = (ImageView) li.inflate(R.layout.view_pager_image, null);
        Glide.with(container.getContext()).load(photos.get(position).getSrc_big()).into(imageView);
        container.addView(imageView);
        return imageView;
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
    }

    @Override
    public int getCount() {
        return photos.size();
    }

    @Override
    public boolean isViewFromObject(View view, Object o) {
        return view == o;
    }
}
