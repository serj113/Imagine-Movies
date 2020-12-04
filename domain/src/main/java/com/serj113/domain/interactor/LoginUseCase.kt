package com.serj113.domain.interactor

import com.serj113.domain.base.FlowUseCase
import com.serj113.model.Account
import com.serj113.model.AuthToken

abstract class LoginUseCase : FlowUseCase<LoginUseCase.Args, Boolean>() {
    data class Args(
        val account: Account,
        val authToken: AuthToken
    )
}