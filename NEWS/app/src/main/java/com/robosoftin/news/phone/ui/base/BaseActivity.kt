package com.robosoftin.news.phone.ui.base

import android.os.Bundle
import android.text.TextUtils
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.annotation.IdRes
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.DialogFragment
import androidx.fragment.app.Fragment
import com.robosoftin.news.R
import com.robosoftin.news.databinding.ActivityBaseBinding
import org.koin.android.ext.android.inject

abstract class BaseActivity : AppCompatActivity() {
	
	//Inject com.mcd.payment.utils
	val fragmentUtils : FragmentUtils by inject()
	
	protected lateinit var mParentBinder : ActivityBaseBinding
	
	override fun onCreate(savedInstanceState : Bundle?) {
		super.onCreate(savedInstanceState)
		mParentBinder = DataBindingUtil.setContentView(this, R.layout.activity_base)
		setContentView()
	}
	
	private fun setContentView() {
		val view = inflateView(mParentBinder.baseContainer, LayoutInflater.from(this))
		view?.let { mParentBinder.baseContainer.addView(it) }
	}
	
	abstract fun inflateView(parent : ViewGroup, inflater : LayoutInflater) : View?
	
	/**
	 * Used for loading fragments..
	 * Override this method if container is changed in subclasses
	 */
	@IdRes
	open fun containerId() : Int {
		return mParentBinder.baseContainer.id
	}
	
	public fun addFragment(
			@IdRes containerId : Int,
			fragment : Fragment,
			args : Bundle? = null,
			addToBackStack : Boolean = true,
			fragmentTag : String? = fragment.javaClass.simpleName
	) {
		if (isFinishing) return
		fragmentUtils.addFragment(
				containerId,
				supportFragmentManager,
				fragment,
				args,
				addToBackStack,
				fragmentTag
		)
	}
	
	fun replaceFragment(
			@IdRes containerId : Int,
			fragment : Fragment,
			args : Bundle? = null,
			addToBackStack : Boolean = false,
			fragmentTag : String? = fragment.javaClass.simpleName
	) {
		if (isFinishing) return
		fragmentUtils.replaceFragment(
				containerId,
				supportFragmentManager,
				fragment,
				args,
				addToBackStack,
				fragmentTag
		)
	}
	
	protected fun getBackstackFragment(fragmentName : String) : Fragment? {
		return fragmentUtils.getBackStackFragmentTag(supportFragmentManager, fragmentName)
	}
	
	protected fun getBackStackFragmentCount() : Int {
		if (isFinishing) return -1
		return fragmentUtils.getBackStackFragmentCount(supportFragmentManager)
	}
	
	open fun showProgressBar(show : Boolean?) {
		when (show) {
			true -> {
				mParentBinder.progressBar.visibility = View.VISIBLE
				mParentBinder.baseContainer.alpha = .5f
			}
			else -> {
				mParentBinder.baseContainer.alpha = 1f
				mParentBinder.progressBar.visibility = View.GONE
			}
		}
	}
	
	/**
	 * Can override this message to show customized error message
	 */
	protected open fun showToastMessage(message : String?) {
		if (!TextUtils.isEmpty(message))
			Toast.makeText(this, message, Toast.LENGTH_LONG).show()
	}
	
	protected open fun showToastMessage(message : Int?) {
		message?.let { Toast.makeText(this, it, Toast.LENGTH_SHORT).show() }
	}
	
	fun showDialog(dialogFragment : DialogFragment) {
		dialogFragment.show(supportFragmentManager, dialogFragment.tag)
	}
}