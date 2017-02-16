package dalvik.commcare.org.commcaretoolkit.activities;

import android.annotation.TargetApi;
import android.app.ActivityManager;
import android.os.Build;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.DisplayMetrics;
import android.view.View;
import android.widget.TextView;

import java.text.DecimalFormat;

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
        TextView deviceModel = (TextView)findViewById(R.id.device_model_value);
        deviceModel.setText(Build.MODEL);

        TextView manufacturer = (TextView)findViewById(R.id.manufacturer_value);
        manufacturer.setText(Build.MANUFACTURER);

        TextView version = (TextView)findViewById(R.id.android_version_value);
        version.setText(Build.VERSION.RELEASE);

        TextView apiLevel = (TextView)findViewById(R.id.api_level_value);
        apiLevel.setText("" + Build.VERSION.SDK_INT);

        DisplayMetrics metrics = getResources().getDisplayMetrics();

        TextView screenDimens = (TextView)findViewById(R.id.screen_dimensions_value);
        screenDimens.setText(metrics.widthPixels + " x " + metrics.heightPixels);

        TextView densityClass = (TextView)findViewById(R.id.density_class_value);
        densityClass.setText(getDensityString(metrics.densityDpi));

        TextView deviceDpi = (TextView)findViewById(R.id.dpi_value);
        deviceDpi.setText("" + metrics.densityDpi);

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN) {
            View displayRAMContainer = findViewById(R.id.ram_display_container);
            displayRAMContainer.setVisibility(View.VISIBLE);
            TextView totalRAM = (TextView)findViewById(R.id.ram_value);
            totalRAM.setText(getTotalRAMString());
        }
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

    @TargetApi(Build.VERSION_CODES.JELLY_BEAN)
    private String getTotalRAMString() {
        ActivityManager activityManager = (ActivityManager)getSystemService(ACTIVITY_SERVICE);
        ActivityManager.MemoryInfo memoryInfo = new ActivityManager.MemoryInfo();
        activityManager.getMemoryInfo(memoryInfo);
        long bytesOfRAM = memoryInfo.totalMem;
        return new DecimalFormat("#.##").format((float)bytesOfRAM / 1e9) + " GB";
    }

}
