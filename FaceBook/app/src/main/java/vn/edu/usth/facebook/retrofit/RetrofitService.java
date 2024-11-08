package vn.edu.usth.facebook.retrofit;

import android.content.Context;
import android.content.SharedPreferences;

import com.google.gson.Gson;

import java.io.IOException;

import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetrofitService {

    private Retrofit retrofit;

    public RetrofitService() {
        initializeRetrofit();
    }

    private void initializeRetrofit() {
        retrofit = new Retrofit.Builder()
                .baseUrl("http://192.168.1.100:8080")
                .addConverterFactory(GsonConverterFactory.create(new Gson()))
                .build();
    }

    public RetrofitService(Context context) {
        initializeRetrofit(context);
    }

    private void initializeRetrofit(Context context) {
        OkHttpClient.Builder client = new OkHttpClient.Builder();

        SharedPreferences sharedPreferences = context.getSharedPreferences("AuthPrefs", Context.MODE_PRIVATE);
        String token = sharedPreferences.getString("auth_token", "");

        if (token != null && !token.isEmpty()) {
            client.addInterceptor(new Interceptor() {
                @Override
                public Response intercept(Chain chain) throws IOException {
                    return chain.proceed(chain.request().newBuilder()
                            .addHeader("Authorization", "Bearer " + token)
                            .build());
                }
            });
        }

        retrofit = new Retrofit.Builder()
                .baseUrl("http://192.168.1.100:8080")
                .client(client.build())
                .addConverterFactory(GsonConverterFactory.create(new Gson()))
                .build();

    }
    public Retrofit getRetrofit() {
        return retrofit;
    }
}