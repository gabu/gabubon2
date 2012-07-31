
package net.gabuchan.androidrecipe.recipe030;

import java.util.Calendar;

import net.gabuchan.androidrecipe.R;
import android.app.TimePickerDialog;
import android.app.TimePickerDialog.OnTimeSetListener;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.FragmentActivity;
import android.view.View;
import android.widget.CheckBox;
import android.widget.TimePicker;
import android.widget.Toast;

public class Recipe030Activity extends FragmentActivity {
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recipe_030);
    }

    public void on24HourClick(View view) {
        boolean checked = ((CheckBox) view).isChecked();
        TimePicker timePicker = (TimePicker) findViewById(R.id.time_picker);
        timePicker.setIs24HourView(checked);
    }

    public void onTimePickerDialogClick(View view) {
        // 24時間表示かどうかを取得
        boolean is24HourView = ((TimePicker) findViewById(R.id.time_picker)).is24HourView();
        // 現在の時分を取得
        final Calendar c = Calendar.getInstance();
        int hour = c.get(Calendar.HOUR_OF_DAY);
        int minute = c.get(Calendar.MINUTE);

        // TimePickerDialogを生成
        TimePickerDialog dialog = new TimePickerDialog(this, new OnTimeSetListener() {
            @Override
            public void onTimeSet(TimePicker view, int hour, int minute) {
                // 時刻が選択された時に呼び出される
                String text = String.format("%d:%d", hour, minute);
                showToast(text);
            }
        }, hour, minute, is24HourView);

        // ダイアログを表示
        dialog.show();
    }

    private void showToast(String text) {
        Toast.makeText(this, text, Toast.LENGTH_SHORT).show();
    }

    public void onTimePickerFragmentClick(View view) {
        // 24時間表示かどうかを取得
        boolean is24HourView = ((TimePicker) findViewById(R.id.time_picker)).is24HourView();

        // TimePickerFragmentを生成
        DialogFragment fragment = new TimePickerFragment();
        // 引数を作って
        Bundle args = new Bundle();
        // 24時間表記かどうかをセットして
        args.putBoolean(TimePickerFragment.KEY_IS_24_HOUR_VIEW, is24HourView);
        // 引数をセット！
        fragment.setArguments(args);
        // 表示！
        fragment.show(getSupportFragmentManager(), "timePicker");
    }
}
