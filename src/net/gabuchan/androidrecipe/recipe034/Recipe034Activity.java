
package net.gabuchan.androidrecipe.recipe034;

import net.gabuchan.androidrecipe.R;
import android.app.Activity;
import android.os.Bundle;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class Recipe034Activity extends Activity {
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recipe_034);
    }

    public void onLeftTopClick(View view) {
        Toast toast = Toast.makeText(this, ((Button) view).getText(), Toast.LENGTH_SHORT);
        toast.setGravity(Gravity.LEFT | Gravity.TOP, 0, 0);
        toast.show();
    }

    public void onCenterTopClick(View view) {
        Toast toast = Toast.makeText(this, ((Button) view).getText(), Toast.LENGTH_SHORT);
        toast.setGravity(Gravity.CENTER | Gravity.TOP, 0, 0);
        toast.show();
    }

    public void onRightTopClick(View view) {
        Toast toast = Toast.makeText(this, ((Button) view).getText(), Toast.LENGTH_SHORT);
        toast.setGravity(Gravity.RIGHT | Gravity.TOP, 0, 0);
        toast.show();
    }

    public void onLeftCenterClick(View view) {
        Toast toast = Toast.makeText(this, ((Button) view).getText(), Toast.LENGTH_SHORT);
        toast.setGravity(Gravity.LEFT | Gravity.CENTER, 0, 0);
        toast.show();
    }

    public void onCenterClick(View view) {
        Toast toast = Toast.makeText(this, ((Button) view).getText(), Toast.LENGTH_SHORT);
        toast.setGravity(Gravity.CENTER | Gravity.CENTER, 0, 0);
        toast.show();
    }

    public void onRightCenterClick(View view) {
        Toast toast = Toast.makeText(this, ((Button) view).getText(), Toast.LENGTH_SHORT);
        toast.setGravity(Gravity.RIGHT | Gravity.CENTER, 0, 0);
        toast.show();
    }

    public void onLeftBottomClick(View view) {
        Toast toast = Toast.makeText(this, ((Button) view).getText(), Toast.LENGTH_SHORT);
        toast.setGravity(Gravity.LEFT | Gravity.BOTTOM, 0, 0);
        toast.show();
    }

    public void onCenterBottomClick(View view) {
        Toast toast = Toast.makeText(this, ((Button) view).getText(), Toast.LENGTH_SHORT);
        toast.setGravity(Gravity.CENTER | Gravity.BOTTOM, 0, 0);
        toast.show();
    }

    public void onRightBottomClick(View view) {
        Toast toast = Toast.makeText(this, ((Button) view).getText(), Toast.LENGTH_SHORT);
        toast.setGravity(Gravity.RIGHT | Gravity.BOTTOM, 0, 0);
        toast.show();
    }

    public void onCustomClick(View v) {
        // レイアウトファイルからViewを生成して
        LayoutInflater inflater = getLayoutInflater();
        View view = inflater.inflate(R.layout.toast_recipe_034, null);

        // 生成したView内のTextViewを取得して
        TextView textView = (TextView) view.findViewById(R.id.text_view);
        // テキストをセット
        textView.setText("わんわん！");

        // トーストを単純に生成して
        Toast toast = new Toast(this);
        // 位置をセットして
        toast.setGravity(Gravity.CENTER, 0, 0);
        // 表示時間
        toast.setDuration(Toast.LENGTH_LONG);
        // レイアウトファイルから生成したViewをセット
        toast.setView(view);
        // 表示！
        toast.show();
    }
}
