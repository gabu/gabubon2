
package net.gabuchan.androidrecipe.recipe101;

import net.gabuchan.androidrecipe.R;
import android.app.ListActivity;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.os.Bundle;
import android.support.v4.widget.SimpleCursorAdapter;

public class Recipe101Activity extends ListActivity {
    private SQLiteDatabase mDB;
    private Cursor mCursor;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // DBをオープン
        SQLiteOpenHelper helper = new MySQLiteOpenHelper(this);
        mDB = helper.getReadableDatabase();

        // 検索条件を作って
        String where = "age >= ?";
        String[] args = {
                "5"
        };
        // 検索してCursorを取得
        mCursor = mDB.query("people", null, where, args, null, null, null);
        // from:カラム名の配列
        String[] from = {
                "name", "age"
        };
        // to: fromに対応するビューのIDの配列
        int[] to = {
                R.id.name_text_view, R.id.age_text_view
        };

        // アダプターを作って
        SimpleCursorAdapter adapter = new SimpleCursorAdapter(this,
                R.layout.list_item_recipe_101,
                mCursor,
                from,
                to,
                0);
        // ListViewにセット
        getListView().setAdapter(adapter);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        // Cursorを閉じる
        mCursor.close();
        // DBを閉じる
        mDB.close();
    }
}
