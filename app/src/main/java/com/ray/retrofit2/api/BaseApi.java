package com.ray.retrofit2.api;

import android.util.Log;

import com.ray.retrofit2.MyApplication;
import com.ray.retrofit2.utils.NetUtils;
import com.ray.retrofit2.utils.ToastUtils;

import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by Azure on 2016/9/5.
 */
public class BaseApi {

    private static final String LOCAL_SERVER_URL = "https://api.douban.com/";

    public static <T> T createApi(Class<T> service) {
        final String url = LOCAL_SERVER_URL;
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(url)
                .addConverterFactory(GsonConverterFactory.create())
                .client(MyApplication.getApplication().genericClient())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .build();
        return retrofit.create(service);
    }

    public static <T> void request(Observable<T> observable,
                                   final IResponseListener<T> listener) {

        if (!NetUtils.isConnected(MyApplication.getApplication())) {
            ToastUtils.getInstance().showToast("网络不可用,请检查网络");
            if (listener != null) {
                listener.onFail();
            }
            return;
        }
        observable.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<T>() {

                               @Override
                               public void onError(Throwable e) {
                                   e.printStackTrace();
                                   Log.d("onError", e.getMessage());
                                   if (listener != null) {
                                       listener.onFail();
                                   }
                               }

                               @Override
                               public void onComplete() {

                               }

                               @Override
                               public void onSubscribe(Disposable disposable) {

                               }

                               @Override
                               public void onNext(T data) {
                                   if (listener != null) {
                                       listener.onSuccess(data);
                                   }
                               }
                           }
                );
    }

    public interface IResponseListener<T> {

        void onSuccess(T data);

        void onFail();
    }

}
