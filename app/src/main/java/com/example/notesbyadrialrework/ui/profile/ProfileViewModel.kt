package com.example.notesbyadrialrework.ui.profile

import androidx.lifecycle.viewModelScope
import com.crocodic.core.api.ApiObserver
import com.crocodic.core.api.ApiResponse
import com.crocodic.core.data.CoreSession
import com.example.notesbyadrialrework.api.ApiService
import com.example.notesbyadrialrework.base.BaseViewModel
import com.example.notesbyadrialrework.data.Const
import com.example.notesbyadrialrework.data.UserDao
import com.google.gson.Gson
import kotlinx.coroutines.launch
import org.json.JSONObject
import javax.inject.Inject

class ProfileViewModel @Inject constructor(private val apiService: ApiService, private val gson: Gson, private val userDao: UserDao, private val session: CoreSession): BaseViewModel() {

    /*fun logout(logout: () -> Unit) = viewModelScope.launch {
        userDao.deleteAll()
        logout()
    }*/

    /* fun getProfile() {
         viewModelScope.launch {
             ApiObserver(
                 block = {apiService.getProfile(name = String(), email = String()) },
                 toast = false,
                 responseListener = object : ApiObserver.ResponseListener {
                     override suspend fun onSuccess(response: JSONObject) {

                         val name = response.getString("name")
                         session.setValue(Const.PROFILE.NAME,name)

                         val email = response.getString("email")
                         session.setValue(Const.PROFILE.EMAIL,email)

                         *//*val photo = response.getString("photo")
                        session.setValue(Const.PROFILE.PHOTO,photo)*//*

                    }

                    override suspend fun onError(response: ApiResponse) {
                        super.onError(response)
                    }
                }
            )
        }
    }*/
}