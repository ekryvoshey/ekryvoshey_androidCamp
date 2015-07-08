package example.com.androidcampproject;

import android.content.Intent;
import android.content.res.Configuration;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.util.Log;
import android.widget.Toast;

public class MainActivity extends ActionBarActivity {
    public static final String TOKEN_KEY = "tokenKey";
    public static final String EXPIRES_KEY = "expiresKey";
    public static final String USER_KEY = "userKey";
    WebViewer myWebView = new WebViewer();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }
    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
        setContentView(R.layout.activity_main);
    }
}
