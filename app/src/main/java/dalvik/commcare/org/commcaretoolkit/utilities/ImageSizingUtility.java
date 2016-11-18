package dalvik.commcare.org.commcaretoolkit.utilities;

import dalvik.commcare.org.commcaretoolkit.activities.HomeActivity;
import dalvik.commcare.org.commcaretoolkit.R;
import dalvik.commcare.org.commcaretoolkit.activities.ImageSizingActivity;

/**
 * Created by amstone326 on 11/23/15.
 */
public class ImageSizingUtility extends ToolkitUtility {

    public ImageSizingUtility(HomeActivity a) {
        super(a);
    }

    @Override
    public String getHomeScreenTitle() {
        return "Size Your Images";
    }

    @Override
    public int getIconResId() {
        return R.drawable.ic_resize_images;
    }

    @Override
    public Class getCorrespondingActivity() {
        return ImageSizingActivity.class;
    }
}
