package example.com.androidcampproject.activities;

import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.v4.app.Fragment;
import android.support.v4.view.MenuItemCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.DisplayMetrics;
import android.view.Menu;
import android.view.MenuItem;
import android.support.v7.widget.ShareActionProvider;
import android.view.animation.AccelerateInterpolator;
import android.view.animation.DecelerateInterpolator;
import android.widget.Toast;

import de.greenrobot.event.EventBus;
import example.com.androidcampproject.MyUtilities;
import example.com.androidcampproject.R;
import example.com.androidcampproject.events.AlbumClickEvent;
import example.com.androidcampproject.events.FriendClickEvent;
import example.com.androidcampproject.events.PhotoClickEvent;
import example.com.androidcampproject.events.UserSignedInEvent;
import example.com.androidcampproject.fragments.AlbumListFragment;
import example.com.androidcampproject.fragments.FriendsListFragment;
import example.com.androidcampproject.fragments.PhotoListFragment;
import example.com.androidcampproject.fragments.WebViewClientFragment;

public class MainActivity extends AppCompatActivity {
    private ShareActionProvider mShareActionProvider;
    private DisplayMetrics displayMetrics = new DisplayMetrics();
    public static Toolbar toolbar;
    public static String fragmentTag = "phone";

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        MenuItem shareItem = menu.findItem(R.id.action_share);
        MenuItem listView = menu.findItem(R.id.action_list_view);
        listView.setVisible(false);
        this.invalidateOptionsMenu();
        MenuItem gridView = menu.findItem(R.id.action_grid_view);
        gridView.setVisible(false);
        this.invalidateOptionsMenu();
        mShareActionProvider = (ShareActionProvider) MenuItemCompat.getActionProvider(shareItem);
        return true;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        EventBus.getDefault().register(this);
        overridePendingTransition(R.animator.activity_open_translate, R.animator.activity_close_scale);

        initToolbar();

        getWindowManager().getDefaultDisplay().getMetrics(displayMetrics);
        int width = displayMetrics.widthPixels;
        Fragment fragmentTwo = getSupportFragmentManager().findFragmentById(R.id.containerTwo);
        if (fragmentTwo == null && width > 768)
            fragmentTag = "tablet";
        if (width <= 768)
            fragmentTag = "phone";

        loadFriendsList();
    }

    public void onEvent(FriendClickEvent event) {
        FragmentManager fManager = getFragmentManager();
        FragmentTransaction fTransaction = fManager.beginTransaction();
        if (fragmentTag == "phone") {
            fTransaction.add(R.id.container, new AlbumListFragment(), "AlbumListFragment");
            fTransaction.addToBackStack("AlbumListFragment");
            fTransaction.commit();
        }
        if (fragmentTag == "tablet") {
            addAlbumListAsSecondFragment();
        }
    }

    public void onEvent(AlbumClickEvent event) {
        FragmentManager fManager = getFragmentManager();
        FragmentTransaction fTransaction = fManager.beginTransaction();
        if (fragmentTag == "phone") {
            fTransaction.add(R.id.container, new PhotoListFragment(), "PhotoListFragment");
            fTransaction.addToBackStack("PhotoListFragment");
            fTransaction.commit();
        }
        if (fragmentTag == "tablet") {
            addPhotoListAsSecondFragment();
        }
    }

    public void onEvent(PhotoClickEvent event) {
        Intent intent = new Intent(MainActivity.this, DetailsActivity.class);
        intent.putExtra("image", event.getSrc());
        intent.putExtra("text", event.getText());
        startActivity(intent);
    }

    @Override
    public void onBackPressed() {
        FragmentManager fManager = getFragmentManager();
        if (fManager.getBackStackEntryCount() > 0) {
            fManager.popBackStack();
        } else super.onBackPressed();
    }

    private void setShareIntent(Intent shareIntent) {
        if (mShareActionProvider != null) {
            mShareActionProvider.setShareIntent(shareIntent);
        }
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

    public void loadFriendsList() {
        FragmentManager fManager = getFragmentManager();
        FragmentTransaction fTransaction = fManager.beginTransaction();
        fTransaction.add(R.id.container, new FriendsListFragment(), "FriendsListFragment");
        fTransaction.addToBackStack("FriendsListFragment");
        fTransaction.commit();
    }

    public void addAlbumListAsSecondFragment() {
        FragmentManager fmSecondFragment = getFragmentManager();
        FragmentTransaction ftSecondFragment = fmSecondFragment.beginTransaction();
        if (fragmentTag == "tablet") {
            ftSecondFragment.add(R.id.containerTwo, new AlbumListFragment());
            ftSecondFragment.addToBackStack("AlbumListFragment");
            ftSecondFragment.commit();
        }
    }

    public void addPhotoListAsSecondFragment() {
        FragmentManager fmSecondFragment = getFragmentManager();
        FragmentTransaction ftSecondFragment = fmSecondFragment.beginTransaction();
        if (fragmentTag == "tablet") {
            ftSecondFragment.add(R.id.containerTwo, new PhotoListFragment());
            ftSecondFragment.addToBackStack("PhotoListFragment");
            ftSecondFragment.commit();
        }
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
