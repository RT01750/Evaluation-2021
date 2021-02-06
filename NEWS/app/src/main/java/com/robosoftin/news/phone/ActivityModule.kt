package com.robosoftin.news.phone

import com.robosoftin.news.phone.presentation.HomeViewModel
import com.robosoftin.news.phone.ui.base.FragmentUtils
import org.koin.android.ext.koin.androidApplication
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val viewModelModule = module {
	viewModel { HomeViewModel(androidApplication(), get()) }
}
val utility = module {
	single { provideFragmentUtils() }
}

fun provideFragmentUtils() = FragmentUtils()

