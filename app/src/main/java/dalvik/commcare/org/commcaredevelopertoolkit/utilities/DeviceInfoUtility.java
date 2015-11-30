package dalvik.commcare.org.commcaredevelopertoolkit.utilities;

import dalvik.commcare.org.commcaredevelopertoolkit.activities.HomeActivity;
import dalvik.commcare.org.commcaredevelopertoolkit.R;
import dalvik.commcare.org.commcaredevelopertoolkit.activities.DeviceInfoActivity;

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
        return R.mipmap.ic_device_info;
    }
}
