package dalvik.commcare.org.commcaredevelopertoolkit.activities;

import android.app.Service;
import android.content.Intent;
import android.os.Binder;
import android.os.IBinder;
import android.support.annotation.Nullable;

/**
 * Created by Saumya on 7/25/2016.
 */
public class TokenRefreshService extends Service {

    private LocalBinder mBinder = new LocalBinder();

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return mBinder;
    }

    private class LocalBinder extends Binder {
        public TokenRefreshService getService() {
            return TokenRefreshService.this;
        }
    }

    @Override
    public int onStartCommand (Intent intent, int flags, int startId){
        String token = intent.getStringExtra("TOKEN");
        //TODO: submit token to server along with saved web user credentials
        return 0;

    }
}
