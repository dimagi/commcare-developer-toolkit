package dalvik.commcare.org.commcaretoolkit.utilities;

import dalvik.commcare.org.commcaretoolkit.R;
import dalvik.commcare.org.commcaretoolkit.activities.HomeActivity;
import dalvik.commcare.org.commcaretoolkit.activities.RawDeviceTestsActivity;

/**
 * Created by amstone326 on 1/25/17.
 */

public class RawDeviceTestsUtility extends ToolkitUtility {

    public RawDeviceTestsUtility(HomeActivity homeActivity) {
        super(homeActivity);
    }

    @Override
    public Class getCorrespondingActivity() {
        return RawDeviceTestsActivity.class;
    }

    @Override
    public String getHomeScreenTitle() {
        return "Device Performance Tests";
    }

    @Override
    public int getIconResId() {
        return R.drawable.ic_performance_tests;
    }
}
