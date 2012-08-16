
package net.gabuchan.androidrecipe.recipe037;

import net.gabuchan.androidrecipe.R;
import android.app.Activity;
import android.app.ProgressDialog;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;

public class Recipe037Activity extends Activity {
    // プログレスダイアログ
    private ProgressDialog mProgressDialog;

    // ハンドラー
    private Handler mHandler = new Handler();

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recipe_037);
    }

    public void onSpinnerClick(View view) {
        // プログレスダイアログを表示
        showProgressDialog(ProgressDialog.STYLE_SPINNER);

        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    // 時間のかかる処理のつもりで5秒スリープ
                    Thread.sleep(5 * 1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                // ハンドラにメッセージを送る
                mHandler.post(new Runnable() {
                    @Override
                    public void run() {
                        // プログレスダイアログを閉じる
                        mProgressDialog.dismiss();
                    }
                });
            }
        }).start();
    }

    public void onHorizontalClick(View view) {
        // プログレスダイアログを表示
        showProgressDialog(ProgressDialog.STYLE_HORIZONTAL);

        new Thread(new Runnable() {
            @Override
            public void run() {
                // 進捗100％の値を5として
                mProgressDialog.setMax(5);
                for (int i = 1; i <= 5; i++) {
                    // 1から5までをセット
                    mProgressDialog.setProgress(i);
                    try {
                        // サンプルのため1秒スリープ
                        Thread.sleep(1 * 1000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
                // ハンドラにメッセージを送る
                mHandler.post(new Runnable() {
                    @Override
                    public void run() {
                        // プログレスダイアログを閉じる
                        mProgressDialog.dismiss();
                    }
                });
            }
        }).start();
    }

    private void showProgressDialog(int style) {
        mProgressDialog = new ProgressDialog(this);
        mProgressDialog.setTitle("通信中");
        mProgressDialog.setMessage("しばらくお待ちください");
        mProgressDialog.setProgressStyle(style);
        mProgressDialog.show();

    }
}
