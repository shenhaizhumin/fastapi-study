package com.example.bubblelayout.api

import com.example.bubblelayout.api.body.CommentBody
import com.example.bubblelayout.api.body.MomentBody
import com.example.bubblelayout.api.body.UserBody
import com.example.bubblelayout.entity.*
import io.reactivex.Observable
import okhttp3.MultipartBody
import okhttp3.RequestBody
import retrofit2.http.*
import java.io.File

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

    @POST(Urls.register)
    fun register(@Body body: UserBody): Observable<BaseResponse<UserEntity>>

    @GET(Urls.userInfo)
    fun userInfo(): Observable<BaseResponse<UserEntity>>

    @PUT(Urls.updateUser)
    fun updateUser(@Body body: UserBody): Observable<BaseResponse<UserEntity>>


    //    @FormUrlEncoded
//    @Multipart
    @POST(Urls.uploadFile)
    fun uploadFile(@Body fileBody: RequestBody): Observable<BaseResponse<FileEntity>>

    @Multipart
    @POST(Urls.uploadFiles)
    fun uploadFiles(@Part parts: List<@JvmSuppressWildcards MultipartBody.Part>): Observable<BaseResponse<MutableList<FileEntity>>>


    @GET(Urls.moments)
    fun getMoments(): Observable<BaseResponse<MutableList<MomentEntity>>>

    @POST(Urls.publish)
    fun publish(@Body body: MomentBody): Observable<BaseResponse<MomentEntity>>

    @POST(Urls.publishComment)
    fun publishComment(@Body body: CommentBody): Observable<BaseResponse<CommentEntity>>

    @POST(Urls.collect)
    fun collect(@Body body: MomentBody): Observable<BaseResponse<CollectEntity>>

    @GET(Urls.friend_moment)
    fun friend_moment(@Path("user_id") id: Long): Observable<BaseResponse<FriendMomentData>>

}