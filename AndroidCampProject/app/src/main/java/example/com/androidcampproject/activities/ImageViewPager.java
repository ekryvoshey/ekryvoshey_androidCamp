package example.com.androidcampproject.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.view.MenuItemCompat;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.ShareActionProvider;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.view.animation.AccelerateInterpolator;
import android.view.animation.DecelerateInterpolator;
import android.widget.ActionMenuView;
import android.widget.Toast;

import java.util.List;

import example.com.androidcampproject.DepthPageTransformer;
import example.com.androidcampproject.MyUtilities;
import example.com.androidcampproject.R;
import example.com.androidcampproject.adapters.ImagePagerAdapter;
import example.com.androidcampproject.adapters.PhotoGridAdapter;
import example.com.androidcampproject.objects.Photo;

/**
 * Created by Esmond on 13.09.2015.
 */
public class ImageViewPager extends AppCompatActivity {
    private int position;
    private Toolbar toolbar;
    private ShareActionProvider mShareActionProvider;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.view_pager);

        position = getIntent().getIntExtra("position", 0);

        List<Photo> photos = PhotoGridAdapter.photos;
        ImagePagerAdapter pagerAdapter = new ImagePagerAdapter(photos);
        ViewPager viewPager = (ViewPager) findViewById(R.id.pager);
        viewPager.setAdapter(pagerAdapter);
        viewPager.setCurrentItem(position);
        viewPager.setPageTransformer(true, new DepthPageTransformer());
        initToolbar();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        MenuItem shareItem = menu.findItem(R.id.action_share);
        mShareActionProvider = (ShareActionProvider) MenuItemCompat.getActionProvider(shareItem);
        shareItem.setVisible(true);
        this.invalidateOptionsMenu();
        return true;
    }

    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.action_share:
                Toast.makeText(getApplicationContext(), "Share clicked", Toast.LENGTH_SHORT).show();
                break;
            default:
                break;
        }
        return false;
    }

    public void initToolbar() {
        toolbar = (Toolbar) findViewById(R.id.toolBar2);
        setSupportActionBar(toolbar);
        this.getSupportActionBar().setDisplayShowTitleEnabled(false);
        setTitle(getString(R.string.appName));
        toolbar.setTitleTextColor(getResources().getColor(android.R.color.white));
    }

    public void toolbarHide(Toolbar toolbar, int duration) {
        toolbar.animate()
                .translationY(-toolbar.getBottom())
                .setDuration(duration)
                .setInterpolator(new AccelerateInterpolator())
                .start();
    }

    private void setShareIntent(Intent shareIntent) {
        if (mShareActionProvider != null) {
            mShareActionProvider.setShareIntent(shareIntent);
        }
    }
}
