package io.github.zeleven.playa.di.module;

import android.app.Application;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import io.github.zeleven.playa.Playa;
import io.github.zeleven.playa.data.source.remote.WanAndroidService;
import io.github.zeleven.playa.utils.Constants;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

@Module
public class ApplicationModule {
    private Playa application;

    public ApplicationModule(Playa application) {
        this.application = application;
    }

    @Provides
    @Singleton
    Application provideApplication() {
        return application;
    }

    @Provides
    @Singleton
    Retrofit provideRetrofit() {
        Retrofit retrofit = new Retrofit.Builder().baseUrl(Constants.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .build();
        return retrofit;
    }

    @Provides
    @Singleton
    WanAndroidService provideWanAndroidService(Retrofit retrofit) {
        return retrofit.create(WanAndroidService.class);
    }
}
