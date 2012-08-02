
package net.gabuchan.androidrecipe.recipe038;

import net.gabuchan.androidrecipe.R;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

public class Recipe038Activity extends Activity {
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recipe_038);
    }

    public void onSimpleClick(View view) {
        new AlertDialog.Builder(this)
                .setMessage("ダウンロードが完了しました！")
                .setPositiveButton("OK", null)
                .create()
                .show();
    }

    public void onWithTitleClick(View view) {
        new AlertDialog.Builder(this)
                .setTitle("お待たせしました！")
                .setMessage("ダウンロードが完了しました！")
                .setPositiveButton("OK", null)
                .create()
                .show();
    }

    public void onTwoClick(View view) {
        new AlertDialog.Builder(this)
                .setTitle("質問です！")
                .setMessage("Androidは好きですか？")
                .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        showToast("ヽ(´ー｀)ノ");
                    }
                })
                .setNegativeButton("No", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        showToast("(´・ω・`)");
                    }
                })
                .create()
                .show();
    }

    public void onThreeClick(View view) {
        new AlertDialog.Builder(this)
                .setTitle("質問です！")
                .setMessage("AndroidとiOSのどちらが好きですか？")
                .setPositiveButton("Android", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        showToast("ですよね！");
                    }
                })
                .setNegativeButton("iOS", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        showToast("僕もです！");
                    }
                })
                .setNeutralButton("どちらでもない", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        showToast("Windows Phone派かー！");
                    }
                })
                .create()
                .show();
    }

    private void showToast(String text) {
        Toast.makeText(this, text, Toast.LENGTH_SHORT).show();
    }

}
