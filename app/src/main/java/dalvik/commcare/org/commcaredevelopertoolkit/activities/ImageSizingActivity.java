package dalvik.commcare.org.commcaredevelopertoolkit.activities;

import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import dalvik.commcare.org.commcaredevelopertoolkit.R;
import dalvik.commcare.org.commcaredevelopertoolkit.ResizeListener;
import dalvik.commcare.org.commcaredevelopertoolkit.UserResizableView;

/**
 * Created by amstone326 on 11/24/15.
 */
public class ImageSizingActivity extends AppCompatActivity implements ResizeListener {

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
        final AlertDialog dialog = builder.create();
        View dialogView = getLayoutInflater().inflate(R.layout.enter_aspect_ratio_view, null);

        Button submitButton = (Button) dialogView.findViewById(R.id.ok_button);
        Button cancelButton = (Button) dialogView.findViewById(R.id.cancel_button);
        final EditText widthEditText = (EditText) dialogView.findViewById(R.id.user_provided_width);
        final EditText heightEditText = (EditText) dialogView.findViewById(R.id.user_provided_height);
        final TextView errorMessage = (TextView) dialogView.findViewById(R.id.error_message);

        submitButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String widthString = getEnteredText(widthEditText);
                String heightString = getEnteredText(heightEditText);
                if ("".equals(widthString) || "".equals(heightString)) {
                    errorMessage.setText(getString(R.string.values_not_entered_error));
                    errorMessage.setVisibility(View.VISIBLE);
                    return;
                }

                float width = Float.parseFloat(widthString);
                float height = Float.parseFloat(heightString);
                UserResizableView resizableView = (UserResizableView)
                        findViewById(R.id.resizable_canvas);
                if (resizableView.processUserProvidedAspectRatio(width, height)) {
                    showClearButton();
                    dialog.dismiss();
                } else {
                    errorMessage.setText(getString(R.string.aspect_ratio_invalid_error));
                    errorMessage.setVisibility(View.VISIBLE);
                }
            }
        });

        cancelButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
            }
        });

        dialog.setView(dialogView);
        dialog.show();
    }

    private static String getEnteredText(EditText et) {
        return et.getText().toString();
    }

    private void showClearButton() {
        final Button clearAspectRatioButton = (Button) findViewById(R.id.clear_aspect_ratio_button);
        clearAspectRatioButton.setVisibility(View.VISIBLE);
        clearAspectRatioButton.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                UserResizableView resizableView = (UserResizableView)
                        findViewById(R.id.resizable_canvas);
                resizableView.clearAspectRatio();
                v.setVisibility(View.GONE);
            }
        });
    }

    @Override
    public void onResize(float width, float height) {
        TextView widthDisplay = (TextView) findViewById(R.id.width_display);
        widthDisplay.setText("width: " + width + " pixels");
        TextView heightDisplay = (TextView) findViewById(R.id.height_display);
        heightDisplay.setText("height: " + height + " pixels");
    }

}
