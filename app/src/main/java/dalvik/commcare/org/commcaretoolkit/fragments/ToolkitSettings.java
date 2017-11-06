package dalvik.commcare.org.commcaretoolkit.fragments;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceFragment;

import dalvik.commcare.org.commcaretoolkit.R;
import dalvik.commcare.org.commcaretoolkit.ToolkitApplication;

/**
 * Created by amstone326 on 11/6/17.
 */

public class ToolkitSettings extends PreferenceFragment {

    private static final String PREFS_FILENAME = "cctoolkit-shared-preferences-file";

    private static final String TARGET_PACKAGE_SETTING = "target-package-id";
    private static final String NORMAL_COMMCARE_PACKAGE_ID = "org.commcare.dalvik";

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getPreferenceManager().setSharedPreferencesName(PREFS_FILENAME);
        addPreferencesFromResource(R.xml.preferences);
        if (getActivity() != null) {
            getActivity().setTitle(getResources().getString(R.string.settings_title));
        }
    }

    public static String getTargetPackageId() {
        return getAppPreferences().getString(TARGET_PACKAGE_SETTING, NORMAL_COMMCARE_PACKAGE_ID);
    }

    private static SharedPreferences getAppPreferences() {
        return ToolkitApplication.instance().getSharedPreferences(PREFS_FILENAME, Context.MODE_PRIVATE);
    }


}
