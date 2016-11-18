package dalvik.commcare.org.commcaretoolkit.analytics;

import com.google.android.gms.analytics.HitBuilders;
import com.google.android.gms.analytics.Tracker;

import dalvik.commcare.org.commcaretoolkit.BuildConfig;
import dalvik.commcare.org.commcaretoolkit.ToolkitApplication;

/**
 * Created by amstone326 on 11/18/16.
 */
public class AnalyticsUtils {

    public static void reportUtilityUsage(String utilityName) {
        System.out.println("reporting utility usage for " + utilityName);
        reportEvent(AnalyticsValues.CATEGORY_GENERAL, AnalyticsValues.ACTION_USE_A_UTILITY,
                utilityName);
    }

    /**
     * Report a google analytics event that has a category, action, and label
     */
    private static void reportEvent(String category, String action, String label) {
        if (!BuildConfig.DEBUG) {
            getTracker().send(new HitBuilders.EventBuilder()
                    .setCategory(category)
                    .setAction(action)
                    .setLabel(label)
                    .build());
        }
    }

    private static Tracker getTracker() {
        return ToolkitApplication.instance().getAnalyticsTracker();
    }
}
