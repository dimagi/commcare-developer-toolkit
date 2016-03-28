package dalvik.commcare.org.commcaredevelopertoolkit.utilities;

import dalvik.commcare.org.commcaredevelopertoolkit.R;
import dalvik.commcare.org.commcaredevelopertoolkit.activities.HomeActivity;
import dalvik.commcare.org.commcaredevelopertoolkit.activities.RefreshToLatestBuildActivity;

/**
 * Created by amstone326 on 3/18/16.
 */
public class RefreshToLatestBuildUtility extends ToolkitUtility {

    public RefreshToLatestBuildUtility(HomeActivity a) {
        super(a);
    }

    @Override
    public Class getCorrespondingActivity() {
        return RefreshToLatestBuildActivity.class;
    }

    @Override
    public String getHomeScreenTitle() {
        return "Refresh to Latest Build";
    }

    @Override
    public int getIconResId() {
        return R.mipmap.ic_device_info;
    }
}
