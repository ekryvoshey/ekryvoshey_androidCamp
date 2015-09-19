package example.com.androidcampproject;

import android.content.Intent;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.ShareActionProvider;

import example.com.androidcampproject.activities.ImageViewPager;

/**
 * Created by Esmond on 15.09.2015.
 */
public class DetailOnPageChangeListener extends ViewPager.SimpleOnPageChangeListener {
    private int currentPage;
    private ShareActionProvider mShareActionProvider;
    private static Intent intent;

    @Override
    public void onPageSelected(int position) {
        intent = ImageViewPager.pagerIntent;
        currentPage = position;
        setShareIntent(intent);
    }

    public final int getCurrentPage() {
        return currentPage;
    }

    private void setShareIntent(Intent shareIntent) {
        if (mShareActionProvider != null) {
            mShareActionProvider.setShareIntent(shareIntent);
        }
    }
}

