package dalvik.commcare.org.commcaretoolkit.device.tests;

import android.content.Context;

/**
 * Created by amstone326 on 1/25/17.
 */

public abstract class RawTest {

    protected static final int TEN_MILLION = 10000000;
    protected static final int ONE_MILLION = 1000000;
    protected static final int TEN_THOUSAND = 10000;

    abstract void runTest(Context appContext);

    abstract String getTestName();

    int numIterationsToRun() {
        return ONE_MILLION;
    }

    TestResult getTestResult(Context appContext) {
        long startTime = System.currentTimeMillis();
        runTest(appContext);
        long endTime = System.currentTimeMillis();
        long elapsedInMilliseconds = endTime - startTime;
        int numIterationsInOneMillisecond = numIterationsToRun() / (int)elapsedInMilliseconds;
        return new TestResult(getTestName(), elapsedInMilliseconds, numIterationsInOneMillisecond);
    }
}