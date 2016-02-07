package test.attprog.carlo.androidgcm.gcm;

import android.app.IntentService;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;

import com.google.android.gms.gcm.GcmListenerService;

/**
 * Created by Carlo on 02/02/2016.
 */
public class MyGcmListenerService extends GcmListenerService {
    /**
     * Creates an IntentService.  Invoked by your subclass's constructor.
     */
    private final String TAG = "LISTENER";

    @Override
    public void onMessageReceived(String from, Bundle data) {

        String message = (String) data.get("message");
        Log.i(TAG,"Message " + message);

    }

}
