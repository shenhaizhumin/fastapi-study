package com.example.bubblelayout.api

import com.example.bubblelayout.entity.UserEntity
import io.reactivex.Observable
import retrofit2.http.FieldMap
import retrofit2.http.FormUrlEncoded
import retrofit2.http.GET
import retrofit2.http.POST

interface ApiService {

    @FormUrlEncoded
    @POST(Urls.session)
    fun session(@FieldMap map: Map<String, String>): Observable<BaseResponse<UserEntity>>

    @GET(Urls.nodes)
    fun node(): Observable<BaseResponse<Any>>

}