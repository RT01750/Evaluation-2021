package com.robosoftin.news.application

import android.app.Application
import com.robosoftin.news.data.di.APIModule
import com.robosoftin.news.data.di.dataManagerModule
import com.robosoftin.news.data.di.netModule
import com.robosoftin.news.data.di.repositoryModule
import com.robosoftin.news.domain.di.useCaseModule
import com.robosoftin.news.phone.utility
import com.robosoftin.news.phone.viewModelModule
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin
import org.koin.core.logger.Level

class NewsApp : Application() {
	
	override fun onCreate() {
		super.onCreate()
		initialize()
	}
	
	private fun initialize() {
		initKoin()
	}
	
	/**
	 * initialize dependency injection here
	 */
	private fun initKoin() {
		startKoin {
			androidLogger(Level.DEBUG)
			androidContext(this@NewsApp)
			modules(
					repositoryModule
					+ viewModelModule
					+ utility
					+ useCaseModule
					+ APIModule
					+ netModule
					+ dataManagerModule
			)
		}
	}
	
}