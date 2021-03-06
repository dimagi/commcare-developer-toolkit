package dalvik.commcare.org.commcaretoolkit.activities;

import android.os.Build;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.DisplayMetrics;
import android.widget.TextView;

import dalvik.commcare.org.commcaretoolkit.R;

/**
 * Created by amstone326 on 11/24/15.
 */
public class DeviceInfoActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.device_info_layout);
        fillInDeviceValues();
    }

    private void fillInDeviceValues() {
        TextView deviceModel = (TextView) findViewById(R.id.device_model_value);
        deviceModel.setText(Build.MODEL);

        TextView version = (TextView) findViewById(R.id.android_version_value);
        version.setText(Build.VERSION.RELEASE);

        TextView apiLevel = (TextView) findViewById(R.id.api_level_value);
        apiLevel.setText("" + Build.VERSION.SDK_INT);

        DisplayMetrics metrics = getResources().getDisplayMetrics();

        TextView screenDimens = (TextView) findViewById(R.id.screen_dimensions_value);
        screenDimens.setText(metrics.widthPixels + " x " + metrics.heightPixels);

        TextView densityClass = (TextView) findViewById(R.id.density_class_value);
        densityClass.setText(getDensityString(metrics.densityDpi));

        TextView deviceDpi = (TextView) findViewById(R.id.dpi_value);
        deviceDpi.setText("" + metrics.densityDpi);
    }

    private static String getDensityString(int densityInt) {
        if (densityInt <= 120) {
            return "Low Density";
        } else if (densityInt <= 160) {
            return "Medium Density";
        } else if (densityInt <= 240) {
            return "High Density";
        } else if (densityInt <= 320) {
            return "X-High Density";
        } else if (densityInt <= 480){
            return "XX-High Density";
        } else {
            return "XXX-High Density";
        }
    }

}
