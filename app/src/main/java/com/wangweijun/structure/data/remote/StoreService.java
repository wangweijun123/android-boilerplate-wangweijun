package com.wangweijun.structure.data.remote;

import com.wangweijun.structure.data.model.AppDetailsModel;
import com.wangweijun.structure.data.model.IResponse;
import com.wangweijun.structure.data.model.RankListModel;

import io.reactivex.Observable;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.GET;
import retrofit2.http.Query;

/**
 * Created by wangweijun1 on 2017/12/6.
 */

public interface StoreService {

    static final String ENDPOINT = "http://mapi.letvstore.com/";

    @GET("mapi/edit/recommend")
    Observable<IResponse<RankListModel>> getRankApps(@Query("pagefrom") String pagefrom, @Query("pagesize") String pagesize, @Query("code") String code);

    @GET("mapi/app/get")
    Observable<IResponse<AppDetailsModel>> getAppDetail(@Query("packagename") String packagename);

    /******** Helper class that sets up a new services *******/
    class Creator {

        public static StoreService newStoreService() {
            Retrofit retrofit = new Retrofit.Builder()
                    .baseUrl(StoreService.ENDPOINT)
                    .addConverterFactory(GsonConverterFactory.create())
                    .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                    .client(OkHttpUtils.getInstance().getOkHttpClient())
                    .build();
            return retrofit.create(StoreService.class);
        }
    }
}
