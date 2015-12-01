package dalvik.commcare.org.commcaredevelopertoolkit.utilities;

import android.app.Activity;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.view.View;

import dalvik.commcare.org.commcaredevelopertoolkit.activities.HomeActivity;

/**
 * Created by amstone326 on 11/23/15.
 */
public abstract class ToolkitUtility {

    private Activity homeActivity;

    public ToolkitUtility(Activity a) {
        this.homeActivity = a;
    }

    public View.OnClickListener getOnClickListener() {
        return new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(homeActivity, getCorrespondingActivity());
                homeActivity.startActivity(i);
            }
        };
    }

    public Drawable getHomeScreenDrawable() {
        if (android.os.Build.VERSION.SDK_INT >= 21) {
            return homeActivity.getDrawable(getIconResId());
        } else {
            return homeActivity.getResources().getDrawable(getIconResId());
        }
    }

    public abstract Class getCorrespondingActivity();
    public abstract String getHomeScreenTitle();
    public abstract int getIconResId();

}
