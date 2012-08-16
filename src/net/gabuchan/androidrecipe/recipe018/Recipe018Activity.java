
package net.gabuchan.androidrecipe.recipe018;

import net.gabuchan.androidrecipe.R;
import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.ImageView.ScaleType;
import android.widget.ViewSwitcher;

public class Recipe018Activity extends Activity {
    private ImageView mImageView;
    private int mCurrentImageResId = R.drawable.gabu;
    
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recipe_018);
        
        mImageView = (ImageView) findViewById(R.id.landscape_image_view);
    }

    public void changeScaleType(View view) {
        switch (view.getId()) {
            case R.id.center:
                mImageView.setScaleType(ScaleType.CENTER);
                break;
            case R.id.center_crop:
                mImageView.setScaleType(ScaleType.CENTER_CROP);
                break;
            case R.id.center_inside:
                mImageView.setScaleType(ScaleType.CENTER_INSIDE);
                break;
            case R.id.fit_center:
                mImageView.setScaleType(ScaleType.FIT_CENTER);
                break;
            case R.id.fit_end:
                mImageView.setScaleType(ScaleType.FIT_END);
                break;
            case R.id.fit_start:
                mImageView.setScaleType(ScaleType.FIT_START);
                break;
            case R.id.fit_xy:
                mImageView.setScaleType(ScaleType.FIT_XY);
                break;
            default:
                break;
        }
    }
    
    public void switchImageView(View view) {
        ViewSwitcher switcher = (ViewSwitcher) findViewById(R.id.view_switcher);
        mImageView = (ImageView) switcher.getNextView();
        mImageView.setImageResource(mCurrentImageResId);
        switcher.showNext();
    }
    
    public void switchImage(View view) {
        if (mCurrentImageResId == R.drawable.gabu) {
            mCurrentImageResId = R.drawable.image_720x720;
        } else {
            mCurrentImageResId = R.drawable.gabu;
        }
        mImageView.setImageResource(mCurrentImageResId);
    }
}
