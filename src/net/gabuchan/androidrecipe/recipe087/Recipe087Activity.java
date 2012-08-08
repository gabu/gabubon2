
package net.gabuchan.androidrecipe.recipe087;

import net.gabuchan.androidrecipe.R;
import net.gabuchan.androidrecipe.recipe086.OrientationManager;
import net.gabuchan.androidrecipe.recipe086.OrientationManager.OnOrientationChangedListener;
import android.app.Activity;
import android.graphics.Matrix;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

public class Recipe087Activity extends Activity {
    private OrientationManager mOrientationManager;
    private TextView mTextView;
    private ImageView mImageView;
    private int mRotation;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recipe_087);

        mTextView = (TextView) findViewById(R.id.text_view);
        mImageView = (ImageView) findViewById(R.id.image_view);
    }
    @Override
    protected void onResume() {
        super.onResume();

        // SensorManagerを取得して
        SensorManager sensorManager = (SensorManager) getSystemService(SENSOR_SERVICE);
        // 画面の回転を取得して
        mRotation = getWindowManager().getDefaultDisplay().getRotation();
        // OrientationManagerを生成
        mOrientationManager = new OrientationManager(sensorManager, mRotation);
        // 傾きが取得できた時のリスナーをセット
        mOrientationManager.setOnOrientationChangedListener(new OnOrientationChangedListener() {
            @Override
            public void onOrientationChanged(float[] values) {
                // 傾きが取得できた時に呼び出される
                Matrix matrix = new Matrix();
                // ラジアンから角度に変換
                float azimuth = (float) Math.toDegrees(values[0]);
                float px = centerX();
                float py = centerY();
                // 方位をマイナスで反転させるとちょうどいい回転に
                matrix.setRotate(-azimuth, px, py);
                // MatrixをImageViewにセット
                mImageView.setImageMatrix(matrix);
                // デバッグ用にTextViewに方位を表示
                mTextView.setText("rotation = " + mRotation + " azimuth = " + azimuth);
            }

            // X方向の中心座標を計算する
            private float centerX() {
                // ImageViewの幅からパディングを引いて2で割る
                return (mImageView.getWidth()
                        - mImageView.getPaddingLeft()
                        - mImageView.getPaddingRight()) / 2f;
            }

            // Y方向の中心座標を計算する
            private float centerY() {
                // ImageViewの高さからパディングを引いて2で割る
                return (mImageView.getHeight()
                        - mImageView.getPaddingTop()
                        - mImageView.getPaddingBottom()) / 2f;
            }
        });
    }

    @Override
    protected void onPause() {
        super.onPause();

        // OrientationManagerを解放
        mOrientationManager.release();
    }
}
