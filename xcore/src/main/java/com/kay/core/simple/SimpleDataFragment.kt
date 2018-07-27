package com.kay.core.simple

import android.os.Bundle
import com.kay.core.ui.AbsBaseFragment
import com.kay.core.utils.observeK
import com.kay.core.viewmodel.ViewModelFactory
import javax.inject.Inject

/**
 * Created by Kay Tran on 14/3/18.
 * Profile: https://github.com/khatv911
 * Email: khatv911@gmail.com
 */
abstract class SimpleDataFragment<T, VM : SimpleDataModel<T>> : AbsBaseFragment() {


    /**
     * The ViewModelFactory
     */
    @Inject
    lateinit var VIEW_MODEL_FACTORY: ViewModelFactory

    protected lateinit var mViewModel: VM

    abstract fun getViewModel(): VM

    abstract fun onDataChanged(t: T?)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mViewModel = getViewModel()
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        mViewModel.setup(this)
        mViewModel.mLiveData.observeK(viewLifecycleOwner) {
            onDataChanged(it)
        }
    }


}