package in.restroin.partnerrestroin.utils;

import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.media.RingtoneManager;
import android.net.Uri;
import android.os.Build;
import android.support.annotation.NonNull;
import android.support.v4.app.NotificationCompat;
import android.util.Log;

import com.google.firebase.messaging.FirebaseMessagingService;
import com.google.firebase.messaging.RemoteMessage;

import in.restroin.partnerrestroin.AnalyticsActivity;
import in.restroin.partnerrestroin.BookingActivity;
import in.restroin.partnerrestroin.R;
import in.restroin.partnerrestroin.interfaces.PartnerRestroINClient;
import in.restroin.partnerrestroin.models.MessageModel;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class PartnerMessagingService extends FirebaseMessagingService {
    private final String API_BASE_URL = "https://www.restroin.in/developers/api/v2/";
    private HttpLoggingInterceptor loggingInterceptor = new HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY);
    private OkHttpClient.Builder httpClient = new OkHttpClient.Builder().addInterceptor(loggingInterceptor);
    private Retrofit.Builder builder = new Retrofit.Builder().baseUrl(API_BASE_URL).client(httpClient.build()).addConverterFactory(GsonConverterFactory.create());
    private Retrofit retrofit = builder.build();
    private Context context;
    private String token;
    @Override
    public void onNewToken(String s) {
        Log.d("FireBaseCloud", "Your FCM Registration Token: " + s);
       this.token = s;
       UpdateTokenToServer(s);
    }

    @Override
    public void onMessageReceived(RemoteMessage remoteMessage) {
        sendNotification(remoteMessage.getNotification().getBody(), remoteMessage.getNotification().getTitle(), remoteMessage.getData().get("booking_id"));
    }

    public String getToken(){
        return token;
    }

    public void UpdateTokenToServer(final String token){
        PartnerRestroINClient client = retrofit.create(PartnerRestroINClient.class);
        final SavedPreferences savedPreferences = new SavedPreferences();
        Call<MessageModel> call = client.updateRegistrationKey(savedPreferences.getApiKey(getApplicationContext()), token, savedPreferences.getPartnerID(getApplicationContext()));
        call.enqueue(new Callback<MessageModel>() {
            @Override
            public void onResponse(@NonNull Call<MessageModel> call,@NonNull Response<MessageModel> response) {
                Log.d("ProfileDetails", "Your registration token has been successfully updated. Your new token id: " + response.body().getMessage());
                savedPreferences.device_uid_set(getApplicationContext(), token);
            }

            @Override
            public void onFailure(@NonNull Call<MessageModel> call,@NonNull Throwable t) {
                Log.d("ProfileDetails", "Error updating details" + t.getMessage());
            }
        });
    }
    private void sendNotification(String messageBody, String title, String booking_id) {
        Intent intent = new Intent(getBaseContext(), BookingActivity.class);
        intent.putExtra("booking_id", booking_id);
        intent.putExtra("booking_step", "Confirm");
        intent.putExtra("booking_status", "Confirm");
        PendingIntent pendingIntent = PendingIntent.getActivity(getBaseContext(), 0, intent, PendingIntent.FLAG_UPDATE_CURRENT);
        Uri defaultSoundUri = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION);
        NotificationCompat.Builder notificationBuilder = new NotificationCompat.Builder(getApplicationContext(), "Default_Channel")
                .setSmallIcon(R.mipmap.ic_launcher_new_round)
                .setLargeIcon(BitmapFactory.decodeResource(getResources(), R.mipmap.ic_launcher_new_round))
                .setContentTitle(title)
                .setPriority(2)
                .setAutoCancel(true)
                .setContentText(messageBody).setSound(defaultSoundUri).setContentIntent(pendingIntent);

        NotificationManager notificationManager =
                (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);

        // Since android Oreo notification channel is needed.
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            NotificationChannel channel = new NotificationChannel("Default_Channel",
                    "RestroIN Booking Notification",
                    NotificationManager.IMPORTANCE_DEFAULT);
            notificationManager.createNotificationChannel(channel);
        }

        notificationManager.notify(0 , notificationBuilder.build());
    }

}
