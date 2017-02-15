package dalvik.commcare.org.commcaretoolkit.device.tests;

import android.content.Context;

import java.io.Serializable;

import dalvik.commcare.org.commcaretoolkit.R;

/**
 * Created by amstone326 on 1/25/17.
 */

public class TestResult implements Serializable {

    private String testName;
    private long timeUnitsToRunAllIterations;
    private int iterationsInOneTimeUnit;
    private boolean usesSeconds;

    TestResult(String testName, long timeUnitsToRunAllIterations,
               int iterationsInOneTimeUnit, boolean usesSeconds) {
        this.testName = testName;
        this.timeUnitsToRunAllIterations = timeUnitsToRunAllIterations;
        this.iterationsInOneTimeUnit = iterationsInOneTimeUnit;
        this.usesSeconds = usesSeconds;
    }

    private String getTimeUnitString(boolean plural) {
        if (usesSeconds) {
            return plural ? "seconds" : "second";
        } else {
            return plural ? "milliseconds" : "millisecond";
        }
    }

    public String getDisplayString(Context context) {
        return testName + ":\n" +
                context.getString(R.string.device_test_duration_string,
                        new String[]{""+timeUnitsToRunAllIterations, getTimeUnitString(true)})
                + "\n"
                + context.getString(R.string.device_test_score_string,
                        new String[]{""+iterationsInOneTimeUnit, getTimeUnitString(false)});
    }

}