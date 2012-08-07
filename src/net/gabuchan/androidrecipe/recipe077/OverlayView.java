
package net.gabuchan.androidrecipe.recipe077;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.view.View;
import android.view.ViewGroup.LayoutParams;

public class OverlayView extends View {
    private Paint mPaint;

    public OverlayView(Context context, int width, int height) {
        super(context);
        // このViewのサイズをセット
        setLayoutParams(new LayoutParams(width, height));
        // Paintを作る
        mPaint = new Paint();
        // 線の色を赤にセット
        mPaint.setColor(Color.RED);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        float endX = canvas.getWidth();
        float centerX = endX / 2.0f;
        float endY = canvas.getHeight();
        float centerY = endY / 2.0f;

        // 横線
        canvas.drawLine(0, centerY, endX, centerY, mPaint);
        // 縦線
        canvas.drawLine(centerX, 0, centerX, endY, mPaint);
    }
}
