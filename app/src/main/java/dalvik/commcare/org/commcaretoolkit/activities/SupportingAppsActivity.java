package dalvik.commcare.org.commcaretoolkit.activities;

import android.app.Activity;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;

import java.util.HashMap;

import dalvik.commcare.org.commcaretoolkit.R;
import dalvik.commcare.org.commcaretoolkit.analytics.AnalyticsUtils;
import dalvik.commcare.org.commcaretoolkit.analytics.AnalyticsValues;

/**
 * Created by amstone326 on 4/18/16.
 */
public class SupportingAppsActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (playStoreIsInstalled()) {
            setContentView(R.layout.supporting_apps_layout);
            fillInAppLinks();
        } else {
            setContentView(R.layout.supporting_apps_unavailable_layout);
        }
    }

    private boolean playStoreIsInstalled() {
        PackageManager pm = getPackageManager();
        try {
            pm.getPackageInfo("com.android.vending", PackageManager.GET_ACTIVITIES);
            return true;
        } catch (PackageManager.NameNotFoundException e) {
            return false;
        }
    }

    private void fillInAppLinks() {
        HashMap<String,String> appNamesToLinks = new HashMap<>();
        appNamesToLinks.put("ES File Explorer", "https://play.google.com/store/apps/details?id=com.estrongs.android.pop");
        appNamesToLinks.put("QR Code Scanner", "https://play.google.com/store/apps/details?id=com.androidrocker.qrscanner");
        appNamesToLinks.put("Audio Recorder", "https://play.google.com/store/apps/details?id=dje073.android.audiorecorderlite");
        appNamesToLinks.put("Breath Counter", "https://play.google.com/store/apps/details?id=org.commcare.respiratory");
        appNamesToLinks.put("Area Mapper", "https://play.google.com/store/apps/details?id=richard.chard.lu.android.areamapper");

        TableLayout table = (TableLayout)findViewById(R.id.app_links_table);
        int index = 0;
        for (String appName : appNamesToLinks.keySet()) {
            addRow(table, appName, appNamesToLinks.get(appName), index);
            index++;
        }
    }

    private void addRow(TableLayout table, String appName, final String appLink, int index) {
        TableLayout rootTable = (TableLayout)LayoutInflater.from(this).inflate(R.layout.supporting_app_row_view, table);
        TableRow row = (TableRow)rootTable.findViewById(R.id.row_view);
        row.setId(index);

        TextView appNameView = (TextView)row.findViewById(R.id.app_name_view);
        appNameView.setText(appName);

        appNameView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AnalyticsUtils.reportUtilityUsage(AnalyticsValues.VALUE_SUPPORTING_APPS_UTILITY);
                SupportingAppsActivity.this.startActivity(
                        new Intent(Intent.ACTION_VIEW, Uri.parse(appLink)));
            }
        });
    }
}
