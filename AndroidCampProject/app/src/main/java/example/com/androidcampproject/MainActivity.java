package example.com.androidcampproject;

import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.v4.app.Fragment;
import android.support.v4.view.MenuItemCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.support.v7.widget.ShareActionProvider;
import android.view.animation.AccelerateInterpolator;
import android.view.animation.DecelerateInterpolator;

import de.greenrobot.event.EventBus;
import example.com.androidcampproject.events.AlbumClickEvent;
import example.com.androidcampproject.events.FriendClickEvent;
import example.com.androidcampproject.events.UserSignedInEvent;
import example.com.androidcampproject.fragments.AlbumListFragment;
import example.com.androidcampproject.fragments.FriendsListFragment;
import example.com.androidcampproject.fragments.PhotoListFragment;
import example.com.androidcampproject.fragments.WebViewClientFragment;

public class MainActivity extends AppCompatActivity {
    private ShareActionProvider mShareActionProvider;
    public static Toolbar toolbar;
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        MenuItem shareItem = menu.findItem(R.id.menu_item_share);
        mShareActionProvider = (ShareActionProvider)MenuItemCompat.getActionProvider(shareItem);
        return true;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        EventBus.getDefault().register(this);

        FragmentManager fmOnCreate = getFragmentManager();
        FragmentTransaction ftOnCreate = fmOnCreate.beginTransaction();
        initToolbar();

        SharedPreferences sharedPref = PreferenceManager.getDefaultSharedPreferences(this);
        String token = sharedPref.getString(MyUtilities.TOKEN_KEY, "");
        if(token.equals("")){
            ftOnCreate.replace(R.id.container, new WebViewClientFragment());
            ftOnCreate.commit();
        } else {
            ftOnCreate.replace(R.id.container, new FriendsListFragment());
            ftOnCreate.commit();
        }
//        ftOnCreate.replace(R.id.listFragment, new AlbumListFragment());
    }

    public void onEvent(UserSignedInEvent event) {
        FragmentManager fManager = getFragmentManager();
        FragmentTransaction fTransaction = fManager.beginTransaction();
        fTransaction.add(R.id.container, new FriendsListFragment(), "FriendsListFragment");
        fTransaction.addToBackStack("FriendsListFragment");
        fTransaction.commit();
    }

    public void onEvent(FriendClickEvent event) {
        FragmentManager fManager = getFragmentManager();
        FragmentTransaction fTransaction = fManager.beginTransaction();
        fTransaction.add(R.id.container, new AlbumListFragment(), "AlbumListFragment");
        fTransaction.addToBackStack("AlbumListFragment");
        fTransaction.commit();
    }

    public void onEvent(AlbumClickEvent event) {
        FragmentManager fManager = getFragmentManager();
        FragmentTransaction fTransaction = fManager.beginTransaction();
        fTransaction.add(R.id.container, new PhotoListFragment(), "PhotoListFragment");
        fTransaction.addToBackStack("PhotoListFragment");
        fTransaction.commit();
    }

    @Override
    public void onBackPressed(){
        FragmentManager fManager = getFragmentManager();
        if(fManager.getBackStackEntryCount()>0) {
            fManager.popBackStack();
            toolbarShow(toolbar, MyUtilities.TOOLBAR_ANIMATION_SPEED);
        }
        else super.onBackPressed();
    }

    //Use this method to share items
    private void setShareIntent(Intent shareIntent) {
        if (mShareActionProvider != null) {
            mShareActionProvider.setShareIntent(shareIntent);
        }
    }

    public void initToolbar(){
        toolbar = (Toolbar) findViewById(R.id.toolBar);
        setSupportActionBar(toolbar);
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
    protected void onDestroy() {
        EventBus.getDefault().unregister(this);
        super.onDestroy();
    }
}
