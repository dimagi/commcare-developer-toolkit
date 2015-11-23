package dalvik.commcare.org.commcaredevelopertoolkit.utilities;

import android.app.Activity;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.view.View;

import dalvik.commcare.org.commcaredevelopertoolkit.HomeActivity;

/**
 * Created by amstone326 on 11/23/15.
 */
public abstract class ToolkitUtility extends Activity {

    private HomeActivity homeActivity;

    public ToolkitUtility() {
        super();
    }

    public ToolkitUtility(HomeActivity a) {
        this.homeActivity = a;
    }

    public View.OnClickListener getOnClickListener() {
        return new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(homeActivity, getClassToLaunch());
                homeActivity.startActivity(i);
            }
        };
    }

    public Drawable getHomeScreenDrawable() {
        return homeActivity.getDrawable(getIconResId());
    }

    public abstract Class getClassToLaunch();
    public abstract String getHomeScreenTitle();
    public abstract int getIconResId();

}
