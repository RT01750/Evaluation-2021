package com.robosoftin.news.data.manager

import android.app.Application
import android.content.SharedPreferences

class PreferenceManager(private val application : Application) {
	
	// constanst here
	
	private val preferences : SharedPreferences
		get() = SecureSharedPreference(application)
	
	private val editor : SharedPreferences.Editor
		get() = preferences.edit()


//	/** User Related Methords **/
//
//	fun setCurrentUser(user : User?) {
//		editor.putString(CURRENT_USER, Gson().toJson(user, User::class.java))
//		editor.commit()
//	}
//
//	fun getCurrentUser() : User? {
//		return Gson().fromJson(preferences.getString(CURRENT_USER, ""), User::class.java)
}
	
