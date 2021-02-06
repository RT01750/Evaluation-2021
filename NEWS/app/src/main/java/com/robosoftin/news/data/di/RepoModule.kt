package com.robosoftin.news.data.di

import com.robosoftin.news.data.repo.home.HomeRepoImpl
import com.robosoftin.news.data.repo.home.HomeRepo
import org.koin.dsl.module

val repositoryModule = module {
	factory<HomeRepo>(definition = { HomeRepoImpl(get()) })
}
