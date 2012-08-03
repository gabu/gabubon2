
package net.gabuchan.androidrecipe.recipe040;

import net.gabuchan.androidrecipe.R;
import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.Toast;

public class Recipe040Activity extends Activity {

    private Integer[] mIds = {
            R.drawable.gabu,
            R.drawable.gabu,
            R.drawable.gabu,
            R.drawable.gabu,
            R.drawable.gabu,
            R.drawable.gabu,
            R.drawable.gabu,
            R.drawable.gabu,
            R.drawable.gabu,
            R.drawable.gabu,
            R.drawable.gabu,
            R.drawable.gabu,
            R.drawable.gabu,
            R.drawable.gabu,
            R.drawable.gabu,
            R.drawable.gabu,
            R.drawable.gabu,
            R.drawable.gabu,
            R.drawable.gabu,
            R.drawable.gabu,
    };

    private class ImageAdapter extends ArrayAdapter<Integer> {

        ImageAdapter(Context context) {
            super(context, 0, mIds);
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            // dpでサイズ指定をしたいのでres/values/dimens.xmlに
            // 定義した値を取得する
            int size = getResources().getDimensionPixelSize(R.dimen.size_120_dp);

            ImageView imageView;
            // convertViewには使いまわすためのViewがあれば入っている
            if (convertView == null) {
                // ない場合は作る
                imageView = new ImageView(getContext());
                // サイズを指定
                imageView.setLayoutParams(new GridView.LayoutParams(size, size));
                // ScaleTypeを指定
                imageView.setScaleType(ImageView.ScaleType.CENTER_INSIDE);
                // パディングを指定
                imageView.setPadding(8, 8, 8, 8);
            } else {
                // ある場合はキャストする
                imageView = (ImageView) convertView;
            }
            // 画像リソースをセット
            imageView.setImageResource(mIds[position]);
            // ImageViewを返す
            return imageView;
        }
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recipe_040);

        GridView gridView = (GridView) findViewById(R.id.grid_view);
        // アダプターをセット
        gridView.setAdapter(new ImageAdapter(this));
        // リスナーをセット
        gridView.setOnItemClickListener(new OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                showToast(position + "がタップされました！");
            }
        });
    }

    private void showToast(String text) {
        Toast.makeText(this, text, Toast.LENGTH_SHORT).show();
    }

}
