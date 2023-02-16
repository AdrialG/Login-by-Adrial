package com.example.notesbyadrialrework.ui.login

import androidx.lifecycle.viewModelScope
import com.crocodic.core.api.ApiCode
import com.crocodic.core.api.ApiObserver
import com.crocodic.core.api.ApiObserver.Companion.apiObserver
import com.crocodic.core.api.ApiResponse
import com.crocodic.core.api.ApiStatus
import com.crocodic.core.data.CoreSession
import com.crocodic.core.extension.toObject
import com.example.notesbyadrialrework.api.ApiService
import com.example.notesbyadrialrework.base.BaseViewModel
import com.example.notesbyadrialrework.data.BaseObserver
import com.example.notesbyadrialrework.data.Const
import com.example.notesbyadrialrework.data.User
import com.example.notesbyadrialrework.data.UserDao
import dagger.hilt.android.lifecycle.HiltViewModel
import org.json.JSONObject
import javax.inject.Inject
import com.google.gson.Gson
import kotlinx.coroutines.launch
import timber.log.Timber

@HiltViewModel
class LoginViewModel @Inject constructor(private val apiService: ApiService, private val gson: Gson, private val userDao: UserDao, private val session: CoreSession, private val observe: BaseObserver): BaseViewModel() {

    fun login(email: String, password: String) = viewModelScope.launch {
        _apiResponse.send(ApiResponse().responseLoading("Hang Tight..."))
        observe(
            block = { apiService.login(email, password) },
            toast = false,
            responseListener = object : ApiObserver.ResponseListener {
            override suspend fun onSuccess(response: JSONObject) {

                Timber.d("DataLogin : $response")

                val data = response.getJSONObject(ApiCode.DATA).toObject<User>(gson)

                val token = response.getString("token")
                session.setValue(Const.TOKEN.API_TOKEN,token)

                userDao.insert(data.copy(idRoom = 1))
                _apiResponse.send(ApiResponse().responseSuccess(
                ))
            }
                override suspend fun onError(response: ApiResponse) {
                    super.onError(response)
                    _apiResponse.send(ApiResponse().responseError())
                }

        })
    }

    fun getToken() {
        viewModelScope.launch {
         ApiObserver(
             block = {apiService.getToken()},
             toast = false,
             responseListener = object : ApiObserver.ResponseListener {
                 override suspend fun onSuccess(response: JSONObject) {

                         val token = response.getString("token")
                         session.setValue(Const.TOKEN.API_TOKEN,token)

                 }

                 override suspend fun onError(response: ApiResponse) {
                     super.onError(response)
                 }
             }
         )
        }
    }

}