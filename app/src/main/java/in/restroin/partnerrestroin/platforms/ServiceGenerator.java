/*
 * Copyright (c) 2018. An change in this code must be done under my supervision and any misuse my lead to legal actions
 */

package in.restroin.partnerrestroin.platforms;

import android.text.TextUtils;
import android.util.Base64;

import okhttp3.Credentials;
import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ServiceGenerator {
    private static final String APP_BASE_URL = "http://192.168.43.24";

    private static OkHttpClient.Builder httpClient = new OkHttpClient.Builder();

    private static Retrofit.Builder retrofitBuilder = new Retrofit.Builder().baseUrl(APP_BASE_URL).addConverterFactory(GsonConverterFactory.create());

    private static Retrofit retrofit = retrofitBuilder.build();

    public static <S> S createService(Class<S> serviceClass){
        return createService(serviceClass,null,null);
    }

    public static <S> S createService(Class<S> serviceClass, String username, String password) {
        if(!TextUtils.isEmpty(username) && !TextUtils.isEmpty(password)){
            String auth_token = Credentials.basic(username, password);
            return createService(serviceClass, auth_token);
        }
        return createService(serviceClass, null);
    }

    public static <S> S createService(
            Class<S> serviceClass, final String authToken) {
        if (!TextUtils.isEmpty(authToken)) {
            AuthenticationInterceptor interceptor =
                    new AuthenticationInterceptor(authToken);

            if (!httpClient.interceptors().contains(interceptor)) {
                httpClient.addInterceptor(interceptor);

                retrofitBuilder.client(httpClient.build());
                retrofit = retrofitBuilder.build();
            }
        }

        return retrofit.create(serviceClass);
    }
}
