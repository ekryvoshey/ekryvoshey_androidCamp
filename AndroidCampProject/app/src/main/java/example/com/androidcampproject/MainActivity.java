package example.com.androidcampproject;

import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;


import de.greenrobot.event.EventBus;
import example.com.androidcampproject.events.FriendClickEvent;
import example.com.androidcampproject.events.UserSignedInEvent;
import example.com.androidcampproject.fragments.AlbumListFragment;
import example.com.androidcampproject.fragments.FriendsListFragment;
import example.com.androidcampproject.fragments.WebViewClientFragment;

public class MainActivity extends AppCompatActivity {

    private static EventBus bus = EventBus.getDefault();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        bus.register(this);

        FragmentManager fmOnCreate = getFragmentManager();
        FragmentTransaction ftOnCreate = fmOnCreate.beginTransaction();

        SharedPreferences sharedPref = PreferenceManager.getDefaultSharedPreferences(this);
        String token = sharedPref.getString(MyUtilities.TOKEN_KEY, "");
        if(token.equals("")){
            ftOnCreate.replace(R.id.listFragment, new WebViewClientFragment());
            ftOnCreate.commit();
        } else {
            ftOnCreate.replace(R.id.listFragment, new FriendsListFragment());
            ftOnCreate.commit();
        }

    }

    public void onEvent(UserSignedInEvent event) {
        FragmentManager fManager = getFragmentManager();
        FragmentTransaction fTransaction = fManager.beginTransaction();
        fTransaction.replace(R.id.listFragment, new FriendsListFragment());
        fTransaction.commit();
    }

    public void onEvent(FriendClickEvent event){
        FragmentManager fManager = getFragmentManager();
        FragmentTransaction fTransaction = fManager.beginTransaction();
        fTransaction.replace(R.id.listFragment, new AlbumListFragment());
        fTransaction.commit();
    }

    @Override
    protected void onDestroy() {
        bus.unregister(this);
        super.onDestroy();
    }
}
