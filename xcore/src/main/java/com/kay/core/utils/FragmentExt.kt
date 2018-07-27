package com.kay.core.utils

import androidx.annotation.AnimRes
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.FragmentManager


/**
 * Created by Kay Tran on 2/2/18.
 * Profile: https://github.com/khatv911
 * Email: khatv911@gmail.com
 */
inline fun androidx.fragment.app.FragmentManager.inTransaction(func: androidx.fragment.app.FragmentTransaction.() -> androidx.fragment.app.FragmentTransaction) {
    beginTransaction()
            .func()
            .commit()
}

fun androidx.fragment.app.FragmentTransaction.addBackStack(tag: String,
                                                           shouldAddToBackStack: Boolean = true
                                                           ): androidx.fragment.app.FragmentTransaction =
        addToBackStack(if (shouldAddToBackStack) tag else null)


fun androidx.fragment.app.FragmentTransaction.animation(
        @AnimRes enterAnimation: Int = android.R.anim.fade_in,
        @AnimRes exitAnimation: Int = android.R.anim.fade_out,
        @AnimRes popEnterAnimation: Int = android.R.anim.fade_in,
        @AnimRes popExitAnimation: Int = android.R.anim.fade_out): androidx.fragment.app.FragmentTransaction =
        setCustomAnimations(enterAnimation, exitAnimation, popEnterAnimation, popExitAnimation)

fun AppCompatActivity.addFragment(fragment: androidx.fragment.app.Fragment, frameId: Int, tag: String, shouldAddToBackStack: Boolean = true) {
    supportFragmentManager.inTransaction {
        animation().add(frameId, fragment).addBackStack(tag, shouldAddToBackStack)
    }
}


fun AppCompatActivity.replaceFragment(fragment: androidx.fragment.app.Fragment, frameId: Int, tag: String, shouldAddToBackStack: Boolean = true) {
    supportFragmentManager.inTransaction {
        animation().replace(frameId, fragment).addBackStack(tag, shouldAddToBackStack)
    }
}

fun AppCompatActivity.replaceToRoot(fragment: androidx.fragment.app.Fragment, frameId: Int, tag: String = "ROOT_FRAGMENT") {
    supportFragmentManager.popBackStackImmediate(tag, FragmentManager.POP_BACK_STACK_INCLUSIVE)
    supportFragmentManager.inTransaction {
        animation().replace(frameId, fragment).addBackStack(tag)
    }
}

fun AppCompatActivity.popFragment() {
    supportFragmentManager.popBackStack()
}


fun androidx.fragment.app.Fragment.addFragment(fragment: androidx.fragment.app.Fragment, frameId: Int, tag: String, shouldAddToBackStack: Boolean = true) {
    fragmentManager?.inTransaction {
        animation().add(frameId, fragment).addBackStack(tag, shouldAddToBackStack)
    }
}

fun androidx.fragment.app.Fragment.replaceFragment(fragment: androidx.fragment.app.Fragment, frameId: Int, tag: String, shouldAddToBackStack: Boolean = true) {
    fragmentManager?.inTransaction {
        animation().replace(frameId, fragment).addBackStack(tag, shouldAddToBackStack)
    }
}



