
package net.gabuchan.androidrecipe.recipe088;

import net.gabuchan.androidrecipe.R;
import android.app.Activity;
import android.location.Criteria;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

public class Recipe088Activity extends Activity {
    private LocationManager mLocationManager;
    private TextView mTextView;

    private LocationListener mListener = new LocationListener() {
        @Override
        public void onStatusChanged(String provider, int status, Bundle extras) {
            // プロバイダの状態が変化したら呼び出される。
        }

        @Override
        public void onProviderEnabled(String provider) {
            // プロバイダが有効になったら呼び出される。
        }

        @Override
        public void onProviderDisabled(String provider) {
            // プロバイダが無効になったら呼び出される。
        }

        @Override
        public void onLocationChanged(Location location) {
            // 位置情報が取得できるたびに呼び出される。
            double latitude = location.getLatitude();
            double longitude = location.getLongitude();
            mTextView.setText("緯度 = " + latitude + ", 経度 = " + longitude);
        }
    };

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recipe_085);

        // TextViewを取得
        mTextView = (TextView) findViewById(R.id.text_view);
    }

    @Override
    protected void onResume() {
        super.onResume();

        // LocationManagerを取得
        mLocationManager = (LocationManager) getSystemService(LOCATION_SERVICE);
        // 現在の状況に最適なプロバイダを取得します。
        // Criteriaで細かい条件が指定できますが今回はデフォルトで
        String provider = mLocationManager.getBestProvider(new Criteria(), true);
        if (provider == null) {
            // 位置情報が取得できるプロバイダがありません。
            // 位置情報の設定がOFFになっているなど。
            Toast.makeText(this, "位置情報の設定がOFFになっていると位置情報は取得できません",
                    Toast.LENGTH_SHORT).show();
        } else {
            // リスナーをセットして位置情報の取得を開始
            mLocationManager.requestLocationUpdates(provider, 0, 0, mListener);
        }
    }

    @Override
    protected void onPause() {
        super.onPause();
        // リスナーを解除
        mLocationManager.removeUpdates(mListener);
    }
}
