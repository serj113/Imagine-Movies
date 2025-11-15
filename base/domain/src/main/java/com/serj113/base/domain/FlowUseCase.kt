package com.serj113.base.domain

import kotlinx.coroutines.flow.Flow

abstract class FlowUseCase<T: Any> : BaseUseCase<Flow<T>>
