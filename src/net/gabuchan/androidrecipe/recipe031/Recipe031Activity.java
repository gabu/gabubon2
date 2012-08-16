
package net.gabuchan.androidrecipe.recipe031;

import net.gabuchan.androidrecipe.R;
import android.annotation.SuppressLint;
import android.app.Activity;
import android.os.Bundle;
import android.view.KeyEvent;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;

@SuppressLint("SetJavaScriptEnabled")
public class Recipe031Activity extends Activity {
    private WebView mWebView;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recipe_031);

        mWebView = (WebView) findViewById(R.id.web_view);
        mWebView.loadUrl("https://www.google.co.jp/");
        // JavaScriptを有効にする
        WebSettings settings = mWebView.getSettings();
        settings.setJavaScriptEnabled(true);
        // リンク先もWebView内に表示させる
        mWebView.setWebViewClient(new WebViewClient());
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        // バックキーで、かつWebView的に戻るページがある場合
        if ((keyCode == KeyEvent.KEYCODE_BACK) && mWebView.canGoBack()) {
            mWebView.goBack();
            return true;
        }
        // それ以外はシステムの挙動に任せる
        return super.onKeyDown(keyCode, event);
    }
}
