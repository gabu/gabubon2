
package net.gabuchan.androidrecipe;

import java.util.ArrayList;
import java.util.List;

import net.gabuchan.androidrecipe.recipe016.Recipe016Activity;
import net.gabuchan.androidrecipe.recipe017.Recipe017Activity;
import net.gabuchan.androidrecipe.recipe018.Recipe018Activity;
import net.gabuchan.androidrecipe.recipe019.Recipe019Activity;
import net.gabuchan.androidrecipe.recipe020.Recipe020Activity;
import net.gabuchan.androidrecipe.recipe021.Recipe021Activity;
import net.gabuchan.androidrecipe.recipe022.Recipe022Activity;
import net.gabuchan.androidrecipe.recipe023.Recipe023Activity;
import net.gabuchan.androidrecipe.recipe024.Recipe024Activity;
import net.gabuchan.androidrecipe.recipe025.Recipe025Activity;
import net.gabuchan.androidrecipe.recipe026.Recipe026Activity;
import net.gabuchan.androidrecipe.recipe027.Recipe027Activity;
import net.gabuchan.androidrecipe.recipe028.Recipe028Activity;
import net.gabuchan.androidrecipe.recipe029.Recipe029Activity;
import net.gabuchan.androidrecipe.recipe030.Recipe030Activity;
import net.gabuchan.androidrecipe.recipe031.Recipe031Activity;
import net.gabuchan.androidrecipe.recipe032.Recipe032Activity;
import net.gabuchan.androidrecipe.recipe033.Recipe033Activity;
import net.gabuchan.androidrecipe.recipe034.Recipe034Activity;
import net.gabuchan.androidrecipe.recipe035.Recipe035Activity;
import net.gabuchan.androidrecipe.recipe036.Recipe036Activity;
import net.gabuchan.androidrecipe.recipe037.Recipe037Activity;
import net.gabuchan.androidrecipe.recipe038.Recipe038Activity;
import net.gabuchan.androidrecipe.recipe039.Recipe039Activity;
import net.gabuchan.androidrecipe.recipe040.Recipe040Activity;
import net.gabuchan.androidrecipe.recipe041.Recipe041Activity;
import net.gabuchan.androidrecipe.recipe042.Recipe042Activity;
import net.gabuchan.androidrecipe.recipe043.Recipe043Activity;
import net.gabuchan.androidrecipe.recipe044.Recipe044Activity;
import net.gabuchan.androidrecipe.recipe045.Recipe045Activity;
import net.gabuchan.androidrecipe.recipe046.Recipe046Activity;
import net.gabuchan.androidrecipe.recipe047.Recipe047Activity;
import net.gabuchan.androidrecipe.recipe048.Recipe048Activity;
import net.gabuchan.androidrecipe.recipe049.Recipe049Activity;
import net.gabuchan.androidrecipe.recipe050.Recipe050Activity;
import net.gabuchan.androidrecipe.recipe051.Recipe051Activity;
import net.gabuchan.androidrecipe.recipe052.Recipe052Activity;
import net.gabuchan.androidrecipe.recipe053.Recipe053Activity;
import net.gabuchan.androidrecipe.recipe054.Recipe054Activity;
import net.gabuchan.androidrecipe.recipe056.Recipe056Activity;
import net.gabuchan.androidrecipe.recipe057.Recipe057Activity;
import net.gabuchan.androidrecipe.recipe058.Recipe058Activity;
import net.gabuchan.androidrecipe.recipe059.Recipe059Activity;
import net.gabuchan.androidrecipe.recipe060.Recipe060Activity;
import net.gabuchan.androidrecipe.recipe061.Recipe061Activity;
import net.gabuchan.androidrecipe.recipe062.Recipe062Activity;
import net.gabuchan.androidrecipe.recipe063.Recipe063Activity;
import net.gabuchan.androidrecipe.recipe064.Recipe064Activity;
import net.gabuchan.androidrecipe.recipe065.Recipe065Activity;
import net.gabuchan.androidrecipe.recipe066.Recipe066Activity;
import net.gabuchan.androidrecipe.recipe067.Recipe067Activity;
import net.gabuchan.androidrecipe.recipe068.Recipe068Activity;
import net.gabuchan.androidrecipe.recipe069.Recipe069Activity;
import net.gabuchan.androidrecipe.recipe070.Recipe070Activity;
import android.app.ListActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;

