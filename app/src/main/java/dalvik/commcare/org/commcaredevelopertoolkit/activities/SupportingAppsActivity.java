package dalvik.commcare.org.commcaredevelopertoolkit.activities;

import android.app.Activity;
import android.os.Bundle;
import android.text.Layout;
import android.text.util.Linkify;
import android.view.LayoutInflater;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;

import java.util.HashMap;

import dalvik.commcare.org.commcaredevelopertoolkit.R;

/**
 * Created by amstone326 on 4/18/16.
 */
public class SupportingAppsActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.supporting_apps_layout);
        fillInAppLinks();
    }

    private void fillInAppLinks() {
        HashMap<String,String> appNamesToLinks = new HashMap<>();
        appNamesToLinks.put("ES File Explorer:", "https://play.google.com/store/apps/details?id=com.estrongs.android.pop");
        appNamesToLinks.put("Barcode Scanner:", "https://play.google.com/store/apps/details?id=com.androidrocker.qrscanner");

        TableLayout table = (TableLayout)findViewById(R.id.app_links_table);
        for (String appName : appNamesToLinks.keySet()) {
            addRow(table, appName, appNamesToLinks.get(appName));
        }
    }

    private void addRow(TableLayout table, String appName, String appLink) {
        TableLayout rootTable = (TableLayout) LayoutInflater.from(this).inflate(R.layout.supporting_app_row_view, table);
        RowView row = rootTable.findViewById()
        TextView appNameView = (TextView)rootTable.findViewById(R.id.app_name_view);
        TextView appLinkView = (TextView)rootTable.findViewById(R.id.app_link_view);
        appNameView.setText(appName);
        appLinkView.setText(appLink);
    }
}
