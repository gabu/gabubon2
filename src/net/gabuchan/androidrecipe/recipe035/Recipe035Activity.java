
package net.gabuchan.androidrecipe.recipe035;

import net.gabuchan.androidrecipe.R;
import android.app.Activity;
import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Intent;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.app.NotificationCompat;
import android.view.View;
import android.widget.Toast;

public class Recipe035Activity extends Activity {
    private Handler mHandler = new Handler();

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recipe_035);
    }

    @Override
    protected void onNewIntent(Intent intent) {
        super.onNewIntent(intent);

        // android:launchMode="singleTask"で
        // 既にアクティビティが存在していた場合
    }

    public void onButtonClick(View view) {
        // ノーティフィケーションがタップされたら呼び出すインテント
        Intent intent = new Intent(this, Recipe035Activity.class);
        PendingIntent contentIntent = PendingIntent.getActivity(this, 0, intent, 0);

        // Notificationオブジェクトを作る
        final Notification notification = new NotificationCompat.Builder(this)
                .setTicker("一瞬出るやつ")
                .setContentTitle("ここがタイトル")
                .setContentText("わんわん！")
                .setContentIntent(contentIntent)
                .setAutoCancel(true) // タップされたら自動的に消えるように
                .setSmallIcon(R.drawable.ic_stat_notify_gabu) // アイコン
                .setDefaults(Notification.DEFAULT_ALL)
                .setContentInfo("インフォ") // 11以降
                .setNumber(99) // 11以降
                .setLargeIcon(BitmapFactory.decodeResource(getResources(), R.drawable.gabu)) // 11以降
                .getNotification();

        // NotificationManagerを取得して
        final NotificationManager manager = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);
        // 通知（表示）！
        manager.notify(1, notification);
    }

    public void onCustomSoundClick(View view) {
        // rawにあるファイルからUriを作るためのパス
        String uriString = "android.resource://" + getPackageName() + "/" + R.raw.sound;

        Intent intent = new Intent(this, Recipe035Activity.class);
        PendingIntent contentIntent = PendingIntent.getActivity(this, 0, intent, 0);

        Notification notification = new NotificationCompat.Builder(this)
                .setContentTitle("カスタム")
                .setContentText("サウンド！")
                .setContentIntent(contentIntent)
                .setAutoCancel(true)
                .setSmallIcon(R.drawable.ic_stat_notify_gabu)
                .setSound(Uri.parse(uriString))
                .getNotification();

        NotificationManager manager = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);
        manager.notify(1, notification);
    }

    public void onCustomLedClick(View view) {
        Intent intent = new Intent(this, Recipe035Activity.class);
        PendingIntent contentIntent = PendingIntent.getActivity(this, 0, intent, 0);

        final Notification notification = new NotificationCompat.Builder(this)
                .setContentTitle("カスタム")
                .setContentText("LED")
                .setContentIntent(contentIntent)
                .setAutoCancel(true)
                .setSmallIcon(R.drawable.ic_stat_notify_gabu)
                .setDefaults(Notification.DEFAULT_SOUND | Notification.DEFAULT_VIBRATE)
                .setLights(Color.RED, 1000, 2000)
                .getNotification();

        final NotificationManager manager = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);

        // 無理矢理だけどLEDのテスト
        Toast.makeText(this, "5秒以内にスクリーンをオフにしてください", Toast.LENGTH_SHORT).show();

        mHandler.postDelayed(new Runnable() {
            @Override
            public void run() {
                manager.notify(1, notification);
            }
        }, 5 * 1000);
    }
}
