
package net.gabuchan.androidrecipe.recipe041;

import net.gabuchan.androidrecipe.R;
import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.TextView;

public class Recipe041Activity extends Activity {

    private class Icon {
        int resId;
        String name;

        Icon(int resId, String name) {
            this.resId = resId;
            this.name = name;
        }
    }

    private Icon[] mIcons = {
            new Icon(android.R.drawable.ic_btn_speak_now, "ic_btn_speak_now"),
            new Icon(android.R.drawable.ic_delete, "ic_delete"),
            new Icon(android.R.drawable.ic_dialog_alert, "ic_dialog_alert"),
            new Icon(android.R.drawable.ic_dialog_dialer, "ic_dialog_dialer"),
            new Icon(android.R.drawable.ic_dialog_email, "ic_dialog_email"),
            new Icon(android.R.drawable.ic_dialog_info, "ic_dialog_info"),
            new Icon(android.R.drawable.ic_dialog_map, "ic_dialog_map"),
            new Icon(android.R.drawable.ic_input_add, "ic_input_add"),
            new Icon(android.R.drawable.ic_input_delete, "ic_input_delete"),
            new Icon(android.R.drawable.ic_input_get, "ic_input_get"),
            new Icon(android.R.drawable.ic_lock_idle_alarm, "ic_lock_idle_alarm"),
            new Icon(android.R.drawable.ic_lock_idle_charging, "ic_lock_idle_charging"),
            new Icon(android.R.drawable.ic_lock_idle_lock, "ic_lock_idle_lock"),
            new Icon(android.R.drawable.ic_lock_idle_low_battery, "ic_lock_idle_low_battery"),
            new Icon(android.R.drawable.ic_lock_lock, "ic_lock_lock"),
            new Icon(android.R.drawable.ic_lock_power_off, "ic_lock_power_off"),
            new Icon(android.R.drawable.ic_lock_silent_mode, "ic_lock_silent_mode"),
            new Icon(android.R.drawable.ic_lock_silent_mode_off, "ic_lock_silent_mode_off"),
            new Icon(android.R.drawable.ic_media_ff, "ic_media_ff"),
            new Icon(android.R.drawable.ic_media_next, "ic_media_next"),
            new Icon(android.R.drawable.ic_media_pause, "ic_media_pause"),
            new Icon(android.R.drawable.ic_media_play, "ic_media_play"),
            new Icon(android.R.drawable.ic_media_previous, "ic_media_previous"),
            new Icon(android.R.drawable.ic_media_rew, "ic_media_rew"),
            new Icon(android.R.drawable.ic_menu_add, "ic_menu_add"),
            new Icon(android.R.drawable.ic_menu_agenda, "ic_menu_agenda"),
            new Icon(android.R.drawable.ic_menu_always_landscape_portrait,
                    "ic_menu_always_landscape_portrait"),
            new Icon(android.R.drawable.ic_menu_call, "ic_menu_call"),
            new Icon(android.R.drawable.ic_menu_camera, "ic_menu_camera"),
            new Icon(android.R.drawable.ic_menu_close_clear_cancel, "ic_menu_close_clear_cancel"),
            new Icon(android.R.drawable.ic_menu_compass, "ic_menu_compass"),
            new Icon(android.R.drawable.ic_menu_crop, "ic_menu_crop"),
            new Icon(android.R.drawable.ic_menu_day, "ic_menu_day"),
            new Icon(android.R.drawable.ic_menu_delete, "ic_menu_delete"),
            new Icon(android.R.drawable.ic_menu_directions, "ic_menu_directions"),
            new Icon(android.R.drawable.ic_menu_edit, "ic_menu_edit"),
            new Icon(android.R.drawable.ic_menu_gallery, "ic_menu_edit"),
            new Icon(android.R.drawable.ic_menu_help, "ic_menu_help"),
            new Icon(android.R.drawable.ic_menu_info_details, "ic_menu_info_details"),
            new Icon(android.R.drawable.ic_menu_manage, "ic_menu_manage"),
            new Icon(android.R.drawable.ic_menu_mapmode, "ic_menu_mapmode"),
            new Icon(android.R.drawable.ic_menu_month, "ic_menu_month"),
            new Icon(android.R.drawable.ic_menu_more, "ic_menu_more"),
            new Icon(android.R.drawable.ic_menu_my_calendar, "ic_menu_my_calendar"),
            new Icon(android.R.drawable.ic_menu_mylocation, "ic_menu_mylocation"),
            new Icon(android.R.drawable.ic_menu_myplaces, "ic_menu_myplaces"),
            new Icon(android.R.drawable.ic_menu_preferences, "ic_menu_preferences"),
            new Icon(android.R.drawable.ic_menu_recent_history, "ic_menu_recent_history"),
            new Icon(android.R.drawable.ic_menu_report_image, "ic_menu_recent_history"),
            new Icon(android.R.drawable.ic_menu_revert, "ic_menu_recent_history"),
            new Icon(android.R.drawable.ic_menu_rotate, "ic_menu_rotate"),
            new Icon(android.R.drawable.ic_menu_save, "ic_menu_save"),
            new Icon(android.R.drawable.ic_menu_search, "ic_menu_search"),
            new Icon(android.R.drawable.ic_menu_send, "ic_menu_send"),
            new Icon(android.R.drawable.ic_menu_set_as, "ic_menu_set_as"),
            new Icon(android.R.drawable.ic_menu_share, "ic_menu_share"),
            new Icon(android.R.drawable.ic_menu_slideshow, "ic_menu_share"),
            new Icon(android.R.drawable.ic_menu_sort_alphabetically, "ic_menu_sort_alphabetically"),
            new Icon(android.R.drawable.ic_menu_sort_by_size, "ic_menu_sort_by_size"),
            new Icon(android.R.drawable.ic_menu_today, "ic_menu_sort_by_size"),
            new Icon(android.R.drawable.ic_menu_upload, "ic_menu_upload"),
            new Icon(android.R.drawable.ic_menu_upload_you_tube, "ic_menu_upload_you_tube"),
            new Icon(android.R.drawable.ic_menu_view, "ic_menu_view"),
            new Icon(android.R.drawable.ic_menu_week, "ic_menu_week"),
            new Icon(android.R.drawable.ic_menu_zoom, "ic_menu_zoom"),
            new Icon(android.R.drawable.ic_notification_clear_all, "ic_notification_clear_all"),
            new Icon(android.R.drawable.ic_notification_overlay, "ic_notification_overlay"),
            new Icon(android.R.drawable.ic_partial_secure, "ic_partial_secure"),
            new Icon(android.R.drawable.ic_popup_disk_full, "ic_popup_disk_full"),
            new Icon(android.R.drawable.ic_popup_reminder, "ic_popup_reminder"),
            new Icon(android.R.drawable.ic_popup_sync, "ic_popup_reminder"),
            new Icon(android.R.drawable.ic_search_category_default, "ic_popup_reminder"),
            new Icon(android.R.drawable.ic_secure, "ic_secure")
    };

