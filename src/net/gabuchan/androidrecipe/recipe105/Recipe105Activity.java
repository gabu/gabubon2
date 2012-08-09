
package net.gabuchan.androidrecipe.recipe105;

import net.gabuchan.androidrecipe.R;
import android.app.Activity;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class Recipe105Activity extends Activity {
    private TextView mTextView;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recipe_105);

        mTextView = (TextView) findViewById(R.id.text_view);
    }

    public void onSaveClick(View view) {
        // プリファレンスを取得
        SharedPreferences sp = getPreferences(MODE_PRIVATE);

        // 編集するためのEditorを取得
        Editor editor = sp.edit();

        // 型に合わせたputメソッドでキーと値をセット
        editor.putBoolean("ex_boolean", true);
        editor.putFloat("ex_float", 3.14f);
        editor.putInt("ex_int", 123);
        editor.putLong("ex_long", 9999);
        editor.putString("ex_string", "gabu");

        // 保存！
        editor.commit();
    }

    public void onLoadClick(View view) {
        // プリファレンスを取得
        SharedPreferences sp = getPreferences(MODE_PRIVATE);

        // 型に合わせたgetメソッドで値を取得
        boolean ex_boolean = sp.getBoolean("ex_boolean", false);
        float ex_float = sp.getFloat("ex_float", 0);
        int ex_int = sp.getInt("ex_int", 0);
        long ex_long = sp.getLong("ex_long", 0);
        String ex_string = sp.getString("ex_string", "");

        StringBuffer sb = new StringBuffer();
        sb.append("ex_boolean = ");
        sb.append(ex_boolean);
        sb.append("\n");
        sb.append("ex_float = ");
        sb.append(ex_float);
        sb.append("\n");
        sb.append("ex_int = ");
        sb.append(ex_int);
        sb.append("\n");
        sb.append("ex_long = ");
        sb.append(ex_long);
        sb.append("\n");
        sb.append("ex_string = ");
        sb.append(ex_string);
        sb.append("\n");
        mTextView.setText(sb);
    }
}
