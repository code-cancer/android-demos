package com.code.cancer.demos.ext

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager

/**
 *  @Author: ZhiFu.Zhang
 *  @Date: 2023-06-09
 *  @What:
 */

fun showFragment(fragmentManager: FragmentManager, clazz: Class<out Fragment>) {
    val newFragment = clazz.newInstance()
    val tag = newFragment::class.java.simpleName
    val transaction = fragmentManager.beginTransaction()
    val fragment = fragmentManager.findFragmentByTag(tag)
    if (fragment != null) {
        transaction.remove(fragment)
    }
    transaction.add(newFragment, tag)
    transaction.commit()
}