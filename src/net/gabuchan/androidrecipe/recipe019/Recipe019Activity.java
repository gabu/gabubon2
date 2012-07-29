
package net.gabuchan.androidrecipe.recipe019;

import net.gabuchan.androidrecipe.R;
import android.app.Activity;
import android.graphics.Typeface;
import android.os.Bundle;
import android.text.Html;
import android.text.TextUtils.TruncateAt;
import android.text.method.LinkMovementMethod;
import android.view.View;
import android.widget.TextView;

public class Recipe019Activity extends Activity {
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recipe_019);

        TextView textView = (TextView) findViewById(R.id.ohisama);
        Typeface typeface = Typeface.createFromAsset(getAssets(), "OhisamaFont11.ttf");
        textView.setTypeface(typeface);

        TextView htmlTextView = (TextView) findViewById(R.id.html_text);
        htmlTextView.setMovementMethod(LinkMovementMethod.getInstance());

        String html = "こんにちは<font color='#ff0000'>@gabu</font>" +
                "これは<b>太字</b>だけど日本語フォントの太字は効かないことが" +
                "多いので英語で<b>test</b>" +
                "これは<a href='http://google.com'>リンク</a>です。";
        htmlTextView.setText(Html.fromHtml(html));
    }

    public void changeEllipsize(View view) {
        TextView textView = (TextView) findViewById(R.id.text_view);
        switch (view.getId()) {
            case R.id.none:
                textView.setEllipsize(null);
                break;
            case R.id.start:
                textView.setEllipsize(TruncateAt.START);
                break;
            case R.id.middle:
                textView.setEllipsize(TruncateAt.MIDDLE);
                break;
            case R.id.end:
                textView.setEllipsize(TruncateAt.END);
                break;
            default:
                break;
        }
    }
}
