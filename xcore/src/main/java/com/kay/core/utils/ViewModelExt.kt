package com.kay.core.utils

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProviders
import com.kay.core.viewmodel.ViewModelFactory

/**
 * Created by Kay Tran on 2/2/18.
 * Profile: https://github.com/khatv911
 * Email: khatv911@gmail.com
 */


@Suppress("UNCHECKED_CAST")
fun <T : ViewModel> ViewModelFactory.inject(fragment: androidx.fragment.app.Fragment, _class: Class<T>): T = ViewModelProviders.of(fragment, this).get(_class)

@Suppress("UNCHECKED_CAST")
fun <T : ViewModel> ViewModelFactory.inject(fragmentActivity: androidx.fragment.app.FragmentActivity, _class: Class<T>): T = ViewModelProviders.of(fragmentActivity, this).get(_class)