public class MainActivity extends ListActivity {
    List<Recipe> mRecipes = new ArrayList<Recipe>();

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        mRecipes.add(new Recipe(R.string.recipe_016_title, Recipe016Activity.class));
        mRecipes.add(new Recipe(R.string.recipe_017_title, Recipe017Activity.class));
        mRecipes.add(new Recipe(R.string.recipe_018_title, Recipe018Activity.class));
        mRecipes.add(new Recipe(R.string.recipe_019_title, Recipe019Activity.class));
        mRecipes.add(new Recipe(R.string.recipe_020_title, Recipe020Activity.class));
        mRecipes.add(new Recipe(R.string.recipe_021_title, Recipe021Activity.class));
        mRecipes.add(new Recipe(R.string.recipe_022_title, Recipe022Activity.class));
        mRecipes.add(new Recipe(R.string.recipe_023_title, Recipe023Activity.class));
        mRecipes.add(new Recipe(R.string.recipe_024_title, Recipe024Activity.class));
        mRecipes.add(new Recipe(R.string.recipe_025_title, Recipe025Activity.class));
        mRecipes.add(new Recipe(R.string.recipe_026_title, Recipe026Activity.class));
        mRecipes.add(new Recipe(R.string.recipe_027_title, Recipe027Activity.class));
        mRecipes.add(new Recipe(R.string.recipe_028_title, Recipe028Activity.class));
        mRecipes.add(new Recipe(R.string.recipe_029_title, Recipe029Activity.class));
        mRecipes.add(new Recipe(R.string.recipe_030_title, Recipe030Activity.class));
        mRecipes.add(new Recipe(R.string.recipe_031_title, Recipe031Activity.class));
        mRecipes.add(new Recipe(R.string.recipe_032_title, Recipe032Activity.class));
        mRecipes.add(new Recipe(R.string.recipe_033_title, Recipe033Activity.class));
        mRecipes.add(new Recipe(R.string.recipe_034_title, Recipe034Activity.class));
        mRecipes.add(new Recipe(R.string.recipe_035_title, Recipe035Activity.class));
        mRecipes.add(new Recipe(R.string.recipe_036_title, Recipe036Activity.class));
        mRecipes.add(new Recipe(R.string.recipe_037_title, Recipe037Activity.class));
        mRecipes.add(new Recipe(R.string.recipe_038_title, Recipe038Activity.class));
        mRecipes.add(new Recipe(R.string.recipe_039_title, Recipe039Activity.class));
        mRecipes.add(new Recipe(R.string.recipe_040_title, Recipe040Activity.class));
        mRecipes.add(new Recipe(R.string.recipe_041_title, Recipe041Activity.class));
        mRecipes.add(new Recipe(R.string.recipe_042_title, Recipe042Activity.class));
        mRecipes.add(new Recipe(R.string.recipe_043_title, Recipe043Activity.class));
        mRecipes.add(new Recipe(R.string.recipe_044_title, Recipe044Activity.class));
        mRecipes.add(new Recipe(R.string.recipe_045_title, Recipe045Activity.class));
        mRecipes.add(new Recipe(R.string.recipe_046_title, Recipe046Activity.class));
        mRecipes.add(new Recipe(R.string.recipe_047_title, Recipe047Activity.class));
        mRecipes.add(new Recipe(R.string.recipe_048_title, Recipe048Activity.class));
        mRecipes.add(new Recipe(R.string.recipe_049_title, Recipe049Activity.class));
        mRecipes.add(new Recipe(R.string.recipe_050_title, Recipe050Activity.class));
        mRecipes.add(new Recipe(R.string.recipe_051_title, Recipe051Activity.class));
        mRecipes.add(new Recipe(R.string.recipe_052_title, Recipe052Activity.class));
        mRecipes.add(new Recipe(R.string.recipe_053_title, Recipe053Activity.class));
        mRecipes.add(new Recipe(R.string.recipe_054_title, Recipe054Activity.class));
        mRecipes.add(new Recipe(R.string.recipe_056_title, Recipe056Activity.class));
        mRecipes.add(new Recipe(R.string.recipe_057_title, Recipe057Activity.class));
        mRecipes.add(new Recipe(R.string.recipe_058_title, Recipe058Activity.class));
        mRecipes.add(new Recipe(R.string.recipe_059_title, Recipe059Activity.class));
        mRecipes.add(new Recipe(R.string.recipe_060_title, Recipe060Activity.class));
        mRecipes.add(new Recipe(R.string.recipe_061_title, Recipe061Activity.class));
        mRecipes.add(new Recipe(R.string.recipe_062_title, Recipe062Activity.class));
        mRecipes.add(new Recipe(R.string.recipe_063_title, Recipe063Activity.class));
        mRecipes.add(new Recipe(R.string.recipe_064_title, Recipe064Activity.class));
        mRecipes.add(new Recipe(R.string.recipe_065_title, Recipe065Activity.class));
        mRecipes.add(new Recipe(R.string.recipe_066_title, Recipe066Activity.class));
        mRecipes.add(new Recipe(R.string.recipe_067_title, Recipe067Activity.class));
        mRecipes.add(new Recipe(R.string.recipe_068_title, Recipe068Activity.class));
        mRecipes.add(new Recipe(R.string.recipe_069_title, Recipe069Activity.class));
        mRecipes.add(new Recipe(R.string.recipe_070_title, Recipe070Activity.class));

        List<String> titles = new ArrayList<String>();
        for (Recipe recipe : mRecipes) {
            titles.add(getString(recipe.resId));
        }

        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,
                android.R.layout.simple_list_item_1, titles);
        setListAdapter(adapter);
    }

    @Override
    protected void onListItemClick(ListView l, View v, int position, long id) {
        super.onListItemClick(l, v, position, id);
        startActivity(new Intent(this, mRecipes.get(position).cls));
    }

    class Recipe {
        int resId;
        Class<?> cls;

        Recipe(int resId, Class<?> cls) {
            this.resId = resId;
            this.cls = cls;
        }
    }
}
