
package net.gabuchan.androidrecipe.recipe049;

import net.gabuchan.androidrecipe.R;
import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.view.animation.AnimationSet;
import android.view.animation.RotateAnimation;
import android.view.animation.ScaleAnimation;
import android.view.animation.TranslateAnimation;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.Spinner;

public class Recipe049Activity extends Activity {
    Spinner mSpinner;
    ImageView mImageView;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recipe_049);

        String[] data = {
                "accelerate_decelerate_interpolator",
                "accelerate_interpolator",
                "anticipate_interpolator",
                "anticipate_overshoot_interpolator",
                "bounce_interpolator",
                // "cycle_interpolator",
                "decelerate_interpolator",
                // "fade_in",
                // "fade_out",
                "linear_interpolator",
                "overshoot_interpolator",
                // "slide_in_left",
                // "slide_out_right"
        };
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,
                android.R.layout.simple_spinner_item, data);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        mSpinner = (Spinner) findViewById(R.id.spinner);
        mSpinner.setAdapter(adapter);

        mImageView = (ImageView) findViewById(R.id.image_view);
        mImageView.setImageResource(R.drawable.gabu);
    }

    public void startMix(View view) {
        AnimationSet animation = new AnimationSet(true);
        // 透明になりながら
        animation.addAnimation(new AlphaAnimation(1.0F, 0.0F));
        // 回転しながら
        animation.addAnimation(new RotateAnimation(0, 360));
        // 拡大しながら
        animation.addAnimation(new ScaleAnimation(1, 2, 1, 2));
        // 移動しながら
        animation.addAnimation(new TranslateAnimation(0, 300, 0, 300));
        // 3秒で
        animation.setDuration(3000);
        mImageView.startAnimation(animation);
    }

    public void startAlpha(View view) {
        AlphaAnimation animation;
        animation = new AlphaAnimation(1.0F, 0.0F);
        // 3秒で
        animation.setDuration(3000);
        mImageView.startAnimation(animation);
    }

    public void startScale(View view) {
        ScaleAnimation animation;
        animation = new ScaleAnimation(1, 2, 1, 2, mImageView.getWidth() / 2.0F,
                mImageView.getHeight() / 2.0F);
        // 3秒で
        animation.setDuration(3000);
        animation.setInterpolator(this, android.R.anim.bounce_interpolator);
        mImageView.startAnimation(animation);
    }

    public void startRotate(View view) {
        RotateAnimation animation;
        animation = new RotateAnimation(0, 360, mImageView.getWidth() / 2.0F,
                mImageView.getHeight() / 2.0F);
        // 3秒で
        animation.setDuration(3000);
        mImageView.startAnimation(animation);
    }

    public void onClick(View view) {
        int position = mSpinner.getSelectedItemPosition();
        int interpolator = 0;
        switch (position) {
            case 0:
                interpolator = android.R.anim.accelerate_decelerate_interpolator;
                break;
            case 1:
                interpolator = android.R.anim.accelerate_interpolator;
                break;
            case 2:
                interpolator = android.R.anim.anticipate_interpolator;
                break;
            case 3:
                interpolator = android.R.anim.anticipate_overshoot_interpolator;
                break;
            case 4:
                interpolator = android.R.anim.bounce_interpolator;
                break;
            case 5:
                interpolator = android.R.anim.decelerate_interpolator;
                break;
            case 6:
                interpolator = android.R.anim.linear_interpolator;
                break;
            case 7:
                interpolator = android.R.anim.overshoot_interpolator;
                break;
        }
        translate(interpolator);
    }

    private void translate(int interpolator) {
        Animation animation = new TranslateAnimation(0, 200, 0, 200);
        animation.setDuration(1000);
        animation.setInterpolator(this, interpolator);

        // 無限に繰り返す
        // translate.setRepeatCount(-1);
        // 4回繰り返す(0を指定した場合1回なので+1回繰り返される）
        // translate.setRepeatCount(3);
        // 元に戻さない（終了状態を維持する）
        // translate.setFillAfter(true);

        // アニメーションスタート
        mImageView.startAnimation(animation);
    }
}
