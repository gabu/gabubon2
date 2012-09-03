
package net.gabuchan.androidrecipe.recipe090;

import java.util.List;

import net.gabuchan.androidrecipe.R;
import android.app.Activity;
import android.app.AlertDialog;
import android.app.SearchManager;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.speech.RecognizerIntent;
import android.view.View;

public class Recipe090Activity extends Activity {
    private static final int REQUEST_CODE_PICK_IMAGE = 1;
    private static final int REQUEST_CODE_PICK_IMAGE_TO_MAIL = 2;
    private static final int REQUEST_CODE_RECOGNIZE = 3;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recipe_090);
    }

    public void onBrowserClick(View view) {
        // ブラウザを呼び出す
        Intent intent = new Intent();
        intent.setAction(Intent.ACTION_VIEW);
        intent.setData(Uri.parse("http://www.google.com"));
        startActivity(intent);
    }

    public void onSearchClick(View view) {
        // ブラウザで検索する
        Intent intent = new Intent();
        intent.setAction(Intent.ACTION_WEB_SEARCH);
        // 検索したい文字列をセット
        intent.putExtra(SearchManager.QUERY, "gabuchan");
        startActivity(intent);
    }

    public void onTelClick(View view) {
        Intent intent = new Intent();
        intent.setAction(Intent.ACTION_VIEW);
        // 電話番号をセット
        // "tel:"は必須なので、動的に生成する場合には
        // 忘れないように注意してください。
        intent.setData(Uri.parse("tel:0123456789"));
        startActivity(intent);
    }

    public void onMapClick(View view) {
        // Googleマップを呼び出す
        Intent intent = new Intent();
        intent.setAction(Intent.ACTION_VIEW);
        intent.setData(Uri.parse("geo:35.170667,136.881859?z=17"));
        startActivity(intent);
    }

    public void onShareClick(View view) {
        // テキストを共有する
        Intent intent = new Intent();
        intent.setAction(Intent.ACTION_SEND);
        intent.setType("text/plain");
        intent.putExtra(Intent.EXTRA_TEXT, "共有したいテキスト。");
        startActivity(intent);
    }

    public void onImageShareClick(View view) {
        // 画像を共有するためにギャラリーを呼び出す
        Intent intent = new Intent(Intent.ACTION_PICK);
        intent.setType("image/*");
        startActivityForResult(intent, REQUEST_CODE_PICK_IMAGE);
    }

    public void onUrlShareClick(View view) {
        // URlを共有する
        Intent intent = new Intent();
        intent.setAction(Intent.ACTION_SEND);
        intent.setType("text/plain");
        intent.putExtra(Intent.EXTRA_TEXT, "http://d.hatena.ne.jp/gabuchan/");
        startActivity(intent);
    }

    private void imageShare(Uri uri) {
        // 画像を共有する
        Intent intent = new Intent();
        intent.setAction(Intent.ACTION_SEND);
        intent.setType("image/jpeg");
        intent.putExtra(Intent.EXTRA_STREAM, uri);
        startActivity(intent);
    }

    public void onRecognizeClick(View view) {
        Intent intent = new Intent();
        intent.setAction(RecognizerIntent.ACTION_RECOGNIZE_SPEECH);
        // 音声認識に使う言語モデルを指定
        intent.putExtra(RecognizerIntent.EXTRA_LANGUAGE_MODEL,
                RecognizerIntent.LANGUAGE_MODEL_FREE_FORM);
        // プロンプトに表示する文字列を指定
        intent.putExtra(RecognizerIntent.EXTRA_PROMPT, "好きな飲み物は？");
        startActivityForResult(intent, REQUEST_CODE_RECOGNIZE);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (resultCode != RESULT_OK) {
            // 正しい結果が得られなかった場合の処理
            return;
        }
        if (requestCode == REQUEST_CODE_PICK_IMAGE) {
            // 画像を共有へ
            imageShare(data.getData());
        } else if (requestCode == REQUEST_CODE_PICK_IMAGE_TO_MAIL) {
            // メール添付送信へ
            sendMail(data.getData());
        } else if (requestCode == REQUEST_CODE_RECOGNIZE) {
            StringBuffer sb = new StringBuffer();
            // 認識結果のリストを取得
            // 似ている言葉など、複数の結果がある場合もある。
            List<String> results = data.getStringArrayListExtra(RecognizerIntent.EXTRA_RESULTS);
            for (String result : results) {
                sb.append(result);
                sb.append("\n");
            }
            // AlertDialogに表示
            new AlertDialog.Builder(this).setMessage(sb).show();
        }
    }

    public void onMailClick(View view) {
        // メールアプリを呼び出す
        Intent intent = new Intent();
        intent.setAction(Intent.ACTION_SENDTO);
        intent.setData(Uri.parse("mailto:hoge@example.com"));
        intent.putExtra(Intent.EXTRA_SUBJECT, "件名です。");
        intent.putExtra(Intent.EXTRA_TEXT, "本文です。\n本文です。");
        startActivity(intent);
    }

    public void onMailAttacheClick(View view) {
        // メール添付する画像を選ぶためにギャラリーを呼び出す
        Intent intent = new Intent(Intent.ACTION_PICK);
        intent.setType("image/*");
        startActivityForResult(intent, REQUEST_CODE_PICK_IMAGE_TO_MAIL);
    }

    private void sendMail(Uri uri) {
        // メール添付をする
        Intent intent = new Intent();
        intent.setAction(Intent.ACTION_SEND);
        String[] to = {
                "hoge@example.com"
        };
        intent.putExtra(Intent.EXTRA_EMAIL, to);
        intent.putExtra(Intent.EXTRA_SUBJECT, "件名です。");
        intent.putExtra(Intent.EXTRA_TEXT, "本文です。\n本文です。");
        // 添付ファイル
        intent.putExtra(Intent.EXTRA_STREAM, uri);
        intent.setType("image/jpeg");
        startActivity(Intent.createChooser(intent, "メールアプリを選択してください"));
    }
}
