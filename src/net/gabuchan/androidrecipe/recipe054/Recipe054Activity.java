
package net.gabuchan.androidrecipe.recipe054;

import java.util.ArrayList;
import java.util.List;

import net.gabuchan.androidrecipe.R;

import android.app.ListActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.ListView;

public class Recipe054Activity extends ListActivity {
    // アダプター
    private ArrayAdapter<String> mAdapter;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        List<String> items = new ArrayList<String>();
        for (int i = 0; i < 20; i++) {
            items.add("item_" + i);
        }

        mAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, items);

        // アダプターをセットする前にフッターをセット
        ListView listView = getListView();
        // フッターをレイアウトファイルから生成
        View footer = getLayoutInflater().inflate(R.layout.footer_recipe_054, null);
        // フッターを追加
        listView.addFooterView(footer, null, true);

        // アダプターをセット
        setListAdapter(mAdapter);

        listView.setOnItemClickListener(new OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                // クリックされたViewがフッターか判定
                if (view.getId() == R.id.footer) {

                    // 表示する数字を計算
                    int count = mAdapter.getCount();
                    int max = count + 5;
                    for (; count < max; count++) {
                        // リストアイテムを追加
                        mAdapter.add("item_" + count);
                    }
                }
            }
        });
    }
}
