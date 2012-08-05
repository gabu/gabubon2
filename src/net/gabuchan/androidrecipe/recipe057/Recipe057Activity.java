
package net.gabuchan.androidrecipe.recipe057;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
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

public class Recipe057Activity extends ListActivity {
    private static final String FILE_NAME = "list_data.dat";

    // リストデータ
    ArrayList<ListItem> mItems;

    // アダプター
    ListItemAdapter mAdapter;

    @SuppressWarnings("unchecked")
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // ファイルが存在するかチェック
        if (getFileStreamPath(FILE_NAME).exists()) {
            try {
                // ファイルを読み込んで
                FileInputStream fis = openFileInput(FILE_NAME);
                ObjectInputStream ois = new ObjectInputStream(fis);
                // 復元！
                mItems = (ArrayList<ListItem>) ois.readObject();
                ois.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        } else {
            // なければnewするだけ
            mItems = new ArrayList<ListItem>();
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

    @Override
    protected void onDestroy() {
        super.onDestroy();
        try {
            FileOutputStream fos = openFileOutput(FILE_NAME, MODE_PRIVATE);
            ObjectOutputStream oos = new ObjectOutputStream(fos);
            oos.writeObject(mItems);
            oos.close();
        } catch (Exception e) {
            e.printStackTrace();
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
}
