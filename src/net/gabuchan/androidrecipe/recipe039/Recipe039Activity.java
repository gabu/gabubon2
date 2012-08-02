
package net.gabuchan.androidrecipe.recipe039;

import net.gabuchan.androidrecipe.R;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

public class Recipe039Activity extends Activity {
    // 選択肢
    private String[] items = new String[] {
            "いぬ", "ねこ", "ひつじ"
    };

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recipe_039);
    }

    public void onSelectClick(View view) {
        new AlertDialog.Builder(this)
                .setTitle("お好きな動物は？")
                .setItems(items, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        showToast(items[which]);
                    }
                })
                .create()
                .show();
    }

    public void onSingleClick(View view) {
        new AlertDialog.Builder(this)
                .setTitle("お好きな動物は？")
                .setSingleChoiceItems(items, 1, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        showToast(items[which]);
                    }
                })
                .setPositiveButton("OK", null)
                .create()
                .show();
    }

    public void onMultiClick(View view) {
        new AlertDialog.Builder(this)
                .setTitle("お好きな動物は？")
                .setMultiChoiceItems(items, new boolean[] {
                        false, true, false
                }, new DialogInterface.OnMultiChoiceClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which, boolean isChecked) {
                        showToast(items[which] + (isChecked ? "追加！" : "解除！"));
                    }
                })
                .setPositiveButton("OK", null)
                .create()
                .show();
    }

    private void showToast(String text) {
        Toast.makeText(this, text, Toast.LENGTH_SHORT).show();
    }

}
