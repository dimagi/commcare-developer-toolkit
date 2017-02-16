package dalvik.commcare.org.commcaretoolkit.device.tests;

import android.content.Context;

import java.io.Serializable;

/**
 * Created by amstone326 on 1/25/17.
 */

public abstract class RawTest {

    protected static final int ONE_MILLION = 1000000;
    protected static final int ONE_HUNDRED_THOUSAND = 100000;
    protected static final int TEN_THOUSAND = 10000;
    protected static final int FIVE_THOUSAND = 10000;
    protected static final int ONE_THOUSAND = 1000;

    /**
     *
     * @return if the test was run successfully
     */
    abstract boolean runTest(Context appContext, int iteration);

    abstract String getTestName();

    int numIterationsToRun() {
        return ONE_MILLION;
    }

    boolean useSecondsAsTimeUnit() {
        // default is milliseconds
        return false;
    }

    /**
     *
     * @return If the test was run successfully
     */
    boolean runAllIterations(Context context) {
        for (int i = 0; i < numIterationsToRun(); i++) {
            if (!runTest(context, i)) {
                return false;
            }
        }
        return true;
    }

    TestResult getTestResult(Context appContext) {
        long startTime = System.currentTimeMillis();
        if (runAllIterations(appContext)) {
            long endTime = System.currentTimeMillis();
            double elapsedTime = endTime - startTime;
            if (useSecondsAsTimeUnit()) {
                elapsedTime = elapsedTime / 1000;
            }
            int numIterationsInOneTimeUnit = (int) Math.floor(numIterationsToRun() / elapsedTime);
            return new TestResult(getTestName(), elapsedTime, numIterationsInOneTimeUnit, useSecondsAsTimeUnit());
        }
        return new TestNotRunResult(getTestName());
    }

    public class TestNotRunResult extends TestResult {
        public TestNotRunResult(String testName) {
            super(testName, -1, -1, false);
        }
    }
}