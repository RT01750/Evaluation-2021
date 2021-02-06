/*
 * Copyright (c) 2020.
 * Written for McDonalds from Robosoft Technologies Pvt Ltd
 *
 * @Author Mithun M
 */

package com.robosoftin.news.data.di

import com.il.data.repository.promotion.HomeRepoImpl
import com.robosoftin.news.data.repo.home.HomeRepo
import org.koin.dsl.module

val repositoryModule = module {
	factory<HomeRepo>(definition = { HomeRepoImpl(get()) })
}
