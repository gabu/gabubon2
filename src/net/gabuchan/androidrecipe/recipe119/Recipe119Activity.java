
package net.gabuchan.androidrecipe.recipe119;

import net.gabuchan.androidrecipe.R;
import android.app.Activity;
import android.os.Bundle;
import android.view.View;

import com.google.android.apps.analytics.GoogleAnalyticsTracker;

public class Recipe119Activity extends Activity {
    private GoogleAnalyticsTracker mTracker;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recipe_119);

        // インスタンス取得
        mTracker = GoogleAnalyticsTracker.getInstance();
        // 自動的に60秒ごとに通信する
        mTracker.startNewSession("UA-34033640-1", 60, this);
        // 通信を手動にすることもできる
        // mTracker.startNewSession("UA-34033640-1", this);

        // ページビューとして計測
        mTracker.trackPageView("/recipe119");
    }

    public void onTrackClick(View view) {
        // イベントとして計測
        mTracker.trackEvent("カテゴリ", "アクション", "ラベル", 0);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        // 終了
        mTracker.stopSession();
    }
}
