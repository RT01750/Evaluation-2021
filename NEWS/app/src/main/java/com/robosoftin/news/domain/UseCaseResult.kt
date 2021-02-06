/*
 * Copyright (c) 2020.
 * Written for McDonalds from Robosoft Technologies Pvt Ltd
 *
 * @Author Mithun M
 */

package com.robosoftin.news.domain

sealed class UseCaseResult<out T : Any> {
	open class Success<out T : Any>(val data : T) : UseCaseResult<T>()
	class Error(val exception : Any) : UseCaseResult<Nothing>()
}