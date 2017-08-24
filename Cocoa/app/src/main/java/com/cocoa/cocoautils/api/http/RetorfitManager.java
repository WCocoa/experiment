package com.cocoa.cocoautils.api.http;


import android.util.Log;

import com.google.gson.Gson;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * ClassName:
 * author: Cocoa
 * date: 2016/10/18.
 */

public class RetorfitManager {
    //        public static final String SERVER_URL = "http://101.200.182.83:2018/wwwroot/api/";
    public static final String SERVER_URL = "http://www.weather.com.cn/data/sk/";
    private static Gson gson;

    public static ApiServices createClientApi() {
        gson = new Gson();
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(SERVER_URL)
                .addConverterFactory(GsonConverterFactory.create())
//                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .client(createClient())
                .build();
        return retrofit.create(ApiServices.class);
    }


    public static OkHttpClient createClient() {


        OkHttpClient httpClient = new OkHttpClient.Builder()

                .addInterceptor(new Interceptor() {

//                    private long sign_time;
//                    private String sign;


                    @Override
                    public Response intercept(Chain chain) throws IOException {
                        Request req = chain.request();
                        String  url = chain.request().url().toString();

                        Request.Builder builder = req.newBuilder()
                                .addHeader("Content-Type", "application/json")
                                .addHeader("Encoding", "utf-8")
                                .method(req.method(), req.body());


                        Log.d("Appliction", "Request" + chain.request().url());

                        Request request   = builder.build();
                        long    startTime = System.nanoTime();

                        Log.d("Appliction", "request.method:" + request.method());
                        Log.d("Appliction", "RequsetUrl:" + request.url());
                        Log.d("Appliction", "Chain-Connection:" + chain.connection());
                        Log.d("Appliction", "RequsetHeader:" + request.headers());
                        Response response = chain.proceed(request);


                        long endtime = System.nanoTime();
                        Log.d("Appliction", "ResponseURL:" + response.request().url());
                        Log.d("Appliction", "RequestTime:" + (endtime - startTime));
                        Log.d("Appliction", "RequestTime:" + response.code());
                        Log.d("Appliction", "ResponseHeader:" + response.headers());
                        Log.d("Appliction", "ResponseHeader:" + response.body());

                        return response;
                    }

                })

                .connectTimeout(15, TimeUnit.SECONDS)
                .readTimeout(15, TimeUnit.SECONDS)
                .writeTimeout(15, TimeUnit.SECONDS)
                .retryOnConnectionFailure(true)
                .build();

        return httpClient;
    }
}
