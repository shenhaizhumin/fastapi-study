package com.example.bubblelayout.api

import com.example.bubblelayout.entity.CategoryEntity
import com.example.bubblelayout.entity.UserEntity
import io.reactivex.Observable
import retrofit2.http.*

interface ApiService {

    @FormUrlEncoded
    @POST(Urls.session)
    fun session(@FieldMap map: Map<String, String>): Observable<BaseResponse<UserEntity>>

    @GET(Urls.nodes)
    fun node(): Observable<BaseResponse<Any>>

    @GET(Urls.categoryApi)
    fun categoryApi(
        @Path("category") category: String,
        @Path("type") type: String,
        @Path("page") page: Int,
        @Path("count") count: Int
    ): Observable<BaseResponse<MutableList<CategoryEntity>>>

    @GET(Urls.categories)
    fun categories(
        @Path("categories") categories: String
    ): Observable<BaseResponse<MutableList<CategoryEntity>>>

}