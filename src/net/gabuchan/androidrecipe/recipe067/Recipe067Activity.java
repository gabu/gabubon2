
package net.gabuchan.androidrecipe.recipe067;

import java.io.IOException;

import net.gabuchan.androidrecipe.R;
import android.app.Activity;
import android.content.Intent;
import android.database.Cursor;
import android.media.ExifInterface;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.widget.TextView;

public class Recipe067Activity extends Activity {
    private static final int REQUEST_CODE = 1;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recipe_067);
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
            // Exifを取得して表示
            showExif(uri);
        }
    }

    private void showExif(Uri uri) {
        String path = getPath(uri);
        try {
            ExifInterface exif = new ExifInterface(path);
            StringBuffer sb = new StringBuffer();
            sb.append(ExifInterface.TAG_APERTURE);
            sb.append(": ");
            sb.append(exif.getAttribute(ExifInterface.TAG_APERTURE));
            sb.append("\n");
            sb.append(ExifInterface.TAG_DATETIME);
            sb.append(": ");
            sb.append(exif.getAttribute(ExifInterface.TAG_DATETIME));
            sb.append("\n");
            sb.append(ExifInterface.TAG_EXPOSURE_TIME);
            sb.append(": ");
            sb.append(exif.getAttribute(ExifInterface.TAG_EXPOSURE_TIME));
            sb.append("\n");
            sb.append(ExifInterface.TAG_FLASH);
            sb.append(": ");
            sb.append(exif.getAttribute(ExifInterface.TAG_FLASH));
            sb.append("\n");
            sb.append(ExifInterface.TAG_FOCAL_LENGTH);
            sb.append(": ");
            sb.append(exif.getAttribute(ExifInterface.TAG_FOCAL_LENGTH));
            sb.append("\n");
            sb.append(ExifInterface.TAG_GPS_ALTITUDE);
            sb.append(": ");
            sb.append(exif.getAttribute(ExifInterface.TAG_GPS_ALTITUDE));
            sb.append("\n");
            sb.append(ExifInterface.TAG_GPS_ALTITUDE_REF);
            sb.append(": ");
            sb.append(exif.getAttribute(ExifInterface.TAG_GPS_ALTITUDE_REF));
            sb.append("\n");
            sb.append(ExifInterface.TAG_GPS_DATESTAMP);
            sb.append(": ");
            sb.append(exif.getAttribute(ExifInterface.TAG_GPS_DATESTAMP));
            sb.append("\n");
            sb.append(ExifInterface.TAG_GPS_LATITUDE);
            sb.append(": ");
            sb.append(exif.getAttribute(ExifInterface.TAG_GPS_LATITUDE));
            sb.append("\n");
            sb.append(ExifInterface.TAG_GPS_LATITUDE_REF);
            sb.append(": ");
            sb.append(exif.getAttribute(ExifInterface.TAG_GPS_LATITUDE_REF));
            sb.append("\n");
            sb.append(ExifInterface.TAG_GPS_LONGITUDE);
            sb.append(": ");
            sb.append(exif.getAttribute(ExifInterface.TAG_GPS_LONGITUDE));
            sb.append("\n");
            sb.append(ExifInterface.TAG_GPS_LONGITUDE_REF);
            sb.append(": ");
            sb.append(exif.getAttribute(ExifInterface.TAG_GPS_LONGITUDE_REF));
            sb.append("\n");
            sb.append(ExifInterface.TAG_GPS_PROCESSING_METHOD);
            sb.append(": ");
            sb.append(exif.getAttribute(ExifInterface.TAG_GPS_PROCESSING_METHOD));
            sb.append("\n");
            sb.append(ExifInterface.TAG_GPS_TIMESTAMP);
            sb.append(": ");
            sb.append(exif.getAttribute(ExifInterface.TAG_GPS_TIMESTAMP));
            sb.append("\n");
            sb.append(ExifInterface.TAG_IMAGE_LENGTH);
            sb.append(": ");
            sb.append(exif.getAttribute(ExifInterface.TAG_IMAGE_LENGTH));
            sb.append("\n");
            sb.append(ExifInterface.TAG_IMAGE_WIDTH);
            sb.append(": ");
            sb.append(exif.getAttribute(ExifInterface.TAG_IMAGE_WIDTH));
            sb.append("\n");
            sb.append(ExifInterface.TAG_ISO);
            sb.append(": ");
            sb.append(exif.getAttribute(ExifInterface.TAG_ISO));
            sb.append("\n");
            sb.append(ExifInterface.TAG_MAKE);
            sb.append(": ");
            sb.append(exif.getAttribute(ExifInterface.TAG_MAKE));
            sb.append("\n");
            sb.append(ExifInterface.TAG_MODEL);
            sb.append(": ");
            sb.append(exif.getAttribute(ExifInterface.TAG_MODEL));
            sb.append("\n");
            sb.append(ExifInterface.TAG_ORIENTATION);
            sb.append(": ");
            sb.append(exif.getAttribute(ExifInterface.TAG_ORIENTATION));
            sb.append("\n");
            sb.append(ExifInterface.TAG_WHITE_BALANCE);
            sb.append(": ");
            sb.append(exif.getAttribute(ExifInterface.TAG_WHITE_BALANCE));
            sb.append("\n");
            TextView textView = (TextView) findViewById(R.id.text_view);
            textView.setText(sb);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private String getPath(Uri uri) {
        String[] projection = new String[] {
                MediaStore.Images.Media.DATA
        // このカラムにファイルパスが入っている
        };
        Cursor cursor = MediaStore.Images.Media.query(getContentResolver(), uri, projection);
        cursor.moveToFirst();
        String path = cursor.getString(cursor.getColumnIndex(MediaStore.Images.Media.DATA));
        cursor.close();
        return path;
    }
}
