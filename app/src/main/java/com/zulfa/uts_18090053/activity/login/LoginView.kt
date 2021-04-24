package com.zulfa.uts_18090053.activity.login

import com.zulfa.uts_18090053.model.User

interface LoginView {
    fun onSuccessLogin(user: User?)
    fun onErrorLogin(msg: String?)
}