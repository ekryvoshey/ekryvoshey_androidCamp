package example.com.androidcampproject.activities;

import android.app.FragmentManager;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.view.MenuItemCompat;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.ShareActionProvider;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;

import java.util.List;

import example.com.androidcampproject.DepthPageTransformer;
import example.com.androidcampproject.DetailOnPageChangeListener;
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
    private ViewPager viewPager;
    private ShareActionProvider mShareActionProvider;
    private static List<Photo> photos;
    public static Intent pagerIntent;

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        MenuItem shareItem = menu.findItem(R.id.action_share);
        mShareActionProvider = (ShareActionProvider) MenuItemCompat.getActionProvider(shareItem);
        shareItem.setVisible(true);
        this.invalidateOptionsMenu();
        return true;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.view_pager);

        position = getIntent().getIntExtra("position", 0);
        photos = PhotoGridAdapter.photos;
        pagerIntent = this.getIntent();

        ImagePagerAdapter pagerAdapter = new ImagePagerAdapter(photos);
        viewPager = (ViewPager) findViewById(R.id.pager);
        viewPager.setAdapter(pagerAdapter);
        viewPager.setCurrentItem(position);
        viewPager.setPageTransformer(true, new DepthPageTransformer());
        viewPager.setOnPageChangeListener(new DetailOnPageChangeListener());

        initToolbar();
    }

    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.action_share:
                String currentImageUrl = photos.get(viewPager.getCurrentItem()).getSrc_big();
                Intent shareIntent = new Intent();
                shareIntent.setAction(Intent.ACTION_SEND);
                shareIntent.putExtra(Intent.EXTRA_STREAM, currentImageUrl);
                shareIntent.setType("image/jpeg");
                startActivity(shareIntent);
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

    @Override
    public void onBackPressed() {
        FragmentManager fManager = getFragmentManager();
        if (fManager.getBackStackEntryCount() > 0) {
            fManager.popBackStack();
        } else super.onBackPressed();
    }

    private void setShareIntent(Intent shareIntent) {
        shareIntent = pagerIntent;
        if (mShareActionProvider != null) {
            mShareActionProvider.setShareIntent(shareIntent);
        }
    }
}
