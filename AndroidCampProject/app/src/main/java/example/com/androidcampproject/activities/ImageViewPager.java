package example.com.androidcampproject.activities;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.widget.ImageView;

import com.bumptech.glide.Glide;

import java.util.ArrayList;
import java.util.List;

import example.com.androidcampproject.R;
import example.com.androidcampproject.adapters.ImagePagerAdapter;
import example.com.androidcampproject.adapters.PhotoGridAdapter;
import example.com.androidcampproject.objects.Photo;

/**
 * Created by Esmond on 13.09.2015.
 */
public class ImageViewPager extends Activity {
    private int position;

    @Override
    public void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);

        setTitle("ViewPager");
        setContentView(R.layout.view_pager);
        position = getIntent().getIntExtra("position", 0);

        List<Photo> photos = PhotoGridAdapter.photos;
        ImagePagerAdapter pagerAdapter = new ImagePagerAdapter(photos);
        
        ViewPager viewPager = (ViewPager)findViewById(R.id.pager);
        viewPager.setAdapter(pagerAdapter);
        viewPager.setCurrentItem(position);
    }
}
