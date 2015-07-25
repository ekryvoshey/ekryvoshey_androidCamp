package example.com.androidcampproject;

import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;


import de.greenrobot.event.EventBus;

public class MainActivity extends AppCompatActivity {
    public static final String TOKEN_KEY = "tokenKey";
    public static final String USER_KEY = "userKey";

    private static EventBus bus = EventBus.getDefault();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        bus.register(this);

        FragmentManager fmOnCreate = getFragmentManager();
        FragmentTransaction ftOnCreate = fmOnCreate.beginTransaction();

        SharedPreferences sharedPref = PreferenceManager.getDefaultSharedPreferences(this);
        String token = sharedPref.getString(TOKEN_KEY, "");
        if(token.equals("")){
            ftOnCreate.replace(R.id.listFragment, new WebViewClientFragment());
            ftOnCreate.commit();
        } else {
            ftOnCreate.replace(R.id.listFragment, new FriendsListFragment());
            ftOnCreate.commit();
        }

    }

    public void onEvent(UserSignedInEvent event) {
        FragmentManager fmEvent = getFragmentManager();
        FragmentTransaction ftEvent = fmEvent.beginTransaction();
        ftEvent.replace(R.id.listFragment, new FriendsListFragment());
        ftEvent.commit();
    }

    @Override
    protected void onDestroy() {
        bus.unregister(this);
        super.onDestroy();
    }
}
