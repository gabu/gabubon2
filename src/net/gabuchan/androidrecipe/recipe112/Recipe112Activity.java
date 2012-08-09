
package net.gabuchan.androidrecipe.recipe112;

import net.gabuchan.androidrecipe.R;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentTransaction;

public class Recipe112Activity extends FragmentActivity {
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recipe_112);

        // フラグメントを追加するためにトランザクション開始
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        // 連絡先の一覧を表示するフラグメントを生成
        ContactsListFragment fragment = new ContactsListFragment();
        // フラグメントを追加
        transaction.add(R.id.fragment_container, fragment);
        // コミット
        transaction.commit();
    }
}
