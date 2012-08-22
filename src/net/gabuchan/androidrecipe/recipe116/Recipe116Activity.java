
package net.gabuchan.androidrecipe.recipe116;

import net.gabuchan.androidrecipe.R;
import android.app.Activity;
import android.app.AlertDialog;
import android.app.backup.BackupManager;
import android.app.backup.RestoreObserver;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

public class Recipe116Activity extends Activity {
    static final String PREF_NAME = "pref";
    private static final String BACKUP_TEST = "backup_test";

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recipe_116);

        // BackupManagerインスタンスを生成して
        BackupManager backupManager = new BackupManager(this);
        try {
            // 明示的にリストアを要求（オブザーバーを指定することもできる）
            backupManager.requestRestore(new RestoreObserver() {
                @Override
                public void restoreStarting(int numPackages) {
                    showToast("restoreStarting:numPackages=" + numPackages);
                }

                @Override
                public void onUpdate(int nowBeingRestored, String currentPackage) {
                    showToast("onUpdate:nowBeingRestored=" + nowBeingRestored + ", currentPackage="
                            + currentPackage);
                }

                @Override
                public void restoreFinished(int error) {
                    showToast("restoreFinished:error=" + error);
                }
            });
        } catch (java.lang.SecurityException e) {
            // Android 2.2のバグです。
            // 詳細: http://code.google.com/p/android/issues/detail?id=10094
        }
    }

    public void onCreateDBClick(View view) {
        // プリファレンス作る
        Editor editor = getSharedPreferences(PREF_NAME, MODE_PRIVATE).edit();
        editor.putString(BACKUP_TEST, "リストアされました！");
        editor.commit();

        // BackupManagerインスタンスを生成して
        BackupManager backupManager = new BackupManager(this);
        // 明示的に通知
        backupManager.dataChanged();
    }

    public void onRestoreCheckClick(View view) {
        SharedPreferences sp = getSharedPreferences(PREF_NAME, MODE_PRIVATE);
        String string = sp.getString(BACKUP_TEST, "できてなければこっち");

        // リストアできていれば
        new AlertDialog.Builder(this).setMessage(string).show();
    }

    private void showToast(String text) {
        Toast.makeText(this, text, Toast.LENGTH_SHORT).show();
    }
}
