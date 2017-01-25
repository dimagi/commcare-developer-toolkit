package dalvik.commcare.org.commcaretoolkit.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;


import dalvik.commcare.org.commcaretoolkit.R;
import dalvik.commcare.org.commcaretoolkit.device.tests.DeviceTestsUtility;
import dalvik.commcare.org.commcaretoolkit.device.tests.RawTestsRunner;
import dalvik.commcare.org.commcaretoolkit.device.tests.TestResult;

/**
 * Created by amstone326 on 1/25/17.
 */

public class RawDeviceTestsActivity extends AppCompatActivity {

    protected static final String KEY_COMPUTED_RESULTS = "computed-results";

    private TestResult[] computedResults;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.run_device_tests_layout);
        if (savedInstanceState != null) {
            this.computedResults = (TestResult[])savedInstanceState.getSerializable(KEY_COMPUTED_RESULTS);
        }

        (findViewById(R.id.run_button)).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                runButtonOnClick();
            }
        });

        (findViewById(R.id.view_details_button)).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(RawDeviceTestsActivity.this, DeviceTestDetailsActivity.class);
                i.putExtra(KEY_COMPUTED_RESULTS, computedResults);
                startActivity(i);
            }
        });
    }

    private void runButtonOnClick() {
        View progressBar = findViewById(R.id.progress_bar);
        View resultsView = findViewById(R.id.after_results_computed_view);

        resultsView.setVisibility(View.GONE);
        progressBar.setVisibility(View.VISIBLE);
        computedResults = RawTestsRunner.run();
        resultsView.setVisibility(View.VISIBLE);
        progressBar.setVisibility(View.GONE);
        ((TextView)findViewById(R.id.overall_score)).setText(
                DeviceTestsUtility.getOverallScoreDisplayString(this, computedResults));
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        outState.putSerializable(KEY_COMPUTED_RESULTS, computedResults);
    }
}
