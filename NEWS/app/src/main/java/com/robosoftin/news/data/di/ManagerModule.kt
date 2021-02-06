package com.robosoftin.news.data.di

import android.app.Application
import com.robosoftin.news.data.manager.PreferenceManager
import com.robosoftin.news.data.manager.UserManager
import org.koin.android.ext.koin.androidApplication
import org.koin.dsl.module

val dataManagerModule = module {
	
	single { providePreference(androidApplication()) }
	
	single { UserManager(get()) }
}

private fun providePreference(
		androidApplication : Application
) = PreferenceManager(androidApplication)