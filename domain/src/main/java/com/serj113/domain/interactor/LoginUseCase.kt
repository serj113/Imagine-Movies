package com.serj113.domain.interactor

import com.serj113.domain.base.FlowUseCaseWithArgs
import com.serj113.model.Account
import com.serj113.model.AuthToken

abstract class LoginUseCase : FlowUseCaseWithArgs<LoginUseCase.Args, Boolean>() {
    data class Args(
        val account: Account,
        val authToken: AuthToken
    )
}
