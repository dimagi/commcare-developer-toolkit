package dalvik.commcare.org.commcaretoolkit.activities;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import dalvik.commcare.org.commcaretoolkit.R;
import dalvik.commcare.org.commcaretoolkit.device.tests.RawTest;
import dalvik.commcare.org.commcaretoolkit.device.tests.TestResult;

/**
 * Created by amstone326 on 1/25/17.
 */

public class DeviceTestDetailsActivity extends Activity {

    private TestResult[] computedResults;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.device_test_details);
        computedResults = (TestResult[])getIntent()
                .getSerializableExtra(RawDeviceTestsActivity.KEY_COMPUTED_RESULTS);
        updateResultsOnScreen();
    }

    private void updateResultsOnScreen() {
        if (computedResults != null) {
            ListView lv = (ListView)findViewById(R.id.test_results);
            lv.setAdapter(new ArrayAdapter<TestResult>(this, android.R.layout.simple_list_item_1,
                    computedResults) {

                @Override
                public View getView(int position, View convertView, ViewGroup parent) {
                    Context context = DeviceTestDetailsActivity.this;
                    View v = convertView;
                    if (v == null) {
                        v = View.inflate(context, R.layout.test_result_view, null);
                    }
                    TestResult current = this.getItem(position);
                    String resultString;
                    if (current instanceof RawTest.TestNotRunResult) {
                        resultString = "Test could not run";
                    } else {
                        resultString = current.getDisplayString(context);
                    }
                    ((TextView)v).setText(resultString);
                    return v;
                }

            });
        }
    }
}
