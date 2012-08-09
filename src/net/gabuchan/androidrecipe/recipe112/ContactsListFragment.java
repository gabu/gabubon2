
package net.gabuchan.androidrecipe.recipe112;

import android.database.Cursor;
import android.os.Bundle;
import android.provider.ContactsContract.Contacts;
import android.support.v4.app.ListFragment;
import android.support.v4.app.LoaderManager.LoaderCallbacks;
import android.support.v4.content.CursorLoader;
import android.support.v4.content.Loader;
import android.support.v4.widget.SimpleCursorAdapter;

public class ContactsListFragment extends ListFragment
        implements LoaderCallbacks<Cursor> {

    private SimpleCursorAdapter mAdapter;

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        setEmptyText("空っぽの時のメッセージ");

        // Cursorはnullでアダプターを作る
        mAdapter = new SimpleCursorAdapter(
                getActivity(),
                android.R.layout.simple_list_item_1,
                null,
                new String[] {
                        Contacts.DISPLAY_NAME
                },
                new int[] {
                        android.R.id.text1
                }, 0);
        // アダプターをセット
        setListAdapter(mAdapter);

        // ローダーを初期化！
        getLoaderManager().initLoader(0, null, this);
    }

    @Override
    public Loader<Cursor> onCreateLoader(int id, Bundle args) {
        // ローダーを作って返すべき時に呼び出される
        
        // 取得したいカラムの配列
        String[] projection = {
                Contacts._ID,
                Contacts.DISPLAY_NAME,
        };
        // 検索条件
        String select = "((" + Contacts.DISPLAY_NAME + " NOTNULL) AND ("
                + Contacts.HAS_PHONE_NUMBER + "=1) AND ("
                + Contacts.DISPLAY_NAME + " != '' ))";
        // 連絡先を検索するCursorLoaderを作って返す
        return new CursorLoader(
                getActivity(),
                Contacts.CONTENT_URI,
                projection,
                select,
                null,
                Contacts.DISPLAY_NAME + " COLLATE LOCALIZED ASC");
    }

    @Override
    public void onLoadFinished(Loader<Cursor> loader, Cursor data) {
        // データの取得が完了した時に呼び出される
        // アダプターに取得したCursorをセット
        mAdapter.swapCursor(data);
    }

    @Override
    public void onLoaderReset(Loader<Cursor> loader) {
        // ローがーがリセットされる時に呼び出される
        // アダプターのCursorを空にする
        mAdapter.swapCursor(null);
    }
}
