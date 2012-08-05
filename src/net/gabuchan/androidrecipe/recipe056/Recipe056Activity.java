
package net.gabuchan.androidrecipe.recipe056;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import net.gabuchan.androidrecipe.R;
import android.app.ListActivity;
import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

public class Recipe056Activity extends ListActivity {
    // アダプター
    ListItemAdapter mAdapter;

    // リストデータ
    ArrayList<ListItem> mItems;

    @SuppressWarnings("unchecked")
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if (savedInstanceState == null) {
            mItems = new ArrayList<ListItem>();
        } else {
            mItems = (ArrayList<ListItem>) savedInstanceState.getSerializable("list_data");
        }
        mAdapter = new ListItemAdapter(this, mItems);

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

                    // リストアイテムを動的に追加
                    int count = mAdapter.getCount();
                    int max = count + 5;
                    for (; count < max; count++) {
                        // リストアイテムを追加
                        mAdapter.add(new ListItem(R.drawable.gabu, "@gabu", "item_" + count));
                    }
                }
            }
        });
    }

    @SuppressWarnings("serial")
    class ListItem implements Serializable {

        int resId; // アイコン画像リソースID
        String name; // ユーザ名
        String comment; // コメント

        ListItem(int resId, String name, String comment) {
            this.resId = resId;
            this.name = name;
            this.comment = comment;
        }
    }

    static class ViewHolder {
        ImageView imageView; // アイコン画像の表示用
        TextView nameTextView; // ユーザ名の表示用
        TextView commentTextView; // コメントの表示用
    }

    class ListItemAdapter extends ArrayAdapter<ListItem> {
        // レイアウトファイルからViewオブジェクトを生成するため
        LayoutInflater mInflater;

        ListItemAdapter(Context context, List<ListItem> items) {
            super(context, 0, items);
            // インフレイターを取得
            mInflater = getLayoutInflater();
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            // ListItemオブジェクトを取得
            ListItem item = getItem(position);

            ViewHolder holder;
            // convertViewには使いまわすためのViewがあれば入っている
            if (convertView == null) {
                // ない場合はレイアウトファイルから生成する
                convertView = mInflater.inflate(R.layout.list_item_recipe_051, null);
                // ViewHolderも作って
                holder = new ViewHolder();
                // 参照をセット
                holder.imageView = (ImageView) convertView.findViewById(R.id.icon);
                holder.nameTextView = (TextView) convertView.findViewById(R.id.name);
                holder.commentTextView = (TextView) convertView.findViewById(R.id.comment);
                // ViewHolderを使いまわせるようにセットしておく
                convertView.setTag(holder);
            } else {
                // ある場合はViewHolderを取り出して再利用
                holder = (ViewHolder) convertView.getTag();
            }
            // アイコン画像をセット
            holder.imageView.setImageResource(item.resId);
            // ユーザ名をセット
            holder.nameTextView.setText(item.name);
            // コメントをセット
            holder.commentTextView.setText(item.comment);
            // 表示するViewを返す
            return convertView;
        }
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        // リストデータを保存
        outState.putSerializable("list_data", mItems);
    }

    @Override
    protected void onRestoreInstanceState(Bundle state) {
        super.onRestoreInstanceState(state);
        // リストデータを復元
        // mItems = (ArrayList<ListItem>) state.getSerializable("list_data");
    }
}
