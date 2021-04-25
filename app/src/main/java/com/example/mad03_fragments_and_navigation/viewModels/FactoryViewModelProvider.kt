package com.example.mad03_fragments_and_navigation.viewModels

// creational design pattern: factory method

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider

// Source: https://developer.android.com/codelabs/kotlin-android-training-view-model?index=..%2F..android-kotlin-fundamentals#7

class FactoryViewModelProvider(private val finalScore: Int) : ViewModelProvider.Factory {

    override fun <T : ViewModel?> create(modelClass: Class<T>): T {

        if (modelClass.isAssignableFrom(EndFragmentViewMod::class.java)) {

            return EndFragmentViewMod(finalScore) as T
        }
        throw IllegalArgumentException("class not found")
    }
}