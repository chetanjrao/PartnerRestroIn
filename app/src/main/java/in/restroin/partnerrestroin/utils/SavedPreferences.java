package in.restroin.partnerrestroin.utils;

import android.content.Context;
import android.content.SharedPreferences;
import android.support.annotation.NonNull;

public class SavedPreferences {
    private Context context;
    private String KEY_TYPE = "AUTHENTICATION";
    private SharedPreferences sharedPreferences;

    public void setSharedPreferences(@NonNull Context context, String username, String password, String public_api_key){
        this.context = context;
        sharedPreferences = context.getSharedPreferences(KEY_TYPE, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        if(getApiKey(context).isEmpty()){
            editor.putString("auth_username", username);
            editor.putString("auth_password", password);
            editor.putString("public_api_key", public_api_key);
            editor.apply();
        }

    }

    private String getApiKey(Context context){
        this.context = context;
        sharedPreferences = context.getSharedPreferences(KEY_TYPE, Context.MODE_PRIVATE);
        return sharedPreferences.getString("public_api_key", null);
    }

}
