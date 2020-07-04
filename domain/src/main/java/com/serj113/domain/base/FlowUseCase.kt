package com.serj113.domain.base

import kotlinx.coroutines.flow.Flow

abstract class FlowUseCase<A, T: Any> : BaseUseCase<A, Flow<T>>