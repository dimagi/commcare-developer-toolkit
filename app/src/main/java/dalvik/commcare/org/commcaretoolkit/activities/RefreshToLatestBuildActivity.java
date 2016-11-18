package dalvik.commcare.org.commcaretoolkit.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

import dalvik.commcare.org.commcaretoolkit.R;

/**
 * Created by amstone326 on 3/18/16.
 */
public class RefreshToLatestBuildActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.refresh_latest_build_layout);

        Button startActionButton = (Button)findViewById(R.id.trigger_action);
        startActionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                intent.addFlags(Intent.FLAG_INCLUDE_STOPPED_PACKAGES);
                intent.setAction("org.commcare.dalvik.api.action.RefreshToLatestBuildAction");
                sendBroadcast(intent);
            }
        });
    }

}
