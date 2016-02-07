package test.attprog.carlo.androidgcm.gcm;

import android.app.IntentService;
import android.content.Intent;

import com.google.android.gms.gcm.GoogleCloudMessaging;
import com.google.android.gms.iid.InstanceID;
import com.google.android.gms.iid.InstanceIDListenerService;

import java.io.IOException;

import test.attprog.carlo.androidgcm.R;

/**
 * Created by Carlo on 02/02/2016.
 */
public class MyInstanceIDListenerService extends InstanceIDListenerService {
    /**
     * Creates an IntentService.  Invoked by your subclass's constructor.
     */

    @Override
    public void onTokenRefresh() {
        // Fetch updated Instance ID token and notify our app's server of any changes (if applicable).
        Intent intent = new Intent(this, RegistrationIntentService.class);
        startService(intent);
    }


}
