package com.serj113.domain.base

import kotlinx.coroutines.flow.Flow

abstract class FlowUseCase<T: Any> : BaseUseCase<Flow<T>>