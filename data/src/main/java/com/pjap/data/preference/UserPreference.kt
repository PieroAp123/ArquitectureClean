package com.pjap.data.preference

import com.pjap.data.preference.manager.PreferencesManager
import com.pjap.domain.entities.User
import com.pjap.domain.repository.LoginRepositoryPreference

class UserPreference(private val sharedPreferenceManager: PreferencesManager) :
    LoginRepositoryPreference {

    override fun saveUser(user: User) {
        sharedPreferenceManager.setValue("USERNAME", user.user!!)
    }


    override fun saveIsLogin(flag: Boolean) {
        sharedPreferenceManager.setValue("ISLOGIN", flag)
    }



    override fun getUser(): User {
        val userName = sharedPreferenceManager.getString("USERNAME")
        return User(userName)
    }

    override fun isLogin(): Boolean {
        return sharedPreferenceManager.getBoolean("ISLOGIN")
    }



}