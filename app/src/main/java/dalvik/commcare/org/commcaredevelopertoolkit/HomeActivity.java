package dalvik.commcare.org.commcaredevelopertoolkit;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.GridView;

import dalvik.commcare.org.commcaredevelopertoolkit.utilities.DeviceInfoUtility;
import dalvik.commcare.org.commcaredevelopertoolkit.utilities.ImageSizingUtility;
import dalvik.commcare.org.commcaredevelopertoolkit.utilities.StackTraceUtility;
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

    private ToolkitUtility[] getUtilitiesList() {
        ToolkitUtility[] allUtilities = new ToolkitUtility[3];
        allUtilities[0] = new ImageSizingUtility(this);
        allUtilities[1] = new DeviceInfoUtility(this);
        allUtilities[2] = new StackTraceUtility(this);
        return allUtilities;
    }

    private void setupActionBar() {
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
    }


}
