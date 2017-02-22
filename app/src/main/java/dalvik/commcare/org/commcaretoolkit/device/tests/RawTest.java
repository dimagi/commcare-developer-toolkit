package dalvik.commcare.org.commcaretoolkit.device.tests;

import android.content.Context;

/**
 * Created by amstone326 on 1/25/17.
 */

public abstract class RawTest {

    protected static final int ONE_MILLION = 1000000;
    protected static final int ONE_HUNDRED_THOUSAND = 100000;
    protected static final int ONE_THOUSAND = 1000;
    protected static final int ONE_HUNDRED = 100;


    /**
     * @return if the test was run successfully
     */
    abstract boolean runTest(Context appContext, int iteration);

    /**
     * Perform any one-time setup for this test
     */
    void testSetup(Context appContext) { }

    /**
     * Perform any one-time setup for this test
     */
    void testTeardown(Context appContext) { }

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
    private boolean runAllIterations(Context context) {
        for (int i = 0; i < numIterationsToRun(); i++) {
            if (!runTest(context, i)) {
                return false;
            }
        }
        return true;
    }

    TestResult getTestResult(Context appContext) {
        testSetup(appContext);
        long startTime = System.currentTimeMillis();
        if (runAllIterations(appContext)) {
            long endTime = System.currentTimeMillis();
            double elapsedTime = endTime - startTime;
            if (useSecondsAsTimeUnit()) {
                elapsedTime = elapsedTime / 1000;
            }
            int numIterationsInOneTimeUnit = (int) Math.floor(numIterationsToRun() / elapsedTime);
            testTeardown(appContext);
            return new TestResult(getTestName(), elapsedTime, numIterationsInOneTimeUnit, useSecondsAsTimeUnit());
        }
        testTeardown(appContext);
        return new TestNotRunResult(getTestName());
    }

    public class TestNotRunResult extends TestResult {
        public TestNotRunResult(String testName) {
            super(testName, -1, -1, false);
        }
    }
}