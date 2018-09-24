package in.restroin.partnerrestroin.utils;

import android.content.Context;
import android.util.Log;

import com.google.firebase.messaging.FirebaseMessagingService;
import com.google.firebase.messaging.RemoteMessage;

public class PartnerMessagingService extends FirebaseMessagingService {
    private Context context;
    private String token;
    @Override
    public void onNewToken(String s) {
        Log.d("FirebaseCloud", "Your FCM Registration Token: " + s);
       this.token = s;
    }

    @Override
    public void onMessageReceived(RemoteMessage remoteMessage) {
        super.onMessageReceived(remoteMessage);
    }

    public String getToken(){
        return token;
    }
}
