package dalvik.commcare.org.commcaretoolkit.device.tests;

/**
 * Created by amstone326 on 1/27/17.
 */
public class VeryLargeObject {

    private static final int NUM_OBJECTS = RawTest.ONE_MILLION;

    private static final String A_STRING = "this is a string";
    private static final int AN_INT = 99;
    private static final double A_FLOAT = .5;
    private static final byte[] SOME_BYTES = new byte[1000];

    Object[] tonsOfObjects;

    public VeryLargeObject() {
        tonsOfObjects = new Object[NUM_OBJECTS];
        for (int i = 0; i < NUM_OBJECTS; i++) {
            double random = Math.random();
            if (random < .25) {
                tonsOfObjects[i] = A_STRING;
            } else if (random < .5) {
                tonsOfObjects[i] = AN_INT;
            } else if (random < .75) {
                tonsOfObjects[i] = SOME_BYTES;
            } else if (random < 1) {
                tonsOfObjects[i] = A_FLOAT;
            }
        }
    }
}
