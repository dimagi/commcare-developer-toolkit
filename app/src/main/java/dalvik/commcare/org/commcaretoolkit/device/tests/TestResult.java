package dalvik.commcare.org.commcaretoolkit.device.tests;

import android.content.Context;

import java.io.Externalizable;
import java.io.Serializable;

import dalvik.commcare.org.commcaretoolkit.R;

/**
 * Created by amstone326 on 1/25/17.
 */

public class TestResult implements Serializable {

    private String testName;
    private long millisecondsToRunAllIterations;
    private int iterationsInOneMillisecond;

    TestResult(String testName, long millisecondsToRunAllIterations, int iterationsInOneMillisecond) {
        this.testName = testName;
        this.millisecondsToRunAllIterations = millisecondsToRunAllIterations;
        this.iterationsInOneMillisecond = iterationsInOneMillisecond;
    }

    public String getDisplayString(Context context) {
        return testName + ":\n" +
                context.getString(R.string.device_test_duration_string, ""+millisecondsToRunAllIterations)
                + "\n"
                + context.getString(R.string.device_test_score_string, ""+iterationsInOneMillisecond);
    }

}