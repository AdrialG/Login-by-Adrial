package com.example.notesbyadrialrework.ui.home.fragment

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.crocodic.core.api.ApiCode
import com.crocodic.core.api.ApiObserver
import com.crocodic.core.api.ApiResponse
import com.crocodic.core.extension.toList
import com.example.notesbyadrialrework.api.ApiService
import com.example.notesbyadrialrework.base.BaseViewModel
import com.example.notesbyadrialrework.data.BaseObserver
import com.example.notesbyadrialrework.data.Note
import com.example.notesbyadrialrework.data.UserDao
import com.google.gson.Gson
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import org.json.JSONObject
import javax.inject.Inject

@HiltViewModel
class FragmentHomeViewModel @Inject constructor(private val apiService: ApiService, private val gson: Gson, val userDao: UserDao, private val observe: BaseObserver) : BaseViewModel() {
    val user = userDao.getUser()

    var note = MutableLiveData<List<Note>>()

    fun getNote() = viewModelScope.launch {
        _apiResponse.send(ApiResponse().responseLoading())
        observe(
            block = { apiService.getNote(search = String()) },
            toast = false,
            responseListener = object : ApiObserver.ResponseListener {
            override suspend fun onSuccess(response: JSONObject) {
                val data =
                    response.getJSONArray(ApiCode.DATA).toList<Note>(gson)
                note.postValue(data)
            }

            override suspend fun onError(response: ApiResponse) {
                super.onError(response)
            }

        })

    }

}