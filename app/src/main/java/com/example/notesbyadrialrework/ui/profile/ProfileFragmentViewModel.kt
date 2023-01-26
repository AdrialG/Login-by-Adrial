package com.example.notesbyadrialrework.ui.profile

import androidx.lifecycle.viewModelScope
import com.crocodic.core.data.CoreSession
import com.example.notesbyadrialrework.api.ApiService
import com.example.notesbyadrialrework.base.BaseViewModel
import com.example.notesbyadrialrework.data.User
import com.example.notesbyadrialrework.data.UserDao
import com.google.gson.Gson
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ProfileFragmentViewModel @Inject constructor(private val apiService: ApiService, private val gson: Gson, private val userDao: UserDao, private val session: CoreSession): BaseViewModel() {

    val getUser = userDao.getUser()

    private val _user = Channel<List<User>>()
    val user = _user.receiveAsFlow()

    fun logout(logout: () -> Unit) = viewModelScope.launch {
        userDao.deleteAll()
        logout()
    }


}