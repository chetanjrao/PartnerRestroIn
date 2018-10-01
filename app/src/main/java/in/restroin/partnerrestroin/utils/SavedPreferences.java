package in.restroin.partnerrestroin.utils;

import android.content.Context;
import android.content.SharedPreferences;
import android.support.annotation.NonNull;

public class SavedPreferences {
    private Context context;
    private String KEY_TYPE = "AUTHENTICATION";
    private SharedPreferences sharedPreferences;

    public void setSharedPreferences(@NonNull Context context, String username, String password, String public_api_key, String partner_id,String restaurant_id){
        this.context = context;
        sharedPreferences = context.getSharedPreferences(KEY_TYPE, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        if(getApiKey(context) == null){
            editor.putString("auth_username", username);
            editor.putString("auth_password", password);
            editor.putString("partner_id", partner_id);
            editor.putString("public_api_key", public_api_key);
            editor.putString("restaurant_id", restaurant_id);
            editor.apply();
        }
    }

    public void removeSharedPreferences(@NonNull Context context){
        this.context = context;
        sharedPreferences = context.getSharedPreferences(KEY_TYPE, Context.MODE_PRIVATE);
        SharedPreferences sharedPreferences1 = context.getSharedPreferences("Notifications", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor1 = sharedPreferences1.edit();
        editor1.remove("device_uid").apply();
        SharedPreferences.Editor editor = sharedPreferences.edit();
            editor.remove("auth_username");
            editor.remove("auth_password");
            editor.remove("partner_id");
            editor.remove("public_api_key");
            editor.remove("restaurant_id");
            editor.apply();

    }

    public void device_uid_set(Context context, String device_id){
        sharedPreferences = context.getSharedPreferences("Notifications", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        if(device_uid_get(context) == null){
            editor.putString("device_uid", device_id).apply();
        }
    }

    public String device_uid_get(Context context){
        sharedPreferences = context.getSharedPreferences("Notifications", Context.MODE_PRIVATE);
        return sharedPreferences.getString("device_uid", null);
    }

    public String getApiKey(Context context){
        this.context = context;
        sharedPreferences = context.getSharedPreferences(KEY_TYPE, Context.MODE_PRIVATE);
        return sharedPreferences.getString("public_api_key", null);
    }



    public String getPartnerID(Context context){
        this.context = context;
        sharedPreferences = context.getSharedPreferences(KEY_TYPE, Context.MODE_PRIVATE);
        return sharedPreferences.getString("partner_id", null);
    }

    public String getUsername(Context context){
        this.context = context;
        sharedPreferences = context.getSharedPreferences(KEY_TYPE, Context.MODE_PRIVATE);
        return sharedPreferences.getString("auth_username", null);
    }

    public String getPassword(Context context){
        this.context = context;
        sharedPreferences = context.getSharedPreferences(KEY_TYPE, Context.MODE_PRIVATE);
        return sharedPreferences.getString("auth_password", null);
    }

    public void setRestaurantID(Context context, String restaurant_id){
        this.context = context;
        sharedPreferences = context.getSharedPreferences(KEY_TYPE, Context.MODE_PRIVATE);
        sharedPreferences.edit().putString("restaurant_id", restaurant_id).apply();
    }

    public String getRestaurantID(Context context){
        this.context = context;
        sharedPreferences = context.getSharedPreferences(KEY_TYPE, Context.MODE_PRIVATE);
        return sharedPreferences.getString("restaurant_id", null);
    }

}
