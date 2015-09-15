package example.com.androidcampproject.activities;

import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;

import de.greenrobot.event.EventBus;
import example.com.androidcampproject.MyUtilities;
import example.com.androidcampproject.R;
import example.com.androidcampproject.events.UserSignedInEvent;
import example.com.androidcampproject.fragments.WebViewClientFragment;

/**
 * Created by Esmond on 16.08.2015.
 */
public class LogInActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.log_in_activity);
        EventBus.getDefault().register(this);
        overridePendingTransition(R.animator.activity_open_translate, R.animator.activity_close_scale);

        FragmentManager fmOnCreate = getFragmentManager();
        FragmentTransaction ftOnCreate = fmOnCreate.beginTransaction();
        SharedPreferences sharedPref = PreferenceManager.getDefaultSharedPreferences(this);
        String token = sharedPref.getString(MyUtilities.TOKEN_KEY, "");
        if (token.equals("")) {
            ftOnCreate.replace(R.id.logInContainer, new WebViewClientFragment(), "WebViewFragment");
            ftOnCreate.commit();
        } else {
            loadMainActivity();
        }
    }

    public void onEvent(UserSignedInEvent event) {
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }

    public void loadMainActivity() {
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }

    @Override
    public void onBackPressed() {
        FragmentManager fManager = getFragmentManager();
        if (fManager.getBackStackEntryCount() > 0) {
            fManager.popBackStack();
        } else super.onBackPressed();
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
