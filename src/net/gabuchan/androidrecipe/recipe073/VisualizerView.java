package net.gabuchan.androidrecipe.recipe073;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.util.AttributeSet;
import android.view.View;

public class VisualizerView extends View {
    private byte[] mBytes;
    private float[] mPoints;
    private Rect mRect = new Rect();

    private Paint mForePaint = new Paint();

    public VisualizerView(Context context) {
        this(context, null);
    }

    public VisualizerView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public VisualizerView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        init();
    }

    private void init() {
        mBytes = null;
        // 線の太さ
        mForePaint.setStrokeWidth(1f);
        // アンチエイリアスをON
        mForePaint.setAntiAlias(true);
        // 線の色
        mForePaint.setColor(Color.rgb(255, 0, 0));
    }

    public void updateVisualizer(byte[] bytes) {
        // Visualizerから受け取ったデータ
        mBytes = bytes;
        // 再描画を要求する -> onDrawが呼ばれる
        invalidate();
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        if (mBytes == null) {
            return;
        }

        if (mPoints == null || mPoints.length < mBytes.length * 4) {
            // 1バイトのデータから[x, y, x, y]と4つ分の座標を計算するので
            // 4倍した数だけ配列を用意する
            mPoints = new float[mBytes.length * 4];
        }

        // Viewのサイズを計算で使うので入れておくだけ
        mRect.set(0, 0, getWidth(), getHeight());

        // mBytesの数だけループ
        for (int i = 0; i < mBytes.length - 1; i++) {
            // 始点のxを計算
            mPoints[i * 4] = mRect.width() * i / (mBytes.length - 1);
            // 始点のyを計算
            mPoints[i * 4 + 1] = mRect.height() / 2
                    + ((byte) (mBytes[i] + 128)) * (mRect.height() / 2) / 128;
            // 終点のxを計算
            mPoints[i * 4 + 2] = mRect.width() * (i + 1) / (mBytes.length - 1);
            // 終点のyを計算
            mPoints[i * 4 + 3] = mRect.height() / 2
                    + ((byte) (mBytes[i + 1] + 128)) * (mRect.height() / 2) / 128;
        }
        // 座標の配列をなぞるように連続して直線を描画
        canvas.drawLines(mPoints, mForePaint);
    }
}
