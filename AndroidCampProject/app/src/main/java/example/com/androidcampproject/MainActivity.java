package example.com.androidcampproject;

import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.app.usage.UsageEvents;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.webkit.WebViewFragment;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Toast;


import java.sql.SQLOutput;

import de.greenrobot.event.EventBus;

public class MainActivity extends AppCompatActivity {
    public static final String TOKEN_KEY = "tokenKey";
    public static final String EXPIRES_KEY = "expiresKey";
    public static final String USER_KEY = "userKey";

    private static EventBus bus = EventBus.getDefault();
    private static WebViewClientFragment wvcf = new WebViewClientFragment();
    private static RecyclerViewFragment rvf = new RecyclerViewFragment();

    public static String accessToken = wvcf.getAccessToken();
    public static String userId = wvcf.getUserId();
    public static String expiresIn = wvcf.getExpiresIn();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        bus.register(this);

        FragmentManager fmOnCreate = getFragmentManager();
        FragmentTransaction ftOnCreate = fmOnCreate.beginTransaction();
        if(accessToken != null){
            ftOnCreate.replace(R.id.listFragment, rvf);
            ftOnCreate.commit();
        } else if (accessToken == null) {
            ftOnCreate.replace(R.id.listFragment, wvcf);
            ftOnCreate.commit();
        }
    }

    public void onEvent(EditorEvent event) {
        FragmentManager fmEvent = getFragmentManager();
        FragmentTransaction ftEvent = fmEvent.beginTransaction();
        ftEvent.replace(R.id.listFragment, rvf);
        ftEvent.commit();
    }

    @Override
    protected void onDestroy() {
        bus.unregister(this);
        super.onDestroy();
    }
}
