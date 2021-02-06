/*
 * Copyright (c) 2020.
 * Written for McDonalds from Robosoft Technologies Pvt Ltd
 *
 * @Author Mithun M
 */

package com.robosoftin.news.domain.di

import com.robosoftin.news.domain.home.HomeUseCase
import com.robosoftin.news.domain.home.HomeUseCaseImpl
import org.koin.dsl.module

val useCaseModule = module {
		factory<HomeUseCase>(
			definition = { HomeUseCaseImpl(get()) }
	)
}
