
package net.gabuchan.androidrecipe.recipe107;

import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import net.gabuchan.androidrecipe.R;
import android.app.Activity;
import android.os.Bundle;
import android.util.Log;

public class Recipe107Activity extends Activity {
    private static final String TAG = "AndroidRecipe";

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recipe_107);

        Log.d(TAG, md5("is same?"));
        Log.d(TAG, md5("is same?"));
        Log.d(TAG, md5("other strings."));
    }
    
    private static String md5(String input) {
        try {
            MessageDigest md = MessageDigest.getInstance("MD5");
            byte[] digest = md.digest(input.getBytes());
            BigInteger number = new BigInteger(1, digest);
            String md5 = number.toString(16);

            while (md5.length() < 32)
                md5 = "0" + md5;

            return md5;
        } catch (NoSuchAlgorithmException e) {
            Log.e("MD5", e.getMessage());
            return null;
        }
    }
}
