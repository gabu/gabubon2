
package net.gabuchan.androidrecipe.recipe066;

import net.gabuchan.androidrecipe.R;
import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.widget.ImageView;

public class Recipe066Activity extends Activity {
    private static final int REQUEST_CODE = 1;

    private ImageView mImageView;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recipe_066);
        mImageView = (ImageView) findViewById(R.id.image_view);
    }

    public void onButtonClick(View view) {
        Intent intent = new Intent(Intent.ACTION_PICK);
        intent.setType("image/*");
        startActivityForResult(intent, REQUEST_CODE);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (resultCode == RESULT_OK && requestCode == REQUEST_CODE) {
            // 選択された画像のUriを取得
            Uri uri = data.getData();
            // Uriからidだけ抜き出す
            String uriString = uri.toString();
            System.out.println(uriString);
            long id = Long.parseLong(uriString.substring(uriString.lastIndexOf("/") + 1));
            // サムネイル画像を取得
            Bitmap thumbnail = MediaStore.Images.Thumbnails.getThumbnail(
                    getContentResolver(),
                    id,
                    MediaStore.Images.Thumbnails.MINI_KIND,
                    null);
            // ImageViewにセット
            mImageView.setImageBitmap(thumbnail);
        }
    }
}
