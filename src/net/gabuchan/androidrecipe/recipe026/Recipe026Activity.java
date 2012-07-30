
package net.gabuchan.androidrecipe.recipe026;

import net.gabuchan.androidrecipe.R;
import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.SeekBar;
import android.widget.SeekBar.OnSeekBarChangeListener;
import android.widget.TextView;
import android.widget.Toast;

public class Recipe026Activity extends Activity {
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recipe_026);

        final TextView textView = (TextView) findViewById(R.id.text_view);

        SeekBar seekBar = (SeekBar) findViewById(R.id.seek_bar);
        seekBar.setOnSeekBarChangeListener(new OnSeekBarChangeListener() {

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
                // ユーザがドラッグを終了した時に呼び出されます
                showToast("onStopTrackingTouch");
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {
                // ユーザがドラッグを開始した時に呼び出されます
                showToast("onStartTrackingTouch");
            }

            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                // ユーザがドラッグ中、つまり値が変化している時に何度も呼び出されます
                textView.setText(String.valueOf(progress));
            }
        });
    }

    public void onButtonClick(View view) {
        SeekBar seekBar = (SeekBar) findViewById(R.id.seek_bar);
        int progress = seekBar.getProgress();
        showToast(String.valueOf(progress));
    }

    private void showToast(String text) {
        Toast.makeText(this, text, Toast.LENGTH_SHORT).show();
    }
}
