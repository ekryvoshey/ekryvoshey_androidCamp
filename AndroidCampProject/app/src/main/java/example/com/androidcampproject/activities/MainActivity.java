package example.com.androidcampproject.activities;

import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.DisplayMetrics;
import android.view.animation.AccelerateInterpolator;
import android.view.animation.DecelerateInterpolator;

import de.greenrobot.event.EventBus;
import example.com.androidcampproject.R;
import example.com.androidcampproject.events.AlbumClickEvent;
import example.com.androidcampproject.events.FriendClickEvent;
import example.com.androidcampproject.events.PhotoClickEvent;
import example.com.androidcampproject.fragments.AlbumGridFragment;
import example.com.androidcampproject.fragments.FriendsListFragment;
import example.com.androidcampproject.fragments.PhotoListFragment;

public class MainActivity extends AppCompatActivity {

    private DisplayMetrics displayMetrics = new DisplayMetrics();
    private boolean isTablet;
    private Toolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        EventBus.getDefault().register(this);
        overridePendingTransition(R.animator.activity_open_translate, R.animator.activity_close_scale);

        getWindowManager().getDefaultDisplay().getMetrics(displayMetrics);
        int width = displayMetrics.widthPixels;
        Fragment fragmentTwo = getSupportFragmentManager().findFragmentById(R.id.containerTwo);
        isTablet = (fragmentTwo == null && width > 768);

        loadFriendsList();
        initToolbar();
    }

    public void onEvent(FriendClickEvent event) {
        albumGridFragmentChange();
    }

    public void onEvent(AlbumClickEvent event) {
        photoGridFragmentChange();
    }

    public void onEvent(PhotoClickEvent event) {
        loadPagerActivity(event);
    }

    public void loadFriendsList() {
        FragmentManager fManager = getFragmentManager();
        FragmentTransaction fTransaction = fManager.beginTransaction();
        fTransaction.add(R.id.container, new FriendsListFragment(), "FriendsListFragment");
        fTransaction.addToBackStack("FriendsListFragment");
        fTransaction.commit();
    }

    public void albumGridFragmentChange() {
        FragmentManager fManager = getFragmentManager();
        FragmentTransaction fTransaction = fManager.beginTransaction();
        if (isTablet) {
            fTransaction.add(R.id.containerTwo, new AlbumGridFragment(), "AlbumGridFragment");
            fTransaction.addToBackStack("AlbumGridFragment");
            fTransaction.commit();
        } else {
            fTransaction.add(R.id.container, new AlbumGridFragment(), "AlbumGridFragment");
            fTransaction.addToBackStack("AlbumGridFragment");
            fTransaction.commit();
        }
    }

    public void photoGridFragmentChange() {
        FragmentManager fManager = getFragmentManager();
        FragmentTransaction fTransaction = fManager.beginTransaction();
        if (isTablet) {
            fTransaction.add(R.id.containerTwo, new PhotoListFragment(), "PhotoListFragment");
            fTransaction.addToBackStack("PhotoListFragment");
            fTransaction.commit();
        } else {
            fTransaction.add(R.id.container, new PhotoListFragment(), "PhotoListFragment");
            fTransaction.addToBackStack("PhotoListFragment");
            fTransaction.commit();
        }
    }

    public void loadPagerActivity(PhotoClickEvent event) {
        Intent intent = new Intent(MainActivity.this, ImageViewPager.class);
        intent.putExtra("image", event.getSrc());
        intent.putExtra("text", event.getText());
        intent.putExtra("position", event.getPosition());
        startActivity(intent);
    }

    @Override
    public void onBackPressed() {
        FragmentManager fManager = getFragmentManager();
        if (fManager.getBackStackEntryCount() > 0) {
            fManager.popBackStack();
        } else super.onBackPressed();
    }

    public void initToolbar() {
        toolbar = (Toolbar) findViewById(R.id.toolBar);
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

    public void toolbarShow(Toolbar toolbar, int duration) {
        toolbar.animate()
                .translationY(0)
                .setDuration(duration)
                .setInterpolator(new DecelerateInterpolator())
                .start();
    }

    @Override
    protected void onPause() {
        super.onPause();
        overridePendingTransition(R.animator.activity_open_scale, R.animator.activity_close_translate);
    }

    @Override
    protected void onDestroy() {
        EventBus.getDefault().unregister(this);
        super.onDestroy();
    }
}
