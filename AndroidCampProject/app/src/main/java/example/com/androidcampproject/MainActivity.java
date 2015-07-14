package example.com.androidcampproject;

import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.webkit.WebViewFragment;
import android.widget.LinearLayout;


import de.greenrobot.event.EventBus;

public class MainActivity extends AppCompatActivity {
    public static final String TOKEN_KEY = "tokenKey";
    public static final String EXPIRES_KEY = "expiresKey";
    public static final String USER_KEY = "userKey";

    EventBus bus = EventBus.getDefault();
    FragmentManager fm = getFragmentManager();
    FragmentTransaction ft = fm.beginTransaction();
    WebViewClientFragment wvcf = new WebViewClientFragment();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        bus.register(this);

        ft.replace(R.id.listFragment, wvcf);
        ft.commit();

        // TODO check if token is sated in preferences
        // TODO Use FragmentManager to set fragments (WebView or friends list - depends if we have token)
    }

    public void onEvent(EditorEvent event){
        event.getData();
    }

    @Override
    protected void onDestroy() {
        // Unregister
        bus.unregister(this);
        super.onDestroy();
    }

    // TODO handle eventbus event and replace WebView Fragment with Friends list fragment
}
