package com.serj113.imaginemovies.base.domain

import kotlinx.coroutines.flow.Flow

abstract class FlowUseCase<T: Any> : BaseUseCase<Flow<T>>
