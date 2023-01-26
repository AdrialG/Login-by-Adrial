package com.example.notesbyadrialrework.api

import com.example.notesbyadrialrework.data.User
import retrofit2.Call
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.POST
import retrofit2.http.Query

interface ApiService {

    @FormUrlEncoded
    @POST("user/login?expired=1")
    suspend fun login(
        @Field("email") email: String?,
        @Field("password") password: String?
    ): String

    @FormUrlEncoded
    @POST("user/get-token")
    suspend fun getToken(): String

    @FormUrlEncoded
    @POST("user")
    suspend fun register(
        @Field("name") name: String?,
        @Field("email") email: String?,
        @Field("password") password: String?
    ): String

    @FormUrlEncoded
    @POST("user/profile")
    suspend fun getUser(
        @Field("name") name: String?,
        @Field("email") email: String?,
        @Field("photo") photo: String?
    ): String
}