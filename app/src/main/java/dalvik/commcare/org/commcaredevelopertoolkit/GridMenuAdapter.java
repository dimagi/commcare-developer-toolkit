package dalvik.commcare.org.commcaredevelopertoolkit;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import dalvik.commcare.org.commcaredevelopertoolkit.activities.HomeActivity;
import dalvik.commcare.org.commcaredevelopertoolkit.utilities.ToolkitUtility;

/**
 * Created by amstone326 on 11/23/15.
 */
public class GridMenuAdapter extends BaseAdapter {

    private ToolkitUtility[] utilities;
    private Context context;

    public GridMenuAdapter(HomeActivity a, ToolkitUtility[] utilities)  {
        this.context = a;
        this.utilities = utilities;
    }

    @Override
    public int getCount() {
        return utilities.length;
    }

    @Override
    public Object getItem(int position) {
        return utilities[position];
    }

    @Override
    public long getItemId(int position) {
        //TODO: figure out what this is
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View v = convertView;
        if (v == null) {
            v = View.inflate(context, R.layout.grid_item_view, null);
        }
        updateView(v, (ToolkitUtility)getItem(position));
        return v;
    }

    private void updateView(View v, ToolkitUtility utility) {
        ImageView iv = (ImageView) v.findViewById(R.id.grid_item_image);
        iv.setImageDrawable(utility.getHomeScreenDrawable());

        TextView tv = (TextView) v.findViewById(R.id.grid_item_title);
        tv.setText(utility.getHomeScreenTitle());

        v.setOnClickListener(utility.getOnClickListener());
    }
}
