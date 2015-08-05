package example.com.androidcampproject;

import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import de.greenrobot.event.EventBus;
import example.com.androidcampproject.events.AlbumClickEvent;
import example.com.androidcampproject.events.FriendClickEvent;
import example.com.androidcampproject.events.UserSignedInEvent;
import example.com.androidcampproject.fragments.AlbumListFragment;
import example.com.androidcampproject.fragments.FriendsListFragment;
import example.com.androidcampproject.fragments.PhotoListFragment;
import example.com.androidcampproject.fragments.WebViewClientFragment;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        EventBus.getDefault().register(this);

        FragmentManager fmOnCreate = getFragmentManager();
        FragmentTransaction ftOnCreate = fmOnCreate.beginTransaction();

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
        }
        else super.onBackPressed();
    }

    @Override
    protected void onDestroy() {
        EventBus.getDefault().unregister(this);
        super.onDestroy();
    }
}
