package dalvik.commcare.org.commcaredevelopertoolkit.utilities;

import android.content.Context;

import dalvik.commcare.org.commcaredevelopertoolkit.HomeActivity;
import dalvik.commcare.org.commcaredevelopertoolkit.R;

/**
 * Created by amstone326 on 11/23/15.
 */
public class DeviceInfoUtility extends ToolkitUtility {

    public DeviceInfoUtility() {
        super();
    }

    public DeviceInfoUtility(HomeActivity a) {
        super(a);
    }

    @Override
    public Class getClassToLaunch() {
        return DeviceInfoUtility.class;
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
