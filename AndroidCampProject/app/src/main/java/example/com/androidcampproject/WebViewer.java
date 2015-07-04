package example.com.androidcampproject;

import android.app.Fragment;
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
    final static String loginUrl = "https://www.google.com.ua/";
    String myUrl;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater,
    ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.webviewfragment_layout, container, false);
        myWebView = (WebView) view.findViewById(R.id.myWebView);

        myWebView.getSettings().setJavaScriptEnabled(true);
        myWebView.setWebViewClient(new WebViewClient());
        myWebView.loadUrl(loginUrl);

        return view;
    }
}
