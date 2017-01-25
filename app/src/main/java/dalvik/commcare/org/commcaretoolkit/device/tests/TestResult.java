package dalvik.commcare.org.commcaretoolkit.device.tests;

/**
 * Created by amstone326 on 1/25/17.
 */

public class TestResult {

    public String testName;
    public long nanosecondsToRunAllIterations;
    public int iterationsInOneSecond;

    TestResult(String testName, long nanosecondsToRunAllIterations, int iterationsInOneSecond) {
        this.testName = testName;
        this.nanosecondsToRunAllIterations = nanosecondsToRunAllIterations;
        this.iterationsInOneSecond = iterationsInOneSecond;
    }

}