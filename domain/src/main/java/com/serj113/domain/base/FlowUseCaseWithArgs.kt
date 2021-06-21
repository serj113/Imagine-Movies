package com.serj113.domain.base

import kotlinx.coroutines.flow.Flow

abstract class FlowUseCaseWithArgs<A, T: Any> : BaseUseCaseWithArgs<A, Flow<T>>
