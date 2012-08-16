
package net.gabuchan.androidrecipe.recipe117;

import java.io.File;

import net.gabuchan.androidrecipe.R;
import android.annotation.TargetApi;
import android.app.Activity;
import android.app.DownloadManager;
import android.app.DownloadManager.Request;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.database.Cursor;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

@TargetApi(9)
public class Recipe117Activity extends Activity {
    private DownloadManager mDownloadManager;
    private BroadcastReceiver mDownloadReceiver;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recipe_117);

        if (Build.VERSION.SDK_INT < Build.VERSION_CODES.GINGERBREAD) {
            Toast.makeText(this, "このレシピは、API Level 9(Android 2.3)からのみ使えます。",
                    Toast.LENGTH_SHORT).show();
            finish();
            return;
        }

        // DownloadManagerを取得
        mDownloadManager = (DownloadManager) getSystemService(DOWNLOAD_SERVICE);
    }

    @Override
    protected void onResume() {
        super.onResume();

        // レシーバーを作る
        mDownloadReceiver = new BroadcastReceiver() {
            @Override
            public void onReceive(Context context, Intent intent) {
                if (!DownloadManager.ACTION_DOWNLOAD_COMPLETE.equals(intent.getAction())) {
                    // ACTION_DOWNLOAD_COMPLETEじゃなかったら何もしない
                    return;
                }
                long downloadId = intent.getLongExtra(DownloadManager.EXTRA_DOWNLOAD_ID, 0);
                if (downloadId == 0) {
                    // downloadIdが取得できなかったら何もしない
                    return;
                }
                // DownloadManager用のクエリーを作って
                DownloadManager.Query query = new DownloadManager.Query();
                // downloadIdを検索条件にして
                query.setFilterById(downloadId);
                // 検索
                Cursor c = mDownloadManager.query(query);
                if (c.moveToFirst()) {
                    // ステータスを取得して
                    int status = c.getInt(c.getColumnIndex(DownloadManager.COLUMN_STATUS));
                    if (status != DownloadManager.STATUS_SUCCESSFUL) {
                        // ステータスが成功じゃなかったら失敗トースト
                        showToast("ダウンロード失敗でした。。。");
                        return;
                    }
                    // 成功だったらトースト表示して
                    String uriString = c.getString(c.getColumnIndex(
                            DownloadManager.COLUMN_LOCAL_URI));
                    showToast("ダウンロード完了！ " + uriString);
                    // ImageVieｗに表示！
                    ImageView view = (ImageView) findViewById(R.id.image_view);
                    view.setImageURI(Uri.parse(uriString));
                }
            }
        };

        // DownloadManager.ACTION_DOWNLOAD_COMPLETEに反応するレシーバーを登録
        registerReceiver(mDownloadReceiver, new IntentFilter(
                DownloadManager.ACTION_DOWNLOAD_COMPLETE));
    }

    @Override
    protected void onPause() {
        super.onPause();

        // レシーバーを解除
        unregisterReceiver(mDownloadReceiver);
    }

    @TargetApi(11)
    public void onDownloadClick(View view) {
        // ダウンロードしたいファイルのURL
        // URLが変わってダウンロードできなくなったらごめんなさい
        Uri uri = Uri.parse("http://twitpic.com/show/thumb/ai0ymg");
        // リクエストを作る
        Request request = new DownloadManager.Request(uri);
        // ダウンロードが完了してもNotificationが消えないようにできるのは11以降でした。。。
        if (Build.VERSION_CODES.HONEYCOMB <= Build.VERSION.SDK_INT) {
            request.setNotificationVisibility(
                    DownloadManager.Request.VISIBILITY_VISIBLE_NOTIFY_COMPLETED);
        }

        // 保存先のUriを作って
        Uri dest = createDestinationUri(uri);
        // 保存先を指定する
        request.setDestinationUri(dest);

        // DownloadManagerを取得して
        DownloadManager downloadManager = (DownloadManager) getSystemService(DOWNLOAD_SERVICE);
        // ダウンロード開始！
        downloadManager.enqueue(request);
    }

    private Uri createDestinationUri(Uri uri) {
        String uriString = uri.toString();
        // ファイル名を抜き出す
        String fileName = uriString.substring(uriString.lastIndexOf("/") + 1);

        // 保存先のディレクトリのFileオブジェクトを生成
        File dir = new File(Environment.getExternalStoragePublicDirectory(
                Environment.DIRECTORY_PICTURES), "gabubon2");
        // ディレクトリがなければ作成する
        if (!dir.exists()) {
            if (!dir.mkdirs()) {
                showToast("SDカードにディレクトリが作成できませんでした。");
            }
        }
        // ディレクトリとファイル名を繋げてFileオブジェクトを作る
        File file = new File(dir, fileName);
        // FileオブジェクトからUriを作る
        return Uri.fromFile(file);
    }

    private void showToast(String text) {
        Toast.makeText(this, text, Toast.LENGTH_SHORT).show();
    }
}
