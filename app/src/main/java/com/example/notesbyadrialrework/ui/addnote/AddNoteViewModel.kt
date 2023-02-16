package com.example.notesbyadrialrework.ui.addnote

import androidx.lifecycle.viewModelScope
import com.crocodic.core.api.ApiObserver
import com.crocodic.core.api.ApiResponse
import com.crocodic.core.data.CoreSession
import com.example.notesbyadrialrework.api.ApiService
import com.example.notesbyadrialrework.base.BaseViewModel
import com.example.notesbyadrialrework.data.BaseObserver
import com.example.notesbyadrialrework.data.Const
import com.example.notesbyadrialrework.data.UserDao
import com.google.gson.Gson
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import org.json.JSONObject
import javax.inject.Inject

@HiltViewModel
class AddNoteViewModel @Inject constructor(private val session: CoreSession, private val apiService: ApiService, private val gson: Gson, private val  userDao: UserDao, private val observe: BaseObserver) : BaseViewModel() {

    fun createNote(title:String,content:String) = viewModelScope.launch {
        _apiResponse.send(ApiResponse().responseLoading())
        observe(
            block = {apiService.createNote(title, content)},
            toast = false,
            responseListener = object : ApiObserver.ResponseListener {
                override suspend fun onSuccess(response: JSONObject) {
                    _apiResponse.send(ApiResponse().responseSuccess("Note Created"))
                }
                override suspend fun onError(response: ApiResponse) {
                    super.onError(response)
                    _apiResponse.send(ApiResponse().responseError())
                }
            }
        )
    }

    fun updateNote(id:String, title:String, content:String) = viewModelScope.launch {
        _apiResponse.send(ApiResponse().responseLoading())
        observe(
            block = {apiService.updateNote(id,title,content)},
            toast = false,
            responseListener = object : ApiObserver.ResponseListener {
                override suspend fun onSuccess(response: JSONObject) {

                    _apiResponse.send(ApiResponse().responseSuccess(" Note Updated"))
                }

                override suspend fun onError(response: ApiResponse) {
                    super.onError(response)
                    _apiResponse.send(ApiResponse().responseError())
                }
            }
        )
    }

    fun deleteNote(id: String) = viewModelScope.launch {
        _apiResponse.send(ApiResponse().responseLoading())
        observe(
            block = {apiService.deleteNote(id)},
            toast = false,
            responseListener = object : ApiObserver.ResponseListener {
                override suspend fun onSuccess(response: JSONObject) {
                    _apiResponse.send(ApiResponse().responseSuccess("Note Deleted"))

                }
                override suspend fun onError(response: ApiResponse) {
                    super.onError(response)
                    _apiResponse.send(ApiResponse().responseError())
                }
            }
        )
    }

//    fun getToken() {
//        viewModelScope.launch {
//            observe(
//                block = { apiService.getToken() },
//                toast = false,
//                responseListener = object : ApiObserver.ResponseListener {
//                    override suspend fun onSuccess(response: JSONObject) {
//                        val token = response.getString("token")
//                        session.setValue(Const.TOKEN.API_TOKEN, token)
//
//                    }
//
//                    override suspend fun onError(response: ApiResponse) {
//                        super.onError(response)
//                    }
//                })
//
//        }
//
//    }

}
