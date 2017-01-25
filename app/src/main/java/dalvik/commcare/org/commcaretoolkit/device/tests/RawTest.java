package dalvik.commcare.org.commcaretoolkit.device.tests;

/**
 * Created by amstone326 on 1/25/17.
 */

public abstract class RawTest {

    private static final int ONE_MILLION = 1000000;
    private static final int ONE_THOUSAND = 1000;

    abstract void runTest();

    abstract String getTestName();

    int numIterationsToRun() {
        return ONE_MILLION;
    }

    TestResult getTestResult() {
        long startTime = System.currentTimeMillis();
        runTest();
        long endTime = System.currentTimeMillis();
        long elapsedInMilliseconds = endTime - startTime;
        int numIterationsInOneMillisecond = numIterationsToRun() / (int)elapsedInMilliseconds;
        return new TestResult(getTestName(), elapsedInMilliseconds, numIterationsInOneMillisecond);
    }
}