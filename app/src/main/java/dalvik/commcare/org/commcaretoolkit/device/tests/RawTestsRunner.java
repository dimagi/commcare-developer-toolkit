package dalvik.commcare.org.commcaretoolkit.device.tests;


import android.content.Context;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Random;

/**
 * Created by amstone326 on 1/25/17.
 */
public class RawTestsRunner {

    private static RawTest mathOpTest1 = new RawTest() {

        @Override
        boolean runTest(Context c, int iteration) {
            int x = iteration / 2 % 10;
            return true;
        }

        @Override
        String getTestName() {
            return "MATH OP TEST 1";
        }
    };

    private static RawTest mathOpTest2 = new RawTest() {

        @Override
        boolean runTest(Context c, int iteration) {
            int x = iteration * 3 - ( + 50);
            return true;
        }

        @Override
        String getTestName() {
            return "MATH OP TEST 2";
        }
    };

    private static RawTest stringOpTest1 = new RawTest() {

        @Override
        boolean runTest(Context c, int iteration) {
            String s = "this is a string".substring(1, 6) + "this is another string";
            return true;
        }

        @Override
        String getTestName() {
            return "STRING OP TEST 1";
        }
    };

    private static RawTest stringOpTest2 = new RawTest() {

        @Override
        boolean runTest(Context c, int iteration) {
            int index = "this is a string".indexOf("string");
            String s = "this is a string".substring(index);
            return true;
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
            return ONE_THOUSAND;
        }

        @Override
        boolean useSecondsAsTimeUnit() {
            return true;
        }

        @Override
        boolean runTest(Context c, int iteration) {
            byte[] reallyBigArray = new byte[ONE_HUNDRED_THOUSAND];
            new Random().nextBytes(reallyBigArray);
            return true;
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
            return ONE_THOUSAND;
        }

        @Override
        boolean useSecondsAsTimeUnit() {
            return true;
        }

        @Override
        boolean runTest(Context c, int iteration) {
            VeryLargeObject o = new VeryLargeObject();
            return true;
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
            return ONE_THOUSAND;
        }

        @Override
        boolean useSecondsAsTimeUnit() {
            return true;
        }

        @Override
        boolean runTest(Context c, int iteration) {
            String filename = "my_filename";
            byte[] aFewBytesToWrite = new byte[30];
            new Random().nextBytes(aFewBytesToWrite);

            // 1) create file
            File file = new File(c.getFilesDir(), filename);

            // 2) write a small number of bytes to the file
            try {
                FileOutputStream outputStream = new FileOutputStream(file);
                outputStream.write(aFewBytesToWrite);
                outputStream.close();
            } catch (IOException e) {
                e.printStackTrace();
                return false;
            }

            // 3) delete file
            file.delete();

            return true;
        }

        @Override
        String getTestName() {
            return "STORAGE TEST 1 (IO Overhead)";
        }
    };

    private static RawTest ioThroughputTest = new RawTest() {

        private File fileToWriteTo;

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
        boolean runTest(Context c, int iteration) {
            try {
                byte[] oneMegabyteToWrite = new byte[1000000];
                new Random().nextBytes(oneMegabyteToWrite);
                FileOutputStream outputStream = new FileOutputStream(this.fileToWriteTo);
                outputStream.write(oneMegabyteToWrite);
                outputStream.close();
                return true;
            } catch (IOException e) {
                e.printStackTrace();
                return false;
            }
        }

        @Override
        void testSetup(Context context) {
            fileToWriteTo = new File(context.getFilesDir(), "my_filename_2");
        }

        @Override
        void testTeardown(Context context) {
            fileToWriteTo.delete();
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
            System.out.println("STARTED running " + allTests[i].getTestName());
            TestResult testResult = allTests[i].getTestResult(context);
            results[i] = testResult;
            System.out.println("STOPPED running " + allTests[i].getTestName());
        }
        return results;
    }

}
