package in.restroin.partnerrestroin.utils;

import okhttp3.OkHttpClient;
import retrofit2.Retrofit;

public class ServiceGenerator {
    public static final String API_BASE_URL = "http://developers.restroin.in";
    private static OkHttpClient.Builder httpClient = new OkHttpClient.Builder();
    private static Retrofit.Builder builder = new Retrofit.Builder().baseUrl(API_BASE_URL);
}
