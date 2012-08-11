
package net.gabuchan.androidrecipe.recipe118;

import net.gabuchan.androidrecipe.R;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.provider.Settings;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

public class Recipe118Activity extends Activity {
    private Spinner mSpinner;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recipe_118);

        mSpinner = (Spinner) findViewById(R.id.spinner);

        String[] actions = {
            Settings.ACTION_WIFI_SETTINGS,
            Settings.ACTION_BLUETOOTH_SETTINGS,
            Settings.ACTION_APPLICATION_DEVELOPMENT_SETTINGS,
            Settings.ACTION_APPLICATION_SETTINGS,
            Settings.ACTION_DATE_SETTINGS,
            Settings.ACTION_DEVICE_INFO_SETTINGS,
            Settings.ACTION_DISPLAY_SETTINGS,
            Settings.ACTION_INPUT_METHOD_SETTINGS,
            Settings.ACTION_INTERNAL_STORAGE_SETTINGS,
            Settings.ACTION_LOCALE_SETTINGS,
            Settings.ACTION_LOCATION_SOURCE_SETTINGS,
            Settings.ACTION_MANAGE_APPLICATIONS_SETTINGS,
            Settings.ACTION_MEMORY_CARD_SETTINGS,
            Settings.ACTION_NETWORK_OPERATOR_SETTINGS,
            Settings.ACTION_PRIVACY_SETTINGS,
            Settings.ACTION_QUICK_LAUNCH_SETTINGS,
            Settings.ACTION_SEARCH_SETTINGS,
            Settings.ACTION_SECURITY_SETTINGS,
            Settings.ACTION_SETTINGS,
            Settings.ACTION_SOUND_SETTINGS,
            Settings.ACTION_SYNC_SETTINGS,
            Settings.ACTION_USER_DICTIONARY_SETTINGS,
            Settings.ACTION_WIFI_IP_SETTINGS,
            Settings.ACTION_WIRELESS_SETTINGS,
        };

        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,
                android.R.layout.simple_spinner_item, actions);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        mSpinner.setAdapter(adapter);
    }

    public void onSettingClick(View view) {
        String action = mSpinner.getSelectedItem().toString();
        Intent intent = new Intent(action);
        startActivity(intent);
    }
}
