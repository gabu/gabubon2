
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
