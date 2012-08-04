
package net.gabuchan.androidrecipe.recipe053;

import java.util.ArrayList;
import java.util.List;

import android.app.ListActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.AbsListView;
import android.widget.AbsListView.OnScrollListener;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

public class Recipe053Activity extends ListActivity {
    private static final String TAG = Recipe053Activity.class.getSimpleName();

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        List<String> items = new ArrayList<String>();
        for (int i = 0; i < 20; i++) {
            items.add("item_" + i);
        }

        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,
                android.R.layout.simple_list_item_1, items);
        // アダプターをセット
        setListAdapter(adapter);

        ListView listView = getListView();
        listView.setOnScrollListener(new OnScrollListener() {
            @Override
            public void onScrollStateChanged(AbsListView view, int scrollState) {
                // 今回は使わない
            }

            @Override
            public void onScroll(AbsListView view, int firstVisibleItem, int visibleItemCount,
                    int totalItemCount) {
                Log.d(TAG, "firstVisibleItem = " + firstVisibleItem);
                Log.d(TAG, "visibleItemCount = " + visibleItemCount);
                Log.d(TAG, "totalItemCount = " + totalItemCount);

                if ((totalItemCount - visibleItemCount) == firstVisibleItem) {
                    showToast("ここが最後です＞＜");
                }
            }
        });
    }

    private void showToast(String text) {
        Toast.makeText(this, text, Toast.LENGTH_SHORT).show();
    }
}
