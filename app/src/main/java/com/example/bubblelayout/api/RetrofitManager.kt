package com.example.bubblelayout.api

import android.util.Log
import com.blankj.utilcode.util.SPUtils
import com.example.bubblelayout.utils.UserInfoUtil
import com.jakewharton.retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import okhttp3.*
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.io.File
import java.io.UnsupportedEncodingException
import java.net.URLDecoder
import java.util.concurrent.TimeUnit

class RetrofitManager {

    private val tokenHeader = Interceptor {
        val request = it.request().newBuilder()
            .addHeader("Authorization", UserInfoUtil.getAccessToken())
            .addHeader("client-type", "app")
            .build()

        return@Interceptor it.proceed(request)
    }

    companion object {
        private var instance: RetrofitManager? = null

        fun getInstance(): RetrofitManager {
            if (instance == null) {
                instance = RetrofitManager()
                instance!!.init()
            }
            return instance!!
        }
    }

    private lateinit var mOkHttpClient: OkHttpClient

    fun init() {
        val loggingInterceptor = HttpLoggingInterceptor(HttpLoggingInterceptor.Logger { message ->
            try {
                Log.e("OKHttp-----", message+"")
            } catch (e: UnsupportedEncodingException) {
                e.printStackTrace()
                Log.e("OKHttp-----", message)
            }
        })
        loggingInterceptor.level = HttpLoggingInterceptor.Level.BODY

//        val cacheFile = File(context.cacheDir, "cache")
//        val cache = Cache(cacheFile, (1024 * 1024 * 100).toLong()) //100Mb
//        val cookieJar =
//            PersistentCookieJar(SetCookieCache(), SharedPrefsCookiePersistor(context))

        val builder = OkHttpClient.Builder()
            .readTimeout(20, TimeUnit.SECONDS)
            .connectTimeout(10, TimeUnit.SECONDS)
            .addInterceptor(tokenHeader)
//            .cookieJar(JavaNetCookieJar())
//            .cookieJar(cookieJar)
//            .addInterceptor(cookie)
//            .addInterceptor(offlineCacheInterceptor)
//            .addInterceptor(netCacheInterceptor)
            .addInterceptor(loggingInterceptor)
//            .sslSocketFactory(TrustAllCerts.createSSLSocketFactory())
//            .hostnameVerifier(TrustAllCerts.TrustAllHostnameVerifier())
//            .cache(cache)
//        builder.interceptors().add(cookie)
        mOkHttpClient = builder.build()
    }

    fun <T> createService(clazz: Class<T>, baseUrl: String = Urls.BASE_URL): T {
        return Retrofit.Builder()
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .addConverterFactory(GsonConverterFactory.create())
            .client(mOkHttpClient)
            .baseUrl(baseUrl)
            .build()
            .create(clazz)
    }
}