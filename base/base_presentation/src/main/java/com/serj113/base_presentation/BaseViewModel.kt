package com.serj113.base_presentation

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.serj113.base_presentation.util.Event

abstract class BaseViewModel<E>: ViewModel() {
    protected val _viewState = MutableLiveData<Event<E>>()
    val viewState: LiveData<Event<E>> = _viewState
}