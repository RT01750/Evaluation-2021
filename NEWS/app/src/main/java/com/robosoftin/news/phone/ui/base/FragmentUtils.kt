package com.robosoftin.news.phone.ui.base

import android.os.Bundle
import androidx.annotation.IdRes
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager

class FragmentUtils {
	
	
	fun addFragment(
			@IdRes containerId : Int,
			fm : FragmentManager,
			fragment : Fragment,
			args : Bundle?,
			addToBackStack : Boolean,
			fragmentTag : String?
	) {
		args?.run {
			fragment.arguments = args
		}
		val ft = fm.beginTransaction().add(containerId, fragment, fragmentTag)
		if (addToBackStack) {
			ft.addToBackStack(null).commit()
		} else {
			ft.commit()
		}
	}
	
	fun replaceFragment(
			@IdRes containerId : Int,
			fm : FragmentManager,
			fragment : Fragment,
			args : Bundle? = null,
			addToBackStack : Boolean = false,
			fragmentTag : String? = fragment.javaClass.simpleName
	) {
		args?.run {
			fragment.arguments = args
		}
		
		val ft = fm.beginTransaction().replace(containerId, fragment, fragmentTag)
		if (addToBackStack) {
			ft.addToBackStack(null).commit()
		} else {
			ft.commit()
		}
	}
	
	
	fun getBackStackFragmentTag(fm : FragmentManager, fragmentName : String) =
			fm.findFragmentByTag(fragmentName)
	
	
	fun getBackStackFragmentCount(fm : FragmentManager) =
			fm.backStackEntryCount
	
}