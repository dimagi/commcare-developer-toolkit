package dalvik.commcare.org.commcaretoolkit.activities;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import dalvik.commcare.org.commcaretoolkit.fragments.ToolkitSettings;

/**
 * Created by amstone326 on 11/6/17.
 */
public class SettingsActivity extends AppCompatActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getFragmentManager().beginTransaction()
                .replace(android.R.id.content, new ToolkitSettings())
                .commit();
    }

}
