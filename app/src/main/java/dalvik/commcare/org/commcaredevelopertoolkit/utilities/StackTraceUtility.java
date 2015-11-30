package dalvik.commcare.org.commcaredevelopertoolkit.utilities;

import dalvik.commcare.org.commcaredevelopertoolkit.activities.HomeActivity;
import dalvik.commcare.org.commcaredevelopertoolkit.R;
import dalvik.commcare.org.commcaredevelopertoolkit.activities.StackTraceActivity;

/**
 * Created by amstone326 on 11/23/15.
 */
public class StackTraceUtility extends ToolkitUtility {

    public StackTraceUtility(HomeActivity a) {
        super(a);
    }

    @Override
    public String getHomeScreenTitle() {
        return "Send a Stack Trace";
    }

    @Override
    public int getIconResId() {
        return R.mipmap.ic_resize_images;
    }

    @Override
    public Class getCorrespondingActivity() {
        return StackTraceActivity.class;
    }

}
