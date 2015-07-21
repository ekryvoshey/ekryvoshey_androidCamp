package example.com.androidcampproject;

import android.app.Fragment;
import android.content.Context;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import de.greenrobot.event.EventBus;

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
    private EditorEvent event = new EditorEvent();

    private String accessToken;
    private String expiresIn;
    private String userId;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.webviewfragment_layout, container, false);
        myWebView = (WebView) view.findViewById(R.id.myWebView);

        myWebView.setWebViewClient(new WebViewClient() {
            @Override
            public boolean shouldOverrideUrlLoading(WebView view, String url) {
                if (url.startsWith(REDIRECT_URI)) {
                    Uri uri = Uri.parse(url.replace("#", "?"));
                    accessToken = uri.getQueryParameter("access_token");
                    expiresIn = uri.getQueryParameter("expires_in");
                    userId = uri.getQueryParameter("user_id");

                    SharedPreferences sharedPref = getActivity().getPreferences(Context.MODE_PRIVATE);
                    SharedPreferences.Editor editor = sharedPref.edit();

                    editor.putString(MainActivity.TOKEN_KEY, accessToken);
                    editor.putString(MainActivity.EXPIRES_KEY, expiresIn);
                    editor.putString(MainActivity.USER_KEY, userId);
                    editor.commit();
                    bus.post(event);
                    return true;
                }
                return false;
            }
        });
        myWebView.loadUrl(login_url);
        return view;
    }
    public String getAccessToken() { return accessToken; }
    public String getUserId() { return userId; }
    public String getExpiresIn() { return expiresIn; }
}
