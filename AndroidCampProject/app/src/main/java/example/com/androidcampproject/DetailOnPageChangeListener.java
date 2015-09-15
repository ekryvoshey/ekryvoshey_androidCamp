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

    @Override
    public void onPageSelected(int position) {
        currentPage = position;
    }

    public final int getCurrentPage() {
        return currentPage;
    }
}

