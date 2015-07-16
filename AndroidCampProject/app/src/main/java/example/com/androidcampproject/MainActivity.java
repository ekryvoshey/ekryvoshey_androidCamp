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


import de.greenrobot.event.EventBus;

public class MainActivity extends AppCompatActivity {
    public static final String TOKEN_KEY = "tokenKey";
    public static final String EXPIRES_KEY = "expiresKey";
    public static final String USER_KEY = "userKey";
    public static final String SAVED_TEXT = "savedText";

    EventBus bus = EventBus.getDefault();
    SharedPreferences sPref;
    SharedPreferences.Editor sEditor;
    String s;

    FragmentManager fm = getFragmentManager();
    FragmentTransaction ft = fm.beginTransaction();

    WebViewClientFragment wvcf = new WebViewClientFragment();
    RecyclerViewFragment rvf = new RecyclerViewFragment();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        bus.register(this);
        SharedPreferences sharedPref = getPreferences(Context.MODE_PRIVATE);
        String data = sharedPref.getString(SAVED_TEXT, null);

        if(data != null){
            ft.replace(R.id.listFragment, rvf);
            ft.commit();
        } else if (data == null) {
            ft.replace(R.id.listFragment, wvcf);
            ft.commit();
        }
    }

    public void onEvent(EditorEvent event) {
        sPref = getPreferences(MODE_PRIVATE);
        SharedPreferences.Editor ed = sPref.edit();
        ed.putString(SAVED_TEXT, event.getData());
        ed.commit();
        FragmentManager fmNew = getFragmentManager();
        FragmentTransaction ftNew = fmNew.beginTransaction();
        ftNew.replace(R.id.listFragment, rvf);
        ftNew.commit();
    }

    @Override
    protected void onDestroy() {
        bus.unregister(this);
        super.onDestroy();
    }
}
