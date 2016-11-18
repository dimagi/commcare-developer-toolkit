package dalvik.commcare.org.commcaretoolkit.utilities;

import dalvik.commcare.org.commcaretoolkit.activities.HomeActivity;
import dalvik.commcare.org.commcaretoolkit.R;
import dalvik.commcare.org.commcaretoolkit.activities.DeviceInfoActivity;

/**
 * Created by amstone326 on 11/23/15.
 */
public class DeviceInfoUtility extends ToolkitUtility {

    public DeviceInfoUtility(HomeActivity a) {
        super(a);
    }

    @Override
    public Class getCorrespondingActivity() {
        return DeviceInfoActivity.class;
    }

    @Override
    public String getHomeScreenTitle() {
        return "About My Device";
    }

    @Override
    public int getIconResId() {
        return R.drawable.ic_device_info;
    }
}
