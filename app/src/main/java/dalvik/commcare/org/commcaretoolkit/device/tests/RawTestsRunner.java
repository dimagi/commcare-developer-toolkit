package dalvik.commcare.org.commcaretoolkit.device.tests;


import android.content.Context;

import java.io.File;
import java.io.FileOutputStream;

/**
 * Created by amstone326 on 1/25/17.
 */
public class RawTestsRunner {

    private static RawTest mathOpTest1 = new RawTest() {

        @Override
        void runTest(Context c, int iteration) {
            int x = iteration / 2 % 10;
        }

        @Override
        String getTestName() {
            return "MATH OP TEST 1";
        }
    };

    private static RawTest mathOpTest2 = new RawTest() {

        @Override
        void runTest(Context c, int iteration) {
            int x = iteration * 3 - ( + 50);
        }

        @Override
        String getTestName() {
            return "MATH OP TEST 2";
        }
    };

    private static RawTest stringOpTest1 = new RawTest() {

        @Override
        void runTest(Context c, int iteration) {
            String s = "this is a string".substring(1, 6) + "this is another string";
        }

        @Override
        String getTestName() {
            return "STRING OP TEST 1";
        }
    };

    private static RawTest stringOpTest2 = new RawTest() {

        @Override
        void runTest(Context c, int iteration) {
            int index = "this is a string".indexOf("string");
            String s = "this is a string".substring(index);
        }

        @Override
        String getTestName() {
            return "STRING OP TEST 2";
        }
    };

    private static RawTest memoryTest1 = new RawTest() {

        @Override
        int numIterationsToRun() {
            // this test takes longer so don't run as many
            return TEN_THOUSAND;
        }

        @Override
        boolean useSecondsAsTimeUnit() {
            return true;
        }

        @Override
        void runTest(Context c, int iteration) {
            byte[] reallyBigArray1 = new byte[ONE_MILLION];
            byte[] reallyBigArray2 = new byte[ONE_MILLION];
        }

        @Override
        String getTestName() {
            return "MEMORY TEST 1 (Large Chunk Allocation)";
        }
    };

    private static RawTest memoryTest2 = new RawTest() {

        @Override
        int numIterationsToRun() {
            // this test takes longer so don't run as many
            return TEN_THOUSAND;
        }

        @Override
        boolean useSecondsAsTimeUnit() {
            return true;
        }

        @Override
        void runTest(Context c, int iteration) {
            VeryLargeObject o = new VeryLargeObject();
        }

        @Override
        String getTestName() {
            return "MEMORY TEST 2 (Large Object Allocation)";
        }

    };

    private static RawTest ioOverheadTest = new RawTest() {

        @Override
        int numIterationsToRun() {
            // We want to run fewer iterations of these
            return TEN_THOUSAND;
        }

        @Override
        boolean useSecondsAsTimeUnit() {
            return true;
        }

        @Override
        void runTest(Context c, int iteration) {
            String filename = "my_filename";
            byte[] aFewBytesToWrite = new byte[30];

            // 1) create file
            File file = new File(c.getFilesDir(), filename);

            // 2) write a small number of bytes to the file
            try {
                FileOutputStream outputStream = new FileOutputStream(file);
                outputStream.write(aFewBytesToWrite);
                outputStream.close();
            } catch (Exception e) {
                e.printStackTrace();
            }

            // 3) delete file
            file.delete();
        }

        @Override
        String getTestName() {
            return "STORAGE TEST 1 (IO Overhead)";
        }
    };

    private static RawTest ioThroughputTest = new RawTest() {

        private String filename = "my_filename";

        @Override
        int numIterationsToRun() {
            // We want to run fewer iterations of these
            return ONE_THOUSAND;
        }

        @Override
        boolean useSecondsAsTimeUnit() {
            return true;
        }

        @Override
        void runTest(Context c, int iteration) {
            byte[] oneMegabyteToWrite = new byte[1000000];
            try {
                FileOutputStream outputStream = new FileOutputStream(filename);
                outputStream.write(oneMegabyteToWrite);
                outputStream.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        @Override
        void runAllIterations(Context context) {
            // Create file only once
            File file = new File(context.getFilesDir(), filename);

            for (int i = 0; i < numIterationsToRun(); i++) {
                runTest(context, i);
            }

            // Delete file only once
            file.delete();
        }

        @Override
        String getTestName() {
            return "STORAGE TEST 2 (IO Throughput)";
        }
    };

    private static final RawTest[] allTests = { mathOpTest1, mathOpTest2, stringOpTest1,
            stringOpTest2, memoryTest1, memoryTest2, ioOverheadTest, ioThroughputTest };

    public static TestResult[] run(Context context) {
        TestResult[] results = new TestResult[allTests.length];
        for (int i = 0; i < allTests.length; i++) {
            TestResult testResult = allTests[i].getTestResult(context);
            results[i] = testResult;
        }
        return results;
    }

}
