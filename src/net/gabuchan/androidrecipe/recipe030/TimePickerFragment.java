
package net.gabuchan.androidrecipe.recipe030;

import java.util.Calendar;

import android.app.Dialog;
import android.app.TimePickerDialog;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.widget.TimePicker;
import android.widget.Toast;

public class TimePickerFragment extends DialogFragment implements
        TimePickerDialog.OnTimeSetListener {
    public static final String KEY_IS_24_HOUR_VIEW = "is24HourView";

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        // 引数から24時間表示かどうかを受け取って
        boolean is24HourView = getArguments().getBoolean(KEY_IS_24_HOUR_VIEW);

        // 現在の時分を取得
        final Calendar c = Calendar.getInstance();
        int hour = c.get(Calendar.HOUR_OF_DAY);
        int minute = c.get(Calendar.MINUTE);

        // TimePickerDialogを生成して返す
        return new TimePickerDialog(getActivity(), this, hour, minute, is24HourView);
    }

    public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
        // 時刻が選択された時に呼び出される
        String text = String.format("%d:%d", hourOfDay, minute);
        Toast.makeText(getActivity(), text, Toast.LENGTH_SHORT).show();
    }
}
