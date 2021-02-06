/*
 * Copyright (c) 2020.
 * Written for McDonalds from Robosoft Technologies Pvt Ltd
 *
 * @Author Mithun M
 */

package com.robosoftin.news.data.dto

import com.robosoftin.news.data.constants.APIConstants
import retrofit2.HttpException


private const val NO_CONNECTION_ERROR_MESSAGE = "No connection!"
private const val BAD_RESPONSE_ERROR_MESSAGE = "Bad response!"
private const val TIME_OUT_ERROR_MESSAGE = "Time out!"
private const val EMPTY_RESPONSE_ERROR_MESSAGE = "Empty response!"
private const val NOT_DEFINED_ERROR_MESSAGE = "Not defined!"
private const val UNAUTHORIZED_ERROR_MESSAGE = "Unauthorized!"
private const val APP_UPDATE_ERROR_MESSAGE = "App Update Required!"
private const val PAYMENT_PENDING = "Pending !"
private const val PAYMENT_FAILED = "Failed!"

data class ErrorModel(
		val message : String?,
		val code : Int?,
		var errorStatus : ErrorStatus
) {
	constructor(errorStatus : ErrorStatus) : this(null, null, errorStatus)
	
	constructor(message : String?, errorStatus : ErrorStatus) : this(message, null, errorStatus)
	
	fun getErrorMessage() : String {
		return when (errorStatus) {
			ErrorStatus.NO_CONNECTION -> NO_CONNECTION_ERROR_MESSAGE
			ErrorStatus.BAD_RESPONSE -> BAD_RESPONSE_ERROR_MESSAGE
			ErrorStatus.TIMEOUT -> TIME_OUT_ERROR_MESSAGE
			ErrorStatus.EMPTY_RESPONSE -> EMPTY_RESPONSE_ERROR_MESSAGE
			ErrorStatus.NOT_DEFINED -> NOT_DEFINED_ERROR_MESSAGE
			ErrorStatus.UNAUTHORIZED -> UNAUTHORIZED_ERROR_MESSAGE
			ErrorStatus.APP_UPDATE_REQUIRED -> APP_UPDATE_ERROR_MESSAGE
			ErrorStatus.PAYMENT_PENDING -> PAYMENT_PENDING
			ErrorStatus.PAYMENT_FAILED -> PAYMENT_FAILED
		}
	}
	
	/**
	 * various error status to know what happened if something goes wrong with a repository call
	 */
	enum class ErrorStatus(val code : Int) {
		/**
		 * error in connecting to repository (Server or Database)
		 */
		NO_CONNECTION(101),
		
		/**
		 * error in getting value (Json Error, Server Error, etc)
		 */
		BAD_RESPONSE(102),
		
		/**
		 * Time out  error
		 */
		TIMEOUT(103),
		
		/**
		 * no data available in repository
		 */
		EMPTY_RESPONSE(104),
		
		/**
		 * an unexpected error
		 */
		NOT_DEFINED(105),
		
		/**
		 * bad credential
		 */
		UNAUTHORIZED(106),
		
		/**
		 * App Update Required
		 */
		APP_UPDATE_REQUIRED(107),
		
		PAYMENT_PENDING(108),
		
		PAYMENT_FAILED(109),
	}
	
}

fun Exception.toErrorModel() : ErrorModel {
	val errorModel : ErrorModel
	if (this is HttpException) {
		errorModel = when (this.code()) {
			APIConstants.ErrorCodes.ERROR_401 -> {
				ErrorModel(ErrorModel.ErrorStatus.UNAUTHORIZED)
			}
			else -> {
				ErrorModel(ErrorModel.ErrorStatus.NOT_DEFINED)
			}
			
		}
	} else {
		errorModel = ErrorModel(ErrorModel.ErrorStatus.NOT_DEFINED)
	}
	return errorModel
}

