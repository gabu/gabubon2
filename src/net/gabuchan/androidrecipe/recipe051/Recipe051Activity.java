
package net.gabuchan.androidrecipe.recipe051;

import java.util.ArrayList;
import java.util.List;

import net.gabuchan.androidrecipe.R;
import android.app.ListActivity;
import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

public class Recipe051Activity extends ListActivity {
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // データを作る
        List<ListItem> items = new ArrayList<ListItem>();
        items.add(new ListItem(R.drawable.gabu, "@gabu", "今日も暑いですね。"));
        items.add(new ListItem(R.drawable.gabu, "@foo", "北海道は寒いです。"));
        items.add(new ListItem(R.drawable.gabu, "@gabu", "北海道に旅行したいです。"));

        // 自前のアダプターを作って
        ListItemAdapter adapter = new ListItemAdapter(this, items);
        // アダプターをセット
        setListAdapter(adapter);
    }

    class ListItem {
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
}
