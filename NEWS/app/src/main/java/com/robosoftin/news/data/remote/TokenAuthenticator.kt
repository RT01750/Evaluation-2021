///*
// * Copyright (c) 2020.
// * Written for McDonalds from Robosoft Technologies Pvt Ltd
// *
// * @Author Mithun M
// */
//
//package com.robosoftin.news.data.remote
//
//import com.robosoftin.news.data.manager.PreferenceManager
//import com.robosoftin.news.data.manager.UserManager
//import okhttp3.Authenticator
//import okhttp3.Request
//import okhttp3.Response
//import okhttp3.Route
//import java.io.IOException
//
//class TokenAuthenticator(
//		private val sPref : PreferenceManager,
//		private val userManager : UserManager
//) : Authenticator {
//	var retryCount : Int = 0
//	val max_retry_count = 5
//
//	/******************************************************************************************
//	 * Authenticator for when the authToken need to be refresh and updated
//	 * everytime we get a 401 error code
//	 *****************************************************************************************/
//	@Throws(IOException::class)
//	override fun authenticate(route : Route?, response : Response) : Request? {
//		return if (retryCount < max_retry_count) {
////			retryCount++
//			val updatedToken = getUpdatedToken(retrofit)
//			return if (updatedToken.isEmpty()) {
//				null
//			} else {
//				response.request.newBuilder()
//						.header(APIConstants.VALIDATE_REQ, updatedToken)
//						.build()
//			}
//		} else {
//			null
//		}
//	}
//
//	/*****************************************************************************************
//	 * Methord to generate new token when token is expired
//	 * @return a new token from the server
//	 *
//	 ******************************************************************************************/
//	private fun getUpdatedToken(clientInstance : RetrofitClientInstance) : String {
//		var newToken = ""
//		val service : UpdateTokenAPI = clientInstance.creat******************************************************************************************/
//}
