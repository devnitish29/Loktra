package learning.nitish.github.dependencies;

import java.io.IOException;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import okhttp3.Credentials;
import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by Nitish Singh Rathore on 5/8/17.
 */

@Module
public class RetrofitModule {

    private String mBaseUrl;


    public RetrofitModule(String mBaseUrl) {
        this.mBaseUrl = mBaseUrl;
    }





    @Provides
    @Singleton
    Retrofit providesRetrofit(){

        final String credentials= Credentials.basic("devnitish29","c4335b84461f259f97d91ca156aca7ea37ba13d3");
        Interceptor basicAuthInterceptor = new Interceptor() {
            @Override
            public Response intercept(Chain chain) throws IOException {
                Request request = chain.request();
                Request authenticatedRequest = request.newBuilder()
                        .header("Authorization", credentials).build();
                return chain.proceed(authenticatedRequest);
            }
        };


        HttpLoggingInterceptor logging = new HttpLoggingInterceptor();
        logging.setLevel(HttpLoggingInterceptor.Level.BODY);
        return new Retrofit.Builder()
                .baseUrl(mBaseUrl)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .client(new OkHttpClient.Builder().addInterceptor(logging).addInterceptor(basicAuthInterceptor).build())
                .build();


    }




}

