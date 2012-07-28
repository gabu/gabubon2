
package net.gabuchan.androidrecipe;

import java.util.ArrayList;
import java.util.List;

import net.gabuchan.androidrecipe.recipe016.Recipe016Activity;
import net.gabuchan.androidrecipe.recipe017.Recipe017Activity;
import net.gabuchan.androidrecipe.recipe018.Recipe018Activity;
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
