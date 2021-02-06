package com.robosoftin.news.data.di

import android.app.Application
import com.google.gson.FieldNamingPolicy
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import com.jakewharton.retrofit2.adapter.kotlin.coroutines.CoroutineCallAdapterFactory
import com.robosoftin.news.BuildConfig
import com.robosoftin.news.data.manager.PreferenceManager
import com.robosoftin.news.data.manager.UserManager
import com.robosoftin.news.data.remote.AuthInterceptor
import okhttp3.Cache
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.koin.android.ext.koin.androidApplication
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory


private fun getCacheSize() = 10 * 1024 * 1024

val netModule = module {
	
	fun provideCache(application : Application) : Cache {
		val cacheSize = getCacheSize()
		return Cache(application.cacheDir, cacheSize.toLong())
	}
	
	fun provideGson() : Gson {
		return GsonBuilder().setFieldNamingPolicy(FieldNamingPolicy.IDENTITY).create()
	}
	
	fun provideRetrofit(factory : Gson, client : OkHttpClient) : Retrofit {
		return Retrofit.Builder()
				.baseUrl(BuildConfig.BASE_URL)
				.addConverterFactory(GsonConverterFactory.create(factory))
				.addCallAdapterFactory(CoroutineCallAdapterFactory())
				.client(client)
				.build()
	}
	
	fun provideOkHttpClient(
			authInterceptor : AuthInterceptor,
			loggingInterceptor : HttpLoggingInterceptor,
			cache : Cache
	) : OkHttpClient {
		return OkHttpClient().newBuilder()
				.cache(cache)
				.connectTimeout(10, java.util.concurrent.TimeUnit.SECONDS)
				.writeTimeout(10, java.util.concurrent.TimeUnit.SECONDS)
				.readTimeout(61, java.util.concurrent.TimeUnit.SECONDS)
//				.authenticator(tokenAuthenticator)
				.addInterceptor(authInterceptor)
				.addInterceptor(loggingInterceptor)
				.build()
	}
	
	fun provideLoggingInterceptor() : HttpLoggingInterceptor {
		val logger = HttpLoggingInterceptor()
		if (BuildConfig.DEBUG)
			logger.level = HttpLoggingInterceptor.Level.BODY
		return logger
	}
	
	fun provideAuth(
			sPref : PreferenceManager,
			userManager : UserManager
	) : AuthInterceptor {
		return AuthInterceptor(sPref, userManager)
	}
	
	single { provideOkHttpClient(get(), get(), get()) }
	single { provideCache(androidApplication()) }
	single { provideGson() }
	single { provideAuth(get(), get()) }
	factory { provideLoggingInterceptor() }
	single { provideRetrofit(get(), get()) }
}