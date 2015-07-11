package example.com.androidcampproject;

import android.app.Fragment;
import android.content.Context;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;
import android.webkit.WebViewClient;

/**
 * Created by Ascaron on 04.07.2015.
 */
public class WebViewer extends Fragment {
    WebView myWebView;
    static String OAUTH_URI = "https://oauth.vk.com/authorize";
    static String REDIRECT_URI = "https://oauth.vk.com/blank.html";
    static String CLIENT_ID = "4980818";
    static String RESPONSE_TYPE = "token";
    static String SCOPE = "offline,wall,friends";
    static String DISPLAY = "mobile";
    String login_url = OAUTH_URI + "?redirect_uri=" + REDIRECT_URI + "&client_id=" + CLIENT_ID +
            "&response_type=" + RESPONSE_TYPE + "&scope=" + SCOPE + "&display=" + DISPLAY;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.webviewfragment_layout, container, false);
        myWebView = (WebView) view.findViewById(R.id.myWebView);
        myWebView.loadUrl(login_url);

        myWebView.setWebViewClient(new WebViewClient() {
            @Override
            public boolean shouldOverrideUrlLoading(WebView view, String url) {
                if (url.startsWith(REDIRECT_URI)) {
                    //створюємо об"єкт, який буде витягувати змінні із ссилки
                    Uri uri = Uri.parse(url);
                    //це 3 змінні які ми хочемо витягнути із ссилки
                    String accessToken = uri.getQueryParameter("access_token");
                    String expiresIn = uri.getQueryParameter("expires_in");
                    String userId = uri.getQueryParameter("user_id");
                    //тут бубудть зберігатися змінні accessToken, expiresIn, userId
                    SharedPreferences sharedPref = getActivity().getPreferences(Context.MODE_PRIVATE);
                    //editor потрібен для можливості зберігати змінні в SharedPreferences
                    SharedPreferences.Editor editor = sharedPref.edit();
                    //зберігаємо змінні, для цього потрібно створити ключ-константу для кожної змінної
                    editor.putString(accessToken, MainActivity.TOKEN_KEY);
                    editor.putString(expiresIn, MainActivity.EXPIRES_KEY);
                    editor.putString(userId, MainActivity.USER_KEY);
                    editor.commit();

                    // TODO editor commit
                    // TODO post event to MainActivity with EventBus

                    return true;
                }
                return false;
            }
        });
        return view;
    }
}
