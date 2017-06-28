package com.ray.retrofit2.api;


import com.ray.retrofit2.model.Book;

import io.reactivex.Observable;
import retrofit2.http.GET;

/**
 * 接口
 */
public interface IServiceApi {
    /**
     * 获取图书信息
     */
    @GET("v2/book/1220562")
    Observable<Book> getBookInfo();

}
