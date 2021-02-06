/*
 * Copyright (c) 2020.
 * Written for McDonalds from Robosoft Technologies Pvt Ltd
 *
 * @Author Mithun M
 */

package com.robosoftin.news.data.repo

import com.robosoftin.news.data.dto.ErrorModel


sealed class RepositoryResult<out T : Any> {
    class Success<out T : Any>(val repositoryData : T) : RepositoryResult<T>()
	class Error(val exception : ErrorModel) : RepositoryResult<Nothing>()
}