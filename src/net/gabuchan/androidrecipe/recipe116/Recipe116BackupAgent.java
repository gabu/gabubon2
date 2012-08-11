
package net.gabuchan.androidrecipe.recipe116;

import android.app.backup.BackupAgentHelper;
import android.app.backup.SharedPreferencesBackupHelper;

public class Recipe116BackupAgent extends BackupAgentHelper {
    // ヘルパーを追加する時のキー
    private static final String PREFS_BACKUP_KEY = "prefs";
    
    @Override
    public void onCreate() {
        super.onCreate();

        // PreferenceActivityで作られるファイル名
        String preferenceActivity = getPackageName() + "_preferences";
        // 自分で名前を指定したプリファレンス
        String sharedPreference = Recipe116Activity.PREF_NAME;
        
        // プリファレンス用のバックアップヘルパーを作る
        SharedPreferencesBackupHelper helper;
        // ファイルを指定
        helper = new SharedPreferencesBackupHelper(this, preferenceActivity, sharedPreference);
        // ヘルパーを追加
        addHelper(PREFS_BACKUP_KEY, helper);
    }
}
