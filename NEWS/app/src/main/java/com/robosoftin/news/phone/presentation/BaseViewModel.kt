package com.robosoftin.news.phone.presentation

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers.Main
import kotlinx.coroutines.Job
import kotlin.coroutines.CoroutineContext

// To avoid writing the same extensions multiple times, we'll make an abstract class for ViewModels
abstract class BaseViewModel(
		application : Application
) : AndroidViewModel(application), CoroutineScope {
	
	// Coroutine's background job
	private val job = Job()
	
	// Define default thread for Coroutine as Main and add job
	override val coroutineContext : CoroutineContext = job + Main
	
	
	override fun onCleared() {
		super.onCleared()
		// Clear job when the linked activity is destroyed to avoid memory leaks
		cancelContext()
	}
	
	fun cancelContext() {
		job.cancel()
	}
}