package dalvik.commcare.org.commcaredevelopertoolkit.utilities;

import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import dalvik.commcare.org.commcaredevelopertoolkit.HomeActivity;
import dalvik.commcare.org.commcaredevelopertoolkit.R;
import dalvik.commcare.org.commcaredevelopertoolkit.ResizeListener;
import dalvik.commcare.org.commcaredevelopertoolkit.UserResizableView;

/**
 * Created by amstone326 on 11/23/15.
 */
public class ImageSizingUtility extends ToolkitUtility implements ResizeListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.image_sizing_layout);
        setupView();
    }

    private void setupView() {
        UserResizableView v = (UserResizableView) findViewById(R.id.resizable_canvas);
        v.setResizeListener(this);
        Button b = (Button) findViewById(R.id.enter_aspect_ratio_button);
        b.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                launchAspectRatioDialog();
            }
        });
    }

    private void launchAspectRatioDialog() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setView(R.layout.enter_aspect_ratio_view);
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

    @Override
    public void onResize(float width, float height) {
        TextView widthDisplay = (TextView) findViewById(R.id.width_display);
        widthDisplay.setText("width: " + width + " pixels");
        TextView heightDisplay = (TextView) findViewById(R.id.height_display);
        heightDisplay.setText("height: " + height + " pixels");
    }
}
