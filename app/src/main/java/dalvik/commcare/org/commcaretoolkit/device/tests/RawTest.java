package dalvik.commcare.org.commcaretoolkit.device.tests;

import android.content.Context;

/**
 * Created by amstone326 on 1/25/17.
 */

public abstract class RawTest {

    protected static final int ONE_MILLION = 1000000;
    protected static final int ONE_HUNDRED_THOUSAND = 100000;
    protected static final int TEN_THOUSAND = 10000;
    protected static final int FIVE_THOUSAND = 10000;
    protected static final int ONE_THOUSAND = 1000;

    abstract void runTest(Context appContext, int iteration);

    abstract String getTestName();

    int numIterationsToRun() {
        return ONE_MILLION;
    }

    boolean useSecondsAsTimeUnit() {
        // default is milliseconds
        return false;
    }

    TestResult getTestResult(Context appContext) {
        long startTime = System.currentTimeMillis();
        runAllIterations(appContext);
        long endTime = System.currentTimeMillis();
        long elapsedTime = endTime - startTime;
        if (useSecondsAsTimeUnit()) {
            elapsedTime = elapsedTime / 1000;
        }
        if (elapsedTime == 0) {
            System.out.println("elapsedTime is 0 for " + getTestName());
        }
        int numIterationsInOneTimeUnit = numIterationsToRun() / (int)elapsedTime;
        return new TestResult(getTestName(), elapsedTime, numIterationsInOneTimeUnit, useSecondsAsTimeUnit());
    }

    void runAllIterations(Context context) {
        for (int i = 0; i < numIterationsToRun(); i++) {
            runTest(context, i);
        }
    }
}