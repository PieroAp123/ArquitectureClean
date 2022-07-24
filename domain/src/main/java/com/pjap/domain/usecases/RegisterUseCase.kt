package com.pjap.domain.usecases

import com.pjap.domain.entities.CustomResult
import com.pjap.domain.entities.RegisterDetail
import com.pjap.domain.repository.RegisterRepositoryNetwork

open class RegisterUseCase(
    private val registerRepositoryNetwork: RegisterRepositoryNetwork
) {
    open fun doRegister(email: String, name: String, lastname: String, phone: String, image: String, password: String, created_at: String, updated_at: String): CustomResult<RegisterDetail> {
        return registerRepositoryNetwork.doRegister(
            email,
            name,
            lastname,
            phone,
            image,
            password,
            created_at,
            updated_at
        )
    }
}