    private static class ViewHolder {
        // アイコン画像の表示用
        ImageView imageView;
        // 名前の表示用
        TextView textView;
    }

    private class IconAdapter extends ArrayAdapter<Icon> {
        // レイアウトファイルからViewオブジェクトを生成するため
        LayoutInflater mInflater;

        IconAdapter(Context context) {
            super(context, 0, mIcons); // Icon配列をセット
            // インフレイターを取得
            mInflater = getLayoutInflater();
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            // Iconオブジェクトを取得
            Icon icon = getItem(position);

            ViewHolder holder;
            // convertViewには使いまわすためのViewがあれば入っている
            if (convertView == null) {
                // ない場合はレイアウトファイルから生成する
                convertView = mInflater.inflate(R.layout.list_item_recipe_041, null);
                // ViewHolderも作って
                holder = new ViewHolder();
                // 参照をセット
                holder.imageView = (ImageView) convertView.findViewById(R.id.image_view);
                holder.textView = (TextView) convertView.findViewById(R.id.text_view);
                // ViewHolderを使いまわせるようにセットしておく
                convertView.setTag(holder);
            } else {
                // ある場合はViewHolderを取り出して再利用
                holder = (ViewHolder) convertView.getTag();
            }
            // アイコン画像をセット
            holder.imageView.setImageResource(icon.resId);
            // 名前をセット
            holder.textView.setText(icon.name);
            // 表示するViewを返す
            return convertView;
        }
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recipe_041);

        GridView gridView = (GridView) findViewById(R.id.grid_view);
        gridView.setAdapter(new IconAdapter(this));
    }
}
