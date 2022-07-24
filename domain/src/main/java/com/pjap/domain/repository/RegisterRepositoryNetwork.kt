package com.pjap.domain.repository

import com.pjap.domain.entities.CustomResult
import com.pjap.domain.entities.RegisterDetail

interface RegisterRepositoryNetwork {
    fun doRegister(
        email: String,
        name: String,
        lastname: String,
        phone: String,
        image: String,
        password: String,
        created_at: String = "",
        updated_at: String = ""
    ): CustomResult<RegisterDetail>
}