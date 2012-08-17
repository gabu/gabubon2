
package net.gabuchan.androidrecipe.recipe114;

import java.lang.ref.WeakReference;

import net.gabuchan.androidrecipe.R;
import twitter4j.AsyncTwitter;
import twitter4j.AsyncTwitterFactory;
import twitter4j.Status;
import twitter4j.TwitterAdapter;
import twitter4j.TwitterException;
import twitter4j.TwitterListener;
import twitter4j.TwitterMethod;
import twitter4j.auth.AccessToken;
import twitter4j.auth.RequestToken;
import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.graphics.Color;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class Recipe114Activity extends Activity {
    // 以下のConsumer Key/Secretはサンプル用です。
    // ご自身のアプリでそのまま使わないようにご理解とご協力のほど宜しくお願いいたします。
    private static final String OAUTH_CONSUMER_KEY = "VEsW2cljn8yfPTubxwmiw";
    private static final String OAUTH_CONSUMER_SECRET = "g0eNKD2Y5lDtrDkYI0GI4MvKcHt5g0ViMxDfOQU6vA";
    private static final String CALLBACK_URL = "gabu://oauth";
    private static final String TOKEN = "token";
    private static final String TOKEN_SECRET = "token_secret";

    private static final int MSG_ON_EXCEPTION = 0;
    private static final int MSG_UPDATED_STATUS = 1;

    private AsyncTwitter mTwitter;
    private RequestToken mRequestToken;
    private ProgressDialog mProgressDialog;
    private EditText mEditText;
    private TextView mTextView;
    private Button mButton;

    private Recipe114Handler mHandler;

    private static class Recipe114Handler extends Handler {
        private final WeakReference<Recipe114Activity> mActivity;

        public Recipe114Handler(Recipe114Activity activity) {
            mActivity = new WeakReference<Recipe114Activity>(activity);
        }

        @Override
        public void handleMessage(Message msg) {
            Recipe114Activity activity = mActivity.get();
            if (activity == null)
                return;

            switch (msg.what) {
                case MSG_UPDATED_STATUS:
                    activity.showToast("つぶやき完了！");
                    // EditTextを空にする
                    activity.mEditText.setText("");
                    break;
                case MSG_ON_EXCEPTION:
                    activity.showToast("失敗しました。。。");
                    break;
            }
            // 共通でプログレスダイアログは閉じる。
            activity.dismissProgressDialog();
        }
    }

    // Twitterリスナー
    private TwitterListener mListener = new TwitterAdapter() {
        @Override
        public void updatedStatus(Status statuses) {
            // つぶやきが完了すると呼び出されます
            mHandler.sendEmptyMessage(MSG_UPDATED_STATUS);
        }

        @Override
        public void onException(TwitterException ex, TwitterMethod method) {
            // 何かエラーが発生する（TwitterExceptionがthrowされる）と呼び出されます
            mHandler.sendEmptyMessage(MSG_ON_EXCEPTION);
            ex.printStackTrace();
        }
    };

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recipe_114);

        mEditText = (EditText) findViewById(R.id.status);
        mTextView = (TextView) findViewById(R.id.count);
        mButton = (Button) findViewById(R.id.tweet);
        // 文字数をカウントしてボタンを制御する
        mEditText.addTextChangedListener(new TextWatcher() {
            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                int textColor;
                int length = 140 - s.length();
                // 140文字をオーバーした時は文字数を赤色に
                if (length < 0) {
                    textColor = Color.RED;
                } else {
                    textColor = Color.BLACK;
                }
                mTextView.setTextColor(textColor);
                mTextView.setText(String.valueOf(length));
                // 文字数が0文字または140文字以上の時はボタンを無効
                if (s.length() == 0 || s.length() > 140) {
                    mButton.setEnabled(false);
                } else {
                    mButton.setEnabled(true);
                }
            }

            @Override
            public void afterTextChanged(Editable s) {
            }

            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }
        });

        mHandler = new Recipe114Handler(this);

        if (hasAccessToken()) {
            // アクセストークンがあればTwitterインスタンスを作って
            mTwitter = createTwitterInstance();
            // ツイート画面を表示
            setUpTweetPage();
        } else {
            // アクセストークンがなければ認証画面を表示
            setUpAuthPage();
        }
    }

    private AsyncTwitter createTwitterInstance() {
        // Twitterインスタンス作る
        AsyncTwitter twitter = new AsyncTwitterFactory().getInstance();
        twitter.setOAuthConsumer(OAUTH_CONSUMER_KEY, OAUTH_CONSUMER_SECRET);
        twitter.setOAuthAccessToken(loadAccessToken());
        twitter.addListener(mListener);
        return twitter;
    }

    private void setUpAuthPage() {
        // 認証画面を表示に
        findViewById(R.id.auth_page).setVisibility(View.VISIBLE);
        // ツイート画面を非表示に
        findViewById(R.id.tweet_page).setVisibility(View.GONE);
    }

    private void setUpTweetPage() {
        // 認証画面を非表示に
        findViewById(R.id.auth_page).setVisibility(View.GONE);
        // ツイート画面を表示に
        findViewById(R.id.tweet_page).setVisibility(View.VISIBLE);
    }

    public void onTweetClick(View view) {
        // プログレスダイアログを表示して
        showProgressDialog("つぶやき中...");
        // つぶやく！
        mTwitter.updateStatus(mEditText.getText().toString());
    }

    public void onAuthClick(View view) {
        // リクエストトークンを取得するタスクを実行
        new GetRequestTokenTask().execute();
    }

    @Override
    protected void onNewIntent(Intent intent) {
        super.onNewIntent(intent);

        // ブラウザから返ってくる
        Uri uri = intent.getData();
        // URLがCALLBACK_URLから始まっていることをチェック
        if (uri != null && uri.toString().startsWith(CALLBACK_URL)) {
            // URLのパラメータからoauth_verifierを値を取得
            String verifier = uri.getQueryParameter("oauth_verifier");
            // アクセストークンを取得するタスクに投げる
            new GetAccessTokenTask().execute(verifier);
        }
    }

    /**
     * OAuthRequestToken を取得する AsyncTask です。
     * 
     * @author gabu
     */
    private class GetRequestTokenTask extends AsyncTask<Void, Void, String> {
        @Override
        protected void onPreExecute() {
            // プログレスダイアログを表示
            showProgressDialog("Twitter認証中");
        }

        @Override
        protected String doInBackground(Void... params) {
            try {
                // エラー時など繰り返しチャレンジできるようにTwitterインスタンスを作りなおす
                mTwitter = createTwitterInstance();
                // リクエストトークンを取得
                mRequestToken = mTwitter.getOAuthRequestToken(CALLBACK_URL);
                // URLを返す
                return mRequestToken.getAuthorizationURL();
            } catch (TwitterException e) {
                e.printStackTrace();
                return null;
            }
        }

        @Override
        protected void onPostExecute(String url) {
            if (url != null) {
                // 認証ページのURLをブラウザで開く
                Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(url));
                startActivity(intent);
            } else {
                // URLがnullの場合はリクエストトークンの取得に失敗している。
                dismissProgressDialog();
                showToast("OAuthRequestTokenの取得に失敗しました。");
            }
        }
    }

    /**
     * OAuthAccessToken を取得する AsyncTask です。
     * 
     * @author gabu
     */
    private class GetAccessTokenTask extends AsyncTask<String, Void, Boolean> {
        @Override
        protected Boolean doInBackground(String... params) {
            String verifier = params[0];
            try {
                AccessToken accessToken = mTwitter.getOAuthAccessToken(mRequestToken, verifier);
                // アクセストークンを保存
                storeAccessToken(accessToken);
                return true;
            } catch (Exception e) {
                e.printStackTrace();
                return false;
            }
        }

        @Override
        protected void onPostExecute(Boolean success) {
            dismissProgressDialog();
            if (success) {
                // 認証が完了したのでツイート画面を表示する
                setUpTweetPage();
                showToast("認証が完了しました！");
            } else {
                showToast("OAuthAccessTokenの取得に失敗しました。");
            }
        }
    }

    // プログレスダイアログを表示します。
    private void showProgressDialog(String message) {
        mProgressDialog = new ProgressDialog(this);
        mProgressDialog.setMessage(message);
        mProgressDialog.show();
    }

    // プログレスダイアログを閉じます。
    private void dismissProgressDialog() {
        if (mProgressDialog != null)
            mProgressDialog.dismiss();
    }

    // アクセストークンをプリファレンスに保存します。
    private void storeAccessToken(AccessToken accessToken) {
        SharedPreferences preferences = getPreferences(MODE_PRIVATE);
        Editor editor = preferences.edit();
        editor.putString(TOKEN, accessToken.getToken());
        editor.putString(TOKEN_SECRET, accessToken.getTokenSecret());
        editor.commit();
    }

    // アクセストークンをプリファレンスから読み込みます。
    private AccessToken loadAccessToken() {
        SharedPreferences preferences = getPreferences(MODE_PRIVATE);
        String token = preferences.getString(TOKEN, null);
        String tokenSecret = preferences.getString(TOKEN_SECRET, null);
        if (token != null && tokenSecret != null) {
            return new AccessToken(token, tokenSecret);
        } else {
            return null;
        }
    }

    // アクセストークンが存在する場合はtrueを返します。
    private boolean hasAccessToken() {
        return loadAccessToken() != null;
    }

    private void showToast(String text) {
        Toast.makeText(this, text, Toast.LENGTH_LONG).show();
    }
}
