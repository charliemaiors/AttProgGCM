package test.attprog.carlo.androidgcm.gcm;

import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.media.RingtoneManager;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.NotificationCompat;
import android.util.Log;

import com.google.android.gms.gcm.GcmListenerService;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import test.attprog.carlo.androidgcm.MainActivity;
import test.attprog.carlo.androidgcm.R;
import test.attprog.carlo.androidgcm.messages.ConfigurationList;
import test.attprog.carlo.androidgcm.messages.Message;

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

        Gson mapper = new GsonBuilder().create();
        String message = (String) data.get("message");
        Log.i(TAG, "Message " + message);
        Message messageVal = mapper.fromJson(message,Message.class);
        ConfigurationList configurations = new ConfigurationList(messageVal.getData().getValues());

        this.sendIntentToMain(configurations);
    }

    private void sendIntentToMain(ConfigurationList configurations) {
        Intent intent = new Intent(this, MainActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        PendingIntent pendingIntent = PendingIntent.getActivity(this, 0 /* Request code */, intent,
                PendingIntent.FLAG_ONE_SHOT);

        Uri defaultSoundUri = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION);
        NotificationCompat.Builder notificationBuilder = new NotificationCompat.Builder(this)
                .setSmallIcon(R.drawable.common_google_signin_btn_icon_dark)
                .setContentTitle("GCM Message")
                .setContentText(configurations.toString())
                .setAutoCancel(true)
                .setSound(defaultSoundUri)
                .setContentIntent(pendingIntent);

        NotificationManager notificationManager =
                (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);

        notificationManager.notify(0 /* ID of notification */, notificationBuilder.build());

    }

}
