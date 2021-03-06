package test.attprog.carlo.androidgcm.gcm;

import android.accounts.Account;
import android.accounts.AccountManager;
import android.app.IntentService;
import android.content.Intent;
import android.util.Log;

import com.google.android.gms.gcm.GoogleCloudMessaging;
import com.google.android.gms.iid.InstanceID;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.http.client.ClientHttpRequestFactory;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.web.client.RestTemplate;

import java.io.IOException;
import java.util.Date;

import test.attprog.carlo.androidgcm.R;
import test.attprog.carlo.androidgcm.messages.IncomingRegistration;
import test.attprog.carlo.androidgcm.messages.RegistrationEnum;

/**
 * Created by Carlo on 02/02/2016.
 */
public class RegistrationIntentService extends IntentService{
    /**
     * Creates an IntentService.  Invoked by your subclass's constructor.
     *
     * @param name Used to name the worker thread, important only for debugging.
     */
    private final static String TAG = "GCM";

    public RegistrationIntentService() {
        super(TAG);
    }

    @Override
    protected void onHandleIntent(Intent intent) {
        Log.i(TAG,"Starting Service at "+ new Date().getTime());
        InstanceID instanceID = InstanceID.getInstance(this);
        String token = null;
        try {
            token = instanceID.getToken(getString(R.string.gcm_defaultSenderId), GoogleCloudMessaging.INSTANCE_ID_SCOPE, null);
        } catch (IOException e) {
            e.printStackTrace();
        }
        Log.i(TAG, "GCM Registration Token: " + token + " at " + new Date().getTime());

        this.sendRegistrationToken(token);

    }

    private void sendRegistrationToken(String token) {

        RestTemplate template = new RestTemplate();
        template.getMessageConverters().add(new MappingJackson2HttpMessageConverter());
        AccountManager accountManager = (AccountManager) getSystemService(ACCOUNT_SERVICE);
        Account[] list = accountManager.getAccounts();
        String googleAccountEmail = null;

        for (Account account : list){
            //Log.i(TAG,"Account type " + account.type + " account name " + account.name);
            if (account.type.equalsIgnoreCase("com.google")){
                Log.i(TAG,"Account " + account.name + " type " + account.type);
                googleAccountEmail = account.name;
            }
        }

        IncomingRegistration ir = new IncomingRegistration(googleAccountEmail,token);
        Log.d(TAG, "Incoming registration " + ir.toString());
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        HttpEntity<IncomingRegistration> registrationHttpEntity = new HttpEntity<>(ir,headers);
        Log.i(TAG,"Sendig new registration token at " + new Date().getTime());
        ResponseEntity<RegistrationEnum> registrationType = template.exchange("http://server:8050//gcm/registration/device", HttpMethod.POST,registrationHttpEntity,RegistrationEnum.class);
        Log.i(TAG, "Registration received " + registrationType.getBody() + " at "  + new Date().getTime());
        RegistrationEnum body = registrationType.getBody();

        if(body.equals(RegistrationEnum.REGISTRATION_OK))
            Log.i(TAG,"Registration OK at " + new Date().getTime());
        if(body.equals(RegistrationEnum.NEW_ACCOUNT_SEND_CONFIG)){
            Log.i(TAG,"Registration NEW at " + new Date().getTime());
        }
    }
}
