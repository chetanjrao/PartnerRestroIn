package in.restroin.partnerrestroin.utils;

import android.support.annotation.NonNull;

import java.io.IOException;

import okhttp3.Credentials;
import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;

public class BasicAuthInterceptor implements Interceptor {
    private String credentials;

    public BasicAuthInterceptor(String username, String password){
        this.credentials = Credentials.basic(username, password);
    }

    @Override
    public Response intercept(@NonNull  Chain chain) throws IOException {
        Request request = chain.request();
        Request authenticationRequest = request.newBuilder().addHeader("Authorization", credentials).build();
        return chain.proceed(authenticationRequest);
    }
}
