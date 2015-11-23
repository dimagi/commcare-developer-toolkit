package dalvik.commcare.org.commcaredevelopertoolkit.utilities;

import android.media.Image;

import dalvik.commcare.org.commcaredevelopertoolkit.HomeActivity;
import dalvik.commcare.org.commcaredevelopertoolkit.R;

/**
 * Created by amstone326 on 11/23/15.
 */
public class ImageSizingUtility extends ToolkitUtility {

    public ImageSizingUtility() {
        super();
    }

    public ImageSizingUtility(HomeActivity a) {
        super(a);
    }

    @Override
    public String getHomeScreenTitle() {
        return "Size Your Images";
    }

    @Override
    public int getIconResId() {
        return R.mipmap.image_sizing;
    }

    @Override
    public Class getClassToLaunch() {
        return ImageSizingUtility.class;
    }

}
