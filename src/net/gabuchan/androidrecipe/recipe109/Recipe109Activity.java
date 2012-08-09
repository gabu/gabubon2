
package net.gabuchan.androidrecipe.recipe109;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;

import net.gabuchan.androidrecipe.R;
import android.app.Activity;
import android.os.Bundle;
import android.os.Environment;
import android.view.View;
import android.widget.Toast;

public class Recipe109Activity extends Activity {
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recipe_109);
    }

    public void onButtonClick(View view) {
        try {
            InputStream stream = getResources().getAssets().open("recipe_109.zip");
            File outDir = new File(Environment.getExternalStorageDirectory(), "gabubon2");
            unzip(stream, outDir);
            showToast(outDir.getAbsolutePath() + "に解凍しました！");
        } catch (IOException e) {
            e.printStackTrace();
            showToast("解凍に失敗しました。" + e.getMessage());
        }
    }

    /**
     * ZIPファイルを解凍します。
     * 
     * @param inFile ZIPファイル
     * @param outDir 解凍先のディレクトリ
     * @throws IOException 入力ファイルが見つからないなど解凍に失敗した時
     */
    private void unzip(File inFile, File outDir) throws IOException {
        unzip(new FileInputStream(inFile), outDir);
    }

    /**
     * ZIPファイルを解凍します。
     * 
     * @param stream ZIPファイルの入力ストリーム
     * @param outDir 解凍先のディレクトリ
     * @throws IOException 解凍に失敗した場合
     */
    private void unzip(InputStream stream, File outDir) throws IOException {
        // 出力ディレクトリがなければ作る
        if (outDir.exists() == false) {
            outDir.mkdir();
        }

        BufferedInputStream bin = null;
        BufferedOutputStream bout = null;
        ZipInputStream zis = new ZipInputStream(new BufferedInputStream(stream));
        try {
            ZipEntry entry;
            while ((entry = zis.getNextEntry()) != null) {
                // 出力ファイル
                File outFile = new File(outDir, entry.getName());
                // ディレクトリの場合は、ディレクトリを作成
                if (entry.isDirectory()) {
                    outFile.mkdir();
                } else {
                    // ファイルの場合は、ファイルを作成
                    // 入力
                    bin = new BufferedInputStream(zis);
                    // 出力
                    bout = new BufferedOutputStream(
                            new FileOutputStream(outFile));
                    // 入力から出力へ
                    int bytedata = 0;
                    while ((bytedata = bin.read()) != -1) {
                        bout.write(bytedata);
                    }
                    bout.flush();
                }
            }
        } finally {
            try {
                if (zis != null)
                    zis.close();
                if (bin != null)
                    bin.close();
                if (bout != null)
                    bout.close();
            } catch (IOException e) {
            }
        }
    }

    private void showToast(String text) {
        Toast.makeText(this, text, Toast.LENGTH_LONG).show();
    }
}
