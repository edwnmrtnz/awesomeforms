package com.github.edwnmrtnz.awesomeforms

import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager

/**
 * Created by edwinmartinez on June 20, 2019
 */

fun AppCompatActivity.replace (containerId : Int, fragmentInstance : Fragment) {
    supportFragmentManager
            .beginTransaction()
            .replace(containerId, fragmentInstance)
            .commit()
}

fun AppCompatActivity.addToBackStack(containerId: Int, fragmentInstance: Fragment, name : String? = null) {
    supportFragmentManager
        .beginTransaction()
        .replace(containerId, fragmentInstance)
        .addToBackStack(name)
        .commit()
}

fun AppCompatActivity.popToBackStack(name : String) {
    supportFragmentManager
        .popBackStack(name, 0)
}

fun AppCompatActivity.popToRoot() {
    supportFragmentManager
        .popBackStackImmediate(supportFragmentManager.getBackStackEntryAt(0).id, FragmentManager.POP_BACK_STACK_INCLUSIVE)

}


