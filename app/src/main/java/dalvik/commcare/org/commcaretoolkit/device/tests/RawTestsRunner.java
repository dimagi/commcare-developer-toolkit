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
        void runTest(Context c) {
            for (int i = 0; i < numIterationsToRun(); i++) {
                int x = i / 2 % 10;
            }
        }

        @Override
        String getTestName() {
            return "MATH OP TEST 1";
        }
    };

    private static RawTest mathOpTest2 = new RawTest() {

        @Override
        void runTest(Context c) {
            for (int i = 0; i < numIterationsToRun(); i++) {
                int x = i * 3 - (i + 50);
            }
        }

        @Override
        String getTestName() {
            return "MATH OP TEST 2";
        }
    };

    private static RawTest stringOpTest1 = new RawTest() {

        @Override
        void runTest(Context c) {
            for (int i = 0; i < numIterationsToRun(); i++) {
                String s = "this is a string".substring(1, 6) + "this is another string";
            }
        }

        @Override
        String getTestName() {
            return "STRING OP TEST 1";
        }
    };

    private static RawTest stringOpTest2 = new RawTest() {

        @Override
        void runTest(Context c) {
            for (int i = 0; i < numIterationsToRun(); i++) {
                int index = "this is a string".indexOf("string");
                String s = "this is a string".substring(index);
            }
        }

        @Override
        String getTestName() {
            return "STRING OP TEST 2";
        }
    };

    private static RawTest memoryTest1 = new RawTest() {

        @Override
        void runTest(Context c) {
            byte[] reallyBigArray1 = new byte[TEN_MILLION];
            byte[] reallyBigArray2 = new byte[TEN_MILLION];
        }

        @Override
        String getTestName() {
            return "MEMORY TEST 1 (Large Chunk Allocation)";
        }
    };

    private static RawTest memoryTest2 = new RawTest() {

        @Override
        void runTest(Context c) {
            VeryLargeObject o = new VeryLargeObject();
        }

        @Override
        String getTestName() {
            return "MEMORY TEST 2 (Large Object Allocation)";
        }

    };

    private static RawTest storageTest1 = new RawTest() {

        @Override
        int numIterationsToRun() {
            // We want to run fewer iterations of these
            return TEN_THOUSAND;
        }

        @Override
        void runTest(Context c) {
            String filename = "my_filename";
            String stringToWrite = "This is some text to write to the file";

            // 1) create file
            File file = new File(c.getFilesDir(), filename);

            // 2) write a small number of bytes to the file
            try {
                FileOutputStream outputStream = new FileOutputStream(file);
                outputStream.write(stringToWrite.getBytes());
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

    private static RawTest storageTest2 = new RawTest() {

        @Override
        int numIterationsToRun() {
            // We want to run fewer iterations of these
            return TEN_THOUSAND;
        }

        @Override
        void runTest(Context c) {

        }

        @Override
        String getTestName() {
            return "STORAGE TEST 1 (IO Throughput)";
        }
    };

    private static final RawTest[] allTests =
            { mathOpTest1, mathOpTest2, stringOpTest1, stringOpTest2, memoryTest1, memoryTest2,
                    storageTest1 };

    public static TestResult[] run(Context context) {
        TestResult[] results = new TestResult[allTests.length];
        for (int i = 0; i < allTests.length; i++) {
            TestResult testResult = allTests[i].getTestResult(context);
            results[i] = testResult;
        }
        return results;
    }

}
