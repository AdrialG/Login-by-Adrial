package com.example.notesbyadrialrework.ui.home

import com.example.notesbyadrialrework.api.ApiService
import com.example.notesbyadrialrework.base.BaseViewModel
import com.example.notesbyadrialrework.data.UserDao
import com.google.gson.Gson
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(private val apiService: ApiService, private val gson: Gson, val userDao: UserDao) : BaseViewModel() {
    val user = userDao.getUser()
}