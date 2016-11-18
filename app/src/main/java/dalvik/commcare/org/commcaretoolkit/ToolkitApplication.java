package dalvik.commcare.org.commcaretoolkit;

import android.app.Application;

import com.google.android.gms.analytics.GoogleAnalytics;
import com.google.android.gms.analytics.Tracker;

/**
 * Created by amstone326 on 11/18/16.
 */

public class ToolkitApplication extends Application {

    private Tracker analyticsTracker;

    synchronized public Tracker getAnalyticsTracker() {
        GoogleAnalytics analyticsInstance = GoogleAnalytics.getInstance(this);
        if (analyticsTracker == null) {
            analyticsTracker = analyticsInstance.newTracker(BuildConfig.ANALYTICS_TRACKING_ID);
            analyticsTracker.enableAutoActivityTracking(true);
        }
        return analyticsTracker;
    }

}
