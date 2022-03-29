package com.pjap.domain.repository

import com.pjap.domain.entities.User

interface LoginRepositoryPreference {
    fun saveUser(user: User)
    fun saveIsLogin(flag: Boolean)
    fun getUser(): User
    fun isLogin(): Boolean
}