package example.com.androidcampproject.fragments;

import android.app.Fragment;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import de.greenrobot.event.EventBus;
import example.com.androidcampproject.MyUtilities;
import example.com.androidcampproject.R;
import example.com.androidcampproject.events.UserSignedInEvent;

/**
 * Created by Esmond on 14.07.2015.
 */
public class WebViewClientFragment extends Fragment {
    static final String OAUTH_URI = "https://oauth.vk.com/authorize";
    static final String REDIRECT_URI = "https://oauth.vk.com/blank.html";
    static final String CLIENT_ID = "4980818";
    static final String RESPONSE_TYPE = "token";
    static final String SCOPE = "offline,wall,friends";
    static final String DISPLAY = "mobile";
    private String login_url = OAUTH_URI + "?redirect_uri=" + REDIRECT_URI + "&client_id=" + CLIENT_ID +
            "&response_type=" + RESPONSE_TYPE + "&scope=" + SCOPE + "&display=" + DISPLAY;
    private WebView myWebView;
    private EventBus bus = EventBus.getDefault();
    private String accessToken;
    private String userId;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.web_view_fragment_layout, container, false);
        myWebView = (WebView) view.findViewById(R.id.myWebView);
        myWebView.setWebViewClient(new WebViewClient() {
            @Override
            public boolean shouldOverrideUrlLoading(WebView view, String url) {
                if (url.startsWith(REDIRECT_URI)) {
                    Uri uri = Uri.parse(url.replace("#", "?"));
                    accessToken = uri.getQueryParameter("access_token");
                    userId = uri.getQueryParameter("user_id");

                    SharedPreferences sharedPref = PreferenceManager.getDefaultSharedPreferences(getActivity());
                    SharedPreferences.Editor editor = sharedPref.edit();

                    editor.putString(MyUtilities.TOKEN_KEY, accessToken);
                    editor.putString(MyUtilities.USER_KEY, userId);
                    editor.commit();
                    bus.post(new UserSignedInEvent());
                    return true;
                }
                return false;
            }
        });
        myWebView.loadUrl(login_url);
        return view;
    }
}
