package com.serj113.base.domain

import kotlinx.coroutines.flow.Flow

abstract class FlowUseCaseWithArgs<A, T: Any> : BaseUseCaseWithArgs<A, Flow<T>>
