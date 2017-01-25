package dalvik.commcare.org.commcaretoolkit.activities;

import android.content.Context;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.ScrollView;
import android.widget.TextView;

import java.util.Map;

import dalvik.commcare.org.commcaretoolkit.R;
import dalvik.commcare.org.commcaretoolkit.device.tests.RawTest;
import dalvik.commcare.org.commcaretoolkit.device.tests.RawTestsRunner;
import dalvik.commcare.org.commcaretoolkit.device.tests.TestResult;

/**
 * Created by amstone326 on 1/25/17.
 */

public class RawDeviceTestsActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.raw_device_tests_layout);

        (findViewById(R.id.run_button)).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                TestResult[] results = RawTestsRunner.run();
                updateResultsOnScreen(results);
            }
        });
    }

    private void updateResultsOnScreen(TestResult[] results) {
        ListView lv = (ListView)findViewById(R.id.test_results);
        lv.setAdapter(new ArrayAdapter<TestResult>(this, android.R.layout.simple_list_item_1,
                results) {

            @Override
            public View getView(int position, View convertView, ViewGroup parent) {
                Context context = RawDeviceTestsActivity.this;
                View v = convertView;
                if (v == null) {
                    v = View.inflate(context, R.layout.test_result_view, null);
                }
                TestResult current = this.getItem(position);
                ((TextView)v).setText(context.getString(R.string.device_test_duration_string,
                        new String[]{current.testName, ""+current.nanosecondsToRunAllIterations}));
                return v;
            }

        });
    }
}
