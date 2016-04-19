package dalvik.commcare.org.commcaredevelopertoolkit.activities;

import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.GridView;

import dalvik.commcare.org.commcaredevelopertoolkit.GridMenuAdapter;
import dalvik.commcare.org.commcaredevelopertoolkit.R;
import dalvik.commcare.org.commcaredevelopertoolkit.utilities.DeviceInfoUtility;
import dalvik.commcare.org.commcaredevelopertoolkit.utilities.ImageSizingUtility;
import dalvik.commcare.org.commcaredevelopertoolkit.utilities.RefreshToLatestBuildUtility;
import dalvik.commcare.org.commcaredevelopertoolkit.utilities.SupportingAppsUtility;
import dalvik.commcare.org.commcaredevelopertoolkit.utilities.ToolkitUtility;

public class HomeActivity extends AppCompatActivity {

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
        gridMenu.setAdapter(adapter);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        super.onCreateOptionsMenu(menu);
        menu.add("Acknowledgements");
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setView(View.inflate(this, R.layout.acknowledgements_dialog, null));
        builder.show();
        return super.onOptionsItemSelected(item);
    }

    private ToolkitUtility[] getUtilitiesList() {
        ToolkitUtility[] allUtilities = new ToolkitUtility[4];
        allUtilities[0] = new DeviceInfoUtility(this);
        allUtilities[1] = new ImageSizingUtility(this);
        allUtilities[2] = new RefreshToLatestBuildUtility(this);
        allUtilities[3] = new SupportingAppsUtility(this);
        return allUtilities;
    }

    private void setupActionBar() {
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
    }


}
