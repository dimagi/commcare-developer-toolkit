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
        long startTime = System.nanoTime();
        runTest();
        long endTime = System.nanoTime();
        long elapsedInNanoSeconds = endTime - startTime;
        //int elapsedInSeconds = (int)(elapsedInNanoSeconds / 1e9);
        //int numIterationsInOneSecond = numIterationsToRun() / elapsedInSeconds;
        return new TestResult(getTestName(), elapsedInNanoSeconds, 0);
    }
}