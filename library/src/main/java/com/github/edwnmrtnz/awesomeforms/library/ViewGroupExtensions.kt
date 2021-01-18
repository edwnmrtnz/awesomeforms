package com.github.edwnmrtnz.awesomeforms.library

import android.os.Parcelable
import android.util.SparseArray
import android.view.ViewGroup
import androidx.core.view.children

/**
 * Created by edwinmartinez on November 26, 2020
 */

fun ViewGroup.saveChildViewStates(): SparseArray<Parcelable> {
    val childViewStates = SparseArray<Parcelable>()
    children.forEach { child -> child.saveHierarchyState(childViewStates) }
    return childViewStates
}

fun ViewGroup.restoreChildViewStates(childViewStates: SparseArray<Parcelable>) {
    children.forEach { child -> child.restoreHierarchyState(childViewStates) }
}