package dalvik.commcare.org.commcaretoolkit.device.tests;

import android.content.Context;

import dalvik.commcare.org.commcaretoolkit.R;

/**
 * Created by amstone326 on 1/25/17.
 */

public class DeviceTestsUtility {

    private static int computeOverallScore(TestResult[] allResults) {
        // TODO: implement
        return 0;
    }

    public static String getOverallScoreDisplayString(Context context, TestResult[] allResults) {
        int score = computeOverallScore(allResults);
        return context.getString(R.string.overall_device_score, ""+score);
    }
}
