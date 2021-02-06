package com.robosoftin.news.data.remote

import com.robosoftin.news.data.manager.PreferenceManager
import com.robosoftin.news.data.manager.UserManager
import okhttp3.Interceptor
import okhttp3.Response

class AuthInterceptor(
		private val sPref : PreferenceManager,
		private val userManager : UserManager
) : Interceptor {
	
	/**
	 * Interceptor class for setting of the headers for every request
	 */
	override fun intercept(chain : Interceptor.Chain) : Response {
		var req = chain.request()
		req = req.newBuilder()
//				.addHeader(APIConstants.CUSTOMER_ID, sPref.getCustomerId().toString())
				
				.build()
		return chain.proceed(request = req)
	}
}