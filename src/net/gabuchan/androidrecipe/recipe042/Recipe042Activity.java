
package net.gabuchan.androidrecipe.recipe042;

import net.gabuchan.androidrecipe.R;
import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

public class Recipe042Activity extends Activity {
    private static final String TAG = Recipe042Activity.class.getSimpleName();

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recipe_042);

        ViewPager viewPager = (ViewPager) findViewById(R.id.view_pager);
        // アダプターをセット
        viewPager.setAdapter(new ImageViewPager(this));
    }

    private class ImageViewPager extends PagerAdapter {
        // 画像リソースIDの配列
        int[] mResIds = {
                R.drawable.recipe_042_1,
                R.drawable.recipe_042_2,
                R.drawable.recipe_042_3,
                R.drawable.recipe_042_4,
                R.drawable.recipe_042_5,
        };

        Context mContext;

        // ImageViewを作る時にContextが必要になるのでコンストラクタで渡す
        ImageViewPager(Context context) {
            this.mContext = context;
        }

        @Override
        public Object instantiateItem(ViewGroup container, int position) {
            Log.d(TAG, "instantiateItem position = " + position);
            // containerに表示したいViewを追加する。
            ImageView imageView = new ImageView(mContext);
            imageView.setImageResource(mResIds[position]);
            container.addView(imageView);
            return imageView;
        }

        @Override
        public void destroyItem(ViewGroup container, int position, Object object) {
            Log.d(TAG, "destroyItem = position" + position);
            // containerからViewを削除する。
            container.removeView((View) object);
        }

        @Override
        public int getCount() {
            // ビューの数を返す（配列の数を返す）
            return mResIds.length;
        }

        @Override
        public boolean isViewFromObject(View view, Object object) {
            boolean equals = view.equals(object);
            Log.d(TAG, "isViewFromObject = " + equals);
            return equals;
        }
    }
}
