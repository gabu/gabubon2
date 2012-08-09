
package net.gabuchan.androidrecipe.recipe104;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

import net.gabuchan.androidrecipe.R;
import android.app.Activity;
import android.os.Bundle;
import android.widget.TextView;

public class Recipe104Activity extends Activity {
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recipe_104);

        TextView textView = (TextView) findViewById(R.id.text_view);
        StringBuffer sb = new StringBuffer();
        try {
            sb.append("UTF-8 = ");
            sb.append(URLEncoder.encode("おはよう", "UTF-8"));
            sb.append("\n");
            sb.append("EUC-JP = ");
            sb.append(URLEncoder.encode("おはよう", "EUC-JP"));
            sb.append("\n");
            sb.append("SJIS = ");
            sb.append(URLEncoder.encode("おはよう", "SJIS"));
            textView.setText(sb);
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
    }
}
