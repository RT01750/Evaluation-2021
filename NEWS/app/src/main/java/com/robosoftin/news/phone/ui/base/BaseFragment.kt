package com.robosoftin.news.phone.ui.base

import android.os.Bundle
import android.text.TextUtils
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import com.robosoftin.news.R
import com.robosoftin.news.databinding.FragmentBaseBinding
import com.robosoftin.news.databinding.LayoutToolbarBinding
import org.koin.android.ext.android.inject

abstract class BaseFragment : Fragment() {
	protected lateinit var rootBinder : FragmentBaseBinding
	protected var toolbarBinder : LayoutToolbarBinding? = null
	
	/*Injected utilities*/
	protected val fragmentUtils : FragmentUtils by inject()
	
	
	override fun onCreate(savedInstanceState : Bundle?) {
		super.onCreate(savedInstanceState)
	}
	
	/**
	 *
	 */
	override fun onCreateView(inflater : LayoutInflater, container : ViewGroup?, savedInstanceState : Bundle?) : View? {
		rootBinder = DataBindingUtil.inflate(inflater, R.layout.fragment_base, container, false)
		onCreateToolBar()
		onCreateChildView()
		onCreateToolTip()
		return rootBinder.root
	}
	
	/**
	 * Creates common toolbar for the screen
	 */
	private fun onCreateToolBar() {
		val view = inflateToolBar(rootBinder.toolBar, LayoutInflater.from(context))
		view.let { rootBinder.toolBar.addView(it) }
	}
	
	open fun inflateToolBar(
			parent : ViewGroup,
			inflater : LayoutInflater
	) : View? {
		toolbarBinder = LayoutToolbarBinding.inflate(inflater, parent, false)
		return toolbarBinder?.root
	}
	
	private fun onCreateChildView() {
		val view = inflateView(rootBinder.baseFragmentContainer, LayoutInflater.from(context))
		view?.let { rootBinder.baseFragmentContainer.addView(it) }
	}
	
	abstract fun inflateView(parent : ViewGroup, inflater : LayoutInflater) : View?
	
	
	private fun onCreateToolTip() {
		val view = inflateToolTip(rootBinder.toolTipContainer, LayoutInflater.from(context))
		view?.let { rootBinder.toolTipContainer.addView(it) }
	}
	
	open fun inflateToolTip(parent : ViewGroup, inflater : LayoutInflater) : View? {
		return null
	}
	
	open fun showProgressBar(show : Boolean?) {
		rootBinder.progressBar.visibility = if (show == true) View.VISIBLE else View.GONE
	}
	
	protected fun showToastMessage(message : String?) {
		if (!TextUtils.isEmpty(message))
			context?.let {
				Toast.makeText(it, message, Toast.LENGTH_LONG).show()
			}
	}
	
	protected fun showToastMessage(message : String?, length : Int = Toast.LENGTH_SHORT) {
		if (!TextUtils.isEmpty(message))
			context?.let {
				Toast.makeText(it, message, length).show()
			}
	}
}