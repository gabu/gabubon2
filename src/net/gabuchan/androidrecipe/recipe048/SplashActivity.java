
package net.gabuchan.androidrecipe.recipe048;

import net.gabuchan.androidrecipe.MainActivity;
import net.gabuchan.androidrecipe.R;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.widget.ImageView;
import android.widget.ImageView.ScaleType;

public class SplashActivity extends Activity {
    private Handler mHandler = new Handler();
    
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ImageView imageView = new ImageView(getApplicationContext());
        imageView.setImageResource(R.drawable.gabubon_splash);
        imageView.setScaleType(ScaleType.CENTER_CROP);
        setContentView(imageView);
        
        mHandler.postDelayed(new Runnable() {
            
            @Override
            public void run() {
                // MainActivityを呼び出して
                Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                startActivity(intent);
                // 自分は終了する
                SplashActivity.this.finish();
            }
        }, 2 * 1000); // 2000ミリ秒後（2秒後）に実行
    }
}
