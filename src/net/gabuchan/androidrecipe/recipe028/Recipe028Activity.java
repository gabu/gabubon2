
package net.gabuchan.androidrecipe.recipe028;

import net.gabuchan.androidrecipe.R;
import android.app.Activity;
import android.graphics.Typeface;
import android.os.Bundle;
import android.widget.DigitalClock;

public class Recipe028Activity extends Activity {
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recipe_028);

        DigitalClock digitalClock = (DigitalClock) findViewById(R.id.digital_clock);
        Typeface typeface = Typeface.createFromAsset(getAssets(), "OhisamaFont11.ttf");
        digitalClock.setTypeface(typeface);
    }
}
