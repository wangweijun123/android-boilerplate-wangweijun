package com.wangweijun.structure.data.remote;

import java.io.File;
import java.io.IOException;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.Arrays;
import java.util.List;

import okhttp3.Cache;
import okhttp3.Dns;
import okhttp3.HttpUrl;
import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.logging.HttpLoggingInterceptor;

/**
 * Created by wangweijun1 on 2017/5/23.
 */

public class OkHttpUtils {
    private static volatile OkHttpUtils sInstance;

    int cacheSize = 10 * 1024 * 1024; // 10 MiB
    // 测试超过缓存大小，删除文件与内存应用
//    int cacheSize = 9500;

    private OkHttpClient mOkHttpClient;

    private OkHttpClient mDnsOkHttpClient;

    private OkHttpClient mCacheOkHttpClient;


    private OkHttpUtils() {

        HttpLoggingInterceptor httpLoggingInterceptor = new HttpLoggingInterceptor();
        httpLoggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY);

        mOkHttpClient = new OkHttpClient.Builder()
                .addNetworkInterceptor(httpLoggingInterceptor)
                .build();


        /** DNS http client */
        mDnsOkHttpClient = new OkHttpClient.Builder()
                .dns(HTTP_DNS)
                .addNetworkInterceptor(httpLoggingInterceptor)
                .addNetworkInterceptor(new Interceptor() {
                    @Override
                    public Response intercept(Chain chain) throws IOException {
                        Response originalResponse = chain.proceed(chain.request());
                        //在返回header中加入缓存消息
                        //下次将不再发送请求
                        return originalResponse.newBuilder().header("Cache-Control", "max-age=600").build();
                    }
                })
                .build();

        mCacheOkHttpClient = new OkHttpClient.Builder()
                .cache(new Cache(new File("/storage/emulated/0/Android/data/com.example.wangweijun1.retrofit_xxx/cache"), cacheSize))
                .build();
    }

    public static OkHttpUtils getInstance() {
        if (sInstance == null) {
            synchronized (OkHttpUtils.class) {
                if (sInstance == null) {
                    sInstance = new OkHttpUtils();
                }
            }
        }
        return sInstance;
    }

    public OkHttpClient getOkHttpClient() {
        return mOkHttpClient;
    }

    public OkHttpClient getHTTPDnsClient() {
        return mDnsOkHttpClient;
    }

    public OkHttpClient getCacheOkHttpClient() {
        return mCacheOkHttpClient;
    }

    Dns HTTP_DNS = new Dns() {
        @Override
        public List<InetAddress> lookup(String hostname) throws UnknownHostException {
            //防御代码
            if (hostname == null) throw new UnknownHostException("hostname == null");
            //dnspod提供的dns服务
            HttpUrl httpUrl = new HttpUrl.Builder().scheme("http")
                    .host("119.29.29.29")
                    .addPathSegment("d")
                    .addQueryParameter("dn", hostname)
                    .build();
            Request dnsRequest = new Request.Builder().url(httpUrl).get().build();
            try {
                String s = OkHttpUtils.getInstance().getOkHttpClient().newCall(dnsRequest).execute().body().string();
                //避免服务器挂了却无法查询DNS
                if (!s.matches("\\b(?:\\d{1,3}\\.){3}\\d{1,3}\\b")) {
                    return Dns.SYSTEM.lookup(hostname);
                }
                return Arrays.asList(InetAddress.getAllByName(s));
            } catch (IOException e) {
                e.printStackTrace();
                return Dns.SYSTEM.lookup(hostname);
            }
        }
    };
}
