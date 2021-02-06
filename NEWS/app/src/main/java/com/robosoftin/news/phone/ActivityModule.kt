/*
 * Copyright (c) 2020.
 * Written for McDonalds from Robosoft Technologies Pvt Ltd
 *
 * @Author Mithun M
 */

package com.robosoftin.news.phone

import com.il.mcdelivery.phone.utils.FragmentUtils
import com.robosoftin.news.phone.presentation.HomeViewModel
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

