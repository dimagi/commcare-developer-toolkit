package dalvik.commcare.org.commcaretoolkit.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.DisplayMetrics;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.GridView;

import dalvik.commcare.org.commcaretoolkit.R;
import dalvik.commcare.org.commcaretoolkit.components.GridMenuAdapter;
import dalvik.commcare.org.commcaretoolkit.utilities.DeviceInfoUtility;
import dalvik.commcare.org.commcaretoolkit.utilities.ImageSizingUtility;
import dalvik.commcare.org.commcaretoolkit.utilities.RefreshToLatestBuildUtility;
import dalvik.commcare.org.commcaretoolkit.utilities.SupportingAppsUtility;
import dalvik.commcare.org.commcaretoolkit.utilities.ToolkitUtility;

public class HomeActivity extends AppCompatActivity {

    private static final int APPROX_WIDTH_IN_INCHES_OF_ONE_UTILITY = 1;
    private static final int MENU_SETTINGS = 0;

    private GridView gridMenu;
    private GridMenuAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        setupActionBar();
        gridMenu = (GridView) findViewById(R.id.grid_menu);
        adapter = new GridMenuAdapter(this, getUtilitiesList());
    }

    @Override
    protected void onResume() {
        super.onResume();
        gridMenu.setNumColumns(computeOptimalNumberOfColumns());
        gridMenu.setAdapter(adapter);
    }

    private void setupActionBar() {
        Toolbar toolbar = (Toolbar)findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
    }

    private int computeOptimalNumberOfColumns() {
        DisplayMetrics metrics = getResources().getDisplayMetrics();
        int approxScreenWidthInInches = metrics.widthPixels / metrics.densityDpi;
        return approxScreenWidthInInches / APPROX_WIDTH_IN_INCHES_OF_ONE_UTILITY;
    }

    private ToolkitUtility[] getUtilitiesList() {
        ToolkitUtility[] allUtilities = new ToolkitUtility[4];
        allUtilities[0] = new DeviceInfoUtility(this);
        allUtilities[1] = new ImageSizingUtility(this);
        allUtilities[2] = new RefreshToLatestBuildUtility(this);
        allUtilities[3] = new SupportingAppsUtility(this);
        return allUtilities;
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        menu.add(0, MENU_SETTINGS, 0, getResources().getString(R.string.menu_settings));
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == MENU_SETTINGS) {
            startActivity(new Intent(this, SettingsActivity.class));
            return true;
        }
        return false;
    }


}
