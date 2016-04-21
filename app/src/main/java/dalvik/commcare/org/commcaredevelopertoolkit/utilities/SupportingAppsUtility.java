package dalvik.commcare.org.commcaredevelopertoolkit.utilities;

import dalvik.commcare.org.commcaredevelopertoolkit.R;
import dalvik.commcare.org.commcaredevelopertoolkit.activities.HomeActivity;
import dalvik.commcare.org.commcaredevelopertoolkit.activities.SupportingAppsActivity;

/**
 * Created by amstone326 on 4/18/16.
 */
public class SupportingAppsUtility extends ToolkitUtility {

    public SupportingAppsUtility(HomeActivity a) {
        super(a);
    }

    @Override
    public Class getCorrespondingActivity() {
        return SupportingAppsActivity.class;
    }

    @Override
    public String getHomeScreenTitle() {
        return "Download Supporting Apps";
    }

    @Override
    public int getIconResId() {
        return R.drawable.ic_app_download;
    }
}
