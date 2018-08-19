package com.kay.core.utils

import android.support.annotation.AnimRes
import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager
import android.support.v4.app.FragmentTransaction
import android.support.v7.app.AppCompatActivity


/**
 * Created by Kay Tran on 2/2/18.
 * Profile: https://github.com/khatv911
 * Email: khatv911@gmail.com
 */
inline fun FragmentManager.inTransaction(func: FragmentTransaction.() -> FragmentTransaction) {
    beginTransaction()
            .func()
            .commit()
}

fun FragmentTransaction.addBackStack(tag: String,
                                                           shouldAddToBackStack: Boolean = true
                                                           ): FragmentTransaction =
        addToBackStack(if (shouldAddToBackStack) tag else null)


fun FragmentTransaction.animation(
        @AnimRes enterAnimation: Int = android.R.anim.fade_in,
        @AnimRes exitAnimation: Int = android.R.anim.fade_out,
        @AnimRes popEnterAnimation: Int = android.R.anim.fade_in,
        @AnimRes popExitAnimation: Int = android.R.anim.fade_out): FragmentTransaction =
        setCustomAnimations(enterAnimation, exitAnimation, popEnterAnimation, popExitAnimation)

fun AppCompatActivity.addFragment(fragment: Fragment, frameId: Int, tag: String, shouldAddToBackStack: Boolean = true) {
    supportFragmentManager.inTransaction {
        animation().add(frameId, fragment).addBackStack(tag, shouldAddToBackStack)
    }
}


fun AppCompatActivity.replaceFragment(fragment:Fragment, frameId: Int, tag: String, shouldAddToBackStack: Boolean = true) {
    supportFragmentManager.inTransaction {
        animation().replace(frameId, fragment).addBackStack(tag, shouldAddToBackStack)
    }
}

fun AppCompatActivity.replaceToRoot(fragment: Fragment, frameId: Int, tag: String = "ROOT_FRAGMENT") {
    supportFragmentManager.popBackStackImmediate(tag, FragmentManager.POP_BACK_STACK_INCLUSIVE)
    supportFragmentManager.inTransaction {
        animation().replace(frameId, fragment).addBackStack(tag)
    }
}

fun AppCompatActivity.popFragment() {
    supportFragmentManager.popBackStack()
}


fun Fragment.addFragment(fragment: Fragment, frameId: Int, tag: String, shouldAddToBackStack: Boolean = true) {
    fragmentManager?.inTransaction {
        animation().add(frameId, fragment).addBackStack(tag, shouldAddToBackStack)
    }
}

fun Fragment.replaceFragment(fragment: Fragment, frameId: Int, tag: String, shouldAddToBackStack: Boolean = true) {
    fragmentManager?.inTransaction {
        animation().replace(frameId, fragment).addBackStack(tag, shouldAddToBackStack)
    }
}



