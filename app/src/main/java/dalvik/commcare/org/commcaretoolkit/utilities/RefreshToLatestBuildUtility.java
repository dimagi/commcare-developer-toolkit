package dalvik.commcare.org.commcaretoolkit.utilities;

import dalvik.commcare.org.commcaretoolkit.R;
import dalvik.commcare.org.commcaretoolkit.activities.HomeActivity;
import dalvik.commcare.org.commcaretoolkit.activities.RefreshToLatestBuildActivity;

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
        return R.drawable.ic_refresh;
    }
}
