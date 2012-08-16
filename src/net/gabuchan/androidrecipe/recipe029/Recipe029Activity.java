
package net.gabuchan.androidrecipe.recipe029;

import java.util.Calendar;

import net.gabuchan.androidrecipe.R;
import android.annotation.TargetApi;
import android.app.DatePickerDialog;
import android.app.DatePickerDialog.OnDateSetListener;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.FragmentActivity;
import android.view.View;
import android.widget.CheckBox;
import android.widget.DatePicker;
import android.widget.Toast;

@TargetApi(11)
public class Recipe029Activity extends FragmentActivity {
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recipe_029);
    }

    public void onDefaultClick(View view) {
        if (Build.VERSION_CODES.HONEYCOMB <= Build.VERSION.SDK_INT) {
            DatePicker datePicker = (DatePicker) findViewById(R.id.date_picker);
            datePicker.setCalendarViewShown(true);
            datePicker.setSpinnersShown(true);
            ((CheckBox) findViewById(R.id.calendar_check_box)).setChecked(true);
            ((CheckBox) findViewById(R.id.spinner_check_box)).setChecked(true);
        } else {
            showNotSupportMessage();
        }
    }

    public void onCalendarClick(View view) {
        if (Build.VERSION_CODES.HONEYCOMB <= Build.VERSION.SDK_INT) {
            DatePicker datePicker = (DatePicker) findViewById(R.id.date_picker);
            boolean checked = ((CheckBox) view).isChecked();
            datePicker.setCalendarViewShown(checked);
        } else {
            showNotSupportMessage();
        }
    }

    public void onSpinnerClick(View view) {
        if (Build.VERSION_CODES.HONEYCOMB <= Build.VERSION.SDK_INT) {
            DatePicker datePicker = (DatePicker) findViewById(R.id.date_picker);
            boolean checked = ((CheckBox) view).isChecked();
            datePicker.setSpinnersShown(checked);
        } else {
            showNotSupportMessage();
        }
    }

    public void onDatePickerDialogClick(View view) {
        // 現在の年月日を取得
        Calendar c = Calendar.getInstance();
        int year = c.get(Calendar.YEAR);
        int month = c.get(Calendar.MONTH);
        int day = c.get(Calendar.DAY_OF_MONTH);

        // DatePickerDialogを生成
        DatePickerDialog dialog = new DatePickerDialog(this, new OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int month, int day) {
                // 日付が選択された時に呼び出される
                String text = String.format("%d-%d-%d", year, month + 1, day);
                showToast(text);
            }
        }, year, month, day);
        // ダイアログを表示
        dialog.show();
    }

    private void showNotSupportMessage() {
        showToast("calendarViewShown, spinnersShownは、API level 11(Android 3.0)からのみ使えます。");
    }
    
    private void showToast(String text) {
        Toast.makeText(this, text, Toast.LENGTH_SHORT).show();
    }

    public void onDatePickerFragmentClick(View view) {
        // DatePickerFragmentを表示
        DialogFragment fragment = new DatePickerFragment();
        fragment.show(getSupportFragmentManager(), "datePicker");
    }
}
