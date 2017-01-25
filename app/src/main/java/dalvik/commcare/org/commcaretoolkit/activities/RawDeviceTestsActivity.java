package dalvik.commcare.org.commcaretoolkit.activities;

import android.content.Context;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;


import dalvik.commcare.org.commcaretoolkit.R;
import dalvik.commcare.org.commcaretoolkit.device.tests.RawTestsRunner;
import dalvik.commcare.org.commcaretoolkit.device.tests.TestResult;

/**
 * Created by amstone326 on 1/25/17.
 */

public class RawDeviceTestsActivity extends AppCompatActivity {

    private static final String KEY_COMPUTED_RESULTS = "computed-results";

    private TestResult[] computedResults;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.raw_device_tests_layout);
        if (savedInstanceState != null) {
            this.computedResults = (TestResult[])savedInstanceState.getSerializable(KEY_COMPUTED_RESULTS);
            updateResultsOnScreen();
        }

        (findViewById(R.id.run_button)).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                computedResults = RawTestsRunner.run();
                updateResultsOnScreen();
            }
        });
    }

    private void updateResultsOnScreen() {
        if (computedResults != null) {
            ListView lv = (ListView)findViewById(R.id.test_results);
            lv.setAdapter(new ArrayAdapter<TestResult>(this, android.R.layout.simple_list_item_1,
                    computedResults) {

                @Override
                public View getView(int position, View convertView, ViewGroup parent) {
                    Context context = RawDeviceTestsActivity.this;
                    View v = convertView;
                    if (v == null) {
                        v = View.inflate(context, R.layout.test_result_view, null);
                    }
                    TestResult current = this.getItem(position);
                    ((TextView)v).setText(current.getDisplayString(context));
                    return v;
                }

            });
        }
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        outState.putSerializable(KEY_COMPUTED_RESULTS, computedResults);
    }
}
