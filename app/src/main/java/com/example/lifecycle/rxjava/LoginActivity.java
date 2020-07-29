package com.example.lifecycle.rxjava;

import androidx.appcompat.app.AppCompatActivity;
import androidx.collection.ArrayMap;

import android.os.Bundle;
import android.util.Log;
import android.util.SparseIntArray;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewStub;
import android.widget.Button;
import android.widget.EditText;

import com.example.lifecycle.R;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.util.concurrent.Callable;
import java.util.concurrent.TimeUnit;

import io.reactivex.Observable;
import io.reactivex.ObservableSource;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.BiConsumer;
import io.reactivex.functions.Consumer;
import io.reactivex.functions.Function;
import io.reactivex.schedulers.Schedulers;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.GET;

public class LoginActivity extends AppCompatActivity {

    private Disposable disposable;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        EditText username = findViewById(R.id.username);
        EditText pwd = findViewById(R.id.pwd);
        ViewStub vs = findViewById(R.id.vs);
//        vs.setVisibility(View.VISIBLE);
//        findViewById(R.id.mIvBackIcon).setOnClickListener(v->{
//            vs.inflate();
//        });
        LayoutInflater.from(this).inflate(R.layout.activity_annotation_test, null);
//        ArrayMap<String, String> stringStringArrayMap = new ArrayMap<>();
//        stringStringArrayMap.put()
        SparseIntArray sparseIntArray = new SparseIntArray();
        sparseIntArray.append(1, 2);
        //被观察者
        Server server = new Server();
        //观察者
        User user1 = new User(1l, "zhangsan", "123");
        User user2 = new User(2l, "lisi", "123");
        server.addObserver(user1);
        server.addObserver(user2);
        server.update("今天天气不错");
        server.removeObserver(user2);
        server.update("hello");
        String[] arr = new String[]{"123", "456"};
        HttpLoggingInterceptor httpLoggingInterceptor = new HttpLoggingInterceptor(message -> Log.e("OKHttp-----", message));
        httpLoggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
        OkHttpClient client = new OkHttpClient.Builder()
//                .addInterceptor(httpLoggingInterceptor)
                .build();
        Retrofit retrofit = new Retrofit.Builder()
                .client(client)
                .baseUrl("http://39.107.77.70:8888")
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .build();
        ApiService apiService = retrofit.create(ApiService.class);
//        Observable.interval()
//        disposable = Observable
//                .interval(3, 2, TimeUnit.SECONDS)
//                .doOnNext(new Consumer<Long>() {
//                    @Override
//                    public void accept(Long aLong) throws Exception {
//                        Disposable subscribe = apiService.getUsers().subscribeOn(Schedulers.io())
//                                .observeOn(AndroidSchedulers.mainThread())
//                                .subscribe(new Consumer<Object>() {
//                                    @Override
//                                    public void accept(Object o) throws Exception {
////                                        System.out.println(o.toString());
//                                    }
//                                });
//                    }
//                })
//
//                .subscribe((Consumer<Object>) o -> System.out.println(o.toString()));
        disposable =  Observable.interval(3,2,TimeUnit.SECONDS)
                .flatMap(new Function<Long, ObservableSource<?>>() {
                    @Override
                    public ObservableSource<?> apply(Long aLong) throws Exception {
                        return apiService.getUsers();
                    }
                })
//                .flatMap(new Function<Object, ObservableSource<?>>() {
//                    @Override
//                    public ObservableSource<?> apply(Object o) throws Exception {
//                        return apiService.getUsers(o);
//                    }
//                })
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<Object>() {
                    @Override
                    public void accept(Object o) throws Exception {

                    }
                });
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (!disposable.isDisposed()) {
            disposable.dispose();
        }
    }

    interface ApiService {
        @GET("/allUser")
        Observable<Object> getUsers();
    }
}
