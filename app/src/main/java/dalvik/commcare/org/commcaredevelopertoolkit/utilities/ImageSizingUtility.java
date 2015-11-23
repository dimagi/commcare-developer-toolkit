package dalvik.commcare.org.commcaredevelopertoolkit.utilities;

import android.os.Bundle;
import android.view.View;

import dalvik.commcare.org.commcaredevelopertoolkit.HomeActivity;
import dalvik.commcare.org.commcaredevelopertoolkit.R;

/**
 * Created by amstone326 on 11/23/15.
 */
public class ImageSizingUtility extends ToolkitUtility {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.image_sizing_layout);
    }

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
