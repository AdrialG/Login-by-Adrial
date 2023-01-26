package com.example.notesbyadrialrework.ui.addnote

import com.example.notesbyadrialrework.api.ApiService
import com.example.notesbyadrialrework.base.BaseViewModel
import com.example.notesbyadrialrework.data.UserDao
import com.google.gson.Gson
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class AddNoteViewModel @Inject constructor(private val apiService: ApiService, private val gson: Gson, private val  userDao: UserDao) : BaseViewModel() {

}
