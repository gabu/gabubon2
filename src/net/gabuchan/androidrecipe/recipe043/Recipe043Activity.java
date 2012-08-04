
package net.gabuchan.androidrecipe.recipe043;

import java.util.ArrayList;

import net.gabuchan.androidrecipe.R;
import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TabHost;
import android.widget.TabWidget;

public class Recipe043Activity extends FragmentActivity {
    TabHost mTabHost;
    ViewPager mViewPager;
    TabsAdapter mTabsAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recipe_043);

        // TabHostを取得して
        mTabHost = (TabHost) findViewById(android.R.id.tabhost);
        // セットアップ！
        mTabHost.setup();

        // ViewPagerを取得して
        mViewPager = (ViewPager) findViewById(R.id.pager);
        // TabHostとViewPagerを渡してTabsAdapterを生成
        mTabsAdapter = new TabsAdapter(this, mTabHost, mViewPager);
        // 1つ目のフラグメントを追加
        mTabsAdapter.addTab(mTabHost.newTabSpec("first").setIndicator("First"),
                FirstFragment.class, null);
        // 2つ目のフラグメントを追加
        mTabsAdapter.addTab(mTabHost.newTabSpec("second").setIndicator("Second"),
                SecondFragment.class, null);
        // 3つ目のフラグメントを追加
        mTabsAdapter.addTab(mTabHost.newTabSpec("third").setIndicator("Third"),
                ThirdFragment.class, null);

        if (savedInstanceState != null) {
            // 保存しておいたタグで現在のタブを指定
            mTabHost.setCurrentTabByTag(savedInstanceState.getString("tab"));
        }
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        // 現在のタブのタグを保存（回転してもタブの位置を保持するため）
        outState.putString("tab", mTabHost.getCurrentTabTag());
    }

    public static class TabsAdapter extends FragmentPagerAdapter
            implements TabHost.OnTabChangeListener, ViewPager.OnPageChangeListener {
        private final Context mContext;
        private final TabHost mTabHost;
        private final ViewPager mViewPager;
        private final ArrayList<TabInfo> mTabs = new ArrayList<TabInfo>();

        // タブの情報（タグとクラス名と引数）を保持するクラス
        static final class TabInfo {
            private final String tag;
            private final Class<?> clss;
            private final Bundle args;

            TabInfo(String _tag, Class<?> _class, Bundle _args) {
                tag = _tag;
                clss = _class;
                args = _args;
            }
        }

        // ダミーのタブのコンテントを作るTabContentFactory
        static class DummyTabFactory implements TabHost.TabContentFactory {
            private final Context mContext;

            public DummyTabFactory(Context context) {
                mContext = context;
            }

            @Override
            public View createTabContent(String tag) {
                // サイズ0のViewを返す
                View v = new View(mContext);
                v.setMinimumWidth(0);
                v.setMinimumHeight(0);
                return v;
            }
        }

        public TabsAdapter(FragmentActivity activity, TabHost tabHost, ViewPager pager) {
            super(activity.getSupportFragmentManager());
            mContext = activity;
            mTabHost = tabHost;
            mViewPager = pager;
            // タブが切り替わった時のリスナーをセット
            mTabHost.setOnTabChangedListener(this);
            // ViewPagerにアダプターとして自分をセット
            mViewPager.setAdapter(this);
            // ViewPagerのページが変わった時のリスナーをセット
            mViewPager.setOnPageChangeListener(this);
        }

        // タブを追加する
        public void addTab(TabHost.TabSpec tabSpec, Class<?> clss, Bundle args) {
            // ダミーをコンテントに追加
            tabSpec.setContent(new DummyTabFactory(mContext));
            // TabInfoに渡すタグ（使わないけど）
            String tag = tabSpec.getTag();
            // TabInfoを作って
            TabInfo info = new TabInfo(tag, clss, args);
            // TabInfoのListに追加
            mTabs.add(info);
            // TabHostにタブを追加！
            mTabHost.addTab(tabSpec);
            // データセットが変わったことを通知
            notifyDataSetChanged();
        }

        @Override
        public int getCount() {
            // タブを数を返す
            return mTabs.size();
        }

        @Override
        public Fragment getItem(int position) {
            // TabInfoのListから要素を取得
            TabInfo info = mTabs.get(position);
            // フラグメントを生成して返す
            return Fragment.instantiate(mContext, info.clss.getName(), info.args);
        }

        @Override
        public void onTabChanged(String tabId) {
            // タブが切り替わったら呼び出される
            // 現在のタブ位置を取得して
            int position = mTabHost.getCurrentTab();
            // ViewPagerの現在位置にセット
            mViewPager.setCurrentItem(position);
        }

        @Override
        public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
        }

        @Override
        public void onPageSelected(int position) {
            // ViewPagerのViewがスワイプで切り替わったら
            TabWidget widget = mTabHost.getTabWidget();
            int oldFocusability = widget.getDescendantFocusability();
            widget.setDescendantFocusability(ViewGroup.FOCUS_BLOCK_DESCENDANTS);
            // タブも切り替える
            mTabHost.setCurrentTab(position);
            widget.setDescendantFocusability(oldFocusability);
        }

        @Override
        public void onPageScrollStateChanged(int state) {
        }
    }
}
