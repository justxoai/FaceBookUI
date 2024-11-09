package vn.edu.usth.facebook.retrofit;

import android.content.Context;
import android.content.SharedPreferences;

import com.google.gson.Gson;

import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetrofitService {
    private static final String BASE_URL = "http://172.19.200.141:8080"; //BACKEND IP ADDRESS
    private final Retrofit retrofit;
    public RetrofitService(Context context) {
        OkHttpClient.Builder okHttpClient = new OkHttpClient.Builder()
                .connectTimeout(60, TimeUnit.SECONDS)
                .readTimeout(60, TimeUnit.SECONDS)
                .writeTimeout(60, TimeUnit.SECONDS)
                .callTimeout(60, TimeUnit.SECONDS)
                .retryOnConnectionFailure(true);
        SharedPreferences sharedPreferences = context.getSharedPreferences("AuthPrefs", Context.MODE_PRIVATE);
        String token = sharedPreferences.getString("auth_token", "");
        if (!token.isEmpty()) {
            okHttpClient.addInterceptor(chain -> chain.proceed(chain.request().newBuilder()
                    .addHeader("Authorization", "Bearer " + token)
                    .build()));
        }
        retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .client(okHttpClient.build())
                .addConverterFactory(GsonConverterFactory.create(new Gson()))
                .build();
    }
    public Retrofit getRetrofit() {
        return retrofit;
    }
}