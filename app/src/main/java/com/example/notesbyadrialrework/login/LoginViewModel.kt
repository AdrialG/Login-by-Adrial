package com.example.notesbyadrialrework.login

import androidx.lifecycle.viewModelScope
import com.crocodic.core.api.ApiCode
import com.crocodic.core.api.ApiObserver
import com.crocodic.core.api.ApiResponse
import com.crocodic.core.api.ApiStatus
import com.crocodic.core.extension.toObject
import com.example.notesbyadrialrework.api.ApiService
import com.example.notesbyadrialrework.base.BaseViewModel
import com.example.notesbyadrialrework.data.User
import com.example.notesbyadrialrework.data.UserDao
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import org.json.JSONObject
import javax.inject.Inject
import com.google.gson.Gson

@HiltViewModel
class LoginViewModel @Inject constructor( private val apiService: ApiService, private val gson: Gson, private val userDao: UserDao): BaseViewModel() {

    fun login(email: String, password: String) = viewModelScope.launch {
        _apiResponse.send(ApiResponse().responseLoading("Hang Tight..."))
        ApiObserver({ apiService.login(email, password)}, false, object : ApiObserver.ResponseListener {
            override suspend fun onSuccess(response: JSONObject) {
                val status = response.getInt(ApiCode.STATUS)
                if (status == ApiCode.SUCCESS) {
                    val data = response.getJSONObject(ApiCode.DATA).toObject<User>(gson)
                    userDao.insert(data.copy(idRoom = 1))
                    _apiResponse.send(ApiResponse().responseSuccess())
                }

                else {
                    val message = response.getString(ApiCode.MESSAGE)
                    _apiResponse.send(ApiResponse(status = ApiStatus.ERROR, message = message))
                }
            }
        })
    }
}