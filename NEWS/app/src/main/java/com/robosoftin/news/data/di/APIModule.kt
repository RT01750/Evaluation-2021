package com.robosoftin.news.data.di

import com.robosoftin.news.data.api.HomeAPI
import org.koin.dsl.module
import retrofit2.Retrofit


val APIModule = module {
	
	single { provideAPI<HomeAPI>(get()) }
	
}

inline fun <reified T> provideAPI(retrofit : Retrofit) : T {
	return retrofit.create(T::class.java)
}



