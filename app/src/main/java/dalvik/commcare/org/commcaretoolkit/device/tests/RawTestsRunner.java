package dalvik.commcare.org.commcaretoolkit.device.tests;


/**
 * Created by amstone326 on 1/25/17.
 */
public class RawTestsRunner {

    private static RawTest mathOpTest1 = new RawTest() {
        @Override
        void runTest() {
            for (int i = 0; i < numIterationsToRun(); i++) {
                int x = i / 2 % 10;
            }
        }

        @Override
        String getTestName() {
            return "Math Op Test 1";
        }
    };

    private static RawTest mathOpTest2 = new RawTest() {
        @Override
        void runTest() {
            for (int i = 0; i < numIterationsToRun(); i++) {
                int x = i * 3 - (i + 50);
            }
        }

        @Override
        String getTestName() {
            return "Math Op Test 2";
        }
    };

    private static RawTest stringOpTest1 = new RawTest() {
        @Override
        void runTest() {
            for (int i = 0; i < numIterationsToRun(); i++) {
                String s = "this is a string".substring(1, 6) + "this is another string";
            }
        }

        @Override
        String getTestName() {
            return "String Op Test 1";
        }
    };

    private static RawTest stringOpTest2 = new RawTest() {
        @Override
        void runTest() {
            for (int i = 0; i < numIterationsToRun(); i++) {
                int index = "this is a string".indexOf("string");
                String s = "this is a string".substring(index);
            }
        }

        @Override
        String getTestName() {
            return "String Op Test 2";
        }
    };

    private static final RawTest[] allTests =
            { mathOpTest1, mathOpTest2, stringOpTest1, stringOpTest2 };

    public static TestResult[] run() {
        TestResult[] results = new TestResult[allTests.length];
        for (int i = 0; i < allTests.length; i++) {
            TestResult testResult = allTests[i].getTestResult();
            results[i] = testResult;
        }
        return results;
    }

}
