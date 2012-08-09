
package net.gabuchan.androidrecipe.recipe113;

import net.gabuchan.androidrecipe.R;
import android.app.Activity;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.widget.TextView;

public class Recipe113Activity extends Activity {
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recipe_113);

        TextView textView = (TextView) findViewById(R.id.text_view);

        ConnectivityManager cm = (ConnectivityManager) getSystemService(CONNECTIVITY_SERVICE);
        NetworkInfo info = cm.getActiveNetworkInfo();
        if (info == null || info.isConnected() == false) {
            // オフライン。。。
            textView.setText("オフライン。。。");
        } else {
            // オンライン！
            textView.setText("オンライン！");
        }
    }
}
