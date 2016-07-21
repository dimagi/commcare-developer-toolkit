package dalvik.commcare.org.commcaredevelopertoolkit.utilities;


import dalvik.commcare.org.commcaredevelopertoolkit.R;
import dalvik.commcare.org.commcaredevelopertoolkit.activities.HomeActivity;
import dalvik.commcare.org.commcaredevelopertoolkit.activities.RegistrationActivity;

/**
 * Utility to register a device with FCM
 */
public class RegisterUtility extends ToolkitUtility {

    public RegisterUtility(HomeActivity a) {
        super(a);
    }

    @Override
    public Class getCorrespondingActivity() {
        return RegistrationActivity.class;
    }

    @Override
    public String getHomeScreenTitle() {
        return "Register With FCM";
    }

    @Override
    public int getIconResId() {
        return R.drawable.cloud;
    }

}
