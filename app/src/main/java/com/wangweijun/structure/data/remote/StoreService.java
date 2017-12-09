package com.wangweijun.structure.data.remote;

import com.wangweijun.structure.data.model.AppDetailsModel;
import com.wangweijun.structure.data.model.HomePageModel;
import com.wangweijun.structure.data.model.IResponse;
import com.wangweijun.structure.data.model.RankListModel;

import java.util.Map;

import io.reactivex.Observable;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.FieldMap;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.HeaderMap;
import retrofit2.http.POST;
import retrofit2.http.Query;

/**
 * Created by wangweijun1 on 2017/12/6.
 */

public interface StoreService {

    static final String ENDPOINT = "http://mapi.letvstore.com/";

    @FormUrlEncoded
    @POST("/mapi/edit/postrecommend")
    Observable<IResponse<HomePageModel>> getHomePageRequest(@FieldMap Map<String, String> params,
                                                            @HeaderMap Map<String, String> headers);

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
