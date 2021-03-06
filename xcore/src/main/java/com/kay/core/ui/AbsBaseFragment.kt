package com.kay.core.ui

import android.os.Bundle
import android.support.v4.widget.ContentLoadingProgressBar
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.FrameLayout
import com.kay.core.R
import com.kay.core.utils.LoadingState
import com.kay.core.utils.LoadingState.Companion.NORMAL
import dagger.android.support.DaggerFragment
import kay.arch.errorhandler.DefaultResolution
import kay.arch.errorhandler.Resolution
import kay.arch.errorhandler.resolver.TimberResolver
import kay.arch.errorhandler.resolver.ToastyResolver

/**
 * Created by Kay Tran on 2/2/18.
 * Profile: https://github.com/khatv911
 * Email: khatv911@gmail.com
 */
abstract class AbsBaseFragment : DaggerFragment() {


    /**
     * provide a resolution lazily.
     * fallback to default
     */
    val uiResolution by lazy {
        getResolution()
    }


    /**
     * Override this method to provide proper resolution
     */
    open fun getResolution(): Resolution = DefaultResolution(mutableListOf()).apply {
        addResolver(TimberResolver())
        addResolver(ToastyResolver(this@AbsBaseFragment))
    }


    /**
     * The common loading view. it should respond to the ViewModel#mLoadingEvent
     * If you wish to have the ability to swipe&refresh,
     */
    protected lateinit var mLoadingView: ContentLoadingProgressBar

    /**
     * The content view, inflated from a single layout file
     */
    protected lateinit var mContentView: View




    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {

        val view = inflater.inflate(R.layout.fragment_base, container, false) as FrameLayout
        val contentFragmentView = inflater.inflate(getLayoutId(), view, false)
        contentFragmentView.id = R.id.base_content
        view.addView(contentFragmentView, FrameLayout.LayoutParams(-1, -1)) // short for match_parent
        return view

    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        mLoadingView = view.findViewById(R.id.progress_bar)
        mContentView = view.findViewById(R.id.base_content)
    }


    open fun onLoadingStateChanged(@LoadingState.Value loadingState: Int) {
        when (loadingState) {
        // have to use postDelayed to show the loading,
        // this will cause temporary leak.
            NORMAL -> {
                showLoadingView()
            }
            else -> {
                hideLoadingView()
            }
        }
    }

    protected fun showLoadingView() {
        mLoadingView.show()
    }

    protected fun hideLoadingView() {
        mLoadingView.hide()
    }

    open fun onError(throwable: Throwable?) {
        throwable?.let {
            uiResolution.resolve(it)
        }
    }

    open fun onSuccess(message: String?) {
        uiResolution.success(message)
    }




    abstract fun getLayoutId(): Int